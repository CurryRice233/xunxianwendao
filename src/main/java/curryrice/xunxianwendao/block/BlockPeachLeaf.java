package curryrice.xunxianwendao.block;

import java.util.Random;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
		if(worldIn.isAirBlock(pos.down()) && random.nextInt(100)==0) {
			int i = random.nextInt(1000);
			if(i<100)
				spawnAsEntity(worldIn,pos.down(),new ItemStack(ItemList.ITEM_PEACH));
			else if(i>=100 && i<110)
				spawnAsEntity(worldIn,pos.down(),new ItemStack(ItemList.ITEM_PEACH_CENTURY));
			else if(i==999)
				spawnAsEntity(worldIn,pos.down(),new ItemStack(ItemList.ITEM_PEACH_MILLENNIUM));
		}
	}
	
	@Override
	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
		
	}

}
