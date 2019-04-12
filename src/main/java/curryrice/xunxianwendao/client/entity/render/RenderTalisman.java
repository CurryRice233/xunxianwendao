package curryrice.xunxianwendao.client.entity.render;

import curryrice.xunxianwendao.entity.item.EntityTalisman;
import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSprite;
import net.minecraft.item.ItemStack;

public class RenderTalisman extends RenderSprite<EntityTalisman>{
	public RenderTalisman(RenderManager renderManagerIn) {
		super(renderManagerIn,ItemList.ITEM_TALISMAN_UNMOVE , Minecraft.getInstance().getItemRenderer());
	}
	
	@Override
    public ItemStack getStackToRender(EntityTalisman entityIn)
    {
        return new ItemStack(this.item);
    }
}
