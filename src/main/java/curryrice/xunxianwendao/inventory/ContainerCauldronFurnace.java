package curryrice.xunxianwendao.inventory;

import curryrice.xunxianwendao.tileentity.TileEntityCauldronFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCauldronFurnace extends Container {
    private IItemHandler items;
    private Boolean burning = false;
    private int cookTime = 0;
    private int totalCookTime = 0;
    private float PExp = 0;

    private TileEntityCauldronFurnace tileEntity;

    public ContainerCauldronFurnace(InventoryPlayer playerInv, TileEntityCauldronFurnace tileEntity) {
        super();
        this.tileEntity = tileEntity;
        this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP).ifPresent(cap -> {
            items = cap;
        });
        this.addSlot(new SlotItemHandler(items, 0, 15, 25));
        this.addSlot(new SlotItemHandler(items, 1, 33, 25));
        this.addSlot(new SlotItemHandler(items, 2, 51, 25));
        this.addSlot(new SlotItemHandler(items, 3, 139, 34) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        });

        // inventory of player 3x9
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // inventory bar of player 1x9
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icontainerlistener = this.listeners.get(i);
            if (this.burning != this.tileEntity.isBurning()) {
                icontainerlistener.sendWindowProperty(this, 0, this.tileEntity.isBurning() ? 0 : 1);
            }
            if (this.cookTime != this.tileEntity.getCookTime()) {
                icontainerlistener.sendWindowProperty(this, 1, this.tileEntity.getCookTime());
            }
            if (this.totalCookTime != this.tileEntity.getTotalCookTime()) {
                icontainerlistener.sendWindowProperty(this, 2, this.tileEntity.getTotalCookTime());
            }
            if (this.PExp != this.tileEntity.getPExp()) {
                icontainerlistener.sendWindowProperty(this, 3, (int) this.tileEntity.getPExp());
            }
        }
        this.burning = tileEntity.isBurning();
        this.cookTime = tileEntity.getCookTime();
        this.totalCookTime = tileEntity.getTotalCookTime();
        this.PExp = tileEntity.getPExp();
    }

    @OnlyIn(Dist.CLIENT)
    public void updateProgressBar(int id, int data) {
        super.updateProgressBar(id, data);
        switch (id) {
            case 0:
                this.burning = data == 0;
                break;
            case 1:
                this.cookTime = data;
                break;
            case 2:
                this.totalCookTime = data;
                break;
            case 3:
                this.PExp = data;
                break;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;//playerIn.getDistanceSq(this.);
    }

    public boolean isBurning() {
        return this.burning;
    }
    public int getCookTime() {
        return cookTime;
    }
    public int getTotalCookTime() {
        return totalCookTime;
    }
    public float getPExp() {
        return PExp;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();
        boolean isMerged = false;

        if (index >=0 && index <4) {
            isMerged = mergeItemStack(newStack, 4, 40, true);
        } else if (index >= 4 && index < 31) {
            isMerged = mergeItemStack(newStack, 0, 4, false) || mergeItemStack(newStack, 31, 40, false);
        } else if (index >= 31 && index < 40) {
            isMerged = mergeItemStack(newStack, 0, 4, false) || mergeItemStack(newStack, 4, 31, false);
        }

        if (!isMerged) {
            return ItemStack.EMPTY;
        }

        if (newStack.getMaxStackSize() == 0) {
            slot.putStack(null);
        } else {
            slot.onSlotChanged();
        }

        slot.onTake(playerIn, newStack);
        return oldStack;
    }
}
