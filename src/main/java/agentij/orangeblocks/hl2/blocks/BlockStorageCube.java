package agentij.orangeblocks.hl2.blocks;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.blocks.itemblocks.ItemBlockCube;
import agentij.orangeblocks.hl2.init.BlockInit;
import agentij.orangeblocks.hl2.init.ItemInit;
import agentij.orangeblocks.hl2.util.NBTWRITE;
import agentij.orangeblocks.hl2.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;
@SuppressWarnings("deprecation")
public class BlockStorageCube extends Block implements IHasModel {
    public static final PropertyInteger CUBES = PropertyInteger.create("type", 0,2);
    public static boolean fallInstantly;
    public BlockStorageCube(String name) {
        super(Material.IRON);
        setCreativeTab(CreativeTabs.MISC);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.METAL);
        setHardness(5F);
        setResistance(10F);
        setHarvestLevel("pickaxe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CUBES,0));

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlockCube(this).setRegistryName(this.getRegistryName()));
    }


    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (int i =0; i<3; ++i)
        {
            ItemStack stack = new ItemStack(this, 1, i);
            if (i==0)
            {
                stack.setTagCompound(new NBTWRITE(new NBTTagCompound()).setString("name", "Portal 2 Storage Cube").getResult());
                //stack.setStackDisplayName("Portal 2 Storage Cube");
                //stack.setTranslatableName("Portal 2 Storage CUbe");
            } else if(i==1)
            {
               // stack.setStackDisplayName("Portal 1 Storage Cube");
                stack.setTagCompound(new NBTWRITE(new NBTTagCompound()).setString("name", "Portal 1 Storage Cube").getResult());
            }else{
                //stack.setStackDisplayName("Companion Cube");
                stack.setTagCompound(new NBTWRITE(new NBTTagCompound()).setString("name", "Companion Cube").getResult());
            }
            items.add(stack);
        }
    }



    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        int meta = stack.getMetadata();
        IBlockState state1=getStateFromMeta(meta);
        worldIn.setBlockState(pos, state1);

    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
    }


    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    /**
     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
     * block, etc.
     */
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            this.checkFallable(worldIn, pos);
        }
    }

    private void checkFallable(World worldIn, BlockPos pos)
    {
        if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0)
        {
            int i = 32;

            if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
            {
                if (!worldIn.isRemote)
                {
                    EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
                    this.onStartFalling(entityfallingblock);
                    worldIn.spawnEntity(entityfallingblock);
                }
            }
            else
            {
                IBlockState state = worldIn.getBlockState(pos);
                worldIn.setBlockToAir(pos);
                BlockPos blockpos;

                for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down())
                {
                    ;
                }

                if (blockpos.getY() > 0)
                {
                    worldIn.setBlockState(blockpos.up(), state); //Forge: Fix loss of state information during world gen.
                }
            }
        }
    }

    protected void onStartFalling(EntityFallingBlock fallingEntity)
    {
        fallingEntity.setHurtEntities(true);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World worldIn)
    {
        return 2;
    }

    public static boolean canFallThrough(IBlockState state)
    {
        Block block = state.getBlock();
        Material material = state.getMaterial();
        return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (rand.nextInt(16) == 0)
        {
            BlockPos blockpos = pos.down();

            if (canFallThrough(worldIn.getBlockState(blockpos)))
            {
                double d0 = (double)((float)pos.getX() + rand.nextFloat());
                double d1 = (double)pos.getY() - 0.05D;
                double d2 = (double)((float)pos.getZ() + rand.nextFloat());
                //worldIn.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(stateIn));
            }
        }
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CUBES);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(CUBES, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(CUBES);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }


    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 2, "hl2:companioncube");
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "hl2:cube");
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 1, "hl2:portalonecube");
    }


    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        int meta = getMetaFromState(state);
        ItemStack stack = new ItemStack(BlockInit.STORAGECUBE, 1, meta);
        stack.setItemDamage(meta);
        return stack.getItem();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(BlockInit.STORAGECUBE, 1, this.getMetaFromState(world.getBlockState(pos)));
    }



    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItem(hand);
        if (playerIn.isSneaking())
        {
            if (playerIn.inventory.addItemStackToInventory(new ItemStack(this, 1, this.getMetaFromState(worldIn.getBlockState(pos)))))
            {
                IBlockState state1 = Blocks.AIR.getDefaultState();
                worldIn.setBlockState(pos, state1);
            }
        }

        return true;
    }
}
