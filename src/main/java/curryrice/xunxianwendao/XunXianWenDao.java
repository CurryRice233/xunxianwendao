package curryrice.xunxianwendao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curryrice.xunxianwendao.block.*;
import curryrice.xunxianwendao.item.*;
import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
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
		
		Biomes.PLAINS.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, 
			    Biome.createCompositeFeature(Feature.MINABLE, 
			        new MinableConfig(MinableConfig.IS_ROCK, Blocks.GOLD_BLOCK.getDefaultState(), 9), 
			        Biome.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64)));
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
				ItemList.item_jade_primary=new ItemJade(0),
				ItemList.item_jade_intermediate=new ItemJade(1),
				ItemList.item_jade_advanced=new ItemJade(2),
				ItemList.item_jade_pickaxe=new ItemJadePickaxe(),
				ItemList.item_jade_sword=new ItemJadeSword(),
				
				// Blocks
				ItemList.jade_ore_item = registerBlockItem(BlockList.jade_ore,CreativeTabs.MAIN)
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
		
		private static Item registerBlockItem(Block block,ItemGroup itemGroup) {
			return new ItemBlock(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
		}
	}
	
	
}
