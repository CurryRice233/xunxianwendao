package curryrice.xunxianwendao;

import java.util.function.Function;

import curryrice.xunxianwendao.capability.CapabilityLoader;
import curryrice.xunxianwendao.client.gui.GuiContainerCauldronFurnace;
import curryrice.xunxianwendao.inventory.ContainerCauldronFurnace;
import curryrice.xunxianwendao.network.NetworkLoader;
import curryrice.xunxianwendao.tileentity.TileEntityCauldronFurnace;
import curryrice.xunxianwendao.tileentity.TileEntityList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
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
    public static final String MODID = "xunxianwendao";
    private static final Logger logger = LogManager.getLogger(MODID);

    public XunXianWenDao() {
        instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> {
            return (openContainer) -> {
                ResourceLocation location = openContainer.getId();
                if (location.toString().equals(XunXianWenDao.MODID + ":gui_cauldron_furnace")) {
                    EntityPlayerSP player = Minecraft.getInstance().player;
                    BlockPos pos = openContainer.getAdditionalData().readBlockPos();
                    TileEntity tileEntity = player.world.getTileEntity(pos);
                    if (tileEntity instanceof TileEntityCauldronFurnace) {
                        return new GuiContainerCauldronFurnace(new ContainerCauldronFurnace(player.inventory, (TileEntityCauldronFurnace) tileEntity));
                    }
                }
                return null;
            };
        });


    }

    private void setup(final FMLCommonSetupEvent event) {
        RegisterInit.registerOre();
        RegisterInit.registerFeature();
        RegisterInit.registerSpawn();
        new CapabilityLoader();
        new EventLoader();
        NetworkLoader.register();
        logger.info("Setup method registered.");
    }

    private void clientRegistries(final FMLClientSetupEvent event) {
        RegisterInit.registerRender();
        logger.info("clientRegistries method registered.");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegsitryEvents {
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                    // Items
                    ItemList.ITEM_JADE_PRIMARY = new ItemJade(0),
                    ItemList.ITEM_JADE_INTERMEDIATE = new ItemJade(1),
                    ItemList.ITEM_JADE_ADVANCED = new ItemJade(2),
                    ItemList.ITEM_CINNABAR = createItem("item_cinnabar"),
                    ItemList.ITEM_LIANGYI_STONE_YING = createItem("item_liangyi_stone_ying"),
                    ItemList.ITEM_LIANGYI_STONE_YANG = createItem("item_liangyi_stone_yang"),

                    ItemList.ITEM_PEACH = new ItemPeach(0),
                    ItemList.ITEM_PEACH_CENTURY = new ItemPeach(1),
                    ItemList.ITEM_PEACH_MILLENNIUM = new ItemPeach(2),

                    ItemList.ITEM_TALISMAN_EMPATY = new ItemTalisman(0, "empaty"),
                    ItemList.ITEM_TALISMAN_UNMOVE = new ItemTalisman(1, "unmove"),
                    ItemList.ITEM_TALISMAN_BURDING = new ItemTalisman(2, "burding"),
                    ItemList.ITEM_TALISMAN_EXPLOSION = new ItemTalisman(3, "explosion"),
                    ItemList.ITEM_TALISMAN_THUNDER = new ItemTalisman(4, "thunder"),
                    ItemList.ITEM_TALISMAN_NAUSEA = new ItemTalisman(5, "nausea"),
                    ItemList.ITEM_TALISMAN_TELEPORT = new ItemTalisman(6, "teleport"),

                    ItemList.ITEM_MEDICINE_REGENERATION = new ItemMedicine(0, "regeneration"),
                    ItemList.ITEM_MEDICINE_STRENGTH = new ItemMedicine(1, "strength"),
                    ItemList.ITEM_MEDICINE_ABSORPTION = new ItemMedicine(2, "absorption"),
                    ItemList.ITEM_MEDICINE_RESISTANCE = new ItemMedicine(3, "resistance"),
                    ItemList.ITEM_MEDICINE_HASTE = new ItemMedicine(4, "haste"),
                    ItemList.ITEM_MEDICINE_SPEED = new ItemMedicine(5, "speed"),
                    ItemList.ITEM_MEDICINE_JUMP_BOOST = new ItemMedicine(6, "jump_boost"),
                    ItemList.ITEM_MEDICINE_FIRE_RESISTANCE = new ItemMedicine(7, "fire_resistance"),
                    ItemList.ITEM_MEDICINE_NIGHT_VISION = new ItemMedicine(8, "night_vision"),
                    ItemList.ITEM_MEDICINE_WATER_BREATHING = new ItemMedicine(9, "water_breathing"),

                    ItemList.ITEM_JADE_PICKAXE = new ItemJadePickaxe(),
                    ItemList.ITEM_JADE_SWORD = new ItemJadeSword(),
                    ItemList.ITEM_PEACH_WOOD_SWORD = new ItemPeachWoodSword(),

                    // Blocks
                    ItemList.JADE_ORE_ITEM = registerBlockItem(BlockList.JADE_ORE, CreativeTabs.MAIN),
                    ItemList.CINNABAR_ORE_ITEM = registerBlockItem(BlockList.CINNABAR_ORE, CreativeTabs.MAIN),
                    ItemList.LIANGYI_STONE_ITEM = registerBlockItem(BlockList.LIANGYI_STONE, CreativeTabs.MAIN),

                    ItemList.PEACH_LOG_ITEM = registerBlockItem(BlockList.PEACH_LOG, CreativeTabs.MAIN),
                    ItemList.PEACH_LEAF_ITEM = registerBlockItem(BlockList.PEACH_LEAF, CreativeTabs.MAIN),
                    ItemList.PEACH_SAPLING_ITEM = registerBlockItem(BlockList.PEACH_SAPLING, CreativeTabs.MAIN),
                    ItemList.SUZAKU_ORICHD_ITEM = registerBlockItem(BlockList.SUZAKU_ORICHD, CreativeTabs.MAIN),
                    ItemList.ICE_GRASS_ITEM = registerBlockItem(BlockList.ICE_GRASS, CreativeTabs.MAIN),
                    ItemList.THUNDER_WOOD_ITEM = registerBlockItem(BlockList.THUNDER_WOOD, CreativeTabs.MAIN),
                    ItemList.PURPLR_HEART_GRASS_ITEM = registerBlockItem(BlockList.PURPLR_HEART_GRASS, CreativeTabs.MAIN),
                    ItemList.GOLD_GRASS_ITEM = registerBlockItem(BlockList.GOLD_GRASS, CreativeTabs.MAIN),

                    ItemList.CAULDRON_FURNACE_ITEM = registerBlockItem(BlockList.CAULDRON_FURNACE, CreativeTabs.MAIN)
            );

            logger.info("Items registered.");
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(
                    BlockList.JADE_ORE = new BlockJadeOre(),
                    BlockList.CINNABAR_ORE = new BlockCinnabarOre(),
                    BlockList.LIANGYI_STONE = new BlockLiangYiStone(),

                    BlockList.PEACH_LOG = new BlockPeachLog(),
                    BlockList.PEACH_LEAF = new BlockPeachLeaf(),
                    BlockList.PEACH_SAPLING = new BlockPeachSapling(),

                    BlockList.SUZAKU_ORICHD = new BlockSuzakuOrichd(),
                    BlockList.ICE_GRASS = new BlockIceGrass(),
                    BlockList.THUNDER_WOOD = new BlockThunderWood(),
                    BlockList.PURPLR_HEART_GRASS = new BlockPurpleHeartGrass(),
                    BlockList.GOLD_GRASS = new BlockGoldGrass(),

                    BlockList.CAULDRON_FURNACE = new BlockCauldronFurnace()
            );

            logger.info("Blocks registered.");
        }

        @SubscribeEvent
        public static void registerEntityType(final RegistryEvent.Register<EntityType<?>> event) {
            event.getRegistry().registerAll(
                    EntityList.entity_evil_zombie = registerEntityType("entity_evil_zombie", EntityEvilZombie.class, EntityEvilZombie::new, 32, 3, true),
                    EntityList.entity_talisman = registerEntityType("entity_talisman", EntityTalisman.class, EntityTalisman::new, 64, 1, true)
            );
        }

        @SubscribeEvent
        public void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
            TileEntityList.CAULDRON_FURNACE = TileEntityType.register("tile_entity_cauldron_furnace", TileEntityType.Builder.create(TileEntityCauldronFurnace::new));
        }


        private static Item registerBlockItem(Block block, ItemGroup itemGroup) {
            return new ItemBlock(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
        }

        private static <T extends Entity> EntityType<T> registerEntityType(String id, Class<? extends T> entityClass, Function<? super World, ? extends T> factory, int range, int updateFrequency, boolean sendsVelocityUpdates) {
            EntityType<T> type = EntityType.Builder.create(entityClass, factory).tracker(range, updateFrequency, sendsVelocityUpdates).build(XunXianWenDao.MODID + ":" + id);
            type.setRegistryName(new ResourceLocation(XunXianWenDao.MODID + ":" + id));
            return type;
        }

        private static ResourceLocation location(String name) {
            return new ResourceLocation(XunXianWenDao.MODID, name);
        }

        private static Item createItem(String name) {
            return new Item(new Item.Properties().group(CreativeTabs.MAIN)).setRegistryName(location(name));
        }
    }

}
