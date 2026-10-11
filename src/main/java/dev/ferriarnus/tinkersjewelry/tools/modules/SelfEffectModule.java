package dev.ferriarnus.tinkersjewelry.tools.modules;

import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosTickHook;
import net.minecraft.util.Mth;
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
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.function.Function;

public record SelfEffectModule(MobEffect effect, ToolFormula duration, ToolFormula amplification, ToolFormula damage) implements ModifierModule, CuriosTickHook {

    private static final String[] VARIABLES = { "level" };
    private static final RecordLoadable<ToolFormula> VARIABLE_LOADER = new VariableFormulaLoadable<>(ToolVariable.LOADER, VARIABLES, ModifierFormula.FallbackFormula.IDENTITY, (formula, variables, percent) -> new ToolFormula(formula, variables, VariableFormula.EMPTY_STRINGS));
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = List.of(CuriosModifierHooks.TICK);
    public static final RecordLoadable<SelfEffectModule> LOADER = RecordLoadable.create(
            Loadables.MOB_EFFECT.requiredField("effect", SelfEffectModule::effect),
            VARIABLE_LOADER.requiredField("duration", SelfEffectModule::duration),
            VARIABLE_LOADER.requiredField("amplification", SelfEffectModule::amplification),
            VARIABLE_LOADER.requiredField("damage", SelfEffectModule::damage), SelfEffectModule::new);

    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack, ModifierEntry entry) {
        LivingEntity entity = slotContext.entity();
        ToolStack tool = ToolStack.from(stack);
        int i = Mth.floor(duration.apply(tool, entry));
        if (!entity.hasEffect(effect) || entity.getEffect(effect).getDuration() < i / 2) {
            entity.addEffect(new MobEffectInstance(effect, i, Mth.floor(amplification.apply(tool, entry)), false, false));
            ToolDamageUtil.damage(tool, Math.round(damage.apply(tool, entry)), entity, stack);
        }
    }

    public static Builder builder(MobEffect effect) {
        return new Builder(effect);
    }

    public static class Builder extends VariableFormula.Builder<Builder, SelfEffectModule, ToolVariable> {

        private final MobEffect effect;
        private ModifierFormula amplificationFormula = PostFixFormula.builder(VARIABLES).constant(0).buildFormula();
        private ModifierFormula damageFormula = PostFixFormula.builder(VARIABLES).constant(1).buildFormula();

        protected Builder(MobEffect effect) {
            super(VARIABLES);
            this.effect = effect;
        }

        public Builder withAmplification(Function<PostFixFormula.Builder<?>, ModifierFormula> amplification) {
            this.amplificationFormula = amplification.apply(PostFixFormula.builder(VARIABLES));
            return this;
        }

        public Builder withDamage(Function<PostFixFormula.Builder<?>, ModifierFormula> damage) {
            this.damageFormula = damage.apply(PostFixFormula.builder(VARIABLES));
            return this;
        }

        @Override
        protected SelfEffectModule build(ModifierFormula formula) {
            return new SelfEffectModule(effect, new ToolFormula(formula, variables), new ToolFormula(amplificationFormula, variables), new ToolFormula(damageFormula, variables));
        }
    }
}
