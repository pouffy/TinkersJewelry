package dev.ferriarnus.tinkersjewelry.tools.modules;

import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import dev.ferriarnus.tinkersjewelry.tools.hooks.HurtEnemyHook;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.data.loadable.Loadables;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.math.ModifierFormula;
import slimeknights.tconstruct.library.json.math.PostFixFormula;
import slimeknights.tconstruct.library.json.variable.VariableFormula;
import slimeknights.tconstruct.library.json.variable.VariableFormulaLoadable;
import slimeknights.tconstruct.library.json.variable.tool.ToolFormula;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;

public record EnemyEffectModule(MobEffect effect, ToolFormula duration, ToolFormula amplification) implements ModifierModule, HurtEnemyHook {

    private static final String[] VARIABLES = { "level" };
    private static final RecordLoadable<ToolFormula> VARIABLE_LOADER = new VariableFormulaLoadable<>(ToolVariable.LOADER, VARIABLES, ModifierFormula.FallbackFormula.IDENTITY, (formula, variables, percent) -> new ToolFormula(formula, variables, VariableFormula.EMPTY_STRINGS));
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = List.of(CuriosModifierHooks.HURT_ENEMY_HOOK);
    public static final RecordLoadable<EnemyEffectModule> LOADER = RecordLoadable.create(
            Loadables.MOB_EFFECT.requiredField("effect", EnemyEffectModule::effect),
            VARIABLE_LOADER.requiredField("duration", EnemyEffectModule::duration),
            VARIABLE_LOADER.requiredField("amplification", EnemyEffectModule::amplification), EnemyEffectModule::new);

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
        if (defender == null || defender.hasEffect(effect)) {
            return;
        }
        ToolStack tool = ToolStack.from(stack);
        defender.addEffect(new MobEffectInstance(effect, Mth.floor(duration.apply(tool, modifier)), Mth.floor(amplification.apply(tool, modifier))));
    }

    public static Builder builder(MobEffect effect) {
        return new Builder(effect);
    }

    public static class Builder extends VariableFormula.Builder<Builder, EnemyEffectModule, ToolVariable> {

        private final MobEffect effect;
        private ModifierFormula amplificationFormula = PostFixFormula.builder(VARIABLES).constant(0).buildFormula();

        protected Builder(MobEffect effect) {
            super(VARIABLES);
            this.effect = effect;
        }

        public Builder withAmplification(Function<PostFixFormula.Builder<?>, ModifierFormula> amplification) {
            this.amplificationFormula = amplification.apply(PostFixFormula.builder(VARIABLES));
            return this;
        }

        @Override
        protected EnemyEffectModule build(ModifierFormula formula) {
            return new EnemyEffectModule(effect, new ToolFormula(formula, variables), new ToolFormula(amplificationFormula, variables));
        }
    }
}
