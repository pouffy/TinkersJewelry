package dev.ferriarnus.tinkersjewelry.tools.stats;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.data.loadable.primitive.FloatLoadable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.tconstruct.library.materials.stats.IRepairableMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import java.util.List;
import java.util.Objects;

public record BlankBandMaterialStats(int durability, float amplification) implements IRepairableMaterialStats {
	
	public static final MaterialStatsId ID = new MaterialStatsId(new ResourceLocation(TinkersJewelry.MODID, "plain_ring"));
	public static final MaterialStatType<BlankBandMaterialStats> TYPE = new MaterialStatType<>(ID, new BlankBandMaterialStats(100, 1f),
			RecordLoadable.create(IRepairableMaterialStats.DURABILITY_FIELD, FloatLoadable.FROM_ZERO.requiredField("amplification", BlankBandMaterialStats::amplification), BlankBandMaterialStats::new));
	private static final List<Component> DESCRIPTION = ImmutableList.of(ToolStats.DURABILITY.getDescription(), JewelryToolStats.AMPLIFICATION.getDescription());

	@Override
	public MaterialStatType<?> getType() {
		return TYPE;
	}

	@Override
	public List<Component> getLocalizedInfo() {
		List<Component> info = Lists.newArrayList();
		info.add(ToolStats.DURABILITY.formatValue(this.durability));
		info.add(JewelryToolStats.AMPLIFICATION.formatValue(this.amplification));
		return info;
	}

	@Override
	public List<Component> getLocalizedDescriptions() {
		return DESCRIPTION;
	}

	@Override
	public void apply(ModifierStatsBuilder modifierStatsBuilder, float scale) {
		ToolStats.DURABILITY.update(modifierStatsBuilder, this.durability * scale);
		JewelryToolStats.AMPLIFICATION.update(modifierStatsBuilder, this.amplification * scale);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BlankBandMaterialStats that = (BlankBandMaterialStats)o;
		return this.durability == that.durability && this.amplification == that.amplification;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(durability,amplification);
	}

}
