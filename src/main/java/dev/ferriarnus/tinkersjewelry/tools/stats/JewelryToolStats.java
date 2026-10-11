package dev.ferriarnus.tinkersjewelry.tools.stats;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;

import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStatId;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class JewelryToolStats {

	public static final FloatToolStat AMPLIFICATION = ToolStats.registerConditional(new FloatToolStat(new ToolStatId(TinkersJewelry.MODID, "amplification"), 0xbd41a2, 1, 0.1f, 3));

	public static void register() {}
}
