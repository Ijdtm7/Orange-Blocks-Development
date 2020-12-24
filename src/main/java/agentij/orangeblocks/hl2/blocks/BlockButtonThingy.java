package agentij.orangeblocks.hl2.blocks;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.init.BlockInit;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockButtonThingy extends BlockBase{
    public static PropertyBool POWERED = PropertyBool.create("powered");
    public static PropertyBool CUBE = PropertyBool.create("cube");
    public static PropertyInteger CUBETYPE = PropertyInteger.create("cubetype", 0, 2);
    //TODO: add the specific button things
    private static final AxisAlignedBB ELEMENTa = new AxisAlignedBB(-0.25, 0, -0.312, 1.188, 0.188, 1.375);
    private static final AxisAlignedBB ELEMENTb = new AxisAlignedBB(0, 0, 0, 0.062, 0.062, 0.062);
    private static final AxisAlignedBB ELEMENTc = new AxisAlignedBB(-0.312, 0.125, -0.312, -0.25, 0.25, 1.438);
    private static final AxisAlignedBB ELEMENTd = new AxisAlignedBB(-0.312, 0.125, 1.375, 1.25, 0.25, 1.438);
    private static final AxisAlignedBB ELEMENTe = new AxisAlignedBB(1.188, 0.125, -0.375, 1.25, 0.25, 1.438);
    private static final AxisAlignedBB ELEMENTf = new AxisAlignedBB(-0.312, 0.125, -0.375, 1.25, 0.25, -0.312);
    private static final AxisAlignedBB ELEMENTg = new AxisAlignedBB(0.438, 0, 1.375, 0.562, 0.312, 1.5);
    private static final AxisAlignedBB ELEMENTh = new AxisAlignedBB(1.188, 0, 0.438, 1.312, 0.312, 0.562);
    private static final AxisAlignedBB ELEMENTi = new AxisAlignedBB(0.438, 0, -0.438, 0.562, 0.312, -0.312);
    private static final AxisAlignedBB ELEMENTj = new AxisAlignedBB(-0.375, 0, 0.438, -0.25, 0.312, 0.562);
    private static final AxisAlignedBB ELEMENTk = new AxisAlignedBB(0.438, 0, 1.5, 0.562, 0.062, 1.562);
    private static final AxisAlignedBB ELEMENTl = new AxisAlignedBB(1.312, 0, 0.438, 1.375, 0.062, 0.562);
    private static final AxisAlignedBB ELEMENTm = new AxisAlignedBB(0.438, 0, -0.5, 0.562, 0.062, -0.438);
    private static final AxisAlignedBB ELEMENT = new AxisAlignedBB(-0.438, 0, 0.438, -0.375, 0.062, 0.562);
    private static final AxisAlignedBB ELEMENTaa = new AxisAlignedBB(-0.25, 0, -0.312, 1.188, 0.188, 1.375);
    private static final AxisAlignedBB ELEMENTab = new AxisAlignedBB(0, 0, 0, 0.062, 0.062, 0.062);
    private static final AxisAlignedBB ELEMENTac = new AxisAlignedBB(-0.312, 0.125, -0.312, -0.25, 0.25, 1.438);
    private static final AxisAlignedBB ELEMENTad = new AxisAlignedBB(-0.312, 0.125, 1.375, 1.25, 0.25, 1.438);
    private static final AxisAlignedBB ELEMENTae = new AxisAlignedBB(1.188, 0.125, -0.375, 1.25, 0.25, 1.438);
    private static final AxisAlignedBB ELEMENTaf = new AxisAlignedBB(-0.312, 0.125, -0.375, 1.25, 0.25, -0.312);
    private static final AxisAlignedBB ELEMENTag = new AxisAlignedBB(0.438, 0, 1.375, 0.562, 0.312, 1.5);
    private static final AxisAlignedBB ELEMENTah = new AxisAlignedBB(1.188, 0, 0.438, 1.312, 0.312, 0.562);
    private static final AxisAlignedBB ELEMENTai = new AxisAlignedBB(0.438, 0, -0.438, 0.562, 0.312, -0.312);
    private static final AxisAlignedBB ELEMENTaj = new AxisAlignedBB(-0.375, 0, 0.438, -0.25, 0.312, 0.562);
    private static final AxisAlignedBB ELEMENTak = new AxisAlignedBB(0.438, 0, 1.5, 0.562, 0.062, 1.562);
    private static final AxisAlignedBB ELEMENTal = new AxisAlignedBB(1.312, 0, 0.438, 1.375, 0.062, 0.562);
    private static final AxisAlignedBB ELEMENTam = new AxisAlignedBB(0.438, 0, -0.5, 0.562, 0.062, -0.438);
    private static final AxisAlignedBB ELEMENTan = new AxisAlignedBB(-0.438, 0, 0.438, -0.375, 0.062, 0.562);
    private static final AxisAlignedBB ELEMENTao = new AxisAlignedBB(0.156, 0.344, 0.156, 0.844, 1.031, 0.844);
    private static final AxisAlignedBB ELEMENTp = new AxisAlignedBB(0.406, 0.594, 0.125, 0.594, 0.781, 0.188);
    private static final AxisAlignedBB ELEMENTq = new AxisAlignedBB(0.406, 0.594, 0.812, 0.594, 0.781, 0.875);
    private static final AxisAlignedBB ELEMENTr = new AxisAlignedBB(0.812, 0.594, 0.406, 0.875, 0.781, 0.594);
    private static final AxisAlignedBB ELEMENTs = new AxisAlignedBB(0.406, 0.312, 0.406, 0.594, 0.375, 0.594);
    private static final AxisAlignedBB ELEMENTt = new AxisAlignedBB(0.125, 0.594, 0.406, 0.188, 0.781, 0.594);
    private static final AxisAlignedBB ELEMENTu = new AxisAlignedBB(0.406, 1, 0.406, 0.594, 1.062, 0.594);
    private static final AxisAlignedBB ELEMENTv = new AxisAlignedBB(0, 0.188, 0, 0.312, 0.5, 0.312);
    private static final AxisAlignedBB ELEMENTw = new AxisAlignedBB(0, 0.875, 0, 0.312, 1.188, 0.312);
    private static final AxisAlignedBB ELEMENTx = new AxisAlignedBB(0, 0.188, 0.688, 0.312, 0.5, 1);
    private static final AxisAlignedBB ELEMENTy= new AxisAlignedBB(0, 0.875, 0.688, 0.312, 1.188, 1);
    private static final AxisAlignedBB ELEMENTz = new AxisAlignedBB(0.688, 0.188, 0, 1, 0.5, 0.312);
    private static final AxisAlignedBB ELEMENTba = new AxisAlignedBB(0.688, 0.875, 0, 1, 1.188, 0.312);
    private static final AxisAlignedBB ELEMENTbb = new AxisAlignedBB(0.688, 0.188, 0.688, 1, 0.5, 1);
    private static final AxisAlignedBB ELEMENTbc = new AxisAlignedBB(0.688, 0.875, 0.688, 1, 1.188, 1);
    private static final AxisAlignedBB ELEMENTbd = new AxisAlignedBB(0.344, 0.25, 0.062, 0.656, 0.438, 0.25);
    private static final AxisAlignedBB ELEMENTbe = new AxisAlignedBB(0.344, 0.25, 0.75, 0.656, 0.438, 0.938);
    private static final AxisAlignedBB ELEMENTbf = new AxisAlignedBB(0.062, 0.531, 0.062, 0.25, 0.844, 0.25);
    private static final AxisAlignedBB ELEMENTbg = new AxisAlignedBB(0.062, 0.25, 0.344, 0.25, 0.438, 0.656);
    private static final AxisAlignedBB ELEMENTbh = new AxisAlignedBB(0.75, 0.531, 0.75, 0.938, 0.844, 0.938);
    private static final AxisAlignedBB ELEMENTbi = new AxisAlignedBB(0.75, 0.938, 0.344, 0.938, 1.125, 0.656);
    private static final AxisAlignedBB ELEMENTbj = new AxisAlignedBB(0.344, 0.938, 0.062, 0.656, 1.125, 0.25);
    private static final AxisAlignedBB ELEMENTbl = new AxisAlignedBB(0.344, 0.938, 0.75, 0.656, 1.125, 0.938);
    private static final AxisAlignedBB ELEMENTbm = new AxisAlignedBB(0.75, 0.531, 0.062, 0.938, 0.844, 0.25);
    private static final AxisAlignedBB ELEMENTbn = new AxisAlignedBB(0.75, 0.25, 0.344, 0.938, 0.438, 0.656);
    private static final AxisAlignedBB ELEMENTbq = new AxisAlignedBB(0.062, 0.531, 0.75, 0.25, 0.844, 0.938);
    private static final AxisAlignedBB ELEMENTbr = new AxisAlignedBB(0.062, 0.938, 0.344, 0.25, 1.125, 0.656);
    /**
     * AxisAlignedBBs and methods getBoundingBox, addCollisonBoxToList, and collisionRayTrace generated using MrCrayfish's Model Creator <a href="https://mrcrayfish.com/tools?id=mc">https://mrcrayfish.com/tools?id=mc</a>
     */
    private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(ELEMENT, ELEMENTa, ELEMENTb, ELEMENTc, ELEMENTd, ELEMENTe, ELEMENTf, ELEMENTg, ELEMENTh, ELEMENTi, ELEMENTj, ELEMENTk, ELEMENTl, ELEMENTm);
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(-0.438, 0, -0.5, 1.375, 0.312, 1.562);
    private static final List<AxisAlignedBB> COLLISION_BOXESCUBE = Lists.newArrayList(ELEMENTaa, ELEMENTab, ELEMENTac, ELEMENTad, ELEMENTae, ELEMENTaf, ELEMENTag, ELEMENTah, ELEMENTai, ELEMENTaj, ELEMENTak, ELEMENTal, ELEMENTam, ELEMENTan, ELEMENTao, ELEMENTp, ELEMENTq, ELEMENTr, ELEMENTs, ELEMENTt, ELEMENTu, ELEMENTv, ELEMENTw, ELEMENTx, ELEMENTy, ELEMENTz, ELEMENTba, ELEMENTbb, ELEMENTbc, ELEMENTbd, ELEMENTbe, ELEMENTbf, ELEMENTbg, ELEMENTbh, ELEMENTbi, ELEMENTbj, ELEMENTbl, ELEMENTbm, ELEMENTbn, ELEMENTbq, ELEMENTbr);
    private static final AxisAlignedBB BOUNDING_BOXCUBE = new AxisAlignedBB(-0.438, 0, -0.5, 1.375, 1.188, 1.562);

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        boolean cube = state.getValue(CUBE);
        if (cube) return BOUNDING_BOXCUBE;
        return BOUNDING_BOX;

    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState)
    {
        entityBox = entityBox.offset(-pos.getX(), -pos.getY(), -pos.getZ());
        boolean cube = state.getValue(CUBE);
        if (cube) {
            for (AxisAlignedBB boxcube : COLLISION_BOXESCUBE)
            {
                if (entityBox.intersects(boxcube))
                    collidingBoxes.add(boxcube.offset(pos));
            }
            return;
        }
        for (AxisAlignedBB box : COLLISION_BOXES)
        {
            if (entityBox.intersects(box))
                collidingBoxes.add(box.offset(pos));
        }
    }

    @Override
    @Nullable
    public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end)
    {
        double distanceSq;
        double distanceSqShortest = Double.POSITIVE_INFINITY;
        RayTraceResult resultClosest = null;
        RayTraceResult result;
        start = start.subtract(pos.getX(), pos.getY(), pos.getZ());
        end = end.subtract(pos.getX(), pos.getY(), pos.getZ());
        boolean cube = state.getValue(CUBE);
        if (cube)
        {
            for (AxisAlignedBB box : COLLISION_BOXESCUBE)
            {
                result = box.calculateIntercept(start, end);
                if (result == null)
                    continue;

                distanceSq = result.hitVec.squareDistanceTo(start);
                if (distanceSq < distanceSqShortest)
                {
                    distanceSqShortest = distanceSq;
                    resultClosest = result;
                }
            }
        }else{
            for (AxisAlignedBB box : COLLISION_BOXES)
            {
                result = box.calculateIntercept(start, end);
                if (result == null)
                    continue;

                distanceSq = result.hitVec.squareDistanceTo(start);
                if (distanceSq < distanceSqShortest)
                {
                    distanceSqShortest = distanceSq;
                    resultClosest = result;
                }
            }
        }

        return resultClosest == null ? null : new RayTraceResult(RayTraceResult.Type.BLOCK, resultClosest.hitVec.addVector(pos.getX(), pos.getY(), pos.getZ()), resultClosest.sideHit, pos);
    }
    public BlockButtonThingy(String name) {
        super(name, Material.IRON, Main.portalBasicTab);
        setSoundType(SoundType.STONE);
        setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE).withProperty(CUBE, false).withProperty(CUBETYPE, 0));
    }

    protected int getRedstoneStrength(IBlockState state)
    {
        return ((Boolean)state.getValue(POWERED)).booleanValue() ? 15 : 0;
    }

    protected IBlockState setRedstoneStrength(IBlockState state, int strength)
    {
        return state.withProperty(POWERED, Boolean.valueOf(strength > 0));
    }
    protected void playClickOnSound(World worldIn, BlockPos color)
    {
        worldIn.playSound((EntityPlayer)null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }
    protected void playClickOffSound(World worldIn, BlockPos color)
    {
        worldIn.playSound((EntityPlayer)null, color, SoundEvents.BLOCK_STONE_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    protected int computeRedstoneStrength(World worldIn, BlockPos pos)
    {
        IBlockState state = worldIn.getBlockState(pos);
        if (!state.getValue(CUBE))
        {
            AxisAlignedBB axisalignedbb = BOUNDING_BOX.offset(pos);
            List<? extends Entity> list;




            list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb);



            if (!list.isEmpty())
            {
                for (Entity entity : list)
                {
                    if (!entity.doesEntityNotTriggerPressurePlate())
                    {
                        return 15;
                    }
                }
            }

            return 0;
        }
        return 15;

    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    public IBlockState getStateFromMeta(int meta)
    {
        if (meta < 2)
        {
            return this.getDefaultState().withProperty(POWERED, Boolean.valueOf(meta == 1));
        } else if (meta <5)
        {
            int powered = meta-2;
            return this.getDefaultState().withProperty(POWERED, Boolean.valueOf(powered == 1)).withProperty(CUBE, true);
        }
        int powered = meta - 5;
        int CUBETYPEE = meta - 3;
        return this.getDefaultState().withProperty(POWERED, Boolean.valueOf(powered == 1)).withProperty(CUBE, true).withProperty(CUBETYPE, CUBETYPEE);

    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        boolean cube = state.getValue(CUBE);
        int type = state.getValue(CUBETYPE);
        if (cube) return state.getValue(POWERED).booleanValue() ? 3+type : 2+type;
        return ((Boolean)state.getValue(POWERED)).booleanValue() ? 1 : 0;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, POWERED, CUBE, CUBETYPE);
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canSpawnInBlock() {
        return true;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return this.canBePlacedOn(worldIn, pos.down());
    }
    private boolean canBePlacedOn(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).isTopSolid() || worldIn.getBlockState(pos).getBlock() instanceof BlockFence;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {

    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote)
        {
            int i = this.getRedstoneStrength(state);

            if (i > 0)
            {
                this.updateState(worldIn, pos, state, i);
            }
        }
    }
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (!worldIn.isRemote)
        {
            int i = this.getRedstoneStrength(state);

            if (i == 0)
            {
                this.updateState(worldIn, pos, state, i);
            }

            if (entityIn instanceof EntityItem)
            {
                EntityItem item = (EntityItem)entityIn;
                ItemStack stack = item.getItem();
                Block block = Block.getBlockFromItem(stack.getItem());
                int meta = stack.getItemDamage();
                if (block instanceof BlockStorageCube)
                {
                    BlockStorageCube storageCube = (BlockStorageCube) block;
                    int x= pos.getX();
                    int y= pos.getY();
                    int z= pos.getZ();
                    BlockPos pos1 = new BlockPos(x,y+1,z);
                    IBlockState state2 = storageCube.getStateFromMeta(meta);
                    if (!state.getValue(CUBE))
                    {
                        int cubecurrenttype = state2.getValue(BlockStorageCube.CUBES);
                        worldIn.setBlockState(pos,state.withProperty(CUBETYPE, cubecurrenttype).withProperty(CUBE, true).withProperty(POWERED, true));
                        item.setDead();
                    }
                }
            }
        }
    }
    protected void updateState(World worldIn, BlockPos pos, IBlockState state, int oldRedstoneStrength)
    {
        int i = this.computeRedstoneStrength(worldIn, pos);
        boolean flag = oldRedstoneStrength > 0;
        boolean flag1 = i > 0;

        if (oldRedstoneStrength != i)
        {
            state = this.setRedstoneStrength(state, i);
            worldIn.setBlockState(pos, state, 2);
            this.updateNeighbors(worldIn, pos);
            worldIn.markBlockRangeForRenderUpdate(pos, pos);
        }

        if (!flag1 && flag)
        {
            this.playClickOffSound(worldIn, pos);
        }
        else if (flag1 && !flag)
        {
            this.playClickOnSound(worldIn, pos);
        }

        if (flag1)
        {
            worldIn.scheduleUpdate(new BlockPos(pos), this, this.tickRate(worldIn));
        }
    }
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (this.getRedstoneStrength(state) > 0)
        {
            this.updateNeighbors(worldIn, pos);
        }

        super.breakBlock(worldIn, pos, state);
    }
    protected void updateNeighbors(World worldIn, BlockPos pos)
    {
        worldIn.notifyNeighborsOfStateChange(pos, this, false);
        worldIn.notifyNeighborsOfStateChange(pos.down(), this, false);
    }
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return this.getRedstoneStrength(blockState);
    }

    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return side == EnumFacing.UP ? this.getRedstoneStrength(blockState) : 0;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.DESTROY;
    }
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote)
        {
            ItemStack stack = playerIn.getHeldItem(hand);
            Item item = stack.getItem();
            int meta = item.getDamage(stack);
            Block block = Block.getBlockFromItem(item);
            if(!playerIn.isSneaking() && block instanceof BlockStorageCube && !state.getValue(CUBE))
            {
                worldIn.setBlockState(pos, state.withProperty(CUBE, true).withProperty(CUBETYPE, meta).withProperty(POWERED, true));
                stack.shrink(1);
            }
            if (!playerIn.isSneaking() && state.getValue(CUBE))
            {
                if (!worldIn.isRemote)
                {
                    playerIn.inventory.addItemStackToInventory(new ItemStack(BlockInit.STORAGECUBE,1,state.getValue(CUBETYPE)));
                    worldIn.setBlockState(pos, state.withProperty(CUBE, false).withProperty(CUBETYPE, 0).withProperty(POWERED, false));
                }
            }
        }



        return true;
    }



    /*@Override
    public boolean hasTileEntity(IBlockState state) {
        return state.getValue(CUBE);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return super.createTileEntity(world, state);
    }*/

    //addcubefalling
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        int x= pos.getX();
        int y= pos.getY();
        int z= pos.getZ();
        BlockPos pos1 = new BlockPos(x,y+1,z);
        IBlockState state1 = worldIn.getBlockState(pos1);
        if (state1.getBlock() == BlockInit.STORAGECUBE && !state.getValue(CUBE))
        {
            int cubecurrenttype = state1.getValue(BlockStorageCube.CUBES);
            worldIn.setBlockState(pos,state.withProperty(CUBETYPE, cubecurrenttype).withProperty(CUBE, true).withProperty(POWERED, true));
            worldIn.setBlockState(pos1, Blocks.AIR.getDefaultState());
        }
    }



}
