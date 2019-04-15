package curryrice.xunxianwendao.init;

import curryrice.xunxianwendao.capability.CapabilityLoader;
import curryrice.xunxianwendao.capability.CapabilityXunXianWenDao;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
			event.getEntity().getCapability(CapabilityLoader.xunxianwendaoCapability).ifPresent(c ->{c.sync((EntityPlayerMP)event.getEntity());});
		}
	}

	@SubscribeEvent
	public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer){
			event.addCapability(CapabilityXunXianWenDao.ProviderPlayer.NAME,new CapabilityXunXianWenDao.ProviderPlayer());
		}
	}
	
	@SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
		event.getOriginal().getCapability(CapabilityLoader.xunxianwendaoCapability).ifPresent(old ->{
			event.getEntityPlayer().getCapability(CapabilityLoader.xunxianwendaoCapability).ifPresent(cap -> cap.deserializeNBT(old.serializeNBT()));
		});
    }
	
	@SubscribeEvent
    public void onPlayerRespawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event){
		event.getPlayer().getCapability(CapabilityLoader.xunxianwendaoCapability).ifPresent(c ->{c.sync((EntityPlayerMP)event.getPlayer());});
	}
	
	@SubscribeEvent
    public void onPlayerChangeDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event){
		event.getPlayer().getCapability(CapabilityLoader.xunxianwendaoCapability).ifPresent(c ->{c.sync((EntityPlayerMP)event.getPlayer());});
	}
	
	@OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onGuiIventoryOpen(GuiScreenEvent event) {
		GuiScreen gui=event.getGui();
		if(gui instanceof GuiInventory) {
			gui.mc.getInstance().player.getCapability(CapabilityLoader.xunxianwendaoCapability).ifPresent(cap -> {
				gui.drawString(Minecraft.getInstance().fontRenderer, cap.toString(), 5, 3, 0x00FFFF);
			});
		}
	}

}
