package agentij.orangeblocks.hl2.util.handlers;

import agentij.orangeblocks.hl2.util.QuickRef;
import agentij.orangeblocks.hl2.util.handlers.portalsystemmultiplayer.PortalSystemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabiliteUsageDetect {


    private static final ResourceLocation portalSystem = new ResourceLocation(QuickRef.ID, "portalsystem");

//make sure we are dealing with a Player
    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(portalSystem, new PortalSystemHandler());
        }
    }
}
