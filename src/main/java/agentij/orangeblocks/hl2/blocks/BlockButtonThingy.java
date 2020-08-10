package agentij.orangeblocks.hl2.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
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
    //TODO: add the specific button things
    private static final AxisAlignedBB ELEMENT = new AxisAlignedBB(-0.062, 0, -0.062, 1.062, 0.188, 1.062);
    private static final AxisAlignedBB ELEMENTa = new AxisAlignedBB(0, 0, 0, 0.062, 0.062, 0.062);
    private static final AxisAlignedBB ELEMENTb = new AxisAlignedBB(-0.125, 0.125, -0.125, -0.062, 0.25, 1.125);
    private static final AxisAlignedBB ELEMENTc = new AxisAlignedBB(-0.125, 0.125, 1.062, 1.125, 0.25, 1.125);
    private static final AxisAlignedBB ELEMENTd = new AxisAlignedBB(1.062, 0.125, -0.125, 1.125, 0.25, 1.125);
    private static final AxisAlignedBB ELEMENTe = new AxisAlignedBB(-0.125, 0.125, -0.125, 1.125, 0.25, -0.062);
    private static final AxisAlignedBB ELEMENTf = new AxisAlignedBB(0.438, 0, 1.062, 0.562, 0.312, 1.188);
    private static final AxisAlignedBB ELEMENTg = new AxisAlignedBB(1.062, 0, 0.438, 1.188, 0.312, 0.562);
    private static final AxisAlignedBB ELEMENTh = new AxisAlignedBB(0.438, 0, -0.188, 0.562, 0.312, -0.062);
    private static final AxisAlignedBB ELEMENTi = new AxisAlignedBB(-0.188, 0, 0.438, -0.062, 0.312, 0.562);
    private static final AxisAlignedBB ELEMENTj = new AxisAlignedBB(0.438, 0, 1.188, 0.562, 0.062, 1.25);
    private static final AxisAlignedBB ELEMENTk = new AxisAlignedBB(1.188, 0, 0.438, 1.25, 0.062, 0.562);
    private static final AxisAlignedBB ELEMENTl = new AxisAlignedBB(0.438, 0, -0.25, 0.562, 0.062, -0.188);
    private static final AxisAlignedBB ELEMENTm = new AxisAlignedBB(-0.25, 0, 0.438, -0.188, 0.062, 0.562);
    /**
     * AxisAlignedBBs and methods getBoundingBox, collisionRayTrace, and collisionRayTrace generated using MrCrayfish's Model Creator <a href="https://mrcrayfish.com/tools?id=mc">https://mrcrayfish.com/tools?id=mc</a>
     */
    private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(ELEMENT, ELEMENTa, ELEMENTb, ELEMENTc, ELEMENTd, ELEMENTe, ELEMENTf, ELEMENTg, ELEMENTh, ELEMENTi, ELEMENTj, ELEMENTk, ELEMENTl, ELEMENTm);
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(-0.25, 0, -0.25, 1.25, 0.312, 1.25);

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState)
    {
        entityBox = entityBox.offset(-pos.getX(), -pos.getY(), -pos.getZ());
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
        return resultClosest == null ? null : new RayTraceResult(RayTraceResult.Type.BLOCK, resultClosest.hitVec.addVector(pos.getX(), pos.getY(), pos.getZ()), resultClosest.sideHit, pos);
    }
    public BlockButtonThingy(String name) {
        super(name, Material.IRON, CreativeTabs.REDSTONE);
        setSoundType(SoundType.STONE);
        setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));
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
        return this.getDefaultState().withProperty(POWERED, Boolean.valueOf(meta == 1));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Boolean)state.getValue(POWERED)).booleanValue() ? 1 : 0;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {POWERED});
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
}
