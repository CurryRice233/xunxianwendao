package curryrice.xunxianwendao.block;

import java.util.List;
import java.util.Random;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.util.Elements;
import curryrice.xunxianwendao.util.IElements;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class BlockSuzakuOrichd extends BlockBush implements IElements {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;
	private static final VoxelShape SHAPE = Block.makeCuboidShape(4D, 0.0D, 4D, 12D, 12D, 12D);
	private static Elements elements = new Elements(0,0,0,1,0);
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

	@Override
	public Elements getElements() {
		return elements;
	}
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentString(elements.toString()).applyTextStyle(TextFormatting.DARK_GRAY));
	}
	
}
