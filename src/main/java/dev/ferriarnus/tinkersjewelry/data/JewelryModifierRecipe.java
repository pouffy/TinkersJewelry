package dev.ferriarnus.tinkersjewelry.data;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import dev.ferriarnus.tinkersjewelry.tools.modifiers.JewelryModifiers;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.IRecipeHelper;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.function.Consumer;

public class JewelryModifierRecipe extends RecipeProvider implements IConditionBuilder, IRecipeHelper {

    public JewelryModifierRecipe(PackOutput generator) {
        super(generator);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        String upgradeFolder = "tools/modifiers/upgrade/";
        String abilityFolder = "tools/modifiers/ability/";
        String slotlessFolder = "tools/modifiers/slotless/";
        String upgradeSalvage = "tools/modifiers/salvage/upgrade/";
        String abilitySalvage = "tools/modifiers/salvage/ability/";
        String defenseFolder = "tools/modifiers/defense/";
        String defenseSalvage = "tools/modifiers/salvage/defense/";
        String compatFolder = "tools/modifiers/compat/";
        String compatSalvage = "tools/modifiers/salvage/compat/";
        String worktableFolder = "tools/modifiers/worktable/";

        ModifierRecipeBuilder.modifier(JewelryModifiers.POLISH)
                .setTools(Ingredient.of(TinkersJewelry.JEWELRY))
                .addInput(Items.CHARCOAL)
                .addInput(Items.HONEYCOMB)
                .addInput(Items.GLOWSTONE_DUST)
                .addInput(ItemTags.SAND)
                .addInput(TinkerModifiers.silkyCloth)
                .setMaxLevel(1).checkTraitLevel()
                .save(consumer, prefix(JewelryModifiers.POLISH, slotlessFolder));

        ModifierRecipeBuilder.modifier(JewelryModifiers.UNDYING)
                .setTools(Ingredient.of(TinkersJewelry.JEWELRY))
                .addInput(Items.TOTEM_OF_UNDYING)
                .setSlots(SlotType.ABILITY, 1)
                .setMaxLevel(1).checkTraitLevel()
                .saveSalvage(consumer, prefix(JewelryModifiers.UNDYING, abilitySalvage))
                .save(consumer, prefix(JewelryModifiers.UNDYING, abilityFolder));

        ModifierRecipeBuilder.modifier(JewelryModifiers.SUBSPACE)
                .setTools(Ingredient.of(TinkersJewelry.JEWELRY))
                .addInput(Items.ENDER_CHEST)
                .setSlots(SlotType.ABILITY, 1)
                .setMaxLevel(1).checkTraitLevel()
                .saveSalvage(consumer, prefix(JewelryModifiers.SUBSPACE, abilitySalvage))
                .save(consumer, prefix(JewelryModifiers.SUBSPACE, abilityFolder));

        ModifierRecipeBuilder.modifier(JewelryModifiers.GLOWING_GEM)
                .setTools(Ingredient.of(TinkersJewelry.JEWELRY))
                .addInput(Items.GLOWSTONE_DUST, 20)
                .setSlots(SlotType.ABILITY, 1)
                .setMaxLevel(1).checkTraitLevel()
                .saveSalvage(consumer, prefix(JewelryModifiers.GLOWING_GEM, upgradeSalvage))
                .save(consumer, prefix(JewelryModifiers.GLOWING_GEM, upgradeFolder));

        ModifierRecipeBuilder.modifier(JewelryModifiers.MAGNETIC)
                .setTools(Ingredient.of(TinkersJewelry.JEWELRY))
                .addInput(Items.COMPASS)
                .setSlots(SlotType.UPGRADE, 1)
                .setMaxLevel(1).checkTraitLevel()
                .save(consumer, prefix(JewelryModifiers.MAGNETIC, upgradeFolder));
    }

    @Override
    public String getName() {
        return "Tinkers's Jewelry Modifier Recipe";
    }

    @Override
    public String getModId() {
        return TinkersJewelry.MODID;
    }
}
