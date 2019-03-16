package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.CreativeTabs;
import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemJade extends Item {
	public ItemJade() {
		super(new Item.Properties().group(CreativeTabs.MAIN));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "item_jade"));
	}
}
