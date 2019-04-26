package curryrice.xunxianwendao.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityCauldronFurnace extends TileEntity implements ITickable {
    private boolean isBurning = false;
    private int totalBurnTime=0;
    private int cookTime = 0;
    private int totalCookTime = 0;
    private float PExp=0;

    protected ItemStackHandler inventory = new ItemStackHandler(4);
    public TileEntityCauldronFurnace() {
        super(TileEntityList.CAULDRON_FURNACE);
    }

    @Override
    public void tick() {
        Block blockDown = this.world.getBlockState(pos.down()).getBlock();
        isBurning = blockDown == Blocks.FIRE;
    }

    public @Nonnull
    <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable EnumFacing side){
        if(cap.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)){
            return LazyOptional.of(() -> inventory).cast();
        }
        return LazyOptional.empty();
    }


    public int getCookTimeNeed(ItemStack stack)
    {
        return 200;
    }

    @Override
    public void read(NBTTagCompound compound) {
        super.read(compound);
        this.inventory.deserializeNBT(compound.getCompound("Inventory"));
        this.isBurning = compound.getBoolean("Burning");
        this.cookTime = compound.getInt("CookTime");
        this.totalCookTime = compound.getInt("TotalCookTime");
        this.totalBurnTime = compound.getInt("TotalBurnTime");
        this.PExp=compound.getFloat("PExp");
    }

    @Override
    public NBTTagCompound write(NBTTagCompound compound)
    {
        super.write(compound);
        compound.setTag("Inventory", this.inventory.serializeNBT());
        compound.setBoolean("Burning", this.isBurning);
        compound.setInt("CookTime", this.cookTime);
        compound.setInt("TotalCookTime", this.totalCookTime);
        compound.setInt("TotalBurnTime", this.totalBurnTime);
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
}
