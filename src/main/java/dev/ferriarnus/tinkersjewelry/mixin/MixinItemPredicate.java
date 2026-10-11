package dev.ferriarnus.tinkersjewelry.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.ferriarnus.tinkersjewelry.GemAttributes;
import dev.ferriarnus.tinkersjewelry.IEntityAwarePredicate;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(ItemPredicate.class)
public class MixinItemPredicate implements IEntityAwarePredicate {

    @Nullable
    @Unique
    private LivingEntity tinkersjewelry$entity;

    @Override
    public void tinkersjewelry$setEntity(LivingEntity entity) {
        this.tinkersjewelry$entity = entity;
    }

    @WrapOperation(method = "matches", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/critereon/EnchantmentPredicate;containedIn(Ljava/util/Map;)Z"))
    public boolean matchEnchantment(EnchantmentPredicate instance, Map<Enchantment, Integer> integer, Operation<Boolean> original) {
        if (tinkersjewelry$entity == null) {
            return original.call(instance, integer);
        }
        double value = 0;
        if (instance.enchantment == Enchantments.BLOCK_FORTUNE) {
            value = tinkersjewelry$entity.getAttributeValue(GemAttributes.FORTUNE.get());
        }
        if (instance.enchantment == Enchantments.SILK_TOUCH) {
            value = tinkersjewelry$entity.getAttributeValue(GemAttributes.SILK.get());
        }
        return original.call(instance, integer) || value > 0;
    }
}
