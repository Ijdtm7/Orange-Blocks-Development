package agentij.orangeblocks.hl2.proxy;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.common.EventHandlerServer;
import agentij.orangeblocks.hl2.common.entity.EntityPortalProjectile;
import agentij.orangeblocks.hl2.common.packet.PacketEntityLocation;
import agentij.orangeblocks.hl2.common.packet.PacketPortalStatus;
import agentij.orangeblocks.hl2.common.packet.PacketRequestTeleport;
import agentij.orangeblocks.hl2.common.packet.PacketSwapType;
import agentij.orangeblocks.hl2.common.tileentity.TileEntityPortal;
import agentij.orangeblocks.hl2.util.QuickRef;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
@SuppressWarnings("deprecation")
public class CommonProxy {
    public void registerItemRenderer(Item item, int meta) {}

    public void preInit() {
        //thanks iChun!
        GameRegistry.registerTileEntity(TileEntityPortal.class, "hl2:tile_portal");

        EntityRegistry.registerModEntity(new ResourceLocation("hl2", "portal_projectile"), EntityPortalProjectile.class, "hl2_portal_projectile", 0, Main.instance, 256, 1, true);

        Main.eventHandlerServer = new EventHandlerServer();
        MinecraftForge.EVENT_BUS.register(Main.eventHandlerServer);

        Main.channel = new SimpleNetworkWrapper(QuickRef.NAME);
        Main.channel.registerMessage(new PacketSwapType.Handler(), PacketSwapType.class, 0, Side.SERVER);
        Main.channel.registerMessage(new PacketPortalStatus.Handler(), PacketPortalStatus.class, 1, Side.CLIENT);
        Main.channel.registerMessage(new PacketRequestTeleport.Handler(), PacketRequestTeleport.class, 2, Side.SERVER);
        Main.channel.registerMessage(new PacketEntityLocation.Handler(), PacketEntityLocation.class, 3, Side.CLIENT);
    }
}
