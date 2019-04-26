package curryrice.xunxianwendao.init;

import com.google.common.collect.ObjectArrays;
import curryrice.xunxianwendao.block.BlockList;
import curryrice.xunxianwendao.client.entity.render.RenderEvilZombie;
import curryrice.xunxianwendao.client.entity.render.RenderTalisman;
import curryrice.xunxianwendao.entity.EntityList;
import curryrice.xunxianwendao.entity.item.EntityTalisman;
import curryrice.xunxianwendao.entity.monster.EntityEvilZombie;
import curryrice.xunxianwendao.world.gen.FeatureList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.init.Biomes.*;

public class RegisterInit {
    // -552632656206977839 ocean desert
    // -6556369896409823215 jungle mountains

    private static Biome[] biomesOcean = new Biome[]{OCEAN, DEEP_OCEAN, WARM_OCEAN, LUKEWARM_OCEAN, COLD_OCEAN, DEEP_WARM_OCEAN, DEEP_LUKEWARM_OCEAN, DEEP_COLD_OCEAN, DEEP_FROZEN_OCEAN};

    private static Biome[] biomesPlains = new Biome[]{PLAINS, SAVANNA, SAVANNA_PLATEAU, SUNFLOWER_PLAINS, SHATTERED_SAVANNA, SHATTERED_SAVANNA_PLATEAU};

    private static Biome[] biomesDesert = new Biome[]{DESERT, DESERT_HILLS, DESERT_LAKES};
    private static Biome[] biomesMountains = new Biome[]{MOUNTAINS, MOUNTAIN_EDGE, WOODED_MOUNTAINS, GRAVELLY_MOUNTAINS, TAIGA_MOUNTAINS, MODIFIED_GRAVELLY_MOUNTAINS};
    private static Biome[] biomesForest = new Biome[]{FOREST, WOODED_HILLS, BIRCH_FOREST, BIRCH_FOREST_HILLS, DARK_FOREST, GIANT_TREE_TAIGA, GIANT_TREE_TAIGA_HILLS, FLOWER_FOREST, TALL_BIRCH_FOREST, TALL_BIRCH_HILLS, DARK_FOREST_HILLS};
    private static Biome[] biomesTaiga = new Biome[]{TAIGA, TAIGA_HILLS, GIANT_SPRUCE_TAIGA, GIANT_SPRUCE_TAIGA_HILLS};
    private static Biome[] biomesSwamp = new Biome[]{SWAMP, SWAMP_HILLS};
    private static Biome[] biomesJungle = new Biome[]{JUNGLE, JUNGLE_HILLS, JUNGLE_EDGE, MODIFIED_JUNGLE, MODIFIED_JUNGLE_EDGE};

    private static Biome[] biomesRiver = new Biome[]{RIVER, BEACH, STONE_SHORE};
    // -6540266385104712536
    private static Biome[] biomesFrozen = new Biome[]{FROZEN_OCEAN, FROZEN_RIVER, SNOWY_TUNDRA, SNOWY_MOUNTAINS, SNOWY_BEACH, SNOWY_TAIGA, SNOWY_TAIGA_HILLS, ICE_SPIKES, SNOWY_TAIGA_MOUNTAINS};
    private static Biome[] biomesEnd = new Biome[]{THE_END, END_HIGHLANDS, END_MIDLANDS, SMALL_END_ISLANDS, END_BARRENS};

    private static Biome[] biomesMushroom = new Biome[]{MUSHROOM_FIELDS, MUSHROOM_FIELD_SHORE};
    private static Biome[] biomesBadlands = new Biome[]{BADLANDS, WOODED_BADLANDS_PLATEAU, BADLANDS_PLATEAU, ERODED_BADLANDS, MODIFIED_WOODED_BADLANDS_PLATEAU, MODIFIED_BADLANDS_PLATEAU};
    // -292075831436830414
    public static void registerOre() {
        oreGen(BlockList.JADE_ORE.getDefaultState(), 5, 15, 0, 0, 64);
        oreGen(BlockList.CINNABAR_ORE.getDefaultState(), 8, 2, 0, 0, 16);
        //oreGen(BlockList.LIANGYI_STONE.getDefaultState(), 7, 7, 0, 0, 16);

        ForgeRegistries.BIOMES.forEach(biome -> biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createCompositeFeature(Feature.REPLACE_BLOCK,
                new ReplaceBlockConfig(BlockMatcher.forBlock(Blocks.STONE), BlockList.LIANGYI_STONE.getDefaultState()), Biome.HEIGHT_4_TO_32, IPlacementConfig.NO_PLACEMENT_CONFIG)));

    }


    public static void registerRender() {
        //Monster
        RenderingRegistry.registerEntityRenderingHandler(EntityEvilZombie.class, (RenderManager manager) -> new RenderEvilZombie(manager));

        //Item
        RenderingRegistry.registerEntityRenderingHandler(EntityTalisman.class, (RenderManager manager) -> new RenderTalisman(manager));
    }

    public static void registerSpawn() {
        EntitySpawnPlacementRegistry.register(EntityList.entity_evil_zombie, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, null);
        /*for (Biome b : (Biome[]) ArrayUtils.addAll(biomesPlains, biomesDesert, biomesMountains, biomesForest, biomesTaiga, biomesSwamp, biomesJungle, biomesRiver, biomesFrozen, biomesBadlands)){
            b.getSpawns(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityList.entity_evil_zombie, 500, 4, 4));
        }*/
        for(Biome b:ObjectArrays.concat(biomesPlains,biomesDesert,Biome.class)){
            b.getSpawns(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityList.entity_evil_zombie, 500, 4, 4));
        }

    }

    public static void registerFeature() {
        // Peach Tree
        for (Biome b : biomesForest) {
            b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    Biome.createCompositeFeature(FeatureList.PEACH_TREE_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Biome.AT_SURFACE_WITH_EXTRA,
                            new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
        }

        // 4962944354647146466
        Biomes.FLOWER_FOREST.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Biome.createCompositeFeature(FeatureList.SUZAKU_ORICHD_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Biome.TWICE_SURFACE, new FrequencyConfig(5)));

        for(Biome b:biomesFrozen){
            b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    Biome.createCompositeFeature(FeatureList.ICE_GRASS_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Biome.TWICE_SURFACE, new FrequencyConfig(5)));
        }

        for(Biome b:biomesMountains){
            b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    Biome.createCompositeFeature(FeatureList.THUNDER_WOOD_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Biome.TWICE_SURFACE, new FrequencyConfig(5)));
        }

        for(Biome b:biomesSwamp){
            b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    Biome.createCompositeFeature(FeatureList.PURPLR_HEART_GRASS_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Biome.TWICE_SURFACE, new FrequencyConfig(5)));
        }

        for(Biome b:biomesDesert){
            b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                    Biome.createCompositeFeature(FeatureList.GOLD_GRASS_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Biome.TWICE_SURFACE, new FrequencyConfig(5)));
        }
    }


    /**
     * @param stateIn         The block to generate
     * @param sizeIn          How many block to generate
     * @param countIn         count to generate in a chunk
     * @param minHeightIn
     * @param maxHeightBaseIn
     * @param maxHeightIn
     */
    private static void oreGen(IBlockState stateIn, int sizeIn, int countIn, int minHeightIn, int maxHeightBaseIn, int maxHeightIn) {
        ForgeRegistries.BIOMES.forEach(biome -> biome.addFeature(
                GenerationStage.Decoration.UNDERGROUND_ORES,
                Biome.createCompositeFeature(
                        Feature.MINABLE,
                        new MinableConfig(MinableConfig.IS_ROCK, stateIn, sizeIn),
                        Biome.COUNT_RANGE,
                        // countInChunk minHeight maxHeightBase maxHeight
                        new CountRangeConfig(countIn, minHeightIn, maxHeightBaseIn, maxHeightIn)
                )
        ));
    }


}
