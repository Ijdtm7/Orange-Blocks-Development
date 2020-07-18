package agentij.orangeblocks.hl2.client.render;

import agentij.orangeblocks.hl2.common.tileentity.TileEntityPortal;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileRendererPortal extends TileEntitySpecialRenderer<TileEntityPortal>
{
    public static final ResourceLocation txBlueBtm = new ResourceLocation("hl2", "textures/blocks/blue_bottom.png");
    public static final ResourceLocation txBlueTop = new ResourceLocation("hl2", "textures/blocks/blue_top.png");
    public static final ResourceLocation txBlueY = new ResourceLocation("hl2", "textures/blocks/blue_floor.png");
    public static final ResourceLocation txOrangeBtm = new ResourceLocation("hl2", "textures/blocks/orange_bottom.png");
    public static final ResourceLocation txOrangeTop = new ResourceLocation("hl2", "textures/blocks/orange_top.png");
    public static final ResourceLocation txOrangeY = new ResourceLocation("hl2", "textures/blocks/orange_floor.png");

    public void render(TileEntityPortal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        if(te.setup)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5D, y + 0.5D, z + 0.5D);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.rotate(te.face.getFrontOffsetY() * 90F, 0F, 0F, 1F);
            GlStateManager.rotate(-te.face.getHorizontalAngle(), 0F, 1F, 0F);
            GlStateManager.translate(0D, 0D, -0.495D);

            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);

            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

            GlStateManager.disableLighting();

            bindTexture(te.face.getAxis() == EnumFacing.Axis.Y ? (te.orange ? txOrangeY : txBlueY) : te.top ? (te.orange ? txOrangeTop : txBlueTop) : te.orange ? txOrangeBtm : txBlueBtm);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();

            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
            bufferbuilder.pos(-0.5D, -0.5D, 0.0D).tex(0D, 1D).endVertex();
            bufferbuilder.pos(0.5D, -0.5D, 0.0D).tex(1D, 1D).endVertex();
            bufferbuilder.pos(0.5D, 0.5D, 0.0D).tex(1D, 0D).endVertex();
            bufferbuilder.pos(-0.5D, 0.5D, 0.0D).tex(0D, 0D).endVertex();
            tessellator.draw();

            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);

            GlStateManager.enableLighting();

            GlStateManager.disableBlend();

            GlStateManager.popMatrix();
        }
    }
}
