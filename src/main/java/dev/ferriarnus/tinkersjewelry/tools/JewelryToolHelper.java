package dev.ferriarnus.tinkersjewelry.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import dev.ferriarnus.tinkersjewelry.items.JewelryItemTags;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotAttribute;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class JewelryToolHelper {

    public static boolean isTool(ItemStack stack) {
        return stack.getItem() instanceof IModifiable modifiable && modifiable.getToolDefinition() != ToolDefinition.EMPTY;
    }

    public static void asTool(ItemStack stack, Consumer<ToolStack> consumer) {
        if (isTool(stack)) {
            consumer.accept(ToolStack.from(stack));
        }
    }

    public static boolean isCurio(ItemStack stack) {
        return stack.getItem() instanceof ICurioItem;
    }

    public static ArrayList<ItemStack> getModifiableStacks(LivingEntity wearer) {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        Optional.ofNullable(getModifiableHeld(wearer, false)).ifPresent(itemStacks::add);
        Optional.ofNullable(getModifiableHeld(wearer, true)).ifPresent(itemStacks::add);
        itemStacks.addAll(getModifiableArmour(wearer));
        itemStacks.addAll(getModifiableCurios(wearer).stream().map(Pair::getFirst).toList());
        return itemStacks;
    }

    public static ArrayList<Pair<ItemStack, SlotContext>> getModifiableCurios(LivingEntity wearer) {
        return new ArrayList<>(getCurioItems(wearer).stream().filter((p) -> isTool(p.getFirst())).toList());
    }

    public static ArrayList<Pair<ItemStack, SlotContext>> getCurioItems(LivingEntity wearer) {
        ArrayList<Pair<ItemStack, SlotContext>> itemStacks = new ArrayList<>();
        CuriosApi.getCuriosInventory(wearer).ifPresent((handler) -> {
            Map<String, ICurioStacksHandler> curios = handler.getCurios();
            for(Map.Entry<String, ICurioStacksHandler> entry : curios.entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for(int i = 0; i < stacksHandler.getSlots(); ++i) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        itemStacks.add(Pair.of(stack, new SlotContext(entry.getKey(), wearer, i, false, stacksHandler.getRenders().size() > i && stacksHandler.getRenders().get(i))));
                    }
                }
            }
        });
        return itemStacks;
    }

    public static ArrayList<ItemStack> getModifiableArmour(LivingEntity wearer) {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        wearer.getArmorSlots().forEach(stack -> {
            if (isTool(stack)) itemStacks.add(stack);
        });
        return itemStacks;
    }

    /**
     * Method for retrieving held tools whilst excluding armour and curios
     * @param wearer the entity wearing the equipment
     * @param offhand whether to target the offhand item
     * @return the held stack if it meets the conditions.
     */
    public static ItemStack getModifiableHeld(LivingEntity wearer, boolean offhand) {
        ItemStack held = offhand ? wearer.getOffhandItem() : wearer.getMainHandItem();
        if (!isTool(held) || (held.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() != (offhand ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND))) {
            return null;
        }
        if (isCurio(held) && !held.is(JewelryItemTags.HANDHELD_CURIO)) return null;
        return held;
    }

    public static float getEffectiveLevel(LivingEntity wearer, ModifierId modifier) {
        return getEffectiveLevel(wearer, modifier, (stack) -> true);
    }

    public static float getEffectiveLevel(LivingEntity wearer, ModifierId modifier, Predicate<ItemStack> stackPredicate) {
        float level = 0;
        for (var stack : getModifiableStacks(wearer).stream().filter(stackPredicate).toList()) {
            var tool = ToolStack.from(stack);
            if (tool.isBroken()) continue;
            level += tool.getModifiers().getEntry(modifier).getEffectiveLevel();
        }
        return level;
    }

    public static boolean hasModifier(ToolStack tool, ModifierId modifier) {
        return tool.getModifiers().getEntry(modifier) != ModifierEntry.EMPTY;
    }

    public static boolean hasModifier(LivingEntity wearer, ModifierId modifier) {
        return hasModifier(wearer, modifier, (stack) -> true);
    }

    public static boolean hasModifier(LivingEntity wearer, ModifierId modifier, Predicate<ItemStack> stackPredicate) {
        boolean equipped = false;
        for (var stack : getModifiableStacks(wearer).stream().filter(stackPredicate).toList()) {
            var tool = ToolStack.from(stack);
            if (tool.isBroken()) continue;
            if (tool.getModifiers().getEntry(modifier) != ModifierEntry.EMPTY) {
                equipped = true;
                break;
            }
        }
        return equipped;
    }

    public static LivingEntity getAttacker(LivingDeathEvent event) {
        LivingEntity attacker = null;
        if (event.getSource().getEntity() instanceof LivingEntity directAttacker) {
            attacker = directAttacker;
        }
        if (attacker == null) {
            attacker = event.getEntity().getLastHurtByMob();
        }
        return attacker;
    }

    public static Pair<Multimap<Attribute, AttributeModifier>, Multimap<String, AttributeModifier>> getToolAttributes(ItemStack stack, SlotContext slotContext, UUID uuid) {
        Multimap<Attribute, AttributeModifier> map = HashMultimap.create();
        if (!isTool(stack)) return Pair.of(HashMultimap.create(), HashMultimap.create());
        ToolStack tool = ToolStack.from(stack);
        for (ModifierEntry modifierEntry: tool.getModifierList()) {
            map.putAll(modifierEntry.getHook(CuriosModifierHooks.ATTRIBUTE).getAttributeModifiers(tool, modifierEntry, slotContext.identifier(), slotContext.index(), slotContext.entity(), uuid, stack));
        }
        Multimap<String, AttributeModifier> slots = HashMultimap.create();
        Set<SlotAttribute> toRemove = new HashSet<>();
        for(Attribute attribute : map.keySet()) {
            if (attribute instanceof SlotAttribute wrapper) {
                slots.putAll(wrapper.getIdentifier(), map.get(attribute));
                toRemove.add(wrapper);
            }
        }
        for(Attribute attribute : toRemove) {
            map.removeAll(attribute);
        }
        return Pair.of(map, slots);
    }
}
