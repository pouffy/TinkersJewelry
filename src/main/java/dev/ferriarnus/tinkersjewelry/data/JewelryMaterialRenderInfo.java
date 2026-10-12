package dev.ferriarnus.tinkersjewelry.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.materials.definition.IMaterial;

import javax.annotation.Nullable;

public class JewelryMaterialRenderInfo extends AbstractMaterialRenderInfoProvider {

	public JewelryMaterialRenderInfo(PackOutput packOutput, @Nullable AbstractMaterialSpriteProvider materialSprites, @Nullable ExistingFileHelper existingFileHelper) {
		super(packOutput, materialSprites, existingFileHelper);
	}

	@Override
	public String getName() {
		return "Tinkers' Jewelry Material Render Info Provider";
	}

	@Override
	protected void addMaterialRenderInfo() {
		// fallback
		buildRenderInfo(IMaterial.UNKNOWN_ID);

		//TODO proper for redstone color.
		buildRenderInfo(JewelryMaterials.redstone).color(0xE52323);
		buildRenderInfo(JewelryMaterials.lapis).color(0x12408b);
		buildRenderInfo(JewelryMaterials.netherstar).color(0xdae2e2);
		buildRenderInfo(JewelryMaterials.scute).color(0x297d49);
		buildRenderInfo(JewelryMaterials.diamond).color(0x1aaaa7);
		buildRenderInfo(JewelryMaterials.quartz).color(0xf7f5f2);
		buildRenderInfo(JewelryMaterials.amethyst).color(0x6f4fab);
		buildRenderInfo(JewelryMaterials.emerald).color(0x00aa2c);
		buildRenderInfo(JewelryMaterials.prismarine).color(0x559a8a);
		buildRenderInfo(JewelryMaterials.heart_of_the_sea).color(0x0d485f);
		buildRenderInfo(JewelryMaterials.ender_eye).color(0x316364);
		buildRenderInfo(JewelryMaterials.echo_shard).color(0x0a5060);
		buildRenderInfo(JewelryMaterials.glowstone).color(0xebaa4e);
		buildRenderInfo(JewelryMaterials.prismarine_crystal).color(0xb4d8ca);
		buildRenderInfo(JewelryMaterials.purpur).color(0xac7bac);

		//buildRenderInfo(JewelryMaterials.ichor_crystal).color(0xff970d);
		//buildRenderInfo(JewelryMaterials.earth_crystal).color(0x01cd4e);
		//buildRenderInfo(JewelryMaterials.sky_crystal).color(0x01cbcd);
		//buildRenderInfo(JewelryMaterials.ender_crystal).color(0xaf4cf6);

		buildRenderInfo(JewelryMaterials.eudialyte).color(0x851228);
		buildRenderInfo(JewelryMaterials.malachite).color(0x055938);
		buildRenderInfo(JewelryMaterials.fire_opal).color(0xff8d76);
		buildRenderInfo(JewelryMaterials.calcite).color(0xe29f11);
		buildRenderInfo(JewelryMaterials.milky_quartz).color(0xe0dce5);
		buildRenderInfo(JewelryMaterials.merlinite).color(0x717297);
		buildRenderInfo(JewelryMaterials.prehnite).color(0xb2e755);
		buildRenderInfo(JewelryMaterials.rubellite).color(0xff4d99);

		buildRenderInfo(JewelryMaterials.ruby).color(0xea1e65);
		buildRenderInfo(JewelryMaterials.peridot).color(0xd5e431);
		buildRenderInfo(JewelryMaterials.sapphire).color(0x44a0fc);
		buildRenderInfo(JewelryMaterials.cinnabar).color(0xd1716e);
		buildRenderInfo(JewelryMaterials.cinnabar).color(0x6ea6e0);
	}

}
