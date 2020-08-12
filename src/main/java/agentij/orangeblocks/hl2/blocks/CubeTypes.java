package agentij.orangeblocks.hl2.blocks;

import net.minecraft.block.BlockSand;
import net.minecraft.util.IStringSerializable;
@Deprecated
public enum CubeTypes implements IStringSerializable {
    CUBE(0, "cube"),
    PORTALCUBE(1, "portalonecube"),
    COMPANION(2, "companioncube");
    private final int meta;
    private final String name;
    private static final CubeTypes META_LOOKUP[] = new CubeTypes[values().length];

    @Override
    public String getName() {
        return this.name;
    }

    private CubeTypes(int meta, String name) {
        this.meta = meta;
        this.name= name;
    }
    public int getMetadata()
    {
        return this.meta;
    }

    public static CubeTypes byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    static
    {
        for (CubeTypes cubetype : values())
        {
            META_LOOKUP[cubetype.getMetadata()] = cubetype;
        }
    }
}
