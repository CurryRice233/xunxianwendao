package curryrice.xunxianwendao.block;

import curryrice.xunxianwendao.inventory.InteractionObjectCauldronFurnace;
import curryrice.xunxianwendao.tileentity.TileEntityCauldronFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockCauldronFurnace extends BlockContainer {
    private static final BooleanProperty BURNING = BlockStateProperties.LIT;
    private static final VoxelShape SHAPE = Block.makeCuboidShape(2D, 0.0D, 2D, 14D, 16D, 14D);
    public BlockCauldronFurnace() {
        super(Block.Properties.create(Material.ANVIL).hardnessAndResistance(10.0F));
        this.setRegistryName("cauldron_furnace");
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getBurningProperty(), Boolean.FALSE));
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote() && player instanceof EntityPlayerMP) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEntityCauldronFurnace) {
                NetworkHooks.openGui((EntityPlayerMP) player, new InteractionObjectCauldronFurnace((TileEntityCauldronFurnace) tileEntity), (buffer) -> {
                    buffer.writeBlockPos(pos);
                });
            }
        }
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){
        if(worldIn.getBlockState(pos.down()).getBlock()== Blocks.FIRE){
            worldIn.setBlockState(pos,BlockList.CAULDRON_FURNACE.getDefaultState().with(this.getBurningProperty(), Boolean.TRUE));
        }else{
            worldIn.setBlockState(pos,BlockList.CAULDRON_FURNACE.getDefaultState().with(this.getBurningProperty(), Boolean.FALSE));
        }

    }


    public BooleanProperty getBurningProperty(){
        return BURNING;
    }

    @Override
    public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
        return SHAPE;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityCauldronFurnace();
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder) {
        builder.add(BURNING);
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
    public boolean allowsMovement(IBlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
}
