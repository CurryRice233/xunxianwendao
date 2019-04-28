package curryrice.xunxianwendao.util;

import java.util.HashMap;

import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.item.Item;

public class CauldronFurnaceRecipes {
	public static HashMap<Elements, Item> map=new HashMap<>();
	static {
		map.put(new Elements(0,3,0,0,0),ItemList.ITEM_MEDICINE_REGENERATION);//再生
		map.put(new Elements(1,0,0,0,2),ItemList.ITEM_MEDICINE_STRENGTH);//大力
		map.put(new Elements(3,0,0,0,0),ItemList.ITEM_MEDICINE_ABSORPTION);//金甲
		map.put(new Elements(2,1,0,0,0),ItemList.ITEM_MEDICINE_RESISTANCE);//铁杉
		map.put(new Elements(0,0,0,0,3),ItemList.ITEM_MEDICINE_HASTE);//急迫
		map.put(new Elements(0,0,2,0,1),ItemList.ITEM_MEDICINE_SPEED);//速度
		map.put(new Elements(0,0,1,0,2),ItemList.ITEM_MEDICINE_JUMP_BOOST);//轻跃
		map.put(new Elements(0,0,0,3,0),ItemList.ITEM_MEDICINE_FIRE_RESISTANCE);//抗火
		map.put(new Elements(0,2,1,0,0),ItemList.ITEM_MEDICINE_NIGHT_VISION);//夜视
		map.put(new Elements(0,0,3,0,0),ItemList.ITEM_MEDICINE_WATER_BREATHING);//龟息
	}
	
	public static Item getFormMap(Elements key) {
		return map.get(key);
	}

}
