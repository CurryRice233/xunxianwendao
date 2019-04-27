package curryrice.xunxianwendao.util;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class DrugRefining {
	public static HashMap<Elements,ItemStack> map=new HashMap<>();
	static {
		/*addToMap(new Elements(0,3,0,0,0),new ItemStack(ItemLoader.medicine,1,0));//再生
		addToMap(new Elements(1,0,0,0,2),new ItemStack(ItemLoader.medicine,1,1));//大力
		addToMap(new Elements(3,0,0,0,0),new ItemStack(ItemLoader.medicine,1,2));//金甲
		addToMap(new Elements(2,1,0,0,0),new ItemStack(ItemLoader.medicine,1,3));//铁杉
		addToMap(new Elements(0,0,0,0,3),new ItemStack(ItemLoader.medicine,1,4));//急迫
		addToMap(new Elements(0,0,2,0,1),new ItemStack(ItemLoader.medicine,1,5));//速度
		addToMap(new Elements(0,0,1,0,2),new ItemStack(ItemLoader.medicine,1,6));//轻跃
		addToMap(new Elements(0,0,0,3,0),new ItemStack(ItemLoader.medicine,1,7));//抗火
		addToMap(new Elements(0,2,1,0,0),new ItemStack(ItemLoader.medicine,1,8));//夜视
		addToMap(new Elements(0,0,3,0,0),new ItemStack(ItemLoader.medicine,1,9));//龟息*/
	}
	
	public static ItemStack addToMap(Elements key,ItemStack value) {
		return map.put(key, value);
	}
	
	public static ItemStack getFormMap(Elements key) {
		ItemStack itemstack=map.get(key);
		if(itemstack==null) {
			return ItemStack.EMPTY;
		}
		return itemstack;
	}

}
