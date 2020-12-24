package agentij.orangeblocks.hl2.common.item;

import agentij.orangeblocks.hl2.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemPortalCore extends Item
{
    public ItemPortalCore()
    {
        setRegistryName("portal_core");
        setUnlocalizedName("portal_core");
        setCreativeTab(Main.portalBasicTab);
    }
}
