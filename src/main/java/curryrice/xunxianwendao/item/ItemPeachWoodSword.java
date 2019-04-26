package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.init.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTier;
import net.minecraft.util.ResourceLocation;

public class ItemPeachWoodSword extends ItemSword{
	
	public ItemPeachWoodSword() {
		super(ItemTier.WOOD,4,-2.0F,(new Item.Properties()).group(CreativeTabs.MAIN));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID,"item_peach_wood_sword"));
	}
	
	@Override
	public float getAttackDamage() {
	      return super.getAttackDamage()/2;
	}

}
