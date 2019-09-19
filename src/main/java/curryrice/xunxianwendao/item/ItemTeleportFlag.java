package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.init.CreativeTabs;
import curryrice.xunxianwendao.util.Teleporter;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.DimensionManager;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTeleportFlag extends Item {
    public ItemTeleportFlag(){
        super(new Item.Properties().group(CreativeTabs.MAIN));
        this.setRegistryName("item_teleport_flag");
    }

    @Override
    public EnumAction getUseAction(ItemStack p_77661_1_) {
        return EnumAction.SPEAR;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 64;
    }

    /*@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

    }*/
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(stack.getOrCreateTag().getInt("dimensionID")==entityLiving.dimension.getId()){
            entityLiving.attemptTeleport(stack.getOrCreateTag().getInt("x"),stack.getOrCreateTag().getInt("y"),
                    stack.getOrCreateTag().getInt("z"));
        }else{
            entityLiving.changeDimension(DimensionType.getById(stack.getOrCreateTag().getInt("dimensionID")),
                    new Teleporter(stack.getOrCreateTag().getInt("x"),stack.getOrCreateTag().getInt("y"),
                            stack.getOrCreateTag().getInt("z")));
        }
        stack.damageItem(1,entityLiving);
        return stack;
    }
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            ItemStack itemStack = new ItemStack(this);
            DimensionType dimension = DimensionType.getById(0);
            itemStack.getOrCreateTag().setString("worldName",DimensionType.func_212678_a(dimension).toString());
            itemStack.getOrCreateTag().setInt("dimensionID",dimension.getId());
            itemStack.getOrCreateTag().setInt("x",0);
            itemStack.getOrCreateTag().setInt("y",0);
            itemStack.getOrCreateTag().setInt("z",0);
            items.add(itemStack);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TextComponentTranslation("talisman.xunxianwendao.teleportTo")
                .appendText(
                        stack.getOrCreateTag().getString("worldName")+
                        stack.getOrCreateTag().getInt("dimensionID")+"("+
                        stack.getOrCreateTag().getInt("x")+" , "+
                        stack.getOrCreateTag().getInt("y")+" , "+
                        stack.getOrCreateTag().getInt("z")+")")
                .applyTextStyle(TextFormatting.GRAY));
    }
}
