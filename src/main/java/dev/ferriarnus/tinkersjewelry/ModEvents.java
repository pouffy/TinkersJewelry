package dev.ferriarnus.tinkersjewelry;

import dev.ferriarnus.tinkersjewelry.capability.SubSpaceCapability;
import dev.ferriarnus.tinkersjewelry.tools.modules.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.tools.definition.module.ToolModule;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TinkersJewelry.MODID)
public class ModEvents {

    @SubscribeEvent
    static void register(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {
            ToolModule.LOADER.register(TinkersJewelry.getResource("overslime_debuf"), OverSlimeDebufModule.LOADER);

            ModifierModule.LOADER.register(TinkersJewelry.getResource("overgrowth"), OvergrowthCurioModule.LOADER);
            ModifierModule.LOADER.register(TinkersJewelry.getResource("teleport_dodge"), TeleportDodgeCurioModule.LOADER);
            ModifierModule.LOADER.register(TinkersJewelry.getResource("attribute_modifier"), AttributeModifierModule.LOADER);
            ModifierModule.LOADER.register(TinkersJewelry.getResource("hurt_enemy"), HurtEnemyModule.LOADER);
            ModifierModule.LOADER.register(TinkersJewelry.getResource("hurt_user"), HurtUserModule.LOADER);
            ModifierModule.LOADER.register(TinkersJewelry.getResource("block_break"), BlockBreakModule.LOADER);
            ModifierModule.LOADER.register(TinkersJewelry.getResource("enemy_effect"), EnemyEffectModule.LOADER);
            ModifierModule.LOADER.register(TinkersJewelry.getResource("self_effect"), SelfEffectModule.LOADER);
        }
    }

    @SubscribeEvent
    public static void registerCap(RegisterCapabilitiesEvent event) {
        event.register(SubSpaceCapability.class);
    }
}
