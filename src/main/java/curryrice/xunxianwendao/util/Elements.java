package curryrice.xunxianwendao.util;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class Elements {
	private int metal, wood, water, fire, earth;
	
	public Elements() {
		this(0,0,0,0,0);
	}
	public Elements(int metal,int wood,int water,int fire,int earth) {
		this.metal=metal;
		this.wood=wood;
		this.water=water;
		this.fire=fire;
		this.earth=earth;
	}
	public void add(Elements e) {
		this.setMetal(this.getMetal()+e.getMetal());
		this.setWood(this.getWood()+e.getWood());
		this.setWater(this.getWater()+e.getWater());
		this.setFire(this.getFire()+e.getFire());
		this.setEarth(this.getEarth()+e.getEarth());
	}
	
	public void set(Elements e) {
		this.setMetal(e.getMetal());
		this.setWood(e.getWood());
		this.setWater(e.getWater());
		this.setFire(e.getFire());
		this.setEarth(e.getEarth());
	}

	
	@Override
	public boolean equals(Object e) {
		if(e instanceof Elements) {
			Elements e2=((Elements)e);
			if(this.getMetal()!=e2.getMetal()) {return false;}
			if(this.getWood()!=e2.getWood()) {return false;}
			if(this.getWater()!=e2.getWater()) {return false;}
			if(this.getFire()!=e2.getFire()) {return false;}
			if(this.getEarth()!=e2.getEarth()) {return false;}
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		int result=17;
		result = result * 31 + this.metal;
		result = result * 31 + this.wood;
		result = result * 31 + this.water;
		result = result * 31 + this.fire;
		result = result * 31 + this.earth;
		return result;
	}
	@Override
	public String toString() {
    	String res="";
    	if(this.metal!=0) {res+=this.metal+I18n.format("elements.xunxianwendao.metal");}
    	if(this.wood!=0) {res+=this.wood+I18n.format("elements.xunxianwendao.wood");}
    	if(this.water!=0) {res+=this.water+I18n.format("elements.xunxianwendao.water");}
    	if(this.fire!=0) {res+=this.fire+I18n.format("elements.xunxianwendao.fire");}
    	if(this.earth!=0) {res+=this.earth+I18n.format("elements.xunxianwendao.earth");}
    	return res;
	}
	
	public static boolean equal(Elements e1, Elements e2) {
		if(e1.getMetal()!=e2.getMetal()) {return false;}
		if(e1.getWood()!=e2.getWood()) {return false;}
		if(e1.getWater()!=e2.getWater()) {return false;}
		if(e1.getFire()!=e2.getFire()) {return false;}
		if(e1.getEarth()!=e2.getEarth()) {return false;}
		return true;
	}
	
	public static Elements plus(Elements e1, Elements e2) {
		Elements e=new Elements();
		e.setMetal(e1.getMetal()+e2.getMetal());
		e.setWood(e1.getWood()+e2.getWood());
		e.setWater(e1.getWater()+e2.getWater());
		e.setFire(e1.getFire()+e2.getFire());
		e.setEarth(e1.getEarth()+e2.getEarth());
		return e;
	}
	
	public static Elements getElementsFromMCItem(Item item) {
		if(IElements.class.isAssignableFrom(item.getClass())) {
			return ((IElements)item).getElements();
		}else {
			
			if(item==Items.COAL) {return new Elements(0,0,0,1,0);}
			if(item==Items.LAVA_BUCKET) {return new Elements(0,0,0,2,0);}
			if(item==Items.DIAMOND) {return new Elements(1,0,0,0,0);}
			
			return new Elements(0,0,0,0,0);
		}
	}
	public static Elements getElementsFromMCItem(Block block) {
		if(IElements.class.isAssignableFrom(block.getClass())) {
			return ((IElements)block).getElements();
		}else {
			return getElementsFromMCItem(Item.getItemFromBlock(block));
		}
	}
	public static Elements getElementsFromMCItem(ItemStack itemstack) {
		Item item=itemstack.getItem();
		if(item instanceof ItemBlock) {
			return getElementsFromMCItem(Block.getBlockFromItem(item));
		}else {
			return getElementsFromMCItem(item);
		}
	}
	public static Elements getElementsFromMCItem(ItemStack... itemstacks) {
		Elements elements=new Elements(0,0,0,0,0);
		for(ItemStack itemstack:itemstacks) {
			elements.add(getElementsFromMCItem(itemstack));
		}
		return elements;
	}
	
	public int getMetal() {
		return metal;
	}

	public void setMetal(int metal) {
		this.metal = metal;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getFire() {
		return fire;
	}

	public void setFire(int fire) {
		this.fire = fire;
	}

	public int getEarth() {
		return earth;
	}

	public void setEarth(int earth) {
		this.earth = earth;
	}
	
	/*public static enum MCElements{
		;
		private static final Map<Integer, Elements.MCElements> META_LOOKUP = Maps.<Integer, Elements.MCElements>newHashMap();
		private int id;
		private Item item;
		private Elements elements;
		private MCElements(int id,Item item,Elements elements){
			this.id=id;
			this.item=item;
			this.elements=elements;
		}
		private MCElements(int id,Block block,Elements elements){
			this.id=id;
			this.item=Item.getItemFromBlock(block);
			this.elements=elements;
		}
		public int getId() {
			return id;
		}
		public Item getItem() {
			return item;
		}
		public Elements getElements() {
			return elements;
		}
		
		public static MCElements byId() {
			
		}
		
		static 
		{
            for (Elements.MCElements ele : values())
            {
                META_LOOKUP.put(Integer.valueOf(ele.getId()), ele);
            }
		}
	}*/
	
}
