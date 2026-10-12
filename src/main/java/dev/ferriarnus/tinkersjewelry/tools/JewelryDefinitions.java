package dev.ferriarnus.tinkersjewelry.tools;

import dev.ferriarnus.tinkersjewelry.data.JewelryMaterials;
import dev.ferriarnus.tinkersjewelry.items.ItemRegistry;
import dev.ferriarnus.tinkersjewelry.tools.stats.BlankBandMaterialStats;
import dev.ferriarnus.tinkersjewelry.tools.stats.GemMaterialStats;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import java.util.ArrayList;
import java.util.List;

public class JewelryDefinitions {

	public static final ToolDefinition RING = ToolDefinition.create(ItemRegistry.RING);

	public static final List<MaterialVariantId> cutGemMaterials = new ArrayList<>();
	public static final List<MaterialVariantId> plainRingMaterials = new ArrayList<>();

	static {
		//Existing Materials
		//Pull requests for these would be considered, but I'd have to examine them carefully.
		plainRingMaterials.add(MaterialIds.copper);
		plainRingMaterials.add(MaterialIds.iron);
		plainRingMaterials.add(MaterialIds.gold);
		plainRingMaterials.add(MaterialIds.osmium);

		plainRingMaterials.add(MaterialIds.silver);
		plainRingMaterials.add(MaterialIds.lead);
		plainRingMaterials.add(MaterialIds.slimesteel);
		plainRingMaterials.add(MaterialIds.amethystBronze);
		plainRingMaterials.add(MaterialIds.nahuatl);
		plainRingMaterials.add(MaterialIds.pigIron);
		plainRingMaterials.add(MaterialIds.roseGold);
		plainRingMaterials.add(MaterialIds.steel);
		plainRingMaterials.add(MaterialIds.bronze);
		plainRingMaterials.add(MaterialIds.constantan);
		plainRingMaterials.add(MaterialIds.invar);
		plainRingMaterials.add(MaterialIds.electrum);
		plainRingMaterials.add(MaterialIds.platedSlimewood);
		plainRingMaterials.add(MaterialIds.manyullyn);
		plainRingMaterials.add(MaterialIds.cinderslime);
		plainRingMaterials.add(MaterialIds.queensSlime);
		plainRingMaterials.add(MaterialIds.cobalt);
		plainRingMaterials.add(MaterialIds.hepatizon);
		plainRingMaterials.add(MaterialIds.knightslime);

		cutGemMaterials.add(JewelryMaterials.hematite);
		cutGemMaterials.add(MaterialIds.rock);
		cutGemMaterials.add(MaterialIds.flint);
		cutGemMaterials.add(MaterialIds.obsidian);
		cutGemMaterials.add(MaterialIds.necroticBone);
		cutGemMaterials.add(MaterialIds.bone);
		cutGemMaterials.add(MaterialIds.blazingBone);
		cutGemMaterials.add(MaterialIds.venombone);
		cutGemMaterials.add(MaterialIds.scorchedStone);
		cutGemMaterials.add(MaterialIds.searedStone);
		cutGemMaterials.add(MaterialIds.enderPearl);
		cutGemMaterials.add(MaterialIds.whitestone);
		cutGemMaterials.add(MaterialIds.necronium);

		cutGemMaterials.add(MaterialIds.ichor);
		cutGemMaterials.add(MaterialIds.enderslime);
		cutGemMaterials.add(MaterialIds.skyslime);
		cutGemMaterials.add(MaterialIds.earthslime);

		//Variants
		plainRingMaterials.add(MaterialIds.oxidizedCopper);
		plainRingMaterials.add(MaterialIds.oxidizedIron);
	}

	public static GeneratorPartTextureJsonGenerator.StatOverride getStatOverrides() {
		GeneratorPartTextureJsonGenerator.StatOverride.Builder builder = new GeneratorPartTextureJsonGenerator.StatOverride.Builder();
		plainRingMaterials.forEach((material) -> builder.addVariant(BlankBandMaterialStats.ID, material));
		cutGemMaterials.forEach((material) -> builder.addVariant(GemMaterialStats.ID, material));
		return builder.build();
	}
}
