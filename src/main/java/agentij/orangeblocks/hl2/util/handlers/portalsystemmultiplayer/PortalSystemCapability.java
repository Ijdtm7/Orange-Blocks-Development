package agentij.orangeblocks.hl2.util.handlers.portalsystemmultiplayer;

import agentij.orangeblocks.hl2.util.interfaces.IPortalSystem;
import com.mojang.util.UUIDTypeAdapter;
import net.minecraft.entity.player.EntityPlayer;

public class PortalSystemCapability implements IPortalSystem {
    @Override
    public boolean HasOrangePortal() {
        return false;
    }

    @Override
    public boolean hasBluePortal() {
        return false;
    }

    @Override
    public String uuid(EntityPlayer player) {
        return UUIDTypeAdapter.fromUUID(player.getGameProfile().getId());
    }
}
