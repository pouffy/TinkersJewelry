package dev.ferriarnus.tinkersjewelry.tools.hooks;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.Collection;

public interface CuriosTickHook {
    void curioTick(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, ItemStack stack);

    record AllMerger(Collection<CuriosTickHook> modules) implements CuriosTickHook {
        @Override
        public void curioTick(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, ItemStack stack) {
            for (CuriosTickHook hook : modules) {
                hook.curioTick(tool, entry, slotId, slotIndex, wearer, stack);
            }
        }
    }
}
