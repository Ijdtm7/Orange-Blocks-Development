/*
 * Copyright (c) 6/21/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.init;

import agentij.orangeblocks.hl2.blocks.*;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();


    public static final Block BUTTONTHING = new BlockButtonThingy("buttonthingy");
    public static final Block STORAGECUBE = new BlockStorageCube("cube");
    public static final Block PORTALONECUBEDISPENSER = new BlockFirstCubeDispenser("cubedispenserportalone");
    //public static final Block DECORATIVEPORTALABLE = new BlockPortalableDecorative("decorativeportalable");
    public static final Block PORTAL1WALLTOP = new BlockPortalableDecorative("portalonewalltop");
    public static final Block PORTAL1WALLBOTTOM = new BlockPortalableDecorative("portalonewallbottom");
    public static final Block PORTAL2WALLTOP = new BlockPortalableDecorative("portaltwowalltop");
    public static final Block PORTAL2WALLBOTTOM = new BlockPortalableDecorative("portaltwowallbottom");
    public static final Block RUSTICWALL = new BlockPortalableDecorative("rusticbottom");
    public static final Block RUSTICTOP = new BlockPortalableDecorative("rustictop");
    public static final Block NONPORTALABLE1 = new BlockDecorativeNonPortable("nonportalableone");
    public static final Block NONPORTALABLE2 = new BlockDecorativeNonPortable("nonportalabletwo");
    public static final Block NONPORTALABLE3 = new BlockDecorativeNonPortable("nonportalablethree");
    public static final Block NONPORTALABLEOLDAPERTURE = new BlockDecorativeNonPortable("nonportalableoldaperture");
    public static final Block NONPORTALABLENEWAPERTURE = new BlockDecorativeNonPortable("nonportalablenewaperture");
}
