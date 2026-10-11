package dev.ferriarnus.tinkersjewelry.tools.modifiers.gem;

import com.google.common.collect.Multimap;
import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.common.ForgeMod;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class ReachGemModifier extends AbstractGemModifier {

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack, ModifierEntry modifier) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = super.getAttributeModifiers(slotContext, uuid, stack, modifier);
        ToolStack toolStack = ToolStack.from(stack);
        float amp = toolStack.getStats().get(JewelryToolStats.AMPLIFICATION);
        int level = toolStack.getModifierLevel(modifier.getId());
        double effect = level * amp;
        attributeModifiers.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(uuid, "tinkersjewelry:entity_reachgem", effect, AttributeModifier.Operation.ADDITION));
        attributeModifiers.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(uuid, "tinkersjewelry:block_reachgem", effect, AttributeModifier.Operation.ADDITION));
        return attributeModifiers;
    }

    @Override
    public void hurtEnemy(ItemStack stack, DamageSource source, double damage, @Nullable LivingEntity defender, @Nullable LivingEntity attacker, ModifierEntry modifier) {
        damageTool(stack, 1, attacker);
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
//        float amp = iToolStackView.getMultiplier(JewelryToolStats.AMPLIFICATION);
//        double effect = modifierEntry.getLevel() * amp;
//        list.add(addDiscription(TranslationKeys.REACH, effect));
    }
}
