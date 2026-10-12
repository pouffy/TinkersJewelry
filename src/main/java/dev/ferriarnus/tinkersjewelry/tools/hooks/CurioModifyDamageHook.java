package dev.ferriarnus.tinkersjewelry.tools.hooks;

import net.minecraft.core.NonNullList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Collection;
import java.util.Map;

import static dev.ferriarnus.tinkersjewelry.tools.JewelryToolHelper.*;

public interface CurioModifyDamageHook {

    float modifyDamageTaken(IToolStackView tool, ItemStack stack, ModifierEntry modifier, EquipmentContext context, String slotId, int slotIndex, DamageSource source, float amount, boolean isDirectDamage);

    static float modifyDamageTaken(ModuleHook<CurioModifyDamageHook> hook, EquipmentContext context, DamageSource source, float amount, boolean isDirectDamage) {
        if (amount <= 0.0F) {
            return 0.0F;
        } else {
            var curios = CuriosApi.getCuriosInventory(context.getEntity());
            if (!curios.isPresent()) {
                return 0.0F;
            }
            ICuriosItemHandler curiosItemHandler = curios.resolve().get();
            for(Map.Entry<String, ICurioStacksHandler> entry : curiosItemHandler.getCurios().entrySet()) {
                ICurioStacksHandler stacksHandler = entry.getValue();
                String identifier = entry.getKey();
                IDynamicStackHandler stackHandler = stacksHandler.getStacks();
                for (int i = 0; i < stacksHandler.getSlots(); ++i) {
                    ItemStack stack = stackHandler.getStackInSlot(i);
                    if (!stack.isEmpty() && isTool(stack)) {
                        ToolStack tool = ToolStack.from(stack);
                        if (!tool.isBroken()) {
                            for (ModifierEntry modifierEntry: tool.getModifierList()) {
                                amount = modifierEntry.getHook(hook).modifyDamageTaken(tool, stack, modifierEntry, context, identifier, i, source, amount, isDirectDamage);
                                if (amount <= 0.0F) {
                                    return 0.0F;
                                }
                            }
                        }
                    }
                }
            }
            return amount;
        }
    }

    public static record AllMerger(Collection<CurioModifyDamageHook> modules) implements CurioModifyDamageHook {
        public float modifyDamageTaken(IToolStackView tool, ItemStack stack, ModifierEntry modifier, EquipmentContext context, String slotId, int slotIndex, DamageSource source, float amount, boolean isDirectDamage) {
            for(CurioModifyDamageHook module : this.modules) {
                amount = module.modifyDamageTaken(tool, stack, modifier, context, slotId, slotIndex, source, amount, isDirectDamage);
                if (amount <= 0.0F) {
                    break;
                }
            }

            return amount;
        }
    }
}
