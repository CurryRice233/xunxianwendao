package curryrice.xunxianwendao.block.tree;

import java.util.Random;

import curryrice.xunxianwendao.world.gen.PeachTreeFeature;
import net.minecraft.block.trees.AbstractTree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PeachTree extends AbstractTree{

	@Override
	public AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return new PeachTreeFeature(true);
	}

}
