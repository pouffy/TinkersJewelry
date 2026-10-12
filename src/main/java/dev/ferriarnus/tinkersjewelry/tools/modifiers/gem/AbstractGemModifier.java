package dev.ferriarnus.tinkersjewelry.tools.modifiers.gem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.ferriarnus.tinkersjewelry.tools.hooks.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.UUID;

public abstract class AbstractGemModifier extends Modifier implements TooltipModifierHook, CuriosEquipHook, CuriosTickHook, AttributeModifierHook, HurtUserHook, HurtEnemyHook, BlockBreakHook {

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, UUID uuid, ItemStack stack) {
		return HashMultimap.create();
	}

	@Override
	public void curioTick(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, ItemStack stack) {

	}

	@Override
	public void hurtUser(ItemStack stack, DamageSource source, double damage, @Nullable LivingEntity defender, @Nullable Entity attacker, ModifierEntry modifier) {

	}

	@Override
	public void hurtEnemy(ItemStack stack, DamageSource source, double damage, @Nullable LivingEntity defender, @Nullable LivingEntity attacker, ModifierEntry modifier) {

	}

	@Override
	public void breakBlock(Player player, ItemStack stackInSlot, BlockPos pos, BlockState state, ModifierEntry modifier) {

	}

	public void damageTool(ItemStack stack, int amount, @Nullable LivingEntity entity) {
		ToolDamageUtil.damage(ToolStack.from(stack), amount, entity, stack);
	}

	public MutableComponent addDiscription(String key, String effect) {
		return applyStyle(Component.literal(" +" + effect +" ").append(Component.translatable(key)));
	}

	public MutableComponent addDiscription(String key, Number effect) {
		return addDiscription(key, String.valueOf(effect));
	}

	@Override
	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		super.registerHooks(hookBuilder);
		hookBuilder.addHook(this, CuriosModifierHooks.EQUIP);
		hookBuilder.addHook(this, CuriosModifierHooks.TICK);
		hookBuilder.addHook(this, CuriosModifierHooks.ATTRIBUTE);
		hookBuilder.addHook(this, CuriosModifierHooks.HURT_USER_HOOK);
		hookBuilder.addHook(this, CuriosModifierHooks.HURT_ENEMY_HOOK);
		hookBuilder.addHook(this, CuriosModifierHooks.BLOCK_BREAK);

		hookBuilder.addHook(this, ModifierHooks.TOOLTIP);
	}
}
