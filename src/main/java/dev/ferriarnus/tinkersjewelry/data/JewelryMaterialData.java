package dev.ferriarnus.tinkersjewelry.data;

import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class JewelryMaterialData extends AbstractMaterialDataProvider {

	public JewelryMaterialData(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	public @NotNull String getName() {
		return "Tinker's Jewelry Materials";
	}
	
	@Override
	protected void addMaterials() {
		material(JewelryMaterials.redstone).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.lapis).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.scute).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.amethyst).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.emerald).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.prismarine).sort(ORDER_SPECIAL).craftable(true);

		material(JewelryMaterials.diamond).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.quartz).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.heart_of_the_sea).tier(2).sort(ORDER_SPECIAL).craftable(true);

		material(JewelryMaterials.ichor_crystal).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.ender_crystal).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.earth_crystal).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.sky_crystal).tier(2).sort(ORDER_SPECIAL).craftable(true);

		material(JewelryMaterials.eudialyte).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.malachite).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.fire_opal).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.calcite).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.milky_quartz).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.merlinite).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.prehnite).tier(2).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.rubellite).tier(2).sort(ORDER_SPECIAL).craftable(true);

		material(JewelryMaterials.netherstar).tier(3).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.ender_eye).tier(3).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.echo_shard).tier(3).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.glowstone).tier(3).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.prismarine_crystal).tier(3).sort(ORDER_SPECIAL).craftable(true);
		material(JewelryMaterials.purpur).tier(3).sort(ORDER_SPECIAL).craftable(true);

		material(JewelryMaterials.ruby).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.RUBY_GEM.location().getPath()));
		material(JewelryMaterials.peridot).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.PERIDOT_GEM.location().getPath()));
		material(JewelryMaterials.sapphire).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.SAPPHIRE_GEM.location().getPath()));
		material(JewelryMaterials.cinnabar).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.CINNABAR_GEM.location().getPath()));
		material(JewelryMaterials.fluorite).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.FLUORITE_GEM.location().getPath()));

		material(JewelryMaterials.xychorium_gem_red).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.RED_XYCHORIUM.location().getPath()));
		material(JewelryMaterials.xychorium_gem_green).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.GREEN_XYCHORIUM.location().getPath()));
		material(JewelryMaterials.xychorium_gem_blue).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.BLUE_XYCHORIUM.location().getPath()));
		material(JewelryMaterials.xychorium_gem_light).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.LIGHT_XYCHORIUM.location().getPath()));
		material(JewelryMaterials.xychorium_gem_dark).tier(2).sort(ORDER_SPECIAL + ORDER_COMPAT).craftable(true).compat(tagExistsCondition(JewelryMaterials.Tags.DARK_XYCHORIUM.location().getPath()));
	}

}
