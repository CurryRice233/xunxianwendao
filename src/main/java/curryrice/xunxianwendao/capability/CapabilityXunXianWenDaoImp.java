package curryrice.xunxianwendao.capability;

public final class CapabilityXunXianWenDaoImp implements ICapabilityXunXianWenDao {

	
	private float maxHealth;
	public CapabilityXunXianWenDaoImp() {
		this.maxHealth=10;
	}
	

	public float getMaxHealth() {
		return this.maxHealth;
	}


	public void setMaxHealth(float health) {
		this.maxHealth=health;
	}

}
