package curryrice.xunxianwendao.block;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.util.Elements;
import curryrice.xunxianwendao.util.IElements;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockIceGrass extends BlockBush implements IElements {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(4D, 0.0D, 4D, 12D, 12D, 12D);
    private static Elements elements = new Elements(0,0,1,0,0);
    public BlockIceGrass() {
        super(Block.Properties.create(Material.PLANTS).needsRandomTick().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.SNOW));
        this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "ice_grass"));
    }

    @Override
    public void randomTick(IBlockState state, World worldIn, BlockPos pos, Random random) {
        int x = random.nextInt(4)-2;
        int y = random.nextInt(2)-1;
        int z = random.nextInt(4)-2;
        BlockPos pos2 = pos.add(x,y,z);
        if(worldIn.isAirBlock(pos2) && isValidGround(worldIn.getBlockState(pos2.down()),worldIn,pos2)){
            worldIn.setBlockState(pos2,this.getDefaultState());
        }
    }

    @Override
    public boolean isValidPosition(IBlockState state, IWorldReaderBase worldIn, BlockPos pos) {
        return this.isValidGround(worldIn.getBlockState(pos.down()), worldIn, pos.down());
    }

    @Override
    protected boolean isValidGround(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return state.getBlock()== Blocks.SNOW_BLOCK;
    }

    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public Elements getElements() {
        return elements;
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TextComponentString(elements.toString()).applyTextStyle(TextFormatting.DARK_GRAY));
    }
}
