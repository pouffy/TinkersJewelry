package dev.ferriarnus.tinkersjewelry.capability;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

public class SubSpaceCapability implements INBTSerializable<CompoundTag> {

    public static final Capability<SubSpaceCapability> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    private final ItemStackHandler inventory;

    public SubSpaceCapability() {
        this.inventory = new ItemStackHandler(6*9);
    }

    @Override
    public CompoundTag serializeNBT() {
        return inventory.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        inventory.deserializeNBT(compoundTag);
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public static class Provider implements ICapabilitySerializable<CompoundTag>, INBTSerializable<CompoundTag> {

        public static final ResourceLocation IDENTIFIER = new ResourceLocation(TinkersJewelry.MODID, "subspace");
        private final SubSpaceCapability instance = new SubSpaceCapability();
        private final LazyOptional<SubSpaceCapability> optional = LazyOptional.of(() -> instance);

        public Provider() {

        }

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction direction) {
            if (capability == INSTANCE) {
                return optional.cast();
            }
            return LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            return instance.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag compoundTag) {
            instance.deserializeNBT(compoundTag);
        }
    }
}
