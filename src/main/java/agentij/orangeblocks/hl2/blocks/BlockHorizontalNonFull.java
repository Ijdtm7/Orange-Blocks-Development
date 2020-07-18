/*
 * Copyright (c) 6/21/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;

@SuppressWarnings("deprecation")
public class BlockHorizontalNonFull extends HorizontalRotation  {
    public BlockHorizontalNonFull() {
        super(Material.CLAY);
    }

    public BlockHorizontalNonFull(Material materialIn) {
        super(materialIn);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;

    }


    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
