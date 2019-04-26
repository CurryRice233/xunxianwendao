package curryrice.xunxianwendao.world.gen;

import curryrice.xunxianwendao.block.BlockList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class IceGrassFeature extends Feature<NoFeatureConfig> {
    @Override
    public boolean func_212245_a(IWorld world, IChunkGenerator<? extends IChunkGenSettings> chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        IBlockState iblockstate = BlockList.ICE_GRASS.getDefaultState();

        for (int j = 0; j < 16; ++j) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),rand.nextInt(8) - rand.nextInt(8));
            if (world.isAirBlock(blockpos) && world.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS_BLOCK) {
                world.setBlockState(blockpos, iblockstate, 2);
                world.setBlockState(blockpos.down(), Blocks.SNOW_BLOCK.getDefaultState(), 2);
                ++i;
            }
        }
        return i > 0;
    }
}
