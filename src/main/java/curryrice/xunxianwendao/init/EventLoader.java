package curryrice.xunxianwendao.init;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.BusBuilder;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventLoader {
	public static final EventBus EVENT_BUS = new EventBus(new BusBuilder());
	
	public EventLoader() {
		MinecraftForge.EVENT_BUS.register(this);
		EventLoader.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if(!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
			//((EntityPlayer)(event.getEntity())).sendStatusMessage(new TextComponentString("Join World"), true);
		}
	}

}
