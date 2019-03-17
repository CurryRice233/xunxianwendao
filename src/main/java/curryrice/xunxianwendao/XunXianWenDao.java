package curryrice.xunxianwendao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curryrice.xunxianwendao.block.BlockJadeOre;
import curryrice.xunxianwendao.block.BlockList;
import curryrice.xunxianwendao.item.ItemJade;
import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("xunxianwendao")
public class XunXianWenDao {
	public static XunXianWenDao instance;
	public static final String modid = "xunxianwendao";
	private static final Logger logger = LogManager.getLogger(modid);
	
	public XunXianWenDao() {
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		logger.info("Setup method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		logger.info("clientRegistries method registered.");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegsitryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
				// Items
				ItemList.item_jade=new ItemJade(),
				
				// Blocks
				ItemList.jade_ore_item = registerItemBlock(BlockList.jade_ore,CreativeTabs.MAIN)
			);
			
			logger.info("Items registered.");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
				BlockList.jade_ore=new BlockJadeOre()
			);
			
			logger.info("Blocks registered.");
		}
		
		private static Item registerItemBlock(Block block,ItemGroup itemGroup) {
			return new ItemBlock(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
		}
	}
	
	
}
