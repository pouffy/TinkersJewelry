package dev.ferriarnus.tinkersjewelry.data;

import dev.ferriarnus.tinkersjewelry.tools.stats.GemMaterialStats;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

public class JewelryMaterialSprite extends AbstractMaterialSpriteProvider {

	@Override
	public String getName() {
		return "Tinkers Jewelry Materials";
	}

	@Override
	protected void addAllMaterials() {
		buildMaterial(JewelryMaterials.redstone)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF340605).addARGB(102, 0xFF410500).addARGB(140, 0xFF5c0700).addARGB(178, 0xFF720000).addARGB(216, 0xFFaa0f01).addARGB(255, 0xFFff0000).build());

		buildMaterial(JewelryMaterials.lapis)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF052463).addARGB(102, 0xFF12408b).addARGB(140, 0xFF1a3d8f).addARGB(178, 0xFF1c53a8).addARGB(216, 0xFF345ec3).addARGB(255, 0xFF5a82e2).build());

		buildMaterial(JewelryMaterials.netherstar)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF649090).addARGB(102, 0xFFb9c9c9).addARGB(140, 0xFFdae2e2).addARGB(178, 0xFFd2d200).addARGB(216, 0xFFe0e277).addARGB(255, 0xFFfdffa8).build());

		buildMaterial(JewelryMaterials.scute)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF206a43).addARGB(102, 0xFF297d49).addARGB(140, 0xFF388d3a).addARGB(178, 0xFF388d3a).addARGB(216, 0xFF3fa442).addARGB(255, 0xFF47bf4a).build());

		buildMaterial(JewelryMaterials.diamond)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF145e53).addARGB(102, 0xFF11727a).addARGB(140, 0xFF1c919a).addARGB(178, 0xFF20c5b5).addARGB(216, 0xFF2ce0d8).addARGB(255, 0xFFa1fbe8).build());

		buildMaterial(JewelryMaterials.quartz)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF897b73).addARGB(102, 0xFFb3a091).addARGB(140, 0xFFddd4c6).addARGB(178, 0xeFF5dfd6).addARGB(216, 0xFFeae5de).addARGB(255, 0xFFf7f5f2).build());

		buildMaterial(JewelryMaterials.amethyst)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF54398a).addARGB(102, 0xFF6f4fab).addARGB(140, 0xFF8d6acc).addARGB(178, 0xFFb38ef3).addARGB(216, 0xFFcfa0f3).addARGB(255, 0xFFfecbe6).build());

		buildMaterial(JewelryMaterials.emerald)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF005300).addARGB(102, 0xFF009529).addARGB(140, 0xFF00aa2c).addARGB(178, 0xFF17dd62).addARGB(216, 0xFF41f384).addARGB(255, 0xFFaffdcd).build());

		buildMaterial(JewelryMaterials.prismarine)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF2e6053).addARGB(102, 0xFF33695b).addARGB(140, 0xFF559a8a).addARGB(178, 0xFF539882).addARGB(216, 0xFF91c2ac).addARGB(255, 0xFFa4d1c2).build());

		buildMaterial(JewelryMaterials.heart_of_the_sea)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF0b3a4d).addARGB(102, 0xFF0d485f).addARGB(140, 0xFF0f5875).addARGB(178, 0xFF1d6d8d).addARGB(216, 0xFF2180a1).addARGB(255, 0xFF1f96b1).build());

		buildMaterial(JewelryMaterials.ender_eye)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF032620).addARGB(102, 0xFF0C3730).addARGB(140, 0xFF0B4D42).addARGB(178, 0xFF659b7d).addARGB(216, 0xFF71ac49).addARGB(255, 0xFF1e4835).build());

		buildMaterial(JewelryMaterials.echo_shard)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF111b21).addARGB(102, 0xFF052a32).addARGB(140, 0xFF034150).addARGB(178, 0xFF0a5060).addARGB(216, 0xFF0a5060).addARGB(255, 0xFF29dfeb).build());

		buildMaterial(JewelryMaterials.glowstone)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF5a391c).addARGB(102, 0xFF8e562e).addARGB(140, 0xFFb47140).addARGB(178, 0xFFebaa4e).addARGB(216, 0xFFffbc5e).addARGB(255, 0xFFf9d49c).build());

		buildMaterial(JewelryMaterials.prismarine_crystal)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF49645c).addARGB(102, 0xFF72a498).addARGB(140, 0xFF7cb3a4).addARGB(178, 0xFF91c5b7).addARGB(216, 0xFFb4d8ca).addARGB(255, 0xFFdfe9dc).build());

		buildMaterial(JewelryMaterials.purpur)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF906590).addARGB(102, 0xFFa472a3).addARGB(140, 0xFFac7bac).addARGB(178, 0xFFb286b2).addARGB(216, 0xFFba95ba).addARGB(255, 0xFFc7a8c7).build());


		buildMaterial(JewelryMaterials.ichor_crystal)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFFB04000).addARGB(102, 0xFFD35200).addARGB(140, 0xFFEC5E00).addARGB(178, 0xFFFF8324).addARGB(216, 0xFFFFB97C).addARGB(255, 0xFFFFFFFF).build());

		buildMaterial(JewelryMaterials.sky_crystal)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF2F5351).addARGB(102, 0xFF3B6D6D).addARGB(140, 0xFF49807E).addARGB(178, 0xFF63ACAB).addARGB(216, 0xFF82D7D5).addARGB(255, 0xFFFFFFFF).build());

		buildMaterial(JewelryMaterials.earth_crystal)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF34532F).addARGB(102, 0xFF406D3B).addARGB(140, 0xFF508049).addARGB(178, 0xFF71AC63).addARGB(216, 0xFF8CD782).addARGB(255, 0xFFFFFFFF).build());

		buildMaterial(JewelryMaterials.ender_crystal)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF6300B0).addARGB(102, 0xFF790DC6).addARGB(140, 0xFFA936ED).addARGB(178, 0xFFBF58F7).addARGB(216, 0xFFD37CFF).addARGB(255, 0xFFEEBFFF).build());


		buildMaterial(JewelryMaterials.eudialyte)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF620819).addARGB(102, 0xFFa71c1c).addARGB(140, 0xFF4f9f9d).addARGB(178, 0xFF7bc0bf).addARGB(216, 0xFFd42b2b).addARGB(255, 0xFFe84848).build());

		buildMaterial(JewelryMaterials.malachite)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF012718).addARGB(102, 0xFF159854).addARGB(140, 0xFF21ac63).addARGB(178, 0xFF07593f).addARGB(216, 0xFF0c7b4b).addARGB(255, 0xFF64d49c).build());

		buildMaterial(JewelryMaterials.fire_opal)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFFa61b38).addARGB(102, 0xFFd02e40).addARGB(140, 0xFFff5754).addARGB(178, 0xFFff8453).addARGB(216, 0xFFff9966).addARGB(255, 0xFFffc86a).build());

		buildMaterial(JewelryMaterials.calcite)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF713f00).addARGB(102, 0xFF9d5103).addARGB(140, 0xFFce7404).addARGB(178, 0xFFf2b733).addARGB(216, 0xFFfac132).addARGB(255, 0xFFf6bf49).build());

		buildMaterial(JewelryMaterials.milky_quartz)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF443d50).addARGB(102, 0xFF797088).addARGB(140, 0xFFb1a9be).addARGB(178, 0xFFc8c2d2).addARGB(216, 0xFFece8f2).addARGB(255, 0xFFfdfcff).build());

		buildMaterial(JewelryMaterials.merlinite)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF161724).addARGB(102, 0xFF40405a).addARGB(140, 0xFF545473).addARGB(178, 0xFF717297).addARGB(216, 0xFF8c8cb2).addARGB(255, 0xFFb3b3d6).build());

		buildMaterial(JewelryMaterials.prehnite)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF1c3000).addARGB(102, 0xFF426703).addARGB(140, 0xFF2f8b27).addARGB(178, 0xFF98cb3f).addARGB(216, 0xFFb2e755).addARGB(255, 0xFFeffeda).build());

		buildMaterial(JewelryMaterials.rubellite)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF801175).addARGB(102, 0xFFbe2165).addARGB(140, 0xFFf03a84).addARGB(178, 0xFFff4d99).addARGB(216, 0xFFff8dae).addARGB(255, 0xffffb8cd).build());


		buildMaterial(JewelryMaterials.ruby)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF850118).addARGB(102, 0xFFb10524).addARGB(140, 0xFFcd0134).addARGB(178, 0xFFea1e65).addARGB(216, 0xFFf7539e).addARGB(255, 0xfff881aa).build());

		buildMaterial(JewelryMaterials.peridot)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF425b00).addARGB(102, 0xFF658e04).addARGB(140, 0xFF80a017).addARGB(178, 0xFFb3c727).addARGB(216, 0xFFd5e431).addARGB(255, 0xFFffff66).build());

		buildMaterial(JewelryMaterials.sapphire)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF2332dd).addARGB(102, 0xFF2e49e2).addARGB(140, 0xFF407cf7).addARGB(178, 0xFF44a0fc).addARGB(216, 0xFF5fbafc).addARGB(255, 0xffcbecff).build());

		buildMaterial(JewelryMaterials.cinnabar)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF690d18).addARGB(102, 0xFFb4263c).addARGB(140, 0xFFc34d56).addARGB(178, 0xFFd1716e).addARGB(216, 0xFFe5a792).addARGB(255, 0xfff7dcb1).build());

		buildMaterial(JewelryMaterials.fluorite)
				.statType(GemMaterialStats.ID)
				.colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF393f92).addARGB(102, 0xFF504a93).addARGB(140, 0xFF5c6fb2).addARGB(178, 0xFF6ea6e0).addARGB(216, 0xFFbe5bc9).addARGB(255, 0xfff0a6de).build());

	}
}
