package dev.ferriarnus.tinkersjewelry.data;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class JewelryMaterialData extends AbstractMaterialDataProvider {

	public JewelryMaterialData(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	public String getName() {
		return "Tinker's Jewelry Materials";
	}
	
	@Override
	protected void addMaterials() {
		addMaterial(JewelryMaterials.redstone, 1, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.lapis, 1, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.netherstar, 3, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.scute, 1, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.diamond, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.quartz, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.amethyst, 1, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.emerald, 1, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.prismarine, 1, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.heart_of_the_sea, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.ender_eye, 3, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.echo_shard, 3, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.glowstone, 3, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.prismarine_crystal, 3, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.purpur, 3, ORDER_SPECIAL, true);

		addMaterial(JewelryMaterials.ichor_crystal, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.ender_crystal, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.earth_crystal, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.sky_crystal, 2, ORDER_SPECIAL, true);

		addMaterial(JewelryMaterials.eudialyte, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.malachite, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.fire_opal, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.calcite, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.milky_quartz, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.merlinite, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.prehnite, 2, ORDER_SPECIAL, true);
		addMaterial(JewelryMaterials.rubellite, 2, ORDER_SPECIAL, true);

		addCompatMaterial(JewelryMaterials.ruby, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.RUBY_GEM.location().getPath());
		addCompatMaterial(JewelryMaterials.peridot, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.PERIDOT_GEM.location().getPath());
		addCompatMaterial(JewelryMaterials.sapphire, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.SAPPHIRE_GEM.location().getPath());
		addCompatMaterial(JewelryMaterials.cinnabar, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.CINNABAR_GEM.location().getPath());
		addCompatMaterial(JewelryMaterials.fluorite, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.FLUORITE_GEM.location().getPath());

		addCompatMaterial(JewelryMaterials.xychorium_gem_red, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.RED_XYCHORIUM.location().getPath());
		addCompatMaterial(JewelryMaterials.xychorium_gem_green, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.GREEN_XYCHORIUM.location().getPath());
		addCompatMaterial(JewelryMaterials.xychorium_gem_blue, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.BLUE_XYCHORIUM.location().getPath());
		addCompatMaterial(JewelryMaterials.xychorium_gem_light, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.LIGHT_XYCHORIUM.location().getPath());
		addCompatMaterial(JewelryMaterials.xychorium_gem_dark, 2, ORDER_SPECIAL + ORDER_COMPAT, true, JewelryMaterials.Tags.DARK_XYCHORIUM.location().getPath());

	}

}
