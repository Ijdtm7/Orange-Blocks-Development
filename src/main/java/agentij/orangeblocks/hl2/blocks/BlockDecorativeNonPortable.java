package agentij.orangeblocks.hl2.blocks;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.util.QuickRef;
import agentij.orangeblocks.hl2.util.interfaces.IPortalabe;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nullable;

public class BlockDecorativeNonPortable extends BlockBase implements IPortalabe {
    public BlockDecorativeNonPortable(String name) {
        super(name, Material.IRON, Main.portalBasicTab);

        setResistance(QuickRef.BLOCKCREEPERESISTANCE);
        setHardness(5F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public boolean portalable() {
        return false;
    }
}
