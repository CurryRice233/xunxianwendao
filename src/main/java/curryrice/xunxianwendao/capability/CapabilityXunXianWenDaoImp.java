package curryrice.xunxianwendao.capability;

import javax.annotation.Nonnull;

import curryrice.xunxianwendao.network.NetworkLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public final class CapabilityXunXianWenDaoImp implements ICapabilityXunXianWenDao {
    private int level;
    private int pharmacistLevel;
    private float magic;
    private float experience;
    private float pharmacistExperience;
    private float maxHealth;

    public CapabilityXunXianWenDaoImp() {
        this.maxHealth = 10;
    }


    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getPharmacistLevel() {
        return this.pharmacistLevel;
    }

    @Override
    public float getMagic() {
        return this.magic;
    }

    @Override
    public float getExperience() {
        return this.experience;
    }

    @Override
    public float getPharmacistExperience() {
        return this.pharmacistExperience;
    }

    @Override
    public float getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void setLevel(int level) {
        this.level = level > 0 ? level : 0;
    }

    @Override
    public void setPharmacistLevel(int level) {
        this.pharmacistLevel = level;
    }

    @Override
    public void setMagic(float magic) {
        this.magic = magic;
    }

    @Override
    public void setExperience(float exp) {
        this.experience = exp;
    }

    @Override
    public void setPharmacistExperience(float exp) {
        this.pharmacistExperience = exp;
    }

    @Override
    public void setMaxHealth(float health) {
        this.maxHealth = health;
    }

    @Override
    public void addMagic(float magic) {
        if ((this.magic + magic) > this.getMagicMax()) {
            this.magic = this.getMagicMax();
        } else {
            this.magic = (this.magic + magic > 0) ? this.magic + magic : 0;
        }

    }

    @Override
    public void addExperience(float exp) {
        if (this.getExperienceMax() > 0 && (this.experience + exp) > this.getExperienceMax()) {
            this.experience = 0;
            this.level++;
            this.addMaxHealth(6);
        } else {
            this.experience += exp;
        }
    }

    @Override
    public void addPharmacistExperience(float exp) {
        if (this.getPharmacistExperienceMax() > 0 && (this.pharmacistExperience + exp) > this.getPharmacistExperienceMax()) {
            this.pharmacistExperience = 0;
            this.pharmacistLevel++;
        } else {
            this.pharmacistExperience += exp;
        }
    }

    @Override
    public void addMaxHealth(float health) {
        this.maxHealth += health;
    }

    @Override
    public float getMagicMax() {
        switch (this.level) {
            case 0:
                return 0;
            case 1:
                return 200;
            case 2:
                return 300;
            case 3:
                return 500;
            case 4:
                return 700;
            case 5:
                return 1000;
            case 6:
                return 1500;
            case 7:
                return 2000;
            case 8:
                return 3000;
            case 9:
                return 5000;
            case 10:
                return 10000;
            default:
                return 1000;
        }
    }

    @Override
    public float getExperienceMax() {
        switch (this.level) {
            case 0:
                return 100;
            case 1:
                return 200;
            case 2:
                return 300;
            case 3:
                return 500;
            case 4:
                return 700;
            case 5:
                return 1000;
            case 6:
                return 1500;
            case 7:
                return 2000;
            case 8:
                return 3000;
            case 9:
                return 5000;
            case 10:
                return 10000;
            default:
                return -1;
        }
    }

    public float getPharmacistExperienceMax() {
        switch (this.pharmacistLevel) {
            case 0:
                return 100;
            case 1:
                return 200;
            case 2:
                return 300;
            case 3:
                return 500;
            case 4:
                return 700;
            case 5:
                return 1000;
            default:
                return -1;
        }
    }


    @Override
    public String getLevelName() {
        return I18n.format("level.xunxianwendao.level.name") + ": " + I18n.format("level.xunxianwendao.level" + this.level);
    }

    @Override
    public String getPharmacistLevelName() {
        return I18n.format("level.xunxianwendao.pharmacistLevel.name") + ": " + I18n.format("level.xunxianwendao.pharmacistLevel" + this.pharmacistLevel);
    }

    @Override
    public String toString() {
        String string = getLevelName() + this.experience + " | ";
        string += getPharmacistLevelName() + this.pharmacistExperience + " | ";
        string += "MaxHealth: " + this.getMaxHealth() + " | ";
        string += "Magic: " + this.getMagic();
        return string;
    }


    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setFloat("maxHealth", this.getMaxHealth());
        compound.setInt("level", this.getLevel());
        compound.setInt("plevel", this.getPharmacistLevel());
        compound.setFloat("magic", this.getMagic());
        compound.setFloat("exp", this.getExperience());
        compound.setFloat("pexp", this.getPharmacistExperience());

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("xunxianwendao", compound);
        return nbt;
    }


    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        NBTTagCompound compound = (NBTTagCompound) nbt.getTag("xunxianwendao");
        if (!compound.isEmpty()) {
            this.setMaxHealth(compound.getFloat("maxHealth"));
            this.setLevel(compound.getInt("level"));
            this.setPharmacistLevel(compound.getInt("plevel"));
            this.setMagic(compound.getFloat("magic"));
            this.setExperience(compound.getFloat("exp"));
            this.setPharmacistExperience(compound.getFloat("pexp"));
            updatePlayerInfo();
        }

    }

    private void updatePlayerInfo() {
        EntityPlayerSP player = Minecraft.getInstance().player;
        if (player != null) {
            player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getMaxHealth());
            player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.getLevel() + 1);
        }
    }

    @Override
    public void sync(@Nonnull EntityPlayerMP player) {
        NetworkLoader.synCapability(this, player);
    }

}
