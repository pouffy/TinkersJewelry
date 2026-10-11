package dev.ferriarnus.tinkersjewelry.data;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import dev.ferriarnus.tinkersjewelry.items.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CompoundIngredient;
import slimeknights.mantle.recipe.helper.ItemOutput;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.recipe.partbuilder.ItemPartRecipeBuilder;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import java.util.function.Consumer;

public class JewelryToolsRecipe extends RecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

	public JewelryToolsRecipe(PackOutput output) {
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		String partFolder = "tools/parts/";
		String castFolder = "smeltery/casts/";
		String folder = "tools/building/";
		toolBuilding(consumer, ItemRegistry.RING, folder);
		partRecipes(consumer, ItemRegistry.PLAIN_RING.get(), ItemRegistry.PLAIN_RING_CAST, 1, partFolder, castFolder);
		uncastablePart(consumer, ItemRegistry.GEM.get(), 1, null, partFolder);

		dummyGem(consumer, ItemRegistry.GEM.get(), MaterialIds.enderPearl, 1, partFolder);
		dummyGem(consumer, ItemRegistry.GEM.get(), MaterialIds.obsidian, 1, partFolder);

		dummyGem(consumer, ItemRegistry.GEM.get(), JewelryMaterials.hematite, 1, partFolder);
	}

	public void dummyGem(Consumer<FinishedRecipe> consumer, IMaterialItem part, MaterialId materialId, int cost, String partFolder) {
		ItemPartRecipeBuilder.item(this.id(part), ItemOutput.fromStack(part.withMaterialForDisplay(materialId))).material(materialId, cost).setPatternItem(CompoundIngredient.of(Ingredient.of(TinkerTags.Items.DEFAULT_PATTERNS))).save(consumer, this.location(partFolder + "builder/" + materialId.getPath()));
	}

	@Override
	public String getName() {
	    return "Tinkers' Jewelry Tool Recipes";
	}

	@Override
	public String getModId() {
		return TinkersJewelry.MODID;
	}
}
