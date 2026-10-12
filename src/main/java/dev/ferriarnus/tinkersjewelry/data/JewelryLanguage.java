package dev.ferriarnus.tinkersjewelry.data;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import dev.ferriarnus.tinkersjewelry.tools.modifiers.JewelryModifiers;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class JewelryLanguage extends LanguageProvider {

    public JewelryLanguage(PackOutput output) {
        super(output, TinkersJewelry.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        items();
        misc();
        materials();
        modifiers();
    }

    private void items() {
        toEng("item.tinkersjewelry.eudialyte");
        toEng("item.tinkersjewelry.fire_opal");
        toEng("item.tinkersjewelry.malachite");
        toEng("item.tinkersjewelry.calcite");
        toEng("item.tinkersjewelry.milky_quartz");
        toEng("item.tinkersjewelry.merlinite");
        toEng("item.tinkersjewelry.prehnite");
        toEng("item.tinkersjewelry.rubellite");

        toEng("item.tinkersjewelry.ring");
        add("item.tinkersjewelry.ring.description", "Rings are simple trinkets that grant effects based on the used gem.");

        add("item.tinkersjewelry.gem", "Cut Gem");
        add("item.tinkersjewelry.gem.description", "Gems hold the traits.");

        toEng("item.tinkersjewelry.plain_ring");
        add("item.tinkersjewelry.plain_ring.description", "Rings are simple trinkets that grant effects based on the used gem.");

        add("item.tinkersjewelry.plain_ring_cast", "Plain Ring Gold Cast");
        toEng("item.tinkersjewelry.plain_ring_sand_cast");
        toEng("item.tinkersjewelry.plain_ring_red_sand_cast");

        add("item.tinkersjewelry.book", "Artificing for Dummies");
        add("item.tinkersjewelry.book.tooltip", "A guide to looking fabulous\nby Ferri");
    }

    private void misc() {
        add("itemGroup.tinkersjewelry.all", "Tinkers' Jewelry Re-Socketed");

        add("key.categories.tinkersjewelry", "Tinkers' Jewelry Re-Socketed");
        toEng("key.tinkersjewelry.subspace");

        toEng("gui.tinkersjewelry.subspace");

        add("pattern.tinkersjewelry.gem", "Cut Gem");
        toEng("pattern.tinkersjewelry.plain_ring");

        add("stat.tinkersjewelry.gem", "Cut Gem");
        toEng("stat.tinkersjewelry.plain_ring");

        add("description.tinkersjewelry.flight", "%s seconds flight");

        add("modifier.tinkersjewelry.undying.requirements", "Requires Soulbound");
        add("modifier.tinkersjewelry.invariant.amplification", "Neutral Amplification");
        add("modifier.tinkersjewelry.temperate.amplification", "Temperate Amplification");
        add("modifier.tinkersjewelry.overwield.amplification", "Overwield Amplification");

        add("tool_stat.tinkersjewelry.amplification", "Amplification: ");
        add("tool_stat.tinkersjewelry.amplification.description", "Increases/decrease the strength of effects");
    }

    private void materials() {
        forMaterial(JewelryMaterials.redstone);
        forMaterial(JewelryMaterials.lapis);
        forMaterial(JewelryMaterials.netherstar);
        forMaterial(JewelryMaterials.scute);
        forMaterial(JewelryMaterials.diamond);
        forMaterial(JewelryMaterials.quartz);
        forMaterial(JewelryMaterials.amethyst);
        forMaterial(JewelryMaterials.emerald);
        forMaterial(JewelryMaterials.prismarine);
        forMaterial(JewelryMaterials.heart_of_the_sea, "Heart of the Sea");
        forMaterial(JewelryMaterials.ender_eye, "Eye of Ender");
        forMaterial(JewelryMaterials.echo_shard);
        forMaterial(JewelryMaterials.glowstone);
        forMaterial(JewelryMaterials.prismarine_crystal);
        forMaterial(JewelryMaterials.purpur);

        //forMaterial(JewelryMaterials.earth_crystal, "Earthslime");
        //forMaterial(JewelryMaterials.sky_crystal, "Skyslime");
        //forMaterial(JewelryMaterials.ender_crystal, "Enderslime");
        //forMaterial(JewelryMaterials.ichor_crystal, "Ichor");

        forMaterial(JewelryMaterials.eudialyte);
        forMaterial(JewelryMaterials.fire_opal);
        forMaterial(JewelryMaterials.malachite);
        forMaterial(JewelryMaterials.calcite);
        forMaterial(JewelryMaterials.milky_quartz);
        forMaterial(JewelryMaterials.merlinite);
        forMaterial(JewelryMaterials.prehnite);
        forMaterial(JewelryMaterials.rubellite);

        forMaterial(JewelryMaterials.ruby);
        forMaterial(JewelryMaterials.peridot);
        forMaterial(JewelryMaterials.sapphire);
        forMaterial(JewelryMaterials.cinnabar);
        forMaterial(JewelryMaterials.fluorite);

        forMaterial(JewelryMaterials.xychorium_gem_blue, "Blue Xychorium");
        forMaterial(JewelryMaterials.xychorium_gem_red, "Red Xychorium");
        forMaterial(JewelryMaterials.xychorium_gem_green, "Green Xychorium");
        forMaterial(JewelryMaterials.xychorium_gem_dark, "Dark Xychorium");
        forMaterial(JewelryMaterials.xychorium_gem_light, "Light Xychorium");
    }

    private void modifiers() {
        forModifier(JewelryModifiers.RAGE_GEM, "Rage", "Gives an attack speed boost");
        forModifier(JewelryModifiers.LIFESTEAL_GEM, "Lifesteal", "Heals based on damage dealt");
        forModifier(JewelryModifiers.FLY_GEM.getId(), "Flight", "Allows creative flight for a few seconds");
        forModifier(JewelryModifiers.ARMOR_GEM, "Armor", "Adds additional armor");
        forModifier(JewelryModifiers.PIERCING_GEM, "Piercing", "Ignores part of the target's armor");
        forModifier(JewelryModifiers.SPEED_GEM, "Speed", "Increases movement speed");
        forModifier(JewelryModifiers.FIRERESISTANCE_GEM, "Fire Resistance", "Gives Fire Resistance");
        forModifier(JewelryModifiers.SILK_GEM, "Silk Touch", "Gives Silk Touch");
        forModifier(JewelryModifiers.FORTUNE_GEM, "Fortune", "Grants fortune");
        forModifier(JewelryModifiers.WATERBREATHING_GEM, "Water Breathing", "Gives Water Breathing");
        forModifier(JewelryModifiers.NIGHTVISION_GEM, "Night Vision", "Gives Night Vision");
        forModifier(JewelryModifiers.POISON_GEM, "Poison", "Applies Poison to the target");
        forModifier(JewelryModifiers.FLAME_GEM, "Flame", "Lights the target on fire");
        forModifier(JewelryModifiers.CLEANSE_GEM.getId(), "Cleanse", "Removes negative effects");
        forModifier(JewelryModifiers.HEALTH_GEM, "Health", "Gives bonus Health");
        forModifier(JewelryModifiers.REACH_GEM, "Reach", "Gives bonus entity reach");
        forModifier(JewelryModifiers.TRADE_GEM, "Trade", "Gives lower prices while trading with villagers");
        forModifier(JewelryModifiers.EXP_GEM, "Experience", "Increases the experience gained");
        forModifier(JewelryModifiers.RESISTANCE_GEM, "Resistance", "Give the Resistance effect");
        forModifier(JewelryModifiers.LUCK_GEM, "Luck", "Increases the wearers luck");
        forModifier(JewelryModifiers.SWIM_GEM, "Swim Speed", "Increases the wearers swimming speed");
        forModifier(JewelryModifiers.GRAVITY_GEM, "Gravity", "Reduces Gravity");
        forModifier(JewelryModifiers.CURRENT_HEALTH_DAMAGE_GEM, "Current Health Damage", "Applies additional current health damage");
        forModifier(JewelryModifiers.CONDUIT_GEM, "Conduit Power", "Give the Conduit Power effect");
        forModifier(JewelryModifiers.DRAW_SPEED_GEM.getId(), "Draw Speed", "Increases the wearers draw speed");
        forModifier(JewelryModifiers.MINING_SPEED_GEM, "Mining Speed", "Increases the wearers mining speed");
        forModifier(JewelryModifiers.ENDERFERENCE_GEM, "Enderference", "Really quite distracting", "Prevents target from teleporting for a short time");
        forModifier(JewelryModifiers.ENDERCLEARANCE_GEM.getId(), "Enderclearance", "Discount on the ender", "Has a chance to teleport attackers away");
        forModifier(JewelryModifiers.ANTI_HEAL_GEM, "Anti-Heal", "Decreases the effectiveness of healing on the enemy");
        forModifier(JewelryModifiers.GLOWING_GEM, "Marked", "That's leaving a mark", "Marks an enemy with the glowing effect");
        forModifier(JewelryModifiers.DARKNESS_GEM, "Darkness", "Casts the darkness effect on the enemy");
        forModifier(JewelryModifiers.KNOCKBACK_GEM, "Knockback", "Grants attack knockback");
        forModifier(JewelryModifiers.WITHER_GEM, "Wither", "Applies Wither to the target");
        forModifier(JewelryModifiers.PRECIOUS, "Precious", "That looks expensive", "Gives an ability slot");
        forModifier(JewelryModifiers.POLISH, "Polished", "Shiny", "Gives an upgrade slot");
        forModifier(JewelryModifiers.UNDYING.getId(), "Undying", "Not today!", "When taking lethal damage, survive on 1 hitpoint");
        forModifier(JewelryModifiers.SUBSPACE, "Subspace", "Where does it go?", "Adds 9 slots to your subspace inventory");
        forModifier(JewelryModifiers.MAGNETIC, "Magnetic", "Magnetic Personality!", "You attract nearby items?!");
        forModifier(JewelryModifiers.MAINTAINED, "Maintained", "Stays shiny, Longer!", "Jewelry has a higher amplification");
        forModifier(JewelryModifiers.DUCTILE, "Ductile", "Reshapes well!", "Tool is longer lasting and amplifies more");
        forModifier(JewelryModifiers.INVARIANT.getId(), "Invariant", "Couldn't care less", "Jewelry amplifies in neutral temperatures");
        forModifier(JewelryModifiers.TEMPERATE.getId(), "Temperate", "Extreme!", "Jewelry amplifies in extreme temperatures");
        forModifier(JewelryModifiers.OVERWIELD, "Overwield", "Like a slimeblade!", "Consumes overslime to boost amplification");
        forModifier(JewelryModifiers.OVERGROWTH_GEM, "Overgrowth", "Fungal!", "Tool slowly grows overslime");
        forModifier(JewelryModifiers.SKYFALL_GEM, "Skyfall", "Falling with style!", "Reduces the effect of gravity");
        forModifier(JewelryModifiers.GODSPEED_GEM, "Godspeed", "Gotta go fast!", "Increases movement, attack and mining speed");
        forModifier(JewelryModifiers.ENDERDODGING_GEM, "Enderdodging", "Agility of an enderman", "Adds a chance of dodging attacks by teleporting, with a higher chance on projectiles");
    }

    private void forMaterial(MaterialId key) {
        toEng("material.%s.%s".formatted(key.getNamespace(), key.getPath()));
    }

    private void forMaterial(MaterialId key, String name) {
        add("material.%s.%s".formatted(key.getNamespace(), key.getPath()), name);
    }

    private void forMaterial(String key, String flavour) {
        toEng(key);
        add(key+".flavor", flavour);
    }

    private void forMaterial(String key, String name, String flavour) {
        add(key, name);
        add(key+".flavor", flavour);
    }

    private void forModifier(ModifierId id, String name, String flavor, String description) {
        String key = "modifier.%s.%s".formatted(id.getNamespace(), id.getPath());
        add(key, name);
        add(key+".flavor", flavor);
        add(key+".description", description);
    }

    private void forModifier(ModifierId id, String name, String description) {
        String key = "modifier.%s.%s".formatted(id.getNamespace(), id.getPath());
        add(key, name);
        add(key+".flavor", description);
        add(key+".description", description);
    }

    private void forModifier(ModifierId id, String description) {
        String key = "modifier.%s.%s".formatted(id.getNamespace(), id.getPath());
        toEng(key);
        add(key+".flavor", description);
        add(key+".description", description);
    }

    private void toEng(String name) {
        // get the last part of the translation key to use as the english name
        String[] parts = name.split("\\.");
        String toTranslate = parts[parts.length - 1];
        this.add(name, "%s".formatted(toEnglishName(toTranslate)));
    }

    public static String toEngStr(Object internalName) {
        String[] parts = internalName.toString().split("\\.");
        String toTranslate = parts[parts.length - 1];
        return toEnglishName(toTranslate);
    }

    public static String toEnglishName(Object internalName) {
        return Arrays.stream(internalName.toString().toLowerCase(Locale.ROOT).split("_"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(" "));
    }
}
