package curryrice.xunxianwendao.client.render;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.capability.ICapabilityXunXianWenDao;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderXunXianWenDao {
    private static final Minecraft mc = Minecraft.getInstance();
    private static final GuiIngame gui = mc.ingameGUI;
    private static final ResourceLocation TEXTURE = new ResourceLocation(XunXianWenDao.MODID,"textures/gui/bars.png");

    public static void renderMagicBar(ICapabilityXunXianWenDao cap){
        GlStateManager.color3f(1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(TEXTURE);
        int barHeight=(int)Math.ceil(52.0*cap.getMagic()/cap.getMagicMax());
        int y=(mc.mainWindow.getScaledHeight()-52)/2;
        //GL11.glEnable(GL11.GL_BLEND);
        gui.drawTexturedModalRect(2, y, 0, 0,5,52);
        gui.drawTexturedModalRect(2, y+52-barHeight, 5, 52-barHeight,5,barHeight);
    }
}
