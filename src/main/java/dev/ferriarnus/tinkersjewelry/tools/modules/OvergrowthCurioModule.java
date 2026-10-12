package dev.ferriarnus.tinkersjewelry.tools.modules;

import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosTickHook;
import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.data.loadable.field.LoadableField;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.special.CapacityBarHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.capacity.OverslimeModule;
import slimeknights.tconstruct.library.modifiers.modules.util.ModifierCondition;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record OvergrowthCurioModule(LevelingValue chance, ModifierCondition<IToolStackView> condition) implements ModifierModule, CuriosTickHook, ModifierCondition.ConditionalModule<IToolStackView> {
    public static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<OvergrowthCurioModule>defaultHooks(CuriosModifierHooks.TICK);
    private static final LoadableField<LevelingValue,OvergrowthCurioModule> CHANCE_FIELD = LevelingValue.LOADABLE.requiredField("chance", OvergrowthCurioModule::chance);
    public static final RecordLoadable<OvergrowthCurioModule> LOADER = RecordLoadable.create(CHANCE_FIELD, ModifierCondition.TOOL_FIELD, OvergrowthCurioModule::new);

    public OvergrowthCurioModule(LevelingValue chance) {
        this(chance, ModifierCondition.ANY_TOOL);
    }

    @Override
    public void curioTick(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, ItemStack stack) {
        if (!wearer.level().isClientSide && wearer.tickCount % 20 == 0 && wearer.getUseItem() != stack && condition.matches(tool, entry)) {
            // has a chance of restoring each second per level
            CapacityBarHook bar = getBar(entry);
            float amp = tool.getStats().get(JewelryToolStats.AMPLIFICATION);
            float chance = this.chance.compute(entry.getEffectiveLevel()) * amp;
            if (bar.getAmount(tool) < bar.getCapacity(tool, entry) && Modifier.RANDOM.nextFloat() < chance) {
                bar.addAmount(tool, entry, 1);
            }
        }
    }

    @Override
    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    /** Gets the bar to use for this modifier */
    private CapacityBarHook getBar(ModifierEntry modifier) {
        return OverslimeModule.INSTANCE;
    }


}
