package dev.ferriarnus.tinkersjewelry.tools.modifiers.gem;

import dev.ferriarnus.tinkersjewelry.tools.modifiers.JewelryModifiers;
import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;

public class CleanseGemModifier extends AbstractGemModifier {

    @Override
    public void curioTick(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, ItemStack stack) {
        if (wearer == null) {
            return;
        }
        List<MobEffectInstance> badEffects = wearer.getActiveEffects().stream().filter(e -> !e.getEffect().isBeneficial()).toList();
        ToolStack toolStack = ToolStack.from(stack);
        int level = toolStack.getModifierLevel(JewelryModifiers.CLEANSE_GEM.getId());
        float amp = toolStack.getStats().get(JewelryToolStats.AMPLIFICATION);
        for (MobEffectInstance effect : badEffects) {
            int duration = effect.getDuration();
            wearer.removeEffect(effect.getEffect());
            damageTool(stack, Mth.ceil(duration / (300f * level * amp)), wearer);
            if (ToolStack.from(stack).isBroken()) {
                break;
            }
        }
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
//        float amp = iToolStackView.getMultiplier(JewelryToolStats.AMPLIFICATION);
//        int effect = (int) (1 * amp);
//        list.add(addDiscription("stats.tinkersjewelry.clensegem", ""));
    }

    @Override
    public Component getDisplayName(int level) {
        // display name without the level
        return super.getDisplayName();
    }
}
