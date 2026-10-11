package dev.ferriarnus.tinkersjewelry.tools.modules;

import dev.ferriarnus.tinkersjewelry.tools.hooks.BlockBreakHook;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.registry.GenericLoaderRegistry;
import slimeknights.tconstruct.library.json.math.ModifierFormula;
import slimeknights.tconstruct.library.json.variable.VariableFormula;
import slimeknights.tconstruct.library.json.variable.VariableFormulaLoadable;
import slimeknights.tconstruct.library.json.variable.tool.ToolFormula;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

public record BlockBreakModule(ToolFormula formula) implements ModifierModule, BlockBreakHook {

    private static final String[] VARIABLES = { "level" };
    private static final RecordLoadable<ToolFormula> VARIABLE_LOADER = new VariableFormulaLoadable<>(ToolVariable.LOADER, VARIABLES, ModifierFormula.FallbackFormula.IDENTITY, (formula, variables, percent) -> new ToolFormula(formula, variables, VariableFormula.EMPTY_STRINGS));
    private static final List<ModuleHook<?>> DEFAULT_MODULES = List.of(CuriosModifierHooks.BLOCK_BREAK);
    public static final RecordLoadable<BlockBreakModule> LOADER = RecordLoadable.create(
            VARIABLE_LOADER.directField(BlockBreakModule::formula), BlockBreakModule::new);

    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_MODULES;
    }

    @Override
    public void breakBlock(Player player, ItemStack stackInSlot, BlockPos pos, BlockState state, ModifierEntry modifier) {
        ToolStack tool = ToolStack.from(stackInSlot);
        ToolDamageUtil.damage(tool, Math.round(formula.apply(tool, modifier)), player, stackInSlot);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends VariableFormula.Builder<Builder, BlockBreakModule, ToolVariable> {

        protected Builder() {
            super(VARIABLES);
        }

        @Override
        protected BlockBreakModule build(ModifierFormula formula) {
            return new BlockBreakModule(new ToolFormula(formula, variables));
        }
    }
}
