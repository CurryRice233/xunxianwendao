package curryrice.xunxianwendao.block;

import curryrice.xunxianwendao.XunXianWenDao;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockPurpleHeartGrass extends BlockBush {
    private static final VoxelShape SHAPE = Block.makeCuboidShape(4D, 0.0D, 4D, 12D, 12D, 12D);
    public BlockPurpleHeartGrass() {
        super(Block.Properties.create(Material.PLANTS).needsRandomTick().doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.PLANT));
        this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "purple_heart_grass"));
    }

    @Override
    public void randomTick(IBlockState state, World worldIn, BlockPos pos, Random random) {
        List<EntityPlayer> list=worldIn.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos).grow(3.0D));
        for(EntityPlayer player:list) {
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(19),150));
        }
        int x = random.nextInt(4)-2;
        int y = random.nextInt(2)-1;
        int z = random.nextInt(4)-2;
        BlockPos pos2 = pos.add(x,y,z);
        if(worldIn.isAirBlock(pos2) && isValidGround(worldIn.getBlockState(pos2.down()),worldIn,pos2)){
            worldIn.setBlockState(pos2,this.getDefaultState());
        }
    }

    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return SHAPE;
    }
}
