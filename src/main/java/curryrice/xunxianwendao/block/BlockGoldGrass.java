package curryrice.xunxianwendao.block;

import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGoldGrass extends BlockBush {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(4D, 0.0D, 4D, 12D, 12D, 12D);
    public BlockGoldGrass() {
        super(Block.Properties.create(Material.PLANTS).needsRandomTick().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
        this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "gold_grass"));
    }

    @Override
    public void randomTick(IBlockState state, World worldIn, BlockPos pos, Random random) {
        if(worldIn.isAirBlock(pos.up()) && random.nextInt(10)==3) {
            if(worldIn.getBlockState(pos.down(2)).getBlock()!=this && worldIn.isAirBlock(pos.up())) {
                worldIn.setBlockState(pos.up(), this.getDefaultState());
            }
        }
    }

    @Override
    public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
        return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos.down());
    }

    @Override
    protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock()== this || state.getBlock()==Blocks.SAND;
    }

    /*@Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return SHAPE;
    }*/
}
