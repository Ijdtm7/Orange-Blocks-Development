/*
 * Copyright (c) 6/17/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.proxy;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.client.EventHandlerClient;
import agentij.orangeblocks.hl2.client.render.RenderPortalProjectile;
import agentij.orangeblocks.hl2.client.render.TileRendererPortal;
import agentij.orangeblocks.hl2.common.entity.EntityPortalProjectile;
import agentij.orangeblocks.hl2.common.tileentity.TileEntityPortal;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void preInit() {
        super.preInit();
        Main.eventHandlerClient = new EventHandlerClient();
        MinecraftForge.EVENT_BUS.register(Main.eventHandlerClient);

        ClientRegistry.registerKeyBinding(Main.eventHandlerClient.keyReset);

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPortal.class, new TileRendererPortal());

        RenderingRegistry.registerEntityRenderingHandler(EntityPortalProjectile.class, new RenderPortalProjectile.Factory());
    }
}
