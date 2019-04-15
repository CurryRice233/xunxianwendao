package curryrice.xunxianwendao.init;

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

public class RegisterInit {
	// -292075831436830414
	public static void registerOre() {
		oreGen(BlockList.JADE_ORE.getDefaultState(), 5, 15, 0, 0, 64);
		oreGen(BlockList.CINNABAR_ORE.getDefaultState(), 8, 2, 0, 0, 16);
		//oreGen(BlockList.LIANGYI_STONE.getDefaultState(), 7, 7, 0, 0, 16);
		
		ForgeRegistries.BIOMES.forEach(biome->biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createCompositeFeature(Feature.REPLACE_BLOCK, 
				new ReplaceBlockConfig(BlockMatcher.forBlock(Blocks.STONE), BlockList.LIANGYI_STONE.getDefaultState()), Biome.HEIGHT_4_TO_32, IPlacementConfig.NO_PLACEMENT_CONFIG)));
		
	}
	
	
	
	public static void registerRender() {
		//
		RenderingRegistry.registerEntityRenderingHandler(EntityEvilZombie.class,(RenderManager manager) -> new RenderEvilZombie (manager));
		
		//Item
		RenderingRegistry.registerEntityRenderingHandler(EntityTalisman.class,(RenderManager manager) -> new RenderTalisman (manager));
	}
	
	public static void registerSpawn() {
		EntitySpawnPlacementRegistry.register(EntityList.entity_evil_zombie, EntitySpawnPlacementRegistry.SpawnPlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, null);
		ForgeRegistries.BIOMES.forEach(biome->biome.getSpawns(EnumCreatureType.MONSTER).add(new SpawnListEntry(EntityList.entity_evil_zombie,500,4,4)));
	}
	
	public static void registerFeature() {
		// Peach Tree
		Biome[] biomes = {Biomes.FOREST,Biomes.BIRCH_FOREST,Biomes.DARK_FOREST,Biomes.BIRCH_FOREST_HILLS,
				Biomes.DARK_FOREST_HILLS,Biomes.FLOWER_FOREST};
		for(Biome b:biomes) {
			b.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
					Biome.createCompositeFeature(FeatureList.PEACH_TREE_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Biome.AT_SURFACE_WITH_EXTRA, 
							new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
		}
		
		// 4962944354647146466
		Biomes.FLOWER_FOREST.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, 
				Biome.createCompositeFeature(FeatureList.SUZAKU_ORICHD_FEATURE, IFeatureConfig.NO_FEATURE_CONFIG, Biome.TWICE_SURFACE, new FrequencyConfig(5)));
		
	}
	
	
	
	/**
	 * @param stateIn The block to generate
	 * @param sizeIn How many block to generate
	 * @param countIn count to generate in a chunk
	 * @param minHeightIn 
	 * @param maxHeightBaseIn
	 * @param maxHeightIn
	 */
	private static void oreGen(IBlockState stateIn,int sizeIn,int countIn, int minHeightIn, int maxHeightBaseIn, int maxHeightIn) {
		ForgeRegistries.BIOMES.forEach(biome->biome.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES, 
			    Biome.createCompositeFeature(
			    		Feature.MINABLE,
				        new MinableConfig(MinableConfig.IS_ROCK,stateIn, sizeIn), 
				        Biome.COUNT_RANGE, 
				        // countInChunk minHeight maxHeightBase maxHeight
				        new CountRangeConfig(countIn,minHeightIn, maxHeightBaseIn, maxHeightIn)
				)
		));
	}
	

}
