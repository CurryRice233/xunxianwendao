package curryrice.xunxianwendao.item;

import java.util.List;

import javax.annotation.Nullable;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.entity.item.EntityTalisman;
import curryrice.xunxianwendao.init.CreativeTabs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemTalisman extends Item{
	private int meta;
	public ItemTalisman(int meta,String name) {
		super(new Item.Properties().group(CreativeTabs.MAIN));
		this.meta=meta;	
		this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "item_talisman_"+name));
	}
	
	public int getMeta() {
		return meta;
	}
	public int getUseDuration(ItemStack stack) {
	      return 1;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
	    return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
	      if (entityplayer == null || !entityplayer.abilities.isCreativeMode) {
	         stack.shrink(1);
	      }

	      if (entityplayer instanceof EntityPlayerMP) {
	         CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
	      }

	      if (!worldIn.isRemote) {
	    	  EntityTalisman talisman= new EntityTalisman(worldIn,entityLiving,meta,stack.getOrCreateTag().getFloat("quality"));
	    	  talisman.shoot(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0.0F, 1.0F, 0.0F);
	    	  worldIn.spawnEntity(talisman);
	      }
		return stack;
	}
	
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)) {
			ItemStack itemStack = new ItemStack(this);
			switch (meta) {
			case 1://unmove
				itemStack.getOrCreateTag().setFloat("quality", 10.0F);
				break;
			case 2://burding
				itemStack.getOrCreateTag().setFloat("quality", 10.0F);
				break;
			case 3://explosion, quality TNT is 4.0F
				itemStack.getOrCreateTag().setFloat("quality", 4.0F);
				break;
			case 5://nausea
				itemStack.getOrCreateTag().setFloat("quality", 10.0F);
				break;
			default:
				break;
			}
			items.add(itemStack);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("talisman.xunxianwendao.quality")
				.appendText(String.valueOf((int)stack.getOrCreateTag().getFloat("quality")))
				.applyTextStyle(TextFormatting.GRAY));
	}
}
