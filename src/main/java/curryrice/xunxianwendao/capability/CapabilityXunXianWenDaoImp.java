package curryrice.xunxianwendao.capability;

public class CapabilityXunXianWenDaoImp implements ICapabilityXunXianWenDao{
	private float maxHealth;
	public CapabilityXunXianWenDaoImp() {
		this.maxHealth=10;
	}
	
	@Override
	public float getMaxHealth() {
		return this.maxHealth;
	}

	@Override
	public void setMaxHealth(float health) {
		this.maxHealth=health;
	}

}
