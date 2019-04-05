package curryrice.xunxianwendao.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityLoader {
	@CapabilityInject(ICapabilityXunXianWenDao.class)
	public static Capability<ICapabilityXunXianWenDao> xunxianwendaoCapability;
	
	public CapabilityLoader() {
		CapabilityManager.INSTANCE.register(ICapabilityXunXianWenDao.class, new CapabilityXunXianWenDao.Storage(), new CapabilityXunXianWenDao.Factory());
	}

}
