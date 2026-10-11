package dev.ferriarnus.tinkersjewelry.tools.modifiers.gem;

import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import javax.annotation.Nullable;
import java.util.List;

public class WitherGemModifier extends AbstractGemModifier{

    @Override
    public void hurtEnemy(ItemStack stack, DamageSource source, double damage, @Nullable LivingEntity defender, @Nullable LivingEntity attacker, ModifierEntry modifier) {
        if (defender == null || defender.hasEffect(MobEffects.WITHER)) {
            return;
        }
        ToolStack tool = ToolStack.from(stack);
        float amp = tool.getStats().get(JewelryToolStats.AMPLIFICATION);
        int level = tool.getModifierLevel(modifier.getId());
        defender.addEffect(new MobEffectInstance(MobEffects.WITHER, Mth.floor(50 * level * amp)));
        damageTool(stack, 1, attacker);
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
//        foltip.add(addloat amp = tool.getMultiplier(JewelryToolStats.AMPLIFICATION);
////        double effect = 1 * amp * 3;
////        toDiscription(TranslationKeys.POISON, effect+"s"));
    }
}
