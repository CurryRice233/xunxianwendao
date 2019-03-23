package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.CreativeTabs;
import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTier;
import net.minecraft.util.ResourceLocation;

public class ItemJadeSword extends ItemSword{
	public ItemJadeSword() {
		super(ItemTier.DIAMOND,4,-2.0F,(new Item.Properties()).group(CreativeTabs.MAIN));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.modid,"item_jade_sword"));
	}

}
