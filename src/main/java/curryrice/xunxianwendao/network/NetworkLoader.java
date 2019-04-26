package curryrice.xunxianwendao.network;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.capability.ICapabilityXunXianWenDao;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkLoader {
	private static final String PROTOCOL_VERSION = Integer.toString(1);
	private static SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation(XunXianWenDao.MODID, "main_channel"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION)
			.simpleChannel();
	
	
	public static void register(){
		int disc = 0;
		HANDLER.registerMessage(disc++, MessageXunXianWenDao.class, MessageXunXianWenDao::encode, MessageXunXianWenDao::decode, MessageXunXianWenDao.Handler::handle);
	}
	
	/**
	 * Sends a packet to the server.<br>
	 * Must be called Client side. 
	 */
	public static void sendToServer(Object msg) {
		HANDLER.sendToServer(msg);
	}
	
	/**
	 * Send a packet to a specific player.<br>
	 * Must be called Server side. 
	 */
	public static void sendTo(Object msg, EntityPlayerMP player){
		if (!(player instanceof FakePlayer)){
			HANDLER.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	
	public static void synCapability(ICapabilityXunXianWenDao msg, EntityPlayerMP player){
		player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(msg.getMaxHealth());
		if(player.getHealth()>msg.getMaxHealth())
			player.setHealth(msg.getMaxHealth());
		player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(msg.getLevel() + 1);
		sendTo(new MessageXunXianWenDao(msg.serializeNBT()), (EntityPlayerMP) player);
	}

}
