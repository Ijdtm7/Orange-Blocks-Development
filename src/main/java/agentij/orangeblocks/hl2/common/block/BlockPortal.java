package agentij.orangeblocks.hl2.common.block;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.blocks.BlockDecorativeNonPortable;
import agentij.orangeblocks.hl2.common.tileentity.TileEntityPortal;
import agentij.orangeblocks.hl2.util.interfaces.IPortalabe;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
@SuppressWarnings("deprecation")
public class BlockPortal extends Block implements ITileEntityProvider
{
    public static final AxisAlignedBB EMPTY_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

    public BlockPortal()
    {
        super(Material.CIRCUITS);
        setHardness(-1F);
        setResistance(1000000.0F);
        setLightLevel(0.5F);
        setRegistryName("portal");
        setUnlocalizedName("blockportal");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityPortal();
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return EMPTY_AABB;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return null;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileEntityPortal)
        {
            TileEntityPortal portal = (TileEntityPortal)te;
            if(portal.setup)
            {
                if(portal.face.getAxis() == EnumFacing.Axis.Y)
                {
                    if(!world.isSideSolid(pos.offset(portal.face, -1), portal.face))
                    {
                        String uuid = portal.uuid;
                        Main.eventHandlerServer.getSaveData(world).kill(world, portal.orange, uuid);
                        world.setBlockToAir(pos);
                    }
                }
                else
                {
                    BlockPos other = portal.top ? pos.down() : pos.up();
                    if(!(world.isSideSolid(pos.offset(portal.face, -1), portal.face) && world.isSideSolid(other.offset(portal.face, -1), portal.face)) || world.getBlockState(other).getBlock() != this)
                    {
                        String uuid = portal.uuid;
                        Main.eventHandlerServer.getSaveData(world).kill(world, portal.orange, uuid);
                        world.setBlockToAir(pos);
                    }
                }
            }
        }
        else
        {
            world.setBlockToAir(pos);
        }
    }

    public static boolean canPlace(World world, BlockPos pos, EnumFacing sideHit, boolean isOrange)
    {
        Main.logger.info(pos.getX() + " " + pos.getY() + " "+ pos.getZ());
        BlockPos blockPos = new BlockPos(pos);
        int index = sideHit.getIndex();
        Main.logger.info(index);
        if (index == 0)
        {
          blockPos=blockPos.up();
        }else if (index==1)
        {
         blockPos=blockPos.down();
        }else if (index==2)
        {
         blockPos=blockPos.south();
        }else if (index==3)
        {
           blockPos=blockPos.north();
        }else if (index==4)
        {
          blockPos=  blockPos.east();
        }else if (index ==5)
        {
           blockPos= blockPos.west();
        }
        Main.logger.info(blockPos.getX() + " " + blockPos.getY() + " "+ blockPos.getZ());
        Block block = world.getBlockState(blockPos).getBlock();
        Main.logger.info(block.getUnlocalizedName());
        if (block instanceof IPortalabe)
        {
            Main.logger.info("detected");
            if (((IPortalabe) block).portalable())
            {

            }else{
                return false;
            }

        }
        if(world.getBlockState(pos).getBlock().isReplaceable(world, pos) || world.getTileEntity(pos) instanceof TileEntityPortal && ((TileEntityPortal)world.getTileEntity(pos)).setup && ((TileEntityPortal)world.getTileEntity(pos)).orange == isOrange)
        {
            if(sideHit.getAxis() == EnumFacing.Axis.Y) //1 block portal
            {
                return world.isSideSolid(pos.offset(sideHit, -1), sideHit);
            }
            else
            {
                BlockPos posDown = pos.down();
                return world.isSideSolid(pos.offset(sideHit, -1), sideHit) && (world.getBlockState(posDown).getBlock().isReplaceable(world, posDown) || world.getTileEntity(posDown) instanceof TileEntityPortal && ((TileEntityPortal)world.getTileEntity(posDown)).setup && ((TileEntityPortal)world.getTileEntity(posDown)).orange == isOrange) && world.isSideSolid(posDown.offset(sideHit, -1), sideHit);
            }
        }
        return false;
    }
}
