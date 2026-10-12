package dev.ferriarnus.tinkersjewelry.tools.modifiers.gem;

import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import dev.shadowsoffire.attributeslib.api.ALObjects;
import net.minecraft.ChatFormatting;
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

public class FlyGemModifier extends AbstractGemModifier {

    private static final String FLIGHT = "description.tinkersjewelry.flight";
    private boolean wasGround = true;

    @Override
    public void curioTick(IToolStackView tool, ModifierEntry entry, String slotId, int slotIndex, LivingEntity wearer, ItemStack stack) {
        if (!(wearer instanceof Player player)) {
            return;
        }

        if (player.getAbilities().flying && wasGround) {
            wasGround = false;
            ToolStack toolStack = ToolStack.from(stack);
            float amp = toolStack.getStats().get(JewelryToolStats.AMPLIFICATION);
            player.removeEffectNoUpdate(ALObjects.MobEffects.FLYING.get());
            player.forceAddEffect(new MobEffectInstance(ALObjects.MobEffects.FLYING.get(), Mth.floor(100 * amp), 0, false, true), null);
            player.getAbilities().flying = true;
            player.onUpdateAbilities();
            damageTool(stack, 15, player);
        }
        else if (player.onGround()) {
            wasGround = true;
            if (!player.hasEffect(ALObjects.MobEffects.FLYING.get()) || player.getEffect(ALObjects.MobEffects.FLYING.get()).getDuration() <= 200) {
                player.addEffect(new MobEffectInstance(ALObjects.MobEffects.FLYING.get(), 400, 0, false, true));
            }
        }
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
        float amp = iToolStackView.getStats().get(JewelryToolStats.AMPLIFICATION);
        double effect = Mth.floor(100 * amp) / 20.0;
        list.add(Component.translatable(FLIGHT, String.format("%,.2f", effect)).withStyle(ChatFormatting.BLUE, ChatFormatting.ITALIC));
    }

    @Override
    public Component getDisplayName(int level) {
        // display name without the level
        return super.getDisplayName();
    }
}
