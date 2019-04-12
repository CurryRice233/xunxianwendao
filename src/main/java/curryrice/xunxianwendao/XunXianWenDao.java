package curryrice.xunxianwendao;

import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curryrice.xunxianwendao.block.*;
import curryrice.xunxianwendao.entity.EntityList;
import curryrice.xunxianwendao.entity.item.EntityTalisman;
import curryrice.xunxianwendao.entity.monster.EntityEvilZombie;
import curryrice.xunxianwendao.init.CreativeTabs;
import curryrice.xunxianwendao.init.EventLoader;
import curryrice.xunxianwendao.init.RegisterInit;
import curryrice.xunxianwendao.item.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
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
		RegisterInit.registerOre();
		RegisterInit.registerFeature();
		new EventLoader();
		logger.info("Setup method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		RegisterInit.registerRender();
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
				ItemList.ITEM_JADE_PRIMARY=new ItemJade(0),
				ItemList.ITEM_JADE_INTERMEDIATE=new ItemJade(1),
				ItemList.ITEM_JADE_ADVANCED=new ItemJade(2),
				ItemList.ITEM_CINNABAR=new Item(new Item.Properties().group(CreativeTabs.MAIN)).setRegistryName(location("item_cinnabar")),
				
				ItemList.ITEM_TALISMAN_EMPATY=new ItemTalisman(0, "empaty"),
				ItemList.ITEM_TALISMAN_UNMOVE=new ItemTalisman(1, "unmove"),
				ItemList.ITEM_TALISMAN_BURDING=new ItemTalisman(2, "burding"),
				ItemList.ITEM_TALISMAN_EXPLOSION=new ItemTalisman(3, "explosion"),
				ItemList.ITEM_TALISMAN_THUNDER=new ItemTalisman(4, "thunder"),
				ItemList.ITEM_TALISMAN_NAUSEA=new ItemTalisman(5, "nausea"),
				ItemList.ITEM_TALISMAN_TELEPORT=new ItemTalisman(6, "teleport"),
				
				ItemList.ITEM_JADE_PICKAXE=new ItemJadePickaxe(),
				ItemList.ITEM_JADE_SWORD=new ItemJadeSword(),
				ItemList.ITEM_PEACH_WOOD_SWORD=new ItemPeachWoodSword(),
				
				
				
				// Blocks
				ItemList.JADE_ORE_ITEM = registerBlockItem(BlockList.JADE_ORE,CreativeTabs.MAIN),
				ItemList.CINNABAR_ORE_ITEM = registerBlockItem(BlockList.CINNABAR_ORE,CreativeTabs.MAIN),
				ItemList.PEACH_LOG_ITEM=registerBlockItem(BlockList.PEACH_LOG, CreativeTabs.MAIN),
				ItemList.PEACH_LEAF_ITEM=registerBlockItem(BlockList.PEACH_LEAF, CreativeTabs.MAIN),
				ItemList.PEACH_SAPLING_ITEM=registerBlockItem(BlockList.PEACH_SAPLING, CreativeTabs.MAIN)
			);
			
			logger.info("Items registered.");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
				BlockList.JADE_ORE=new BlockJadeOre(),
				BlockList.CINNABAR_ORE=new Block(Block.Properties.create(Material.ROCK)).setRegistryName(location("cinnabar_ore")),
				
				BlockList.PEACH_LOG=new BlockPeachLog(),
				BlockList.PEACH_LEAF=new BlockPeachLeaf(),
				BlockList.PEACH_SAPLING=new BlockPeachSapling()
			);
			
			logger.info("Blocks registered.");
		}
		
		@SubscribeEvent
		public static void registerEntityType(final RegistryEvent.Register<EntityType<?>> event) {
			event.getRegistry().registerAll(
				EntityList.entity_evil_zombie=registerEntityType("entity_evil_zombie", EntityEvilZombie.class, EntityEvilZombie::new, 32, 3, true),
				EntityList.entity_talisman=registerEntityType("entity_talisman",EntityTalisman.class, EntityTalisman::new,64,1,true)
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
		private static ResourceLocation location(String name) {
			return new ResourceLocation(XunXianWenDao.modid, name);
		}
	}
	
}
