package curryrice.xunxianwendao.entity.monster;

import curryrice.xunxianwendao.entity.EntityList;
import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
		Entity entity = cause.getTrueSource();
		if(entity instanceof EntityPlayer) {
			ItemStack stack=((EntityPlayer)entity).getHeldItemMainhand();
			if(stack.getItem()==ItemList.ITEM_JADE_SWORD && this.rand.nextInt(10)<1) {
				this.entityDropItem(new ItemStack(ItemList.ITEM_JADE_PRIMARY, 1));
			}else if(stack.getItem()==ItemList.ITEM_PEACH_WOOD_SWORD) {
				this.entityDropItem(new ItemStack(ItemList.ITEM_JADE_PRIMARY, 1));
			}
			
		}
    }
	@Override
	public EntityType<?> getType() {
		return EntityList.entity_evil_zombie;
	}

}
