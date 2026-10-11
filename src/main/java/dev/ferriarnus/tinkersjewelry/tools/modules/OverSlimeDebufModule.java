package dev.ferriarnus.tinkersjewelry.tools.modules;

import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierManager;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.definition.module.ToolHooks;
import slimeknights.tconstruct.library.tools.definition.module.ToolModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolStatsHook;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

import static slimeknights.tconstruct.tools.TinkerModifiers.overslime;

public record OverSlimeDebufModule() implements ToolStatsHook, ToolModule {
    public static final OverSlimeDebufModule INSTANCE = new OverSlimeDebufModule();
    public static final RecordLoadable<OverSlimeDebufModule> LOADER = new SingletonLoader<>(INSTANCE);
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<OverSlimeDebufModule>defaultHooks(ToolHooks.TOOL_STATS);

    @Override
    public void addToolStats(IToolContext iToolContext, ModifierStatsBuilder modifierStatsBuilder) {
        if (iToolContext.getModifierLevel(overslime.getId()) > 0 && !hasFriend(iToolContext)) {
            JewelryToolStats.AMPLIFICATION.multiplyAll(modifierStatsBuilder, 0.8F);
        }
    }

    @Override
    public RecordLoadable<? extends ToolModule> getLoader() {
        return LOADER;
    }

    @Override
    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    /** Checks if the given tool has an overslime friend */
    private static boolean hasFriend(IToolContext context) {
        for (ModifierEntry entry : context.getModifierList()) {
            if (ModifierManager.isInTag(entry.getId(), TinkerTags.Modifiers.OVERSLIME_FRIEND)) {
                return true;
            }
        }
        return false;
    }
}
