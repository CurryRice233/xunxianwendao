package curryrice.xunxianwendao.client.gui;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.inventory.ContainerCauldronFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiContainerCauldronFurnace extends GuiContainer {
    private static final String TEXTURE_PATH= XunXianWenDao.MODID + ":" + "textures/gui/container/gui_cauldron_furnace.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    private ContainerCauldronFurnace container;
    public GuiContainerCauldronFurnace(ContainerCauldronFurnace containerCauldronFurnace) {
        super(containerCauldronFurnace);
        this.container=containerCauldronFurnace;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color3f(1.0F, 1.0F, 1.0F);
        this.drawDefaultBackground();


        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        /*int burnTime=this.inventory.getBurnTime();
        int cookTime=this.inventory.getCookTime();
        this.totalBurnTime=inventory.getTotalBurnTime();
        this.totalCookTime=inventory.getTotalCookTime();

        int cookTextureWidth = 1 + (int) Math.ceil(22.0 * cookTime / this.totalCookTime);
        this.drawTexturedModalRect(offsetX + 109, offsetY + 34, 176, 14, cookTextureWidth, 17);

        int burnTextureHeight = 1 + (int) Math.ceil(14.0 * burnTime / this.totalBurnTime);
        this.drawTexturedModalRect(offsetX + 36, offsetY + 34  + (14-burnTextureHeight), 176, 14-burnTextureHeight, 14, burnTextureHeight);
        */
        if(container.isBurning()){
            this.drawTexturedModalRect(offsetX + 35, offsetY + 48, 177, 0, 14, 14);
        }


    }
}
