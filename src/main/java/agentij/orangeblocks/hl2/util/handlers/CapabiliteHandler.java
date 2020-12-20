package agentij.orangeblocks.hl2.util.handlers;

import agentij.orangeblocks.hl2.util.interfaces.IPortalSystem;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class CapabiliteHandler {


    //inject capability with variable
    @CapabilityInject(IPortalSystem.class)
    public static final Capability<IPortalSystem> PORTAL_SYSTEM_CAPABILITY = null;
}
