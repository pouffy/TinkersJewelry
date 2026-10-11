package dev.ferriarnus.tinkersjewelry.tools.modifiers.gem;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.tools.stats.ToolType;

import javax.annotation.Nullable;
import java.util.List;

public class TemperateModifier extends Modifier implements ConditionalStatModifierHook, TooltipModifierHook {

    private static final float MAX_TEMPERATURE = 1.25f;
    private static final float BASELINE_TEMPERATURE = 0.75f;
    private static final float MAX_AMPLIFICATION = 0.15f / MAX_TEMPERATURE;

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.CONDITIONAL_STAT, ModifierHooks.TOOLTIP);
    }

    /** Default logic that starting at 25% gives a bonus of 5% less per level */
    private static float diminishingPercent(float level) {
        // formula gives 25%, 45%, 60%, 70%, 75% for first 5 levels
        if (level < 5) {
            return 0.025f * level * (11 - level);
        }
        // after level 5.5 the above formula breaks, so just do +5% per level
        // means for levels 6 to 10, you get 80%, 85%, 90%, 95%, 100%
        // in default config we never go past level 5, but nice for datapacks to allow
        return 0.75f + (level - 5) * 0.05f;
    }
    /** Gets the bonus for the given position */
    private static float getBonus(LivingEntity living, BlockPos pos) {
        // temperature ranges from -1.25 to 1.25, so make it go -1 to 1
        // negative is cold, positive is hot
        return (living.level().getBiome(pos).value().getTemperature(pos) - BASELINE_TEMPERATURE);
    }

    @Override
    public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
        if (stat == JewelryToolStats.AMPLIFICATION) {
            float bonus = getBonus(living, living.blockPosition());
            baseValue += Mth.abs(bonus) * MAX_AMPLIFICATION * multiplier * modifier.getEffectiveLevel();
        }
        return baseValue;
    }

    @Override
    public void addTooltip(IToolStackView tool, ModifierEntry entry, @Nullable Player player, List<Component> tooltip, TooltipKey key, TooltipFlag tooltipFlag) {
        ToolType type = ToolType.from(tool.getItem(), ToolType.NO_MELEE);
        if (type != null) {
            float bonus = entry.getEffectiveLevel();
            if (player != null && key == TooltipKey.SHIFT) {
                bonus *= getBonus(player, player.blockPosition());
            } else {
                bonus *= 1;
            }
            float value = Mth.abs(bonus) * tool.getStats().get(JewelryToolStats.AMPLIFICATION) * MAX_AMPLIFICATION;
            TooltipModifierHook.addStatBoost(tool, this, JewelryToolStats.AMPLIFICATION, TinkersJewelry.JEWELRY, value, tooltip);
        }
    }
}
