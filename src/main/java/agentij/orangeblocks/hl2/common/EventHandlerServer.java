package agentij.orangeblocks.hl2.common;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.common.block.BlockPortal;
import agentij.orangeblocks.hl2.common.item.ItemPortalCore;
import agentij.orangeblocks.hl2.common.item.ItemPortalGun;
import agentij.orangeblocks.hl2.common.packet.PacketPortalStatus;
import agentij.orangeblocks.hl2.common.portal.PortalInfo;
import agentij.orangeblocks.hl2.common.world.PortalSavedData;
import agentij.orangeblocks.hl2.util.handlers.SoundRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.HashMap;

public class EventHandlerServer
{
    @SubscribeEvent
    public void onRegisterBlock(RegistryEvent.Register<Block> event)
    {
        Main.blockPortalGun = new BlockPortal();
        event.getRegistry().register(Main.blockPortalGun);
    }

    @SubscribeEvent
    public void onRegisterItem(RegistryEvent.Register<Item> event)
    {
        Main.itemPortalGun = new ItemPortalGun();
        event.getRegistry().register(Main.itemPortalGun);

        Main.itemPortalCore = new ItemPortalCore();
        event.getRegistry().register(Main.itemPortalCore);
    }

    @SubscribeEvent
    public void onRegisterSound(RegistryEvent.Register<SoundEvent> event)
    {
        SoundRegistry.init(event.getRegistry());
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        updatePlayerDimensionStatus(event.player);
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        updatePlayerDimensionStatus(event.player);
    }

    @SubscribeEvent
    public void onPlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event)
    {
        updatePlayerDimensionStatus(event.player);
    }

    public void updatePlayerDimensionStatus(EntityPlayer player)
    {
        PortalSavedData data = getSaveData(player.getEntityWorld());
        HashMap<String, PortalInfo> map = data.portalInfo.get(player.getEntityWorld().provider.getDimension());
        Main.channel.sendTo(new PacketPortalStatus(map != null && map.containsKey("blue"), map != null && map.containsKey("orange")), (EntityPlayerMP)player);
    }

    public PortalSavedData getSaveData(World world)
    {
        PortalSavedData data = (PortalSavedData)world.loadData(PortalSavedData.class, PortalSavedData.DATA_ID);
        if(data == null)
        {
            data = new PortalSavedData(PortalSavedData.DATA_ID);
            world.setData(PortalSavedData.DATA_ID, data);
            data.markDirty();
        }
        return data;
    }
}
