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

public class BlockJadeOre extends Block{
	public BlockJadeOre() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10,10));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "jade_ore"));
	}
	
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		if(fortune>0 && worldIn.rand.nextInt(10)<fortune)
			return ItemList.ITEM_JADE_INTERMEDIATE;
		return ItemList.ITEM_JADE_PRIMARY;
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
