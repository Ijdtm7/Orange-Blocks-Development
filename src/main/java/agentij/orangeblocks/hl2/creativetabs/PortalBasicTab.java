package agentij.orangeblocks.hl2.creativetabs;

import agentij.orangeblocks.hl2.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PortalBasicTab extends CreativeTabs {
    public PortalBasicTab() {
        super("orangeblocksportal-basic");
        setBackgroundImageName("portalbasictab.png");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Item.getItemFromBlock(BlockInit.STORAGECUBE));
    }

}
