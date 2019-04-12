package curryrice.xunxianwendao.entity.item;

import java.util.concurrent.atomic.AtomicInteger;

import curryrice.xunxianwendao.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTalisman extends EntityThrowable{
	private int meta;
	private float quality;
	
	private AtomicInteger ticksToWait=new AtomicInteger(-1);
	private double speed;
	private EntityLivingBase entity;

	public EntityTalisman(World worldIn) {
		super(EntityList.entity_talisman, worldIn);
	}
	public EntityTalisman(World worldIn, EntityLivingBase throwerIn,int meta,float quality){
        super(EntityList.entity_talisman,throwerIn, worldIn);
        this.meta=meta;
        this.quality=quality;
    }
	
	public EntityTalisman(World worldIn,int meta) {
		super(EntityList.entity_talisman, worldIn);
		this.meta=meta;
	}

	@Override
	public void onImpact(RayTraceResult result) {
		if(!this.world.isRemote) {
			switch (meta) {
			case 1: //unmove
				if(result.type == RayTraceResult.Type.ENTITY && result.entity instanceof EntityLivingBase) {
					ticksToWait.set((int)quality*20);
					System.out.println("set ticksToWait"+this.world.isRemote);
					this.world.tickEntity(this, true);
					this.entity = (EntityLivingBase)result.entity;
					this.speed=entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue();
					this.entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
					
					entity.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
				}else {
					this.remove();
					System.out.println("remove1");
				}
				this.addVelocity(-this.motionX,-this.motionY, -this.motionZ);
				break;
			case 2: //burding
				if(result.type == RayTraceResult.Type.ENTITY && result.entity instanceof EntityLivingBase) {
					this.entity = (EntityLivingBase)result.entity;
					this.entity.setFire((int)quality);
					this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
				}else if(result.type == RayTraceResult.Type.BLOCK){
					BlockPos pos=result.getBlockPos().offset(result.sideHit);
					if(this.world.isAirBlock(pos))
						this.world.setBlockState(pos, Blocks.FIRE.getDefaultState());
					this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
				}
				this.remove();
				System.out.println("remove2");
				break;
			case 3://explosion, quality TNT is 4.0F
				this.world.createExplosion(this, this.posX, this.posY + (double)(this.height / 16.0F), this.posZ, quality, true);
				this.remove();
				System.out.println("remove3");
				break;
			case 4://thunder
				this.world.addWeatherEffect(new EntityLightningBolt(world,this.posX,this.posY,this.posZ,false));
				this.remove();
				System.out.println("remove4");
				break;
			case 5://nausea
				if(result.type == RayTraceResult.Type.ENTITY && result.entity instanceof EntityLivingBase) {
					this.entity = (EntityLivingBase)result.entity;
					this.entity.addPotionEffect(new PotionEffect(Potion.getPotionById(9),(int)quality*20));
				}
				this.remove();
				System.out.println("remove5");
				break;
			case 6://teleport
				if(result.type == RayTraceResult.Type.ENTITY && result.entity instanceof EntityLivingBase) {
					this.entity = (EntityLivingBase)result.entity;
					entity.setPositionAndUpdate(this.getThrower().posX, this.getThrower().posY, this.getThrower().posZ);
					entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
				}else if(result.type == RayTraceResult.Type.BLOCK) {
					BlockPos pos=result.getBlockPos().offset(result.sideHit);
					this.getThrower().playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
					this.getThrower().setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
					this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
				}
				this.remove();
				System.out.println("remove6");
				break;

			default:
				this.remove();
				System.out.println("remove7");
				break;
			}
		}
		
	}
	@Override
    protected float getGravityVelocity(){
        return 0.0F;
    }
	
	@Override
	public void tick() {
		if(this.ticksToWait.get()==-1) {
			System.out.println("11111"+this.world.isRemote);
			if(!this.world.isRemote && MathHelper.abs((float)this.motionX)<0.1 && MathHelper.abs((float)this.motionY)<0.1 && MathHelper.abs((float)this.motionZ)<0.1 ) { 
				System.out.println("remove8 start"+this.world.isRemote);
				this.remove();
				System.out.println("remove8 end"+this.world.isRemote);
			}
			super.tick();
		}else if(this.ticksToWait.get()==0) {
			entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(speed);
			entity.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1.0F, 1.0F);
			this.remove();
			System.out.println("remove9");
		}else {
			this.ticksToWait.decrementAndGet();
		}
	}

}
