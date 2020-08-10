/*
 * Copyright (c) 6/21/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.blocks;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.init.BlockInit;
import agentij.orangeblocks.hl2.init.ItemInit;
import agentij.orangeblocks.hl2.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nullable;

public class BlockBase extends Block implements IHasModel {
    public BlockBase(String name, Material materialIn, @Nullable CreativeTabs tab) {
        super(materialIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        if (tab != null) {
            setCreativeTab(tab);
        }





        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
    

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0);
    }
}

