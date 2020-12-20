package agentij.orangeblocks.hl2.util.handlers;

import agentij.orangeblocks.hl2.util.QuickRef;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class CapabiliteUsageDetect {


    private static final ResourceLocation portalSystem = new ResourceLocation(QuickRef.ID, "portalsystem");

//make sure we are dealing with a Player
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            //TODO: Capability for multiplayer portals
        }
    }
}
