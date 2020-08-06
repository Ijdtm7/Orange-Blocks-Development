/*
 * Copyright (c) 6/17/2020 Agent Ij
 */
//founded on 6/17/2020
package agentij.orangeblocks.hl2;

import agentij.orangeblocks.hl2.client.EventHandlerClient;
import agentij.orangeblocks.hl2.common.EventHandlerServer;
import agentij.orangeblocks.hl2.proxy.CommonProxy;
import agentij.orangeblocks.hl2.util.QuickRef;
import agentij.orangeblocks.hl2.util.handlers.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = QuickRef.ID, name = QuickRef.NAME, version = QuickRef.VERSION, acceptedMinecraftVersions = "[1.12.2]")
public class Main
{
    public static Logger logger = LogManager.getLogger(QuickRef.NAME);

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = QuickRef.CLIENT, serverSide = QuickRef.SERVER)
    public static CommonProxy proxy;

    public static EventHandlerClient eventHandlerClient;
    public static EventHandlerServer eventHandlerServer;

    public static Item itemPortalGun;
    public static Item itemPortalCore;
    public static Item itemMultiPortalGun;

    public static Block blockPortalGun;

    public static SimpleNetworkWrapper channel;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        RegistryHandler.preInitRegistries(event);
        proxy.preInit();
    }
    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {
        RegistryHandler.initRegistries(event);
    }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
        RegistryHandler.postInitRegistries(event);
    }
}
