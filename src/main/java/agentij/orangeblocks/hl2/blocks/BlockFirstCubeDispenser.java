package agentij.orangeblocks.hl2.blocks;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.init.BlockInit;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@SuppressWarnings({"deprecation","unused"})
//use blockbase
public class BlockFirstCubeDispenser extends BlockBase {


    boolean diditchange=false;


    //define blockstates
    public static PropertyBool POWERED = PropertyBool.create("powered");
    //TODO: add companion cube



    public BlockFirstCubeDispenser(String name) {


        //temporaily send up Redstone tab in Creative until I get around to making a dedicated portal tab, probably will today
        super(name, Material.IRON, CreativeTabs.REDSTONE);


        setSoundType(SoundType.METAL);
        setDefaultState(this.blockState.getBaseState().withProperty(POWERED, false));
    }


    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, POWERED);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (meta == 1) {
            return this.getDefaultState().withProperty(POWERED, true);
        }else {
            return this.getDefaultState().withProperty(POWERED, false);
        }

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(POWERED) ? 1 : 0;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }


    //Why the fuck they all called element.
    private static final AxisAlignedBB ELEMENT1 = new AxisAlignedBB(-0.188, 0, 0.562, -0.125, 0.25, 0.625);
    private static final AxisAlignedBB ELEMENT2 = new AxisAlignedBB(-0.25, 0.062, -0.812, 1.188, 0.125, -0.188);
    private static final AxisAlignedBB ELEMENT3 = new AxisAlignedBB(-0.25, 0.062, 1.188, 1.188, 0.125, 1.812);
    private static final AxisAlignedBB ELEMENT4 = new AxisAlignedBB(-0.812, 0.062, 0.375, -0.188, 0.125, 0.625);
    private static final AxisAlignedBB ELEMENT5 = new AxisAlignedBB(1.125, 0.062, 0.375, 1.688, 0.125, 0.625);
    private static final AxisAlignedBB ELEMENT6 = new AxisAlignedBB(-0.188, 0, 0.375, -0.125, 0.25, 0.438);
    private static final AxisAlignedBB ELEMENT7 = new AxisAlignedBB(-0.188, 0.188, 0.375, -0.125, 0.25, 0.625);
    private static final AxisAlignedBB ELEMENT8 = new AxisAlignedBB(-0.188, 0, 0.375, -0.125, 0.062, 0.625);
    private static final AxisAlignedBB ELEMENT9 = new AxisAlignedBB(-0.188, 0.062, 0.438, -0.125, 0.188, 0.562);
    private static final AxisAlignedBB ELEMENT10 = new AxisAlignedBB(0.438, -0.688, -0.25, 0.5, 0, -0.188);
    private static final AxisAlignedBB ELEMENT11 = new AxisAlignedBB(-0.25, -0.688, 0.5, -0.188, 0, 0.562);
    private static final AxisAlignedBB ELEMENT12 = new AxisAlignedBB(1.125, -0.625, 0.5, 1.188, 0, 0.562);
    private static final AxisAlignedBB ELEMENT13 = new AxisAlignedBB(0.438, -0.688, 1.188, 0.5, 0, 1.25);
    private static final AxisAlignedBB ELEMENT14 = new AxisAlignedBB(1.125, 0, 0.5, 1.188, 1.688, 0.562);
    private static final AxisAlignedBB ELEMENT15 = new AxisAlignedBB(0.438, 0, 1.188, 0.5, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENT16 = new AxisAlignedBB(-0.25, 0, 0.5, -0.188, 1.688, 0.562);
    private static final AxisAlignedBB ELEMENT17 = new AxisAlignedBB(0.438, 0, -0.25, 0.5, 1.688, -0.188);
    private static final AxisAlignedBB ELEMENT18 = new AxisAlignedBB(0.5, 0.125, -0.25, 1.125, 1.688, -0.188);
    private static final AxisAlignedBB ELEMENT19 = new AxisAlignedBB(-0.188, 0.125, -0.25, 0.438, 1.688, -0.188);
    private static final AxisAlignedBB ELEMENT20 = new AxisAlignedBB(-0.188, 0.125, 1.188, 0.438, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENT21 = new AxisAlignedBB(0.5, 0.125, 1.188, 1.125, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENT22 = new AxisAlignedBB(1.125, 0.125, -0.25, 1.188, 1.688, 0.5);
    private static final AxisAlignedBB ELEMENT23 = new AxisAlignedBB(1.125, 0.125, 0.562, 1.188, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENT24 = new AxisAlignedBB(-0.25, 0.125, -0.25, -0.188, 1.688, 0.5);
    private static final AxisAlignedBB ELEMENT25 = new AxisAlignedBB(-0.25, 0.125, 0.562, -0.188, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENT26 = new AxisAlignedBB(-0.312, 1.688, -0.312, 1.25, 1.75, -0.188);
    private static final AxisAlignedBB ELEMENT27 = new AxisAlignedBB(-0.312, 1.688, -0.25, -0.188, 1.75, 1.312);
    private static final AxisAlignedBB ELEMENT28 = new AxisAlignedBB(1.062, 1.688, -0.25, 1.25, 1.75, 1.312);
    private static final AxisAlignedBB ELEMENT29 = new AxisAlignedBB(-0.25, 1.688, 1.188, 1.25, 1.75, 1.312);

    private static final AxisAlignedBB ELEMENT = new AxisAlignedBB(0.375, 0.062, 0.562, 0.625, 0.125, 0.625);
    private static final AxisAlignedBB ELEMENTa = new AxisAlignedBB(-0.25, 0.062, -0.25, 1.188, 0.125, 0.375);
    private static final AxisAlignedBB ELEMENTb = new AxisAlignedBB(-0.25, 0.062, 0.625, 1.188, 0.125, 1.25);
    private static final AxisAlignedBB ELEMENTc = new AxisAlignedBB(-0.25, 0.062, 0.375, 0.375, 0.125, 0.625);
    private static final AxisAlignedBB ELEMENTd = new AxisAlignedBB(0.625, 0.062, 0.375, 1.188, 0.125, 0.625);
    private static final AxisAlignedBB ELEMENTe = new AxisAlignedBB(0.375, 0.062, 0.375, 0.625, 0.125, 0.438);
    private static final AxisAlignedBB ELEMENTf = new AxisAlignedBB(0.375, 0.062, 0.375, 0.438, 0.125, 0.625);
    private static final AxisAlignedBB ELEMENTg = new AxisAlignedBB(0.562, 0.062, 0.375, 0.625, 0.125, 0.625);
    private static final AxisAlignedBB ELEMENTh = new AxisAlignedBB(0.438, 0.125, 0.438, 0.562, 0.188, 0.562);
    private static final AxisAlignedBB ELEMENTi = new AxisAlignedBB(0.156, 0.344, 0.156, 0.844, 1.031, 0.844);
    private static final AxisAlignedBB ELEMENTj = new AxisAlignedBB(0.406, 0.594, 0.125, 0.594, 0.781, 0.188);
    private static final AxisAlignedBB ELEMENTk = new AxisAlignedBB(0.406, 0.594, 0.812, 0.594, 0.781, 0.875);
    private static final AxisAlignedBB ELEMENTl = new AxisAlignedBB(0.812, 0.594, 0.406, 0.875, 0.781, 0.594);
    private static final AxisAlignedBB ELEMENTm = new AxisAlignedBB(0.406, 0.312, 0.406, 0.594, 0.375, 0.594);
    private static final AxisAlignedBB ELEMENTn = new AxisAlignedBB(0.125, 0.594, 0.406, 0.188, 0.781, 0.594);
    private static final AxisAlignedBB ELEMENTo = new AxisAlignedBB(0.406, 1, 0.406, 0.594, 1.062, 0.594);
    private static final AxisAlignedBB ELEMENTp = new AxisAlignedBB(0, 0.188, 0, 0.312, 0.5, 0.312);
    private static final AxisAlignedBB ELEMENTq = new AxisAlignedBB(0, 0.875, 0, 0.312, 1.188, 0.312);
    private static final AxisAlignedBB ELEMENTr = new AxisAlignedBB(0, 0.188, 0.688, 0.312, 0.5, 1);
    private static final AxisAlignedBB ELEMENTs = new AxisAlignedBB(0, 0.875, 0.688, 0.312, 1.188, 1);
    private static final AxisAlignedBB ELEMENTt = new AxisAlignedBB(0.688, 0.188, 0, 1, 0.5, 0.312);
    private static final AxisAlignedBB ELEMENTu = new AxisAlignedBB(0.688, 0.875, 0, 1, 1.188, 0.312);
    private static final AxisAlignedBB ELEMENTv = new AxisAlignedBB(0.688, 0.188, 0.688, 1, 0.5, 1);
    private static final AxisAlignedBB ELEMENTx = new AxisAlignedBB(0.688, 0.875, 0.688, 1, 1.188, 1);
    private static final AxisAlignedBB ELEMENTw = new AxisAlignedBB(0.344, 0.25, 0.062, 0.656, 0.438, 0.25);
    private static final AxisAlignedBB ELEMENTy = new AxisAlignedBB(0.344, 0.25, 0.75, 0.656, 0.438, 0.938);
    private static final AxisAlignedBB ELEMENTz = new AxisAlignedBB(0.062, 0.531, 0.062, 0.25, 0.844, 0.25);
    private static final AxisAlignedBB ELEMENTaa = new AxisAlignedBB(0.062, 0.25, 0.344, 0.25, 0.438, 0.656);
    private static final AxisAlignedBB ELEMENTab = new AxisAlignedBB(0.75, 0.531, 0.75, 0.938, 0.844, 0.938);
    private static final AxisAlignedBB ELEMENTac = new AxisAlignedBB(0.75, 0.938, 0.344, 0.938, 1.125, 0.656);
    private static final AxisAlignedBB ELEMENTad = new AxisAlignedBB(0.344, 0.938, 0.062, 0.656, 1.125, 0.25);
    private static final AxisAlignedBB ELEMENTae = new AxisAlignedBB(0.344, 0.938, 0.75, 0.656, 1.125, 0.938);
    private static final AxisAlignedBB ELEMENTaf = new AxisAlignedBB(0.75, 0.531, 0.062, 0.938, 0.844, 0.25);
    private static final AxisAlignedBB ELEMENTag = new AxisAlignedBB(0.75, 0.25, 0.344, 0.938, 0.438, 0.656);
    private static final AxisAlignedBB ELEMENTah = new AxisAlignedBB(0.062, 0.531, 0.75, 0.25, 0.844, 0.938);
    private static final AxisAlignedBB ELEMENTai = new AxisAlignedBB(0.062, 0.938, 0.344, 0.25, 1.125, 0.656);
    private static final AxisAlignedBB ELEMENTaj = new AxisAlignedBB(0.438, 0, -0.25, 0.5, 0.062, 0.438);
    private static final AxisAlignedBB ELEMENTal = new AxisAlignedBB(-0.25, 0, 0.5, 0.438, 0.062, 0.562);
    private static final AxisAlignedBB ELEMENTak = new AxisAlignedBB(0.562, 0, 0.5, 1.188, 0.062, 0.562);
    private static final AxisAlignedBB ELEMENTam = new AxisAlignedBB(0.438, 0, 0.562, 0.5, 0.062, 1.25);
    private static final AxisAlignedBB ELEMENTan = new AxisAlignedBB(1.125, 0, 0.5, 1.188, 1.688, 0.562);
    private static final AxisAlignedBB ELEMENTao = new AxisAlignedBB(0.438, 0, 1.188, 0.5, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENTap = new AxisAlignedBB(-0.25, 0, 0.5, -0.188, 1.688, 0.562);
    private static final AxisAlignedBB ELEMENTaq = new AxisAlignedBB(0.438, 0, -0.25, 0.5, 1.688, -0.188);
    private static final AxisAlignedBB ELEMENTar = new AxisAlignedBB(0.5, 0.125, -0.25, 1.125, 1.688, -0.188);
    private static final AxisAlignedBB ELEMENTas = new AxisAlignedBB(-0.188, 0.125, -0.25, 0.438, 1.688, -0.188);
    private static final AxisAlignedBB ELEMENTat = new AxisAlignedBB(-0.188, 0.125, 1.188, 0.438, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENTau = new AxisAlignedBB(0.5, 0.125, 1.188, 1.125, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENTav = new AxisAlignedBB(1.125, 0.125, -0.25, 1.188, 1.688, 0.5);
    private static final AxisAlignedBB ELEMENTaw = new AxisAlignedBB(1.125, 0.125, 0.562, 1.188, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENTax = new AxisAlignedBB(-0.25, 0.125, -0.25, -0.188, 1.688, 0.5);
    private static final AxisAlignedBB ELEMENTay = new AxisAlignedBB(-0.25, 0.125, 0.562, -0.188, 1.688, 1.25);
    private static final AxisAlignedBB ELEMENTaz = new AxisAlignedBB(-0.312, 1.688, -0.312, 1.25, 1.75, 1.312);

    private static final List<AxisAlignedBB> COLLISION_BOXES_OPEN = Lists.newArrayList(ELEMENT1, ELEMENT2, ELEMENT3, ELEMENT4, ELEMENT5, ELEMENT6, ELEMENT7, ELEMENT8, ELEMENT9, ELEMENT10, ELEMENT11, ELEMENT12, ELEMENT13, ELEMENT14, ELEMENT15, ELEMENT16, ELEMENT17, ELEMENT18, ELEMENT19, ELEMENT20, ELEMENT21, ELEMENT22, ELEMENT23, ELEMENT24, ELEMENT25, ELEMENT26, ELEMENT27, ELEMENT28, ELEMENT29);
    private static final AxisAlignedBB BOUNDING_BOX_OPEN = new AxisAlignedBB(-0.812, -0.688, -0.812, 1.688, 1.75, 1.812);


    private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(ELEMENT, ELEMENTa, ELEMENTb, ELEMENTc, ELEMENTd, ELEMENTe, ELEMENTf, ELEMENTg, ELEMENTh, ELEMENTi, ELEMENTj, ELEMENTk, ELEMENTl, ELEMENTm, ELEMENTn, ELEMENTo, ELEMENTp, ELEMENTq, ELEMENTr, ELEMENTs, ELEMENTt, ELEMENTu, ELEMENTv, ELEMENTw, ELEMENTx, ELEMENTy, ELEMENTz, ELEMENTaa, ELEMENTab, ELEMENTac, ELEMENTad, ELEMENTae, ELEMENTaf, ELEMENTag, ELEMENTah, ELEMENTai, ELEMENTaj, ELEMENTak, ELEMENTal, ELEMENTam, ELEMENTan, ELEMENTao, ELEMENTap, ELEMENTaq, ELEMENTar, ELEMENTas, ELEMENTat, ELEMENTau, ELEMENTav, ELEMENTaw, ELEMENTax, ELEMENTay, ELEMENTaz);
    private static final AxisAlignedBB BOUNDING_BOXCLOSED = new AxisAlignedBB(-0.312, 0, -0.312, 1.25, 1.75, 1.312);
    //ugh now i gotta do this shit again for the open model? cmon man.
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
       if (state.getValue(POWERED))
       {
           return BOUNDING_BOX_OPEN;
       }
        return BOUNDING_BOXCLOSED;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState)
    {

        if (state.getValue(POWERED))
        {
            entityBox = entityBox.offset(-pos.getX(), -pos.getY(), -pos.getZ());
            for (AxisAlignedBB box : COLLISION_BOXES_OPEN)
            {
                if (entityBox.intersects(box))
                    collidingBoxes.add(box.offset(pos));
            }
        }else{
            entityBox = entityBox.offset(-pos.getX(), -pos.getY(), -pos.getZ());
            for (AxisAlignedBB box : COLLISION_BOXES)
            {
                if (entityBox.intersects(box))
                    collidingBoxes.add(box.offset(pos));
            }
        }


    }

    @Override
    @Nullable
    public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end)
    {
        if (state.getValue(POWERED))
        {

            double distanceSq;
            double distanceSqShortest = Double.POSITIVE_INFINITY;
            RayTraceResult resultClosest = null;
            RayTraceResult result;
            start = start.subtract(pos.getX(), pos.getY(), pos.getZ());
            end = end.subtract(pos.getX(), pos.getY(), pos.getZ());
            for (AxisAlignedBB box : COLLISION_BOXES_OPEN)
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
        }else {
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

    }


    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote)  //make sure we are dealing with a server, not client
        {
            if (worldIn.isBlockPowered(pos) && !diditchange)
            {
                worldIn.setBlockState(pos, state.withProperty(POWERED, true));

                diditchange=true;
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();
                String texttranslated = new TextComponentTranslation("hl2.message.errorcube").getUnformattedComponentText();
                BlockPos cubePos = new BlockPos(x, y - 1, z);
                IBlockState state1 = worldIn.getBlockState(cubePos);
                if (state1.getBlock() == Blocks.AIR) {
                    IBlockState portalState = BlockInit.STORAGECUBE.getDefaultState().withProperty(BlockStorageCube.CUBES, 1);
                    worldIn.setBlockState(cubePos, portalState);
                } else {
                    Main.logger.info("[ERROR] Unable to dispense cube as there is an object obstructing it below");
                    //List<EntityPlayer> nearbyplayers = worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(cubePos).grow(20));
                   // for (EntityPlayer player : nearbyplayers) {
                    //    player.sendStatusMessage(new TextComponentString(TextFormatting.GOLD + texttranslated), false);
                  //  }
                   // worldIn.setBlockState(pos, this.getDefaultState());
                }
            }else if (!worldIn.isBlockPowered(pos)){
                worldIn.setBlockState(pos, state.withProperty(POWERED,false));
                diditchange=false;
            }
            if (state.getValue(POWERED) && !diditchange) {

            }
        }
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote)  //make sure we are dealing with a server, not client
        {
            if (worldIn.isBlockPowered(pos) && !diditchange)
            {
                worldIn.setBlockState(pos, state.withProperty(POWERED, true));
               // worldIn.setBlockState(pos, this.getDefaultState().withProperty(POWERED, true));
                Main.logger.info("should be giving a change in blockstate");

                diditchange=true;
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();
                String texttranslated = new TextComponentTranslation("hl2.message.errorcube").getUnformattedComponentText();
                BlockPos cubePos = new BlockPos(x, y - 1, z);
                IBlockState state1 = worldIn.getBlockState(cubePos);
                Block belowBlock = state1.getBlock();
                Material material = state1.getMaterial();
                if (belowBlock == Blocks.AIR || belowBlock == Blocks.FIRE || material == Material.AIR || material==Material.WATER || material == Material.LAVA) {
                    worldIn.setBlockState(pos, state.withProperty(POWERED, true));
                    IBlockState portalState = BlockInit.STORAGECUBE.getDefaultState().withProperty(BlockStorageCube.CUBES, 1);
                   worldIn.setBlockState(cubePos, portalState);
                   return;
                } else {
                    Main.logger.info("[ERROR] Unable to dispense cube as there is an object obstructing it below");
                    List<EntityPlayer> nearbyplayers = worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(cubePos).grow(20));
                    for (EntityPlayer player : nearbyplayers) {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.GOLD + texttranslated), false);
                      }
                    worldIn.setBlockState(pos, this.getDefaultState());
                }
            }else{
                worldIn.setBlockState(pos, state.withProperty(POWERED,false));
                diditchange=false;
            }
            if (state.getValue(POWERED) && !diditchange) {

            }
        }
    }
}
