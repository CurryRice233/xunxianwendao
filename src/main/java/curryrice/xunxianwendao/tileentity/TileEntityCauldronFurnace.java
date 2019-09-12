package curryrice.xunxianwendao.tileentity;

import curryrice.xunxianwendao.block.BlockList;
import curryrice.xunxianwendao.util.CauldronFurnaceRecipes;
import curryrice.xunxianwendao.util.Elements;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityCauldronFurnace extends TileEntity implements ITickable {
    private boolean isBurning = false;
    private int cookTime = 0;
    private int totalCookTime = 0;
    private float PExp = 0;

    private boolean cooking = false;
    protected ItemStackHandler inventory = new ItemStackHandler(4);

    public TileEntityCauldronFurnace() {
        super(TileEntityList.CAULDRON_FURNACE);
    }

    @Override
    public void tick() {
        if (!this.world.isRemote()) {
            Block blockDown = this.world.getBlockState(pos.down()).getBlock();
            isBurning = blockDown == Blocks.FIRE;

            Item item = CauldronFurnaceRecipes.getFormMap(Elements.getElementsFromMCItem(
                    inventory.getStackInSlot(0), inventory.getStackInSlot(1), inventory.getStackInSlot(2)));
            ItemStack itemStackToDo = item == null ? ItemStack.EMPTY : new ItemStack(item);
            ItemStack itemStackIn = inventory.getStackInSlot(3);

            if (isBurning && canSmelt(itemStackToDo, itemStackIn)) {
                ++this.cookTime;
                this.totalCookTime = this.getCookTimeNeed(itemStackToDo);
                if (this.cookTime >= this.totalCookTime) {
                    this.cookTime = 0;
                    this.PExp += this.totalCookTime / 200;
                    this.smeltItem(itemStackToDo, itemStackIn);
                    this.markDirty();
                }

                if (!cooking) {
                    cooking = true;
                    this.world.setBlockState(pos, BlockList.CAULDRON_FURNACE.getDefaultState().with(BlockList.CAULDRON_FURNACE.getBurningProperty(), Boolean.TRUE));
                    this.markDirty();
                }
            } else {
                if (cooking) {
                    cooking = false;
                    this.world.setBlockState(pos, BlockList.CAULDRON_FURNACE.getDefaultState().with(BlockList.CAULDRON_FURNACE.getBurningProperty(), Boolean.FALSE));
                    this.markDirty();
                }
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }


        }
    }

    public @Nonnull
    <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable EnumFacing side) {
        if (cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)) {
                return LazyOptional.of(() -> inventory).cast();
        }
        return LazyOptional.empty();
    }


    public int getCookTimeNeed(ItemStack stack) {
        return 200;
    }

    @Override
    public void read(NBTTagCompound compound) {
        super.read(compound);
        this.inventory.deserializeNBT(compound.getCompound("Inventory"));
        this.isBurning = compound.getBoolean("Burning");
        this.cookTime = compound.getInt("CookTime");
        this.totalCookTime = compound.getInt("TotalCookTime");
        this.PExp = compound.getFloat("PExp");
    }

    @Override
    public NBTTagCompound write(NBTTagCompound compound) {
        super.write(compound);
        compound.setTag("Inventory", this.inventory.serializeNBT());
        compound.setBoolean("Burning", this.isBurning);
        compound.setInt("CookTime", this.cookTime);
        compound.setInt("TotalCookTime", this.totalCookTime);
        compound.setFloat("PExp", this.PExp);
        return compound;
    }

    public boolean isBurning() {
        return this.isBurning;
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

    public void setPExp(float pExp) {
        this.PExp = pExp;
    }

    private boolean canSmelt(ItemStack itemStackToDo, ItemStack itemStackIn) {
        if (itemStackToDo.isEmpty())
            return false;
        if (itemStackIn.isEmpty())
            return true;
        if (!itemStackToDo.isItemEqual(itemStackIn))
            return false;
        return itemStackToDo.getCount() + itemStackIn.getCount() <= itemStackToDo.getMaxStackSize();
    }

    public void smeltItem(ItemStack itemStackToDo, ItemStack itemStackIn) {
        if (itemStackIn.isEmpty()) {
            inventory.setStackInSlot(3, itemStackToDo.copy());
        } else {
            itemStackIn.grow(itemStackToDo.getCount());
        }
        inventory.getStackInSlot(0).shrink(1);
        inventory.getStackInSlot(1).shrink(1);
        inventory.getStackInSlot(2).shrink(1);
    }
}
