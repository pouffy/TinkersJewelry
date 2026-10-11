package dev.ferriarnus.tinkersjewelry.tools.stats;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.Collections;
import java.util.List;

public record GemMaterialStats() implements IMaterialStats {
	
	public static final MaterialStatsId ID = new MaterialStatsId(new ResourceLocation(TinkersJewelry.MODID, "gem"));
	public static final GemMaterialStats INSTANCE = new GemMaterialStats();
	public static final MaterialStatType<GemMaterialStats> TYPE = new MaterialStatType<>(ID, INSTANCE, new SingletonLoader<>(INSTANCE));
	private static final List<Component> EMPTY = Collections.singletonList(Component.empty());

	@Override
	public MaterialStatType<?> getType() {
		return TYPE;
	}

	@Override
	public MaterialStatsId getIdentifier() {
		return ID;
	}

	@Override
	public List<Component> getLocalizedInfo() {
		return EMPTY;
	}

	@Override
	public List<Component> getLocalizedDescriptions() {
		return EMPTY;
	}

	@Override
	public void apply(ModifierStatsBuilder modifierStatsBuilder, float v) {

	}

}
