/*
 * Copyright (c) 7/17/2020 Agent Ij
 */

package agentij.orangeblocks.hl2.client;

import agentij.orangeblocks.hl2.Main;
import agentij.orangeblocks.hl2.client.portal.PortalStatus;
import agentij.orangeblocks.hl2.common.packet.PacketSwapType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.lwjgl.input.Keyboard;

public class EventHandlerClient
{
    public static final ResourceLocation txLEmpty = new ResourceLocation("hl2", "textures/overlay/lempty.png");
    public static final ResourceLocation txLFull = new ResourceLocation("hl2", "textures/overlay/lfull.png");
    public static final ResourceLocation txREmpty = new ResourceLocation("hl2", "textures/overlay/rempty.png");
    public static final ResourceLocation txRFull = new ResourceLocation("hl2", "textures/overlay/rfull.png");


    //public KeyBinding keySwitch = new KeyBinding("key.hl2.switch", Keyboard.KEY_G, "key.categories.portalgun");
    public KeyBinding keyReset = new KeyBinding("key.hl2.reset", Keyboard.KEY_R, "key.categories.portalgun");

    public boolean keySwitchDown = false;
    public boolean keyResetDown = false;

    public PortalStatus status = null;
    public int teleportCooldown = 0;

    public boolean justTeleported = false;
    public double mX = 0D;
    public double mY = 0D;
    public double mZ = 0D;

    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent event)
    {
        ModelLoader.setCustomModelResourceLocation(Main.itemPortalGun, 0, new ModelResourceLocation("hl2:pg_blue", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Main.itemPortalGun, 1, new ModelResourceLocation("hl2:pg_orange", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Main.itemPortalCore, 0, new ModelResourceLocation("hl2:pg_core", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Main.itemMultiPortalGun, 0, new ModelResourceLocation("hl2:pg_blue", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Main.itemMultiPortalGun, 1, new ModelResourceLocation("hl2:pg_orange", "inventory"));
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            Minecraft mc = Minecraft.getMinecraft();
            if(mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Main.itemPortalGun || mc.player.getHeldItemOffhand().getItem() == Main.itemPortalGun || mc.player.getHeldItemMainhand().getItem() == Main.itemMultiPortalGun || mc.player.getHeldItemOffhand().getItem() == Main.itemMultiPortalGun))
            {
                /*if(!keySwitchDown && keySwitch.isKeyDown())
                {
                    Main.channel.sendToServer(new PacketSwapType(false, 0));
                }*/
                if(!keyResetDown && keyReset.isKeyDown())
                {
                    Main.channel.sendToServer(new PacketSwapType(true, GuiScreen.isShiftKeyDown() ? 1 : 0));
                }
                //keySwitchDown = keySwitch.isKeyDown();
                keyResetDown = keyReset.isKeyDown();
            }
        }
        else
        {
            Minecraft mc = Minecraft.getMinecraft();
            if(teleportCooldown > 0 && !mc.isGamePaused())
            {
                teleportCooldown--;
            }
            if(justTeleported)
            {
                if(mc.player != null && mc.player.motionX == 0.0D && mc.player.motionY == 0.0D && mc.player.motionZ == 0.0D)
                {
                    justTeleported = false;
                    mc.player.motionX = mX;
                    mc.player.motionY = mY;
                    mc.player.motionZ = mZ;
                }
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            Minecraft mc = Minecraft.getMinecraft();
            if(mc.currentScreen == null && !mc.gameSettings.hideGUI && mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Main.itemPortalGun || mc.player.getHeldItemOffhand().getItem() == Main.itemPortalGun || mc.player.getHeldItemMainhand().getItem() == Main.itemMultiPortalGun || mc.player.getHeldItemOffhand().getItem() == Main.itemMultiPortalGun))
            {
                //is holding a portal gun

                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

                ScaledResolution reso = new ScaledResolution(mc);
                double size = 40;
                double x1 = reso.getScaledWidth() / 2D - size + 1;
                double x2 = reso.getScaledWidth() / 2D + size + 1;
                double y1 = reso.getScaledHeight() / 2D - size + 1;
                double y2 = reso.getScaledHeight() / 2D + size + 1;

                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferbuilder = tessellator.getBuffer();

                mc.getTextureManager().bindTexture(status != null && status.blue ? txLFull : txLEmpty);
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos(x2, y2, 0.0D).tex(1D, 1D).color(5, 130, 255, 255).endVertex();
                bufferbuilder.pos(x2, y1, 0.0D).tex(1D, 0D).color(5, 130, 255, 255).endVertex();
                bufferbuilder.pos(x1, y1, 0.0D).tex(0D, 0D).color(5, 130, 255, 255).endVertex();
                bufferbuilder.pos(x1, y2, 0.0D).tex(0D, 1D).color(5, 130, 255, 255).endVertex();
                tessellator.draw();

                mc.getTextureManager().bindTexture(status != null && status.orange ? txRFull : txREmpty);
                bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                bufferbuilder.pos(x2, y2, 0.0D).tex(1D, 1D).color(255, 176, 6, 255).endVertex();
                bufferbuilder.pos(x2, y1, 0.0D).tex(1D, 0D).color(255, 176, 6, 255).endVertex();
                bufferbuilder.pos(x1, y1, 0.0D).tex(0D, 0D).color(255, 176, 6, 255).endVertex();
                bufferbuilder.pos(x1, y2, 0.0D).tex(0D, 1D).color(255, 176, 6, 255).endVertex();
                tessellator.draw();
            }
        }
    }

    @SubscribeEvent
    public void onConnectToServerEvent(FMLNetworkEvent.ClientConnectedToServerEvent event)
    {
        status = null;
        justTeleported = false;
    }
}
