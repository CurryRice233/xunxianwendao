package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.init.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemPeach extends ItemFood{
	public ItemPeach(int meta) {
		super(7, 0.7F, false, new Properties().group(CreativeTabs.MAIN));
		switch(meta) {
		case 1:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "item_peach_century"));
			break;
		case 2:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "item_peach_millennium"));
			break;
		default:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "item_peach"));
		}
		
	}
	
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		
	}
}
