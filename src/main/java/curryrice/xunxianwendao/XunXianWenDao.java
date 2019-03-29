package curryrice.xunxianwendao;

import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curryrice.xunxianwendao.block.*;
import curryrice.xunxianwendao.client.entity.render.RenderEvilZombie;
import curryrice.xunxianwendao.entity.EntityList;
import curryrice.xunxianwendao.entity.monster.EntityEvilZombie;
import curryrice.xunxianwendao.item.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

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
		this.registerOre();
		
		logger.info("Setup method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		this.registerRender();
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
		
		@SubscribeEvent
		public static void registerEntityType(final RegistryEvent.Register<EntityType<?>> event) {
			event.getRegistry().registerAll(
				EntityList.entity_evil_zombie=registerEntityType("entity_evil_zombie", EntityEvilZombie.class, EntityEvilZombie::new, 32, 3, true)
			);
		}
		
		private static Item registerBlockItem(Block block,ItemGroup itemGroup) {
			return new ItemBlock(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
		}
		private static <T extends Entity> EntityType<T> registerEntityType(String id, Class<? extends T> entityClass, Function<? super World, ? extends T> factory, int range, int updateFrequency, boolean sendsVelocityUpdates){
			EntityType<T> type = EntityType.Builder.create(entityClass, factory).tracker(range, updateFrequency, sendsVelocityUpdates).build(XunXianWenDao.modid + ":" + id);
			type.setRegistryName(new ResourceLocation(XunXianWenDao.modid + ":" + id));
			return type;
		}
	}
	
	private void registerOre() {
		ForgeRegistries.BIOMES.forEach(biome->biome.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES, 
			    Biome.createCompositeFeature(
			    		Feature.MINABLE, 
				        new MinableConfig(MinableConfig.IS_ROCK, BlockList.jade_ore.getDefaultState(), 4), 
				        Biome.COUNT_RANGE, 
				        // countInChunk minHeight maxHeightBase maxHeight
				        new CountRangeConfig(2, 0, 0, 16)
				)
		));
	}
	
	private void registerRender() {
		RenderingRegistry.registerEntityRenderingHandler(EntityEvilZombie.class,(RenderManager manager) -> new RenderEvilZombie (manager));
	}
	
	
}
