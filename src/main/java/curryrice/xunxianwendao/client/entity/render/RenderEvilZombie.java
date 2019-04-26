package curryrice.xunxianwendao.client.entity.render;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.entity.monster.EntityEvilZombie;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderEvilZombie extends RenderLivingBase<EntityEvilZombie>{
	private static final ResourceLocation EVIL_ZOMBIE_TEXTURE = new ResourceLocation(XunXianWenDao.MODID + ":" + "textures/entity/evil_zombie.png");

	public RenderEvilZombie(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelZombie(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityEvilZombie entity) {
		return EVIL_ZOMBIE_TEXTURE;
	}

}
