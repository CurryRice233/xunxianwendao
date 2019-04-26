package curryrice.xunxianwendao.block;

import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;

public class BlockPeachLog extends BlockLog{

	public BlockPeachLog() {
		super(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "peach_log"));
	}

}
