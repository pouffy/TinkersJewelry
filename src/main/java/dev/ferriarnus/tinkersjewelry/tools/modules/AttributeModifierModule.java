package dev.ferriarnus.tinkersjewelry.tools.modules;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.ferriarnus.tinkersjewelry.tools.hooks.AttributeModifierHook;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.data.loadable.Loadables;
import slimeknights.mantle.data.loadable.primitive.StringLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.TinkerLoadables;
import slimeknights.tconstruct.library.json.math.ModifierFormula;
import slimeknights.tconstruct.library.json.variable.VariableFormula;
import slimeknights.tconstruct.library.json.variable.VariableFormulaLoadable;
import slimeknights.tconstruct.library.json.variable.tool.ToolFormula;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;
import java.util.UUID;

public record AttributeModifierModule(String unique, Attribute attribute, AttributeModifier.Operation operation, ToolFormula formula) implements ModifierModule, AttributeModifierHook {

    private static final String[] VARIABLES = { "level" };
    private static final RecordLoadable<ToolFormula> VARIABLE_LOADER = new VariableFormulaLoadable<>(ToolVariable.LOADER, VARIABLES, ModifierFormula.FallbackFormula.IDENTITY, (formula, variables, percent) -> new ToolFormula(formula, variables, VariableFormula.EMPTY_STRINGS));
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = List.of(CuriosModifierHooks.ATTRIBUTE);

    public static final RecordLoadable<AttributeModifierModule> LOADER = RecordLoadable.create(
            StringLoadable.DEFAULT.requiredField("unique", AttributeModifierModule::unique),
            Loadables.ATTRIBUTE.requiredField("attribute", AttributeModifierModule::attribute),
            TinkerLoadables.OPERATION.requiredField("operation", AttributeModifierModule::operation),
            VARIABLE_LOADER.directField(AttributeModifierModule::formula), AttributeModifierModule::new);


    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack, ModifierEntry modifier) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = HashMultimap.create();
        ToolStack toolStack = ToolStack.from(stack);
        attributeModifiers.put(attribute, new AttributeModifier(uuid, unique, formula.apply(toolStack, modifier), operation));
        return attributeModifiers;
    }

    /** Creates a new builder instance */
    public static Builder builder(Attribute attribute, AttributeModifier.Operation operation) {
        return new Builder(attribute, operation);
    }

    public static class Builder extends VariableFormula.Builder<Builder, AttributeModifierModule, ToolVariable> {
        protected final Attribute attribute;
        protected final AttributeModifier.Operation operation;
        protected String unique;

        protected Builder(Attribute attribute, AttributeModifier.Operation operation) {
            super(VARIABLES);
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

        @Override
        protected AttributeModifierModule build(ModifierFormula formula) {
            if (unique == null) {
                throw new IllegalStateException("Must set unique for attributes");
            }
            return new AttributeModifierModule(unique, attribute, operation, new ToolFormula(formula, variables));
        }
    }
}
