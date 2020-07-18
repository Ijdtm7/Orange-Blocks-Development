/*
 * Copyright (c) 6/21/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.blocks;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.init.BlockInit;
import agentij.orangeblocks.hl2.init.ItemInit;
import agentij.orangeblocks.hl2.util.interfaces.IHasModel;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nullable;

public class BlockHorizontalNonFullBase extends BlockHorizontalNonFull implements IHasModel {

    private int meta;

    public BlockHorizontalNonFullBase(Material materialIn, String name, int  meta, @Nullable CreativeTabs tabs) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        if (tabs != null)
        {
            setCreativeTab(tabs);
        }

        this.meta=meta;
        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockHorizontalNonFullBase(String name, int meta, @Nullable CreativeTabs tabs) {
        this(Material.CLAY, name, meta, tabs);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), meta);
    }
}
