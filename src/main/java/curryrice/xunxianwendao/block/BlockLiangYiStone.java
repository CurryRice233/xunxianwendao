package curryrice.xunxianwendao.block;

import javax.annotation.Nullable;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockLiangYiStone extends Block{

	public BlockLiangYiStone() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10.0F));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID,"liangyi_stone"));
	}
	
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		if(worldIn.rand.nextBoolean())
			return ItemList.ITEM_LIANGYI_STONE_YING;
		return ItemList.ITEM_LIANGYI_STONE_YANG;
	}
	
	@Override
	public int getHarvestLevel(IBlockState state) {
		return 4;
	}
	
	@Nullable
	@Override
	public ToolType getHarvestTool(IBlockState state) {
		return ToolType.AXE;
	}

}
