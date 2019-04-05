package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.init.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class ItemJadeSword extends ItemSword{
	public ItemJadeSword() {
		super(ToolMaterial.jade,4,-2.0F,(new Item.Properties()).group(CreativeTabs.MAIN));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.modid,"item_jade_sword"));
	}

}
