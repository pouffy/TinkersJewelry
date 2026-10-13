package dev.ferriarnus.tinkersjewelry;

import com.google.common.collect.Multimap;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CurioModifyDamageHook;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import dev.ferriarnus.tinkersjewelry.tools.modifiers.JewelryModifiers;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandlerModifiable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;

import static dev.ferriarnus.tinkersjewelry.tools.JewelryToolHelper.*;
import static slimeknights.tconstruct.tools.logic.ModifierEvents.SOULBOUND;

@Mod.EventBusSubscriber(modid = TinkersJewelry.MODID)
public class CommonEvents {

    // Block break based damage (mining speed, mining level ...)
    @SubscribeEvent
    static void onBlockBreak(BlockEvent.BreakEvent event) {
        getModifiableCurios(event.getPlayer()).forEach(p -> asTool(p.getFirst(), (tool) -> {
            if (!tool.isBroken()) {
                for (ModifierEntry entry : tool.getModifierList()) {
                    entry.getHook(CuriosModifierHooks.BLOCK_BREAK).breakBlock(event.getPlayer(), p.getFirst(), event.getPos(), event.getState(), entry);
                }
            }
        }));
    }

    // Entity damage based damage
    @SubscribeEvent
    static void onAttackEntity(LivingAttackEvent event) {
        //hurt player (or entity) (thorns, flames, teleport ...)
        getModifiableCurios(event.getEntity()).forEach(p -> asTool(p.getFirst(), (tool) -> {
            if (!tool.isBroken()) {
                for (ModifierEntry entry : tool.getModifierList()) {
                    entry.getHook(CuriosModifierHooks.HURT_USER_HOOK).hurtUser(p.getFirst(), event.getSource(), event.getAmount(), event.getEntity(), event.getSource().getEntity(), entry);
                }
            }
        }));

        if (event.getSource().getEntity() instanceof LivingEntity entity) { //hurt by player (or entity) (attack speed, range, ...)
            getModifiableCurios(entity).forEach(p -> asTool(p.getFirst(), (tool) -> {
                if (!tool.isBroken()) {
                    for (ModifierEntry entry : tool.getModifierList()) {
                        entry.getHook(CuriosModifierHooks.HURT_ENEMY_HOOK).hurtEnemy(p.getFirst(), event.getSource(), event.getAmount(), event.getEntity(), entity, entry);
                    }
                }
            }));
        }
    }

    @SubscribeEvent
    static void livingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        DamageSource source = event.getSource();
        float amount = event.getAmount();
        EquipmentContext context = new EquipmentContext(entity);
        amount = CurioModifyDamageHook.modifyDamageTaken(CuriosModifierHooks.MODIFY_HURT, context, source, amount, OnAttackedModifierHook.isDirectDamage(source));
        event.setAmount(amount);
        if (amount <= 0) {
            event.setCanceled(true);
        }
    }

    private static final String SOULBOUND_SLOT = "curios_soulbound";

    @SubscribeEvent
    static void onLivingDeath(LivingDeathEvent event) {
        // this is the latest we can add slot markers to the items so we can return them to slots
        LivingEntity entity = event.getEntity();
        if (!entity.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) && entity instanceof Player player && !(player instanceof FakePlayer)) {
            // Curios soul bound check
            getCurioItems(entity).forEach(stack -> {
                if (ModifierUtil.checkVolatileFlag(stack.getFirst(), SOULBOUND)) {
                    stack.getFirst().getOrCreateTag().putInt(SOULBOUND_SLOT, stack.getSecond().index());
                }
            });
        }
    }

    @SubscribeEvent
    static void onPlayerDropItems(LivingDropsEvent event) {
        // only care about real players with keep inventory off
        LivingEntity entity = event.getEntity();
        var curios = CuriosApi.getCuriosInventory(event.getEntity());
        if (!curios.isPresent()) {
            return;
        }
        if (!entity.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) && entity instanceof Player player && !(entity instanceof FakePlayer)) {
            IItemHandlerModifiable curiosItemHandler = curios.resolve().get().getEquippedCurios();
            Collection<ItemEntity> drops = event.getDrops();
            Iterator<ItemEntity> iter = drops.iterator();
            Inventory inventory = player.getInventory();
            List<ItemEntity> takenSlot = new ArrayList<>();
            while (iter.hasNext()) {
                ItemEntity itemEntity = iter.next();
                ItemStack stack = itemEntity.getItem();
                // find items with our soulbound tag set and move them back into the inventory, will move them over later
                CompoundTag tag = stack.getTag();
                if (tag != null && tag.contains(SOULBOUND_SLOT, Tag.TAG_ANY_NUMERIC)) {
                    int slot = tag.getInt(SOULBOUND_SLOT);
                    // return the tool to its requested slot if possible, remove from the drops
                    if (curiosItemHandler.getStackInSlot(slot).isEmpty()) {
                        curiosItemHandler.setStackInSlot(slot, stack);
                        tag.remove(SOULBOUND_SLOT);
                    } else {
                        // hold off on handling items that did not get the requested slot for now
                        // want to make sure they don't get in the way of items that have not yet been seen
                        takenSlot.add(itemEntity);
                    }
                    iter.remove();
                    // don't clear the tag yet, we need it one last time for player clone
                }
            }
            // handle items that did not get their requested slot last, to ensure they don't take someone else's slot while being added to a default
            for (ItemEntity itemEntity : takenSlot) {
                ItemStack stack = itemEntity.getItem();
                if (!inventory.add(stack)) {
                    // last resort, somehow we just cannot put the stack anywhere, so drop it on the ground
                    // this should never happen, but better to be safe
                    // ditch the soulbound slot tag, to prevent item stacking issues
                    CompoundTag tag = stack.getTag();
                    if (tag != null) {
                        tag.remove(SOULBOUND_SLOT);
                        if (tag.isEmpty()) {
                            stack.setTag(null);
                        }
                    }
                    drops.add(itemEntity);
                }
            }
        }
    }

    @SubscribeEvent
    static void undying(LivingDeathEvent event) {
        if (event.isCanceled()) {
            return;
        }
        LivingEntity entity = event.getEntity();
        if (entity.isDeadOrDying()) {
            getModifiableCurios(event.getEntity()).forEach(p -> asTool(p.getFirst(), (tool) -> {
                if (!tool.isBroken() && hasModifier(tool, JewelryModifiers.UNDYING.getId())) {
                    entity.setHealth(1.0F);
                    entity.removeAllEffects();
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                    entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                    entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
                    ToolDamageUtil.damage(tool, 300, entity, p.getFirst());
                    event.setCanceled(true);
                }
            }));
        }
    }

    //Ticks the hook on any modifiable curio
    @SubscribeEvent
    static void livingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        getModifiableCurios(livingEntity).forEach(p -> asTool(p.getFirst(), (tool) -> {
            SlotContext slotContext = p.getSecond();
            if (!tool.isBroken()) {
                for (ModifierEntry modifierEntry: tool.getModifierList()) {
                    modifierEntry.getHook(CuriosModifierHooks.TICK).curioTick(tool, modifierEntry, slotContext.identifier(), slotContext.index(), livingEntity, p.getFirst());
                }
            }
        }));
        CuriosApi.getCuriosInventory(livingEntity).ifPresent((handler) -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for(Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                String identifier = entry.getKey();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for(int i = 0; i < stacksHandler.getSlots(); ++i) {
                    NonNullList<Boolean> renderStates = stacksHandler.getRenders();
                    SlotContext slotContext = new SlotContext(identifier, livingEntity, i, false, renderStates.size() > i && renderStates.get(i));
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!livingEntity.level().isClientSide) {
                        ItemStack prevStack = stackHandler.getPreviousStackInSlot(i);
                        if (!ItemStack.matches(stack, prevStack)) {
                            UUID uuid = CuriosApi.getSlotUuid(slotContext);
                            if (!prevStack.isEmpty()) {
                                Multimap<Attribute, AttributeModifier> map = getToolAttributes(prevStack, slotContext, uuid).getFirst();
                                livingEntity.getAttributes().removeAttributeModifiers(map);
                                handler.removeSlotModifiers(getToolAttributes(prevStack, slotContext, uuid).getSecond());
                            }
                            if (!stack.isEmpty()) {
                                Multimap<Attribute, AttributeModifier> map = getToolAttributes(stack, slotContext, uuid).getFirst();
                                livingEntity.getAttributes().addTransientAttributeModifiers(map);
                                handler.addTransientSlotModifiers(getToolAttributes(prevStack, slotContext, uuid).getSecond());
                            }
                        }
                    }
                }
            }
        });
    }


}
