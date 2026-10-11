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
            ToolModule.LOADER.register(new ResourceLocation(TinkersJewelry.MODID, "overslime_debuf"), OverSlimeDebufModule.LOADER);

            ModifierModule.LOADER.register(new ResourceLocation(TinkersJewelry.MODID, "attribute_modifier"), AttributeModifierModule.LOADER);
            ModifierModule.LOADER.register(new ResourceLocation(TinkersJewelry.MODID, "hurt_enemy"), HurtEnemyModule.LOADER);
            ModifierModule.LOADER.register(new ResourceLocation(TinkersJewelry.MODID, "hurt_user"), HurtUserModule.LOADER);
            ModifierModule.LOADER.register(new ResourceLocation(TinkersJewelry.MODID, "block_break"), BlockBreakModule.LOADER);
            ModifierModule.LOADER.register(new ResourceLocation(TinkersJewelry.MODID, "enemy_effect"), EnemyEffectModule.LOADER);
            ModifierModule.LOADER.register(new ResourceLocation(TinkersJewelry.MODID, "self_effect"), SelfEffectModule.LOADER);
        }
    }

    @SubscribeEvent
    public static void registerCap(RegisterCapabilitiesEvent event) {
        event.register(SubSpaceCapability.class);
    }
}
