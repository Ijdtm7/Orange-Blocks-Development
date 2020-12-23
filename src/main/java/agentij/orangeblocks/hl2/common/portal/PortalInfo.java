package agentij.orangeblocks.hl2.common.portal;

import agentij.orangeblocks.hl2.common.tileentity.TileEntityPortal;
import agentij.orangeblocks.hl2.util.handlers.SoundRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PortalInfo
{
    public boolean isOrange;
    public BlockPos pos;
    public String uuid;

    public PortalInfo(boolean o, BlockPos poss, String uuid)
    {
        isOrange = o;
        pos = poss;
        this.uuid = uuid;
    }

    public TileEntity kill(World world, String uuid)
    {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileEntityPortal)
        {
            TileEntityPortal portal = (TileEntityPortal)te;
            if (portal.uuid.equals(uuid))
            {
                world.setBlockToAir(pos);
            }

            if(portal.face.getAxis() != EnumFacing.Axis.Y)
            {
                BlockPos offset = portal.top ? pos.down() : pos.up();
                if(world.getTileEntity(offset) instanceof TileEntityPortal)
                {
                    if (portal.uuid.equals(uuid))
                    {
                        world.setBlockToAir(offset);
                    }

                }
            }

            world.playSound(null, pos.getX() + (portal.face.getAxis() != EnumFacing.Axis.Y ? 1D : 0.5D), pos.getY() + (portal.face.getAxis() == EnumFacing.Axis.Y ? 0.0D : 0.5D), pos.getZ() + (portal.face.getAxis() != EnumFacing.Axis.Y ? 1D : 0.5D), SoundRegistry.fizzle, SoundCategory.BLOCKS, 0.3F, 1F);
        }
        else
        {
            world.setBlockToAir(pos);
        }
        return te;
    }

    public NBTTagCompound toNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("orange", isOrange);
        tag.setLong("pos", pos.toLong());
        tag.setString("uuid", uuid);
        return tag;
    }

    public static PortalInfo createFromNBT(NBTTagCompound tag)
    {
        return new PortalInfo(tag.getBoolean("orange"), BlockPos.fromLong(tag.getLong("pos")), tag.getString("uuid"));
    }
}
