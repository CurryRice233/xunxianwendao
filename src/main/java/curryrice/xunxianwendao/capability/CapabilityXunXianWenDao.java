package curryrice.xunxianwendao.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.INBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class CapabilityXunXianWenDao {
	public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound>{
		private ICapabilityXunXianWenDao capability = new CapabilityXunXianWenDaoImp();
		private IStorage<ICapabilityXunXianWenDao> storage = CapabilityLoader.xunxianwendaoCapability.getStorage();
		
		@Override
		@SuppressWarnings("unchecked")
		public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
			if(CapabilityLoader.xunxianwendaoCapability.equals(cap)) {
				return (LazyOptional<T>) capability;
			}
			return null;
		}

		@Override
		public NBTTagCompound serializeNBT() {
			NBTTagCompound compound = new NBTTagCompound();
			compound.setTag("xunxianwendao", storage.writeNBT(CapabilityLoader.xunxianwendaoCapability, capability, null));
			return compound;
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			storage.readNBT(CapabilityLoader.xunxianwendaoCapability, capability, null, nbt.getTag("xunxianwendao"));
		}
		
	}
	
	public static class Storage implements Capability.IStorage<ICapabilityXunXianWenDao>{

		@Override
		public INBTBase writeNBT(Capability<ICapabilityXunXianWenDao> capability, ICapabilityXunXianWenDao instance,
				EnumFacing side) {
			NBTTagCompound compound = new NBTTagCompound();
			compound.setFloat("maxHealth", instance.getMaxHealth());
			return null;
		}

		@Override
		public void readNBT(Capability<ICapabilityXunXianWenDao> capability, ICapabilityXunXianWenDao instance,
				EnumFacing side, INBTBase nbt) {
			NBTTagCompound compound= (NBTTagCompound)nbt;
			instance.setMaxHealth(compound.getFloat("maxHealth"));
			
		}
		
	}
	
	public static class Factory implements Callable<ICapabilityXunXianWenDao>{
		@Override
		public ICapabilityXunXianWenDao call() throws Exception {
			return new CapabilityXunXianWenDaoImp();
		}
	}

}
