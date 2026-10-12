package dev.ferriarnus.tinkersjewelry.tools.hooks;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Collection;
import java.util.UUID;

public interface AttributeModifierHook {
    Multimap<Attribute, AttributeModifier> getAttributeModifiers(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, UUID uuid, ItemStack stack);

    record AllMerger(Collection<AttributeModifierHook> modules) implements AttributeModifierHook {
        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, UUID uuid, ItemStack stack) {
            Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
            for (var module : modules) {
                multimap.putAll(module.getAttributeModifiers(tool, entry, slotId, slotIndex, wearer, uuid, stack));
            }
            return multimap;
        }
    }
}
