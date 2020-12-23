package agentij.orangeblocks.hl2.util.handlers.portalsystemmultiplayer;

import agentij.orangeblocks.hl2.util.handlers.CapabiliteHandler;
import agentij.orangeblocks.hl2.util.interfaces.IPortalSystem;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PortalSystemHandler implements ICapabilitySerializable<NBTBase> {

    private final IPortalSystem instance = CapabiliteHandler.PORTAL_SYSTEM_CAPABILITY.getDefaultInstance();



    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabiliteHandler.PORTAL_SYSTEM_CAPABILITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabiliteHandler.PORTAL_SYSTEM_CAPABILITY ? CapabiliteHandler.PORTAL_SYSTEM_CAPABILITY.cast(instance) : null;
    }


    @Override
    public NBTBase serializeNBT() {
        return CapabiliteHandler.PORTAL_SYSTEM_CAPABILITY.writeNBT(instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CapabiliteHandler.PORTAL_SYSTEM_CAPABILITY.readNBT(instance, null, nbt);
    }

    public static class Storage implements Capability.IStorage<IPortalSystem>
    {

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IPortalSystem> capability, IPortalSystem instance, EnumFacing side) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setBoolean("OrangePortal", instance.HasOrangePortal());
            compound.setBoolean("BluePortal", instance.hasBluePortal());
            return compound;
        }

        @Override
        public void readNBT(Capability<IPortalSystem> capability, IPortalSystem instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound compound = (NBTTagCompound) nbt;
            instance.setHasOrangePortal(compound.getBoolean("OrangePortal"));
            instance.setHasBluePortal(compound.getBoolean("BluePortal"));
        }
    }
}
