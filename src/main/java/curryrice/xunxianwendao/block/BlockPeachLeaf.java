package curryrice.xunxianwendao.block;

import java.util.Random;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPeachLeaf extends BlockLeaves{

	public BlockPeachLeaf() {
		super(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "peach_leaf"));
	}
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return ItemList.PEACH_SAPLING_ITEM;
	}
	
	@Override
	public void randomTick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		if(random.nextInt(100)==0 && worldIn.isAirBlock(pos.down())) {
			spawnAsEntity(worldIn,pos.down(),new ItemStack(Items.APPLE));// will change to peach
		}
	}
	
	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		
	}

}
