package dev.ferriarnus.tinkersjewelry.data;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import dev.ferriarnus.tinkersjewelry.items.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;

import java.util.function.Consumer;

public class JewelryMaterialRecipe extends RecipeProvider implements IMaterialRecipeHelper {

	public JewelryMaterialRecipe(PackOutput output) {
		super(output);
	}
	
	@Override
	public String getName() {
		return "Tinkers' Jewelry Material Recipe";
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		String folder = "tools/materials/";
		materialRecipe(consumer, JewelryMaterials.redstone, Ingredient.of(Items.REDSTONE), 1, 1, folder + "gems/redstone");
		materialRecipe(consumer, JewelryMaterials.eudialyte, Ingredient.of(ItemRegistry.EUDIALYTE), 1, 1, folder + "gems/blood");
		materialRecipe(consumer, JewelryMaterials.lapis, Ingredient.of(Items.LAPIS_LAZULI), 1, 1, folder + "gems/lapis");
		materialRecipe(consumer, JewelryMaterials.netherstar, Ingredient.of(Items.NETHER_STAR), 1, 1, folder + "gems/netherstar");
		materialRecipe(consumer, JewelryMaterials.scute, Ingredient.of(Items.SCUTE), 1, 1, folder + "gems/scute");
		materialRecipe(consumer, JewelryMaterials.diamond, Ingredient.of(Items.DIAMOND), 1, 1, folder + "gems/diamond");
		materialRecipe(consumer, JewelryMaterials.quartz, Ingredient.of(Items.QUARTZ), 1, 1, folder + "gems/quartz");
		materialRecipe(consumer, JewelryMaterials.amethyst, Ingredient.of(Items.AMETHYST_SHARD), 1, 1, folder + "gems/amethyst");
		materialRecipe(consumer, JewelryMaterials.emerald, Ingredient.of(Items.EMERALD), 1, 1, folder + "gems/emerald");
		materialRecipe(consumer, JewelryMaterials.prismarine, Ingredient.of(Items.PRISMARINE_SHARD), 1, 1, folder + "gems/prismarine");
		materialRecipe(consumer, JewelryMaterials.heart_of_the_sea, Ingredient.of(Items.HEART_OF_THE_SEA), 1, 1, folder + "gems/heart_of_the_sea");
		materialRecipe(consumer, JewelryMaterials.ender_eye, Ingredient.of(Items.ENDER_EYE), 1, 1, folder + "gems/ender_eye");
		materialRecipe(consumer, JewelryMaterials.echo_shard, Ingredient.of(Items.ECHO_SHARD), 1, 1, folder + "gems/echo_shard");
		materialRecipe(consumer, JewelryMaterials.glowstone, Ingredient.of(Items.GLOWSTONE_DUST), 1, 1, folder + "gems/glowstone");
		materialRecipe(consumer, JewelryMaterials.prismarine_crystal, Ingredient.of(Items.PRISMARINE_CRYSTALS), 1, 1, folder + "gems/prismarine_crystal");
		materialRecipe(consumer, JewelryMaterials.purpur, Ingredient.of(Items.PURPUR_BLOCK), 1, 1, folder + "gems/purpur");

		materialRecipe(consumer, JewelryMaterials.malachite, Ingredient.of(ItemRegistry.MALACHITE), 1, 1, folder + "gems/poison");
		materialRecipe(consumer, JewelryMaterials.fire_opal, Ingredient.of(ItemRegistry.FIRE_OPAL), 1, 1, folder + "gems/blaze");
		materialRecipe(consumer, JewelryMaterials.calcite, Ingredient.of(ItemRegistry.CALCITE), 1, 1, folder + "gems/speed");
		materialRecipe(consumer, JewelryMaterials.milky_quartz, Ingredient.of(ItemRegistry.MILKY_QUARTZ), 1, 1, folder + "gems/cleanse");
		materialRecipe(consumer, JewelryMaterials.merlinite, Ingredient.of(ItemRegistry.MERLINITE), 1, 1, folder + "gems/nightvision");
		materialRecipe(consumer, JewelryMaterials.prehnite, Ingredient.of(ItemRegistry.PREHNITE), 1, 1, folder + "gems/exp");
		materialRecipe(consumer, JewelryMaterials.rubellite, Ingredient.of(ItemRegistry.RUBELLITE), 1, 1, folder + "gems/health");

		optionalMaterialRecipe(consumer, JewelryMaterials.ruby, JewelryMaterials.Tags.RUBY_GEM, 1, 1, folder + "gems/ruby");
		optionalMaterialRecipe(consumer, JewelryMaterials.peridot, JewelryMaterials.Tags.PERIDOT_GEM, 1, 1, folder + "gems/period");
		optionalMaterialRecipe(consumer, JewelryMaterials.sapphire, JewelryMaterials.Tags.SAPPHIRE_GEM, 1, 1, folder + "gems/sapphire");
		optionalMaterialRecipe(consumer, JewelryMaterials.cinnabar, JewelryMaterials.Tags.CINNABAR_GEM, 1, 1, folder + "gems/cinnabar");
		optionalMaterialRecipe(consumer, JewelryMaterials.fluorite, JewelryMaterials.Tags.FLUORITE_GEM, 1, 1, folder + "gems/fluorite");

		optionalMaterialRecipe(consumer, JewelryMaterials.xychorium_gem_red, JewelryMaterials.Tags.RED_XYCHORIUM, 1, 1, folder + "gems/xychorium_gem_red");
		optionalMaterialRecipe(consumer, JewelryMaterials.xychorium_gem_green, JewelryMaterials.Tags.GREEN_XYCHORIUM, 1, 1, folder + "gems/xychorium_gem_green");
		optionalMaterialRecipe(consumer, JewelryMaterials.xychorium_gem_blue, JewelryMaterials.Tags.BLUE_XYCHORIUM, 1, 1, folder + "gems/xychorium_gem_blue");
		optionalMaterialRecipe(consumer, JewelryMaterials.xychorium_gem_light, JewelryMaterials.Tags.LIGHT_XYCHORIUM, 1, 1, folder + "gems/xychorium_gem_light");
		optionalMaterialRecipe(consumer, JewelryMaterials.xychorium_gem_dark, JewelryMaterials.Tags.DARK_XYCHORIUM, 1, 1, folder + "gems/xychorium_gem_dark");

		materialRecipe(consumer, JewelryMaterials.hematite, Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation("tinkers_things", "hematite"))), 1, 1, folder + "gems/hematite");
		materialRecipe(consumer, JewelryMaterials.ichor_crystal, Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TConstruct.MOD_ID, "ichor_slime_crystal"))), 1, 1, folder + "gems/ichor_slime_crystal");
		materialRecipe(consumer, JewelryMaterials.earth_crystal, Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TConstruct.MOD_ID, "earth_slime_crystal"))), 1, 1, folder + "gems/earth_slime_crystal");
		materialRecipe(consumer, JewelryMaterials.sky_crystal, Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TConstruct.MOD_ID, "sky_slime_crystal"))), 1, 1, folder + "gems/sky_slime_crystal");
		materialRecipe(consumer, JewelryMaterials.ender_crystal, Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TConstruct.MOD_ID, "ender_slime_crystal"))), 1, 1, folder + "gems/ender_slime_crystal");

	}

	public void optionalMaterialRecipe(Consumer<FinishedRecipe> consumer, MaterialVariantId material, TagKey<Item> tag, int value, int needed, String saveName) {
		Consumer<FinishedRecipe> wrapped = this.withCondition(consumer, this.tagCondition(tag.location().getPath()));
		this.materialRecipe(wrapped, material, Ingredient.of(tag), value, needed, null, saveName);
	}

	@Override
	public String getModId() {
		return TinkersJewelry.MODID;
	}
}
