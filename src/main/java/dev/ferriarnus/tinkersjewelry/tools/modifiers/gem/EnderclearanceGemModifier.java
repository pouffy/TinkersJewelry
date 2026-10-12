package dev.ferriarnus.tinkersjewelry.tools.modifiers.gem;

import com.google.common.collect.Multimap;
import dev.ferriarnus.tinkersjewelry.tools.modifiers.JewelryModifiers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.events.teleport.EnderclearanceTeleportEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.utils.TeleportHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EnderclearanceGemModifier extends AbstractGemModifier {

    @Override
    public void hurtUser(ItemStack stack, DamageSource source, double damage, @Nullable LivingEntity defender, @Nullable Entity attacker, ModifierEntry modifier) {
        if (!source.isIndirect()) {
            if (attacker instanceof LivingEntity entity) {
                ToolStack tool = ToolStack.from(stack);
                int level = tool.getModifierLevel(JewelryModifiers.ENDERCLEARANCE_GEM.getId());

                if (TConstruct.RANDOM.nextFloat() < (float)level * 0.25F) {
                    TeleportHelper.randomNearbyTeleport(entity, EnderclearanceTeleportEvent.TELEPORT_FACTORY);
                    damageTool(stack, 1, defender);
                }
            }
        }
    }

    @Override
    public void addTooltip(IToolStackView iToolStackView, ModifierEntry modifierEntry, @Nullable Player player, List<Component> list, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {

    }
}
