package agentij.orangeblocks.hl2.util.interfaces;

import net.minecraft.entity.player.EntityPlayer;

public interface IPortalSystem {

    boolean HasOrangePortal();

    boolean hasBluePortal();

    String uuid(EntityPlayer player);

    void setHasOrangePortal(boolean orangePortal);
    void setHasBluePortal(boolean bluePortal);
}
