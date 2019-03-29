package curryrice.xunxianwendao.entity.monster;

import curryrice.xunxianwendao.entity.EntityList;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEvilZombie extends EntityZombie{

	public EntityEvilZombie(World worldIn) {
		super(worldIn);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32D);
	}
	
	@Override
    public void onDeath(DamageSource cause){
		super.onDeath(cause);
    }
	@Override
	public EntityType<?> getType() {
		return EntityList.entity_evil_zombie;
	}

}
