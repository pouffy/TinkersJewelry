package dev.ferriarnus.tinkersjewelry.tools.modules;

import dev.ferriarnus.tinkersjewelry.tools.hooks.CurioModifyDamageHook;
import dev.ferriarnus.tinkersjewelry.tools.hooks.CuriosModifierHooks;
import dev.ferriarnus.tinkersjewelry.tools.stats.JewelryToolStats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.predicate.IJsonPredicate;
import slimeknights.mantle.data.predicate.damage.DamageSourcePredicate;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.events.teleport.EnderdodgingTeleportEvent;
import slimeknights.tconstruct.library.json.LevelingInt;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.util.ModifierCondition;
import slimeknights.tconstruct.library.modifiers.modules.util.ModuleBuilder;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.TeleportHelper;
import slimeknights.tconstruct.shared.TinkerEffects;

import java.util.List;

public record TeleportDodgeCurioModule(IJsonPredicate<LivingEntity> defender, IJsonPredicate<DamageSource> damageSource, LevelingValue chance, LevelingInt cooldown, ModifierCondition<IToolStackView> condition) implements ModifierModule, CurioModifyDamageHook, ModifierCondition.ConditionalModule<IToolStackView> {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS;
    public static final RecordLoadable<TeleportDodgeCurioModule> LOADER;

    public RecordLoadable<? extends ModifierModule> getLoader() {
        return LOADER;
    }

    public List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public float modifyDamageTaken(IToolStackView tool, ItemStack stack, ModifierEntry modifier, EquipmentContext context, String slotId, int slotIndex, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity entity = context.getEntity();
        if (!entity.hasEffect(TinkerEffects.enderference.get()) && this.defender.matches(entity) && this.damageSource.matches(source)) {
            float level = modifier.getEffectiveLevel();
            float amp = tool.getStats().get(JewelryToolStats.AMPLIFICATION);
            float chance = this.chance.compute(level) * amp;
            if (entity.getRandom().nextFloat() < chance && TeleportHelper.randomNearbyTeleport(context.getEntity(), (e, x, y, z) -> new EnderdodgingTeleportEvent(e, x, y, z, modifier))) {
                int cooldown = this.cooldown.compute(level);
                if (cooldown > 0) {
                    entity.addEffect(new MobEffectInstance(TinkerEffects.enderference.get(), cooldown));
                }
                ToolDamageUtil.damage(tool, (int)amount, entity, stack);
                return 0.0F;
            }
        }

        return amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    static {
        DEFAULT_HOOKS = HookProvider.defaultHooks(CuriosModifierHooks.MODIFY_HURT);
        LOADER = RecordLoadable.create(
                LivingEntityPredicate.LOADER.defaultField("defender", TeleportDodgeCurioModule::defender),
                DamageSourcePredicate.LOADER.defaultField("damage_source", TeleportDodgeCurioModule::damageSource),
                LevelingValue.LOADABLE.requiredField("chance", TeleportDodgeCurioModule::chance),
                LevelingInt.LOADABLE.requiredField("cooldown", TeleportDodgeCurioModule::cooldown),
                ModifierCondition.TOOL_FIELD, TeleportDodgeCurioModule::new);
    }

    public static class Builder extends ModuleBuilder.Stack<Builder> implements LevelingInt.Builder<TeleportDodgeCurioModule> {
        private IJsonPredicate<LivingEntity> defender;
        private IJsonPredicate<DamageSource> damageSource;
        private LevelingValue chance;

        private Builder() {
            this.defender = LivingEntityPredicate.ANY;
            this.damageSource = DamageSourcePredicate.ANY;
            this.chance = LevelingValue.ONE;
        }

        public TeleportDodgeCurioModule amount(int flat, int eachLevel) {
            return new TeleportDodgeCurioModule(this.defender, this.damageSource, this.chance, new LevelingInt(flat, eachLevel), this.condition);
        }

        public Builder defender(IJsonPredicate<LivingEntity> defender) {
            this.defender = defender;
            return this;
        }

        public Builder damageSource(IJsonPredicate<DamageSource> damageSource) {
            this.damageSource = damageSource;
            return this;
        }

        public Builder chance(LevelingValue chance) {
            this.chance = chance;
            return this;
        }
    }
}
