package curryrice.xunxianwendao.block;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.block.tree.PeachTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockPeachSapling extends BlockSapling{

	public BlockPeachSapling() {
		super(new PeachTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "peach_sapling"));
	}

}
