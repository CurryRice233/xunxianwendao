package curryrice.xunxianwendao.init;

import curryrice.xunxianwendao.block.BlockList;
import curryrice.xunxianwendao.client.entity.render.RenderEvilZombie;
import curryrice.xunxianwendao.client.entity.render.RenderTalisman;
import curryrice.xunxianwendao.entity.item.EntityTalisman;
import curryrice.xunxianwendao.entity.monster.EntityEvilZombie;
import curryrice.xunxianwendao.world.gen.FeatureList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterInit {
	// -292075831436830414
	public static void registerOre() {
		oreGen(BlockList.JADE_ORE.getDefaultState(), 5, 15, 0, 0, 64);
		oreGen(BlockList.CINNABAR_ORE.getDefaultState(), 8, 2, 0, 0, 16);
		
	}
	
	
	
	public static void registerRender() {
		//
		RenderingRegistry.registerEntityRenderingHandler(EntityEvilZombie.class,(RenderManager manager) -> new RenderEvilZombie (manager));
		//ForgeRegistries.BIOMES.forEach(biome->biome.
		
		//Item
		RenderingRegistry.registerEntityRenderingHandler(EntityTalisman.class,(RenderManager manager) -> new RenderTalisman (manager));
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
