package curryrice.xunxianwendao.init;

import curryrice.xunxianwendao.block.BlockList;
import curryrice.xunxianwendao.client.entity.render.RenderEvilZombie;
import curryrice.xunxianwendao.entity.monster.EntityEvilZombie;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterInit {
	
	public static void registerOre() {
		oreGen(BlockList.jade_ore.getDefaultState(), 5, 15, 0, 0, 64);
		oreGen(BlockList.cinnabar_ore.getDefaultState(), 4, 2, 0, 0, 16);
		
	}
	
	public static void registerRender() {
		RenderingRegistry.registerEntityRenderingHandler(EntityEvilZombie.class,(RenderManager manager) -> new RenderEvilZombie (manager));
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
