package dev.ferriarnus.tinkersjewelry.data;

import dev.ferriarnus.tinkersjewelry.GemAttributes;
import dev.ferriarnus.tinkersjewelry.tools.modifiers.JewelryModifiers;
import dev.ferriarnus.tinkersjewelry.tools.modules.*;
import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.variable.tool.ModDataSource;
import slimeknights.tconstruct.library.json.variable.tool.ModDataVariable;
import slimeknights.tconstruct.library.json.variable.tool.ToolStatVariable;
import slimeknights.tconstruct.library.json.variable.tool.ToolVariable;
import slimeknights.tconstruct.library.modifiers.modules.behavior.ConditionalStatModule;
import slimeknights.tconstruct.library.modifiers.modules.build.ModifierRequirementsModule;
import slimeknights.tconstruct.library.modifiers.modules.build.ModifierSlotModule;
import slimeknights.tconstruct.library.modifiers.modules.build.StatBoostModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.modifiers.util.ModifierTooltip;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.shared.TinkerEffects;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

import static slimeknights.tconstruct.library.json.math.ModifierFormula.*;

public class JewelryModifierProvider extends AbstractModifierProvider {

    public JewelryModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        ModifierSlotModule UPGRADE = ModifierSlotModule.slot(SlotType.UPGRADE).eachLevel(1);
        ModifierSlotModule ABILITY = ModifierSlotModule.slot(SlotType.ABILITY).eachLevel(1);

        buildModifier(JewelryModifiers.PRECIOUS)
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(ABILITY);

        buildModifier(JewelryModifiers.POLISH)
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(UPGRADE);

        buildModifier(JewelryModifiers.UNDYING)
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(ModifierRequirementsModule.builder()
                        .requireModifier(ModifierIds.soulbound, 1)
                        .modifierKey(JewelryModifiers.UNDYING)
                        .build());

        buildModifier(JewelryModifiers.ANTI_HEAL_GEM)
                .addModule(EnemyEffectModule.builder(ALObjects.MobEffects.GRIEVOUS.get())
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(50).variable(LEVEL).multiply()
                        .constant(50).add()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.ARMOR_GEM)
                .addModule(AttributeModifierModule.builder(Attributes.ARMOR, AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.ARMOR_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(0.5f).multiply()
                        .customVariable("amplification").multiply()
                        .constant(1.5f).add().build())
                .addModule(HurtUserModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.CONDUIT_GEM)
                .addModule(SelfEffectModule.builder(MobEffects.CONDUIT_POWER)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(200).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.DARKNESS_GEM)
                .addModule(EnemyEffectModule.builder(MobEffects.DARKNESS)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(200).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.CURRENT_HEALTH_DAMAGE_GEM)
                .addModule(AttributeModifierModule.builder(ALObjects.Attributes.CURRENT_HP_DAMAGE.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.CURRENT_HEALTH_DAMAGE_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(0.1f).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.ENDERFERENCE_GEM)
                .addModule(EnemyEffectModule.builder(TinkerEffects.enderference.get())
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(100).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.EXP_GEM)
                .addModule(AttributeModifierModule.builder(ALObjects.Attributes.EXPERIENCE_GAINED.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.EXP_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(0.2f).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.FIRERESISTANCE_GEM)
                .addModule(SelfEffectModule.builder(MobEffects.FIRE_RESISTANCE)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(200).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.FLAME_GEM)
                .addModule(AttributeModifierModule.builder(ALObjects.Attributes.FIRE_DAMAGE.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.FLAME_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.FORTUNE_GEM)
                .addModule(AttributeModifierModule.builder(GemAttributes.FORTUNE.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.FORTUNE_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).customVariable("amplification").multiply().build())
                .addModule(BlockBreakModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.GLOWING_GEM)
                .addModule(EnemyEffectModule.builder(MobEffects.GLOWING)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(200).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.GRAVITY_GEM)
                .addModule(AttributeModifierModule.builder(ForgeMod.ENTITY_GRAVITY.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.GRAVITY_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(-0.01f).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtUserModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.HEALTH_GEM)
                .addModule(AttributeModifierModule.builder(Attributes.MAX_HEALTH, AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.HEALTH_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(3).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtUserModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.KNOCKBACK_GEM)
                .addModule(AttributeModifierModule.builder(Attributes.ATTACK_KNOCKBACK, AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.KNOCKBACK_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(0.1f).multiply()
                        .constant(0.5f).add()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.LIFESTEAL_GEM)
                .addModule(AttributeModifierModule.builder(ALObjects.Attributes.LIFE_STEAL.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.LIFESTEAL_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(1).subtract()
                        .constant(0.05f).multiply()
                        .constant(0.1f).add()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.LUCK_GEM)
                .addModule(AttributeModifierModule.builder(Attributes.LUCK, AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.LUCK_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).customVariable("amplification").multiply().build())
                //TODO
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.MINING_SPEED_GEM)
                .addModule(AttributeModifierModule.builder(ALObjects.Attributes.MINING_SPEED.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.MINING_SPEED_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(0.25f).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(BlockBreakModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.NIGHTVISION_GEM)
                .addModule(SelfEffectModule.builder(MobEffects.NIGHT_VISION)
                        .withDamage(f -> f.constant(5).buildFormula())
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(450).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.PIERCING_GEM)
                .addModule(AttributeModifierModule.builder(ALObjects.Attributes.ARMOR_PIERCE.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.PIERCING_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(1).subtract()
                        .constant(0.05f).multiply()
                        .constant(0.1f).add()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.POISON_GEM)
                .addModule(EnemyEffectModule.builder(MobEffects.POISON)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(100).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.RAGE_GEM)
                .addModule(AttributeModifierModule.builder(Attributes.ATTACK_SPEED, AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.RAGE_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(1).subtract()
                        .constant(0.25f).multiply()
                        .constant(1.25f).add()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.REACH_GEM)
                .addModule(AttributeModifierModule.builder(ForgeMod.ENTITY_REACH.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.REACH_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).customVariable("amplification").multiply().build())
                .addModule(AttributeModifierModule.builder(ForgeMod.BLOCK_REACH.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.REACH_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.RESISTANCE_GEM)
                .addModule(SelfEffectModule.builder(MobEffects.DAMAGE_RESISTANCE)
                        .withAmplification(f -> f.variable(LEVEL).constant(1).subtract().buildFormula())
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(200).customVariable("amplification").multiply().build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.SILK_GEM)
                .addModule(AttributeModifierModule.builder(GemAttributes.SILK.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.SILK_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).customVariable("amplification").multiply().build())
                .addModule(BlockBreakModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.SPEED_GEM)
                .addModule(AttributeModifierModule.builder(Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.SPEED_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(1).subtract()
                        .constant(0.3f).multiply()
                        .constant(1.2f).add()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.SWIM_GEM)
                .addModule(AttributeModifierModule.builder(ForgeMod.SWIM_SPEED.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.SWIM_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).customVariable("amplification").multiply().build())
                .addModule(HurtUserModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.TRADE_GEM)
                .addModule(AttributeModifierModule.builder(GemAttributes.TRADE.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.TRADE_GEM)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).constant(7).multiply()
                        .customVariable("amplification").multiply().build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.WATERBREATHING_GEM)
                .addModule(SelfEffectModule.builder(MobEffects.WATER_BREATHING)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(200).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.WITHER_GEM)
                .addModule(EnemyEffectModule.builder(MobEffects.WITHER)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(50).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .addModule(HurtEnemyModule.builder()
                        .flat(1))
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.OVERWIELD)
                .addModule(ConditionalStatModule.stat(JewelryToolStats.AMPLIFICATION)
                        .formula()
                        .customVariable("overslime", new ModDataVariable(TinkerModifiers.overslime.getId(), ModDataSource.PERSISTENT))
                        .variable(LEVEL).min() // must have 1 overslime per level
                        .constant(0.1f).multiply()
                        .variable(MULTIPLIER).multiply()
                        .variable(VALUE).add()
                        .build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);


        buildModifier(JewelryModifiers.MAGNETIC)
                .addModule(SelfEffectModule.builder(TinkerEffects.magnetic.get())
                        .withDamage(f -> f.constant(0).buildFormula())
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .constant(200).variable(LEVEL).multiply()
                        .customVariable("amplification").multiply().build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.MAINTAINED)
                .addModule(ConditionalStatModule.stat(JewelryToolStats.AMPLIFICATION)
                        .customVariable("durability", ToolVariable.CURRENT_DURABILITY)
                        .customVariable("max_durability", new ToolStatVariable(ToolStats.DURABILITY))
                        .formula()
                        .customVariable("max_durability").constant(0.5f).multiply().duplicate()
                        .customVariable("durability").subtractFlipped()
                        .nonNegative().divideFlipped()
                        .variable(LEVEL).multiply()
                        .constant(1.1f).multiply()
                        .variable(MULTIPLIER).multiply()
                        .variable(VALUE).multiply().build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);

        buildModifier(JewelryModifiers.DUCTILE)
                .addModule(StatBoostModule.multiplyBase(JewelryToolStats.AMPLIFICATION).eachLevel(0.07f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.DURABILITY).eachLevel(0.1f));

        buildModifier(JewelryModifiers.SUBSPACE)
                .addModule(AttributeModifierModule.builder(GemAttributes.SUBSPACE.get(), AttributeModifier.Operation.ADDITION)
                        .uniqueFrom(JewelryModifiers.SUBSPACE)
                        .customVariable("amplification", new ToolStatVariable(JewelryToolStats.AMPLIFICATION))
                        .formula()
                        .variable(LEVEL).build())
                .showInTooltips(ModifierTooltip.ShowInTooltips.ALWAYS);
    }

    @Override
    public String getName() {
        return "Tinkers' Jewelry Modifier Provider";
    }
}
