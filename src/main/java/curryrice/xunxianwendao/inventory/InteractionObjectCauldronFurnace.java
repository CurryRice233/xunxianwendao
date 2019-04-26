package curryrice.xunxianwendao.inventory;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.tileentity.TileEntityCauldronFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;

import javax.annotation.Nullable;

public class InteractionObjectCauldronFurnace implements IInteractionObject {
    private TileEntityCauldronFurnace tileEntityCauldronFurnace;
    public InteractionObjectCauldronFurnace(TileEntityCauldronFurnace tileEntityCauldronFurnace){
        this.tileEntityCauldronFurnace=tileEntityCauldronFurnace;
    }

    @Override
    public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
        return new ContainerCauldronFurnace(inventoryPlayer,this.tileEntityCauldronFurnace);
    }

    @Override
    public String getGuiID() {
        return XunXianWenDao.MODID +":gui_cauldron_furnace";
    }

    @Override
    public ITextComponent getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return null;
    }
}
