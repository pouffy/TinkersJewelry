package dev.ferriarnus.tinkersjewelry.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.TooltipBuilder;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CuriosRingItem extends ModifiableItem implements ICurioItem{

	public CuriosRingItem(Properties properties, ToolDefinition toolDefinition) {
		super(properties, toolDefinition);
	}

	@Override
	public List<Component> getStatInformation(IToolStackView tool, @Nullable Player player, List<Component> tooltips, TooltipKey key, TooltipFlag tooltipFlag) {
		List<Component> list = super.getStatInformation(tool, player, tooltips, key, tooltipFlag);
		TooltipBuilder builder = new TooltipBuilder(tool, list);
		builder.add(JewelryToolStats.AMPLIFICATION);
		return builder.getTooltips();
	}

	@Override
	public void curioTick(SlotContext slotContext, ItemStack stack) {
		ToolStack tool = ToolStack.from(stack);
		if (tool.isBroken()) {
			return;
		}
		for (ModifierEntry entry: tool.getModifierList()) {
			entry.getHook(CuriosModifierHooks.TICK).curioTick(slotContext, stack, entry);
		}
	}
	
	@Override
	public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
		ToolStack tool = ToolStack.from(stack);
		if (tool.isBroken()) {
			return;
		}
		for (ModifierEntry entry: tool.getModifierList()) {
			entry.getHook(CuriosModifierHooks.EQUIP).onEquip(slotContext, prevStack, stack);
		}
	}

	@Override
	public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
		ToolStack tool = ToolStack.from(stack);
		for (ModifierEntry entry: tool.getModifierList()) {
			entry.getHook(CuriosModifierHooks.EQUIP).onUnequip(slotContext, newStack, stack);
		}
	}

	@Override
	public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
		ToolStack tool = ToolStack.from(stack);
		if (tool.isBroken()) {
			return true;
		}
		for (ModifierEntry entry: tool.getModifierList()) {
			if (!entry.getHook(CuriosModifierHooks.EQUIP).canUnequip(slotContext, stack)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean canEquip(SlotContext slotContext, ItemStack stack) {
		ToolStack tool = ToolStack.from(stack);
		if (tool.isBroken()) {
			return false;
		}
		for (ModifierEntry entry: tool.getModifierList()) {
			if (!entry.getHook(CuriosModifierHooks.EQUIP).canEquip(slotContext, stack)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
		ToolStack tool = ToolStack.from(stack);
		if (tool.isBroken()) {
			return false;
		}
		for (ModifierEntry entry: tool.getModifierList()) {
			if (!entry.getHook(CuriosModifierHooks.EQUIP).canEquipFromUse(slotContext, stack)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
		ToolStack tool = ToolStack.from(stack);
		Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
		if (tool.isBroken()) {
			return multimap;
		}
		if (tool.isBroken()) {
			return multimap;
		}
		for (ModifierEntry entry : tool.getModifierList()) {
			multimap.putAll(entry.getHook(CuriosModifierHooks.ATTRIBUTE).getAttributeModifiers(slotContext, uuid, stack, entry));
		}
		return multimap;
	}
	
	@Override
	public List<Component> getSlotsTooltip(List<Component> tooltips, ItemStack stack) {
		return Collections.emptyList();
	}
}
