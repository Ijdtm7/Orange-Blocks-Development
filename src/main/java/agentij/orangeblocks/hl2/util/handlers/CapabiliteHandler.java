package agentij.orangeblocks.hl2.util.handlers;

import agentij.orangeblocks.hl2.util.handlers.portalsystemmultiplayer.PortalSystemCapability;
import agentij.orangeblocks.hl2.util.handlers.portalsystemmultiplayer.PortalSystemHandler;
import agentij.orangeblocks.hl2.util.interfaces.IPortalSystem;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabiliteHandler {


    //inject capability with variable
    @CapabilityInject(IPortalSystem.class)
    public static final Capability<IPortalSystem> PORTAL_SYSTEM_CAPABILITY = null;



    public static void register()
    {
        CapabilityManager.INSTANCE.register(IPortalSystem.class, new PortalSystemHandler.Storage(), PortalSystemCapability::new);
    }
}
