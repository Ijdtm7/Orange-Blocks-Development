/*
 * Copyright (c) 6/21/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.items;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.init.ItemInit;
import agentij.orangeblocks.hl2.util.interfaces.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name, CreativeTabs tab)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tab);

        ItemInit.ITEMS.add(this);

    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0);
    }


}
