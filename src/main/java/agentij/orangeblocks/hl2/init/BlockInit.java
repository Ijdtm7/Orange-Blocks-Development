/*
 * Copyright (c) 6/21/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.init;

import agentij.orangeblocks.hl2.blocks.BlockButtonThingy;
import agentij.orangeblocks.hl2.blocks.BlockFirstCubeDispenser;
import agentij.orangeblocks.hl2.blocks.BlockStorageCube;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();


    public static final Block BUTTONTHING = new BlockButtonThingy("buttonthingy");
    public static final Block STORAGECUBE = new BlockStorageCube("cube");
    public static final Block PORTALONECUBEDISPENSER = new BlockFirstCubeDispenser("cubedispenserportalone");
}
