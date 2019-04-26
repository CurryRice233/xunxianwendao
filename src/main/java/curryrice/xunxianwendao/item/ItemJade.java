package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.init.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemJade extends Item {
	public ItemJade(int meta) {
		super(new Item.Properties().group(CreativeTabs.MAIN));
		switch(meta) {
		case 1:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "item_jade_intermediate"));break;
		case 2:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "item_jade_advanced"));break;
		default:
			this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "item_jade_primary"));break;
		}
		
	}
}
