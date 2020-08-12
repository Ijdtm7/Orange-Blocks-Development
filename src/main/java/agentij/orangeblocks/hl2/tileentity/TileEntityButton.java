package agentij.orangeblocks.hl2.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class TileEntityButton extends TileEntity {
    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {

        BlockPos blockPos=getPos();
        return new SPacketUpdateTileEntity(blockPos,0,getUpdateTag());
    }


    @Override
    public NBTTagCompound getUpdateTag()
    {

        NBTTagCompound supertag=super.getUpdateTag();
        writeToNBT(supertag);
        return supertag;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {

        NBTTagCompound nbtTagCompound=pkt.getNbtCompound();
        readFromNBT(nbtTagCompound);
    }


    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }

    protected NetworkRegistry.TargetPoint getOwnTargetPoint()
    {
        return new NetworkRegistry.TargetPoint(world.provider.getDimension(),getX(),getY(),getZ(), 7);
    }



    public int getX(){return getPos().getX();}
    public int getY(){return getPos().getY();}
    public int getZ(){return getPos().getZ();}
}
