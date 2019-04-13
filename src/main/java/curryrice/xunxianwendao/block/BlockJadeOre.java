package curryrice.xunxianwendao.block;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.item.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockJadeOre extends Block{
	public BlockJadeOre() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10,10));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.modid, "jade_ore"));
	}
	
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		if(fortune>0 && worldIn.rand.nextInt(10)<fortune)
			return ItemList.ITEM_JADE_INTERMEDIATE;
		return ItemList.ITEM_JADE_PRIMARY;
	}
	
}
