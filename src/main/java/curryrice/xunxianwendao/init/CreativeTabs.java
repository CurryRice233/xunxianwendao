package curryrice.xunxianwendao.init;

import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CreativeTabs {
	public static ItemGroup MAIN=new ItemGroup("xunxianwendao_main") {
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(ItemList.item_jade_primary);
		}
	};
}
