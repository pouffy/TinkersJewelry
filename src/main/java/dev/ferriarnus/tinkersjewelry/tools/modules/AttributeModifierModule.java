package dev.ferriarnus.tinkersjewelry.tools.modules;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.ferriarnus.tinkersjewelry.tools.hooks.AttributeModifierHook;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.Loadables;
import slimeknights.mantle.data.loadable.primitive.StringLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.TinkerLoadables;
import slimeknights.tconstruct.library.json.math.ModifierFormula;
import slimeknights.tconstruct.library.json.variable.VariableFormula;
import slimeknights.tconstruct.library.json.variable.VariableFormulaLoadable;
import slimeknights.tconstruct.library.json.variable.tool.ToolFormula;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.behavior.AttributeModule;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.helper.TooltipUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;
import java.util.UUID;

public record AttributeModifierModule(String unique, Attribute attribute, AttributeModifier.Operation operation, ToolFormula formula, AttributeModule.TooltipStyle tooltipStyle) implements ModifierModule, AttributeModifierHook, TooltipModifierHook {

    private static final String[] VARIABLES = { "level" };
    private static final RecordLoadable<ToolFormula> VARIABLE_LOADER = new VariableFormulaLoadable<>(ToolVariable.LOADER, VARIABLES, ModifierFormula.FallbackFormula.IDENTITY, (formula, variables, percent) -> new ToolFormula(formula, variables, VariableFormula.EMPTY_STRINGS));
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = List.of(CuriosModifierHooks.ATTRIBUTE);
    private static final List<ModuleHook<?>> TOOLTIP_HOOKS = List.of(CuriosModifierHooks.ATTRIBUTE, ModifierHooks.TOOLTIP);

    public static final RecordLoadable<AttributeModifierModule> LOADER = RecordLoadable.create(
            StringLoadable.DEFAULT.requiredField("unique", AttributeModifierModule::unique),
            Loadables.ATTRIBUTE.requiredField("attribute", AttributeModifierModule::attribute),
            TinkerLoadables.OPERATION.requiredField("operation", AttributeModifierModule::operation),
            VARIABLE_LOADER.directField(AttributeModifierModule::formula),
            AttributeModule.TooltipStyle.LOADABLE.defaultField("tooltip_style", AttributeModule.TooltipStyle.ATTRIBUTE, AttributeModifierModule::tooltipStyle),
            AttributeModifierModule::new);


    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        if (this.tooltipStyle == AttributeModule.TooltipStyle.ATTRIBUTE) {
            return DEFAULT_HOOKS;
        } else {
            return this.tooltipStyle == AttributeModule.TooltipStyle.NONE ? DEFAULT_HOOKS : TOOLTIP_HOOKS;
        }
    }

    /** Creates a new builder instance */
    public static Builder builder(Attribute attribute, AttributeModifier.Operation operation) {
        return new Builder(attribute, operation);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        ToolStack toolStack = ToolStack.from(stack);
        attributeModifiers.put(attribute, new AttributeModifier(uuid, unique, formula.apply(toolStack, entry), operation));
        return attributeModifiers;
    }

    public static void addTooltip(Modifier modifier, Attribute attribute, AttributeModifier.Operation operation, AttributeModule.TooltipStyle tooltipStyle, float amount, @Nullable UUID uuid, @Nullable Player player, List<Component> tooltip) {
        switch (tooltipStyle) {
            case ATTRIBUTE -> TooltipUtil.addAttribute(attribute, operation, amount, uuid, player, tooltip);
            case BOOST -> TooltipModifierHook.addFlatBoost(modifier, Component.translatable(attribute.getDescriptionId()), amount, tooltip);
            case PERCENT -> TooltipModifierHook.addPercentBoost(modifier, Component.translatable(attribute.getDescriptionId()), amount, tooltip);
        }
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag flag) {
        float value = this.formula.apply(tool, modifier);
        if (value != 0.0F) {
            addTooltip(modifier.getModifier(), this.attribute, this.operation, this.tooltipStyle, value, null, player, tooltip);
        }
    }

    public static class Builder extends VariableFormula.Builder<Builder, AttributeModifierModule, ToolVariable> {
        protected final Attribute attribute;
        protected final AttributeModifier.Operation operation;
        protected String unique;
        private AttributeModule.TooltipStyle tooltipStyle;

        protected Builder(Attribute attribute, AttributeModifier.Operation operation) {
            super(VARIABLES);
            this.tooltipStyle = AttributeModule.TooltipStyle.ATTRIBUTE;
            this.attribute = attribute;
            this.operation = operation;
        }

        /**
         * Sets the unique string directly
         */
        public Builder unique(String unique) {
            this.unique = unique;
            return this;
        }

        /**
         * Sets the unique string using a resource location
         */
        public Builder uniqueFrom(ResourceLocation id) {
            return unique(id.getNamespace() + ".modifier." + id.getPath());
        }

        public Builder tooltipStyle(AttributeModule.TooltipStyle tooltipStyle) {
            this.tooltipStyle = tooltipStyle;
            return this;
        }

        @Override
        protected AttributeModifierModule build(ModifierFormula formula) {
            if (unique == null) {
                throw new IllegalStateException("Must set unique for attributes");
            }
            return new AttributeModifierModule(unique, attribute, operation, new ToolFormula(formula, variables), tooltipStyle);
        }
    }
}
