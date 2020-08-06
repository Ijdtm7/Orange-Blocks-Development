package agentij.orangeblocks.hl2.event;

import agentij.orangeblocks.hl2.common.item.ItemPortalGunMulti;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class PlayerEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event){
        EntityPlayer player = event.getPlayer();
        if(player != null){
            Item item = player.getHeldItemMainhand().getItem();
            if(item instanceof ItemPortalGunMulti){
                event.setCanceled(true);
            }
        }
    }

}
