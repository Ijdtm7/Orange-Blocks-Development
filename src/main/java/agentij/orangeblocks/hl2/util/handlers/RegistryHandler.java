/*
 * Copyright (c) 6/19/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.util.handlers;

import agentij.orangeblocks.hl2.commands.CommandKillAllPortals;
import agentij.orangeblocks.hl2.init.BlockInit;
import agentij.orangeblocks.hl2.init.EntityInit;
import agentij.orangeblocks.hl2.init.ItemInit;
import agentij.orangeblocks.hl2.util.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ItemInit.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : BlockInit.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel) block).registerModels();
            }
        }


    }



    public static void preInitRegistries(FMLPreInitializationEvent event)
    {
        EntityInit.RegisterEntities();
        RenderHandler.RegisterEntityRenders();
    }

    public static void initRegistries(FMLInitializationEvent event)
    {

    }

    public static void postInitRegistries(FMLPostInitializationEvent event)
    {

    }


    public static void serverRegistries(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandKillAllPortals());
    }
}
