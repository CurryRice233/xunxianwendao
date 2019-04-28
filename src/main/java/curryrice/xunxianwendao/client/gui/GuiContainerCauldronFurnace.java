package curryrice.xunxianwendao.client.gui;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.inventory.ContainerCauldronFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiContainerCauldronFurnace extends GuiContainer {
    private static final String TEXTURE_PATH= XunXianWenDao.MODID + ":" + "textures/gui/container/gui_cauldron_furnace.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    private ContainerCauldronFurnace container;
    public GuiContainerCauldronFurnace(ContainerCauldronFurnace containerCauldronFurnace) {
        super(containerCauldronFurnace);
        this.container=containerCauldronFurnace;
    }

    public void render(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.render(mouseX,mouseY,partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color3f(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int cookTextureWidth = 1 + (int) Math.ceil(22.0 * this.container.getCookTime() / this.container.getTotalCookTime());
        this.drawTexturedModalRect(offsetX + 109, offsetY + 34, 176, 14, cookTextureWidth, 17);
        if(container.isBurning()){
            this.drawTexturedModalRect(offsetX + 35, offsetY + 48, 177, 0, 14, 14);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String title = I18n.format("block.xunxianwendao.cauldron_furnace");
        //this.fontRenderer.drawString(title+this.totalBurnTime+" "+this.totalCookTime+" "+inventory.getBurnTime()+" "+inventory.getCookTime()+" "+inventory.getPExp(), (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);
    }
}
