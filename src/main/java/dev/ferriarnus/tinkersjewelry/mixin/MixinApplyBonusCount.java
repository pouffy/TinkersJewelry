package dev.ferriarnus.tinkersjewelry.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.ferriarnus.tinkersjewelry.GemAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ApplyBonusCount.class)
public class MixinApplyBonusCount {

    @WrapOperation(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/item/ItemStack;)I"))
    public int applyFortune(Enchantment enchantment, ItemStack stack, Operation<Integer> original, @Local(argsOnly = true) LootContext context) {
        if (enchantment != Enchantments.BLOCK_FORTUNE || !context.hasParam(LootContextParams.THIS_ENTITY)) {
            return original.call(enchantment, stack);
        }
        Entity ent = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (ent instanceof LivingEntity livingEntity) {
            double value = livingEntity.getAttributeValue(GemAttributes.FORTUNE.get());
            int fortune = original.call(enchantment, stack);
            return (int) Math.round(fortune + value);
        }
        return original.call(enchantment, stack);
    }
}
