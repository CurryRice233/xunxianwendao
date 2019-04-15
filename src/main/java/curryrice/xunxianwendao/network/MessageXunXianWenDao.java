package curryrice.xunxianwendao.network;

import java.util.function.Supplier;

import curryrice.xunxianwendao.capability.CapabilityLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageXunXianWenDao{
	private NBTTagCompound nbt;
	
	public MessageXunXianWenDao(NBTTagCompound nbt) {
		this.nbt=nbt;
	}
	
	public static void encode(MessageXunXianWenDao msg, PacketBuffer buf) {
		buf.writeCompoundTag(msg.nbt);
	}
	
	public static MessageXunXianWenDao decode(PacketBuffer buf) {
		return new MessageXunXianWenDao(buf.readCompoundTag());
	}
	
	public static class Handler{
		public static void handle(final MessageXunXianWenDao msg, Supplier<NetworkEvent.Context> ctx){
			ctx.get().enqueueWork(() -> {
				Minecraft.getInstance().player.getCapability(CapabilityLoader.xunxianwendaoCapability)
					.ifPresent(cap -> cap.deserializeNBT(msg.nbt));
			});
			ctx.get().setPacketHandled(true);
        }
	}
}
