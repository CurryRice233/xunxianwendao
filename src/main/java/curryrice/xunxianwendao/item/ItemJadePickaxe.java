package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.CreativeTabs;
import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

public class ItemJadePickaxe extends ItemPickaxe {
	public ItemJadePickaxe() {
		super(ToolMaterial.jade, 2, -1.0F, (new Item.Properties()).group(CreativeTabs.MAIN));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.modid,"item_jade_pickaxe"));
	}

}
