package dev.ferriarnus.tinkersjewelry.tools.modifiers;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;

import dev.ferriarnus.tinkersjewelry.tools.modifiers.gem.*;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.DynamicModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class JewelryModifiers {

	protected static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersJewelry.MODID);

	public static final ModifierId PRECIOUS = id("precious");
	public static final ModifierId POLISH = id("polish");
	public static final ModifierId MAGNETIC = id("magnetic");
	public static final ModifierId MAINTAINED = id("maintained");
	public static final ModifierId DUCTILE = id("ductile");
	public static final ModifierId SUBSPACE = id("subspace");
	public static final ModifierId OVERWIELD = id("overwield");

	public static final ModifierId ANTI_HEAL_GEM = id("antiheal_gem");
	public static final ModifierId ARMOR_GEM = id("armor_gem");
	public static final ModifierId CONDUIT_GEM = id("conduit_gem");
	public static final ModifierId DARKNESS_GEM = id("darkness_gem");
	public static final ModifierId CURRENT_HEALTH_DAMAGE_GEM = id("current_health_damage_gem");
	public static final ModifierId ENDERFERENCE_GEM = id("enderference_gem");
	public static final ModifierId EXP_GEM = id("exp_gem");
	public static final ModifierId FLAME_GEM = id("flame_gem");
	public static final ModifierId FIRERESISTANCE_GEM = id("fireresistance_gem");
	public static final ModifierId FORTUNE_GEM = id("fortune_gem");
	public static final ModifierId GLOWING_GEM = id("glowing_gem");
	public static final ModifierId GRAVITY_GEM = id("gravity_gem");
	public static final ModifierId HEALTH_GEM = id("health_gem");
	public static final ModifierId KNOCKBACK_GEM = id("knockback_gem");
	public static final ModifierId LIFESTEAL_GEM = id("lifesteal_gem");
	public static final ModifierId LUCK_GEM = id("luck_gem");
	public static final ModifierId MINING_SPEED_GEM = id("mining_speed_gem");
	public static final ModifierId PIERCING_GEM = id("piercing_gem");
	public static final ModifierId NIGHTVISION_GEM = id("nightvision_gem");
	public static final ModifierId RAGE_GEM = id("rage_gem");
	public static final ModifierId REACH_GEM = id("reach_gem");
	public static final ModifierId RESISTANCE_GEM = id("resistance_gem");
	public static final ModifierId SILK_GEM = id("silk_gem");
	public static final ModifierId SPEED_GEM = id("speed_gem");
	public static final ModifierId SWIM_GEM = id("swim_gem");
	public static final ModifierId TRADE_GEM = id("trade_gem");
	public static final ModifierId POISON_GEM = id("poison_gem");
	public static final ModifierId WATERBREATHING_GEM = id("waterbreathing_gem");
	public static final ModifierId WITHER_GEM = id("wither_gem");

	public static final ModifierId OVERGROWTH_GEM = id("overgrowth_gem");
	public static final ModifierId SKYFALL_GEM = id("skyfall_gem");
	public static final ModifierId GODSPEED_GEM = id("godspeed_gem");
	public static final ModifierId ENDERDODGING_GEM = id("enderdodging_gem");

	public static final ModifierId COBALAMIN_GEM = id("cobalamin_gem");
	public static final ModifierId LOYAL_GEM = id("loyal_gem");

	public static final StaticModifier<FlyGemModifier> FLY_GEM = MODIFIERS.register("fly_gem", FlyGemModifier::new);
	public static final StaticModifier<CleanseGemModifier> CLEANSE_GEM = MODIFIERS.register("cleanse_gem", CleanseGemModifier::new);
	public static final StaticModifier<DrawSpeedGemModifier> DRAW_SPEED_GEM = MODIFIERS.register("draw_speed_gem", DrawSpeedGemModifier::new);
	public static final StaticModifier<EnderclearanceGemModifier> ENDERCLEARANCE_GEM = MODIFIERS.register("enderclearance_gem", EnderclearanceGemModifier::new);

	public static final StaticModifier<InvariantModifier> INVARIANT = MODIFIERS.register("invariant", InvariantModifier::new);
	public static final StaticModifier<TemperateModifier> TEMPERATE = MODIFIERS.register("temperate", TemperateModifier::new);

	public static final DynamicModifier UNDYING = MODIFIERS.registerDynamic("undying");

	public static void register(IEventBus modEventBus) {
		MODIFIERS.register(modEventBus);
	}

	private static ModifierId id(String name) {
		return new ModifierId(TinkersJewelry.MODID, name);
	}
}
