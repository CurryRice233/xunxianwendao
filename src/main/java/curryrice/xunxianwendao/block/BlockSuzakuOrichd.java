package curryrice.xunxianwendao.block;

import java.util.Random;

import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;

public class BlockSuzakuOrichd extends BlockBush{
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
	private static final VoxelShape SHAPE = Block.makeCuboidShape(4D, 0.0D, 4D, 12D, 12D, 12D);

	public BlockSuzakuOrichd() {
		super(Block.Properties.create(Material.PLANTS).needsRandomTick().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "suzaku_orichd"));
		this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));
	}
	
	
	@Override
	public void getDrops(IBlockState state, net.minecraft.util.NonNullList<ItemStack> drops, World world, BlockPos pos, int fortune) {
		if(state.get(this.getAgeProperty()) >= 7) {
			drops.add(new ItemStack(this.getItemDropped(state, world, pos, fortune),2));
		}else {
			drops.add(new ItemStack(this.getItemDropped(state, world, pos, fortune),1));
		}
	}

	@Override
	public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
		return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos.down());
	}

	@Override
	public boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() == Blocks.GRASS_BLOCK;
	}
	
	@Override
	public void randomTick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		if(state.get(this.getAgeProperty()) < 7) {
			worldIn.setBlockState(pos, this.getDefaultState().with(AGE, state.get(AGE)+1));
		}
	}
	
	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return SHAPE;
	}
	
	public IntegerProperty getAgeProperty() {
		return AGE;
	}
	
	@Override
	public void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
		builder.add(AGE);
	}
	
	
}
