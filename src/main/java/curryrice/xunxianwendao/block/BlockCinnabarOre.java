package curryrice.xunxianwendao.block;

import javax.annotation.Nullable;

import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

public class BlockCinnabarOre extends Block{

	public BlockCinnabarOre() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10.0F));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.modid,"cinnabar_ore"));
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
