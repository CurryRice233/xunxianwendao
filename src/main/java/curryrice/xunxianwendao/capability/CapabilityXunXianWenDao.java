package curryrice.xunxianwendao.capability;

import java.util.concurrent.Callable;

import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityXunXianWenDao {
	public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound>{
		public static final ResourceLocation NAME = new ResourceLocation(XunXianWenDao.MODID, "xunxianwendao_level");
		private ICapabilityXunXianWenDao impl = new CapabilityXunXianWenDaoImp();
		private LazyOptional<ICapabilityXunXianWenDao> cap = LazyOptional.of(() -> impl);

		@Override
		@SuppressWarnings("unchecked")
		public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing side) {
			if(capability == CapabilityLoader.xunxianwendaoCapability) {
				return cap.cast();
			}
			return LazyOptional.empty();
		}

		@Override
		public NBTTagCompound serializeNBT() {
			return impl.serializeNBT();
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			impl.deserializeNBT(nbt);
		}
		
	}
	
	public static class Storage implements Capability.IStorage<ICapabilityXunXianWenDao>{

		@Override
		public INBTBase writeNBT(Capability<ICapabilityXunXianWenDao> capability, ICapabilityXunXianWenDao instance,
				EnumFacing side) {
			return instance.serializeNBT();
		}

		@Override
		public void readNBT(Capability<ICapabilityXunXianWenDao> capability, ICapabilityXunXianWenDao instance,
				EnumFacing side, INBTBase nbt) {
			instance.deserializeNBT((NBTTagCompound)nbt);
		}
		
	}
	
	public static class Factory implements Callable<ICapabilityXunXianWenDao>{
		@Override
		public ICapabilityXunXianWenDao call() throws Exception {
			return new CapabilityXunXianWenDaoImp();
		}
	}

}
