package curryrice.xunxianwendao.capability;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface ICapabilityXunXianWenDao extends INBTSerializable<NBTTagCompound> {
	public int getLevel();
	public int getPharmacistLevel();
	public float getMagic();
	public float getMagicMax();
	public float getExperience();
	public float getExperienceMax();
	public float getPharmacistExperience();
	public float getPharmacistExperienceMax();
	public float getMaxHealth();
	
	public void setLevel(int level);
	public void setPharmacistLevel(int level);
	public void setMagic(float magic);
	public void setExperience(float exp);
	public void setPharmacistExperience(float exp);
	public void setMaxHealth(float health);
	
	public void addMagic(float magic);
	public void addExperience(float exp);
	public void addPharmacistExperience(float exp);
	public void addMaxHealth(float health);
	
	public String getLevelName();
	public String getPharmacistLevelName();
	
	public void sync(@Nonnull EntityPlayerMP player);
}
