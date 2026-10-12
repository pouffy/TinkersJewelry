package dev.ferriarnus.tinkersjewelry.tools.hooks;

import com.google.common.collect.HashMultimap;
import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.module.ModuleHook;

public class CuriosModifierHooks {

    public static final ModuleHook<AttributeModifierHook> ATTRIBUTE = ModifierHooks.register(TinkersJewelry.getResource("attribute"), AttributeModifierHook.class, AttributeModifierHook.AllMerger::new, (tool, entry, slot, index, wearer, uuid, stack) -> HashMultimap.create());
    public static final ModuleHook<CuriosEquipHook> EQUIP = ModifierHooks.register(TinkersJewelry.getResource("equip"), CuriosEquipHook.class, CuriosEquipHook.AllMerger::new, new CuriosEquipHook() {});
    public static final ModuleHook<CuriosTickHook> TICK = ModifierHooks.register(TinkersJewelry.getResource("tick"), CuriosTickHook.class, CuriosTickHook.AllMerger::new, (tool, entry, slot, index, wearer, stack) -> {});
    public static final ModuleHook<HurtEnemyHook> HURT_ENEMY_HOOK = ModifierHooks.register(TinkersJewelry.getResource("hurt_enemy"), HurtEnemyHook.class, HurtEnemyHook.AllMerger::new, (stack, source, damage, defender, attacker, modifier) -> {});
    public static final ModuleHook<HurtUserHook> HURT_USER_HOOK = ModifierHooks.register(TinkersJewelry.getResource("hurt_user"), HurtUserHook.class, HurtUserHook.AllMerger::new, (stack, source, damage, defender, attacker, modifier) -> {});
    public static final ModuleHook<BlockBreakHook> BLOCK_BREAK = ModifierHooks.register(TinkersJewelry.getResource("block_break"), BlockBreakHook.class, BlockBreakHook.AllMerger::new, (player, stackInSlot, pos, state, modifier) -> {});

    public static final ModuleHook<CurioModifyDamageHook> MODIFY_HURT = ModifierHooks.register(TinkersJewelry.getResource("modify_hurt"), CurioModifyDamageHook.class, CurioModifyDamageHook.AllMerger::new, (tool, stack, modifier, context, slotId, slotIndex, source, amount, isDirectDamage) -> 0);

    public static void init() {

    }
}
