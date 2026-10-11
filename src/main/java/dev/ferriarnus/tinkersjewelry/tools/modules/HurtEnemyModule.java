package dev.ferriarnus.tinkersjewelry.tools.modules;

import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import dev.ferriarnus.tinkersjewelry.tools.hooks.HurtEnemyHook;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.data.loadable.Loadables;
import slimeknights.mantle.data.loadable.primitive.StringLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.registry.GenericLoaderRegistry;
import slimeknights.tconstruct.library.json.TinkerLoadables;
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

import javax.annotation.Nullable;
import java.util.List;

public record HurtEnemyModule(ToolFormula formula) implements ModifierModule, HurtEnemyHook {

    private static final String[] VARIABLES = { "level" };
    private static final RecordLoadable<ToolFormula> VARIABLE_LOADER = new VariableFormulaLoadable<>(ToolVariable.LOADER, VARIABLES, ModifierFormula.FallbackFormula.IDENTITY, (formula, variables, percent) -> new ToolFormula(formula, variables, VariableFormula.EMPTY_STRINGS));
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = List.of(CuriosModifierHooks.HURT_ENEMY_HOOK);
    public static final RecordLoadable<HurtEnemyModule> LOADER = RecordLoadable.create(
            VARIABLE_LOADER.directField(HurtEnemyModule::formula), HurtEnemyModule::new);

    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public void hurtEnemy(ItemStack stack, DamageSource source, double damage, @Nullable LivingEntity defender, @Nullable LivingEntity attacker, ModifierEntry modifier) {
        ToolStack tool = ToolStack.from(stack);
        ToolDamageUtil.damage(tool, Math.round(formula.apply(tool, modifier)), attacker, stack);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends VariableFormula.Builder<Builder, HurtEnemyModule, ToolVariable> {

        protected Builder() {
            super(VARIABLES);
        }

        @Override
        protected HurtEnemyModule build(ModifierFormula formula) {
            return new HurtEnemyModule(new ToolFormula(formula, variables));
        }
    }
}
