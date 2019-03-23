package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.CreativeTabs;
import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemJade extends Item {
	public ItemJade(int level) {
		super(new Item.Properties().group(CreativeTabs.MAIN));
		switch(level) {
		case 1:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "item_jade_intermediate"));break;
		case 2:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "item_jade_advanced"));break;
		default:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "item_jade_primary"));break;
		}
		
	}
}
