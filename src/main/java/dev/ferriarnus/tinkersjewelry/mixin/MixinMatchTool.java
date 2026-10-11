package dev.ferriarnus.tinkersjewelry.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.ferriarnus.tinkersjewelry.IEntityAwarePredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MatchTool.class)
public class MixinMatchTool {

    @WrapOperation(method = "test(Lnet/minecraft/world/level/storage/loot/LootContext;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/critereon/ItemPredicate;matches(Lnet/minecraft/world/item/ItemStack;)Z"))
    public boolean matchEnchantment(ItemPredicate instance, ItemStack map, Operation<Boolean> original, @Local(argsOnly = true) LootContext context) {
        if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof LivingEntity living) {
            ((IEntityAwarePredicate) instance).tinkersjewelry$setEntity(living);
        }
        return original.call(instance, map);
    }
}
