package agentij.orangeblocks.hl2.util.handlers.portalsystemmultiplayer;

import agentij.orangeblocks.hl2.util.interfaces.IPortalSystem;
import com.mojang.util.UUIDTypeAdapter;
import net.minecraft.entity.player.EntityPlayer;

public class PortalSystemCapability implements IPortalSystem {
    boolean hasOrangePortal=false;
    boolean hasBluePortal=false;


    @Override
    public boolean HasOrangePortal() {
        return hasOrangePortal;
    }

    @Override
    public boolean hasBluePortal() {
        return hasBluePortal;
    }

    @Override
    public String uuid(EntityPlayer player) {
        return UUIDTypeAdapter.fromUUID(player.getGameProfile().getId());
    }

    @Override
    public void setHasOrangePortal(boolean orangePortal) {
        hasOrangePortal=orangePortal;
    }

    @Override
    public void setHasBluePortal(boolean bluePortal) {
        hasBluePortal=bluePortal;
    }
}
