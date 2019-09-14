package curryrice.xunxianwendao.block;

import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockTrigram extends Block {
    private int meta;
    private static final VoxelShape SHAPE = Block.makeCuboidShape(2D, 0.0D, 2D, 14D, 2D, 14D);
    public BlockTrigram(int meta, String name){
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(10.0F));
        this.meta=meta;
        this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID,"trigram_"+name));
    }
    public int getMeta() {
        return meta;
    }

    @Override
    public boolean isFullCube(IBlockState p_149686_1_) {
        return false;
    }
    @Override
    public EnumBlockRenderType getRenderType(IBlockState p_149645_1_) {
        return EnumBlockRenderType.MODEL;
    }
    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return SHAPE;
    }
}
