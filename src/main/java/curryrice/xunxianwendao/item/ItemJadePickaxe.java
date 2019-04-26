package curryrice.xunxianwendao.item;

import javax.annotation.Nullable;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.init.CreativeTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemJadePickaxe extends ItemPickaxe {
	public ItemJadePickaxe() {
		super(ToolMaterial.jade, 2, -1.0F, (new Item.Properties()).group(CreativeTabs.MAIN));
		this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID,"item_jade_pickaxe"));
	}
	@Override
	public int getHarvestLevel(ItemStack stack, net.minecraftforge.common.ToolType tool, @Nullable EntityPlayer player, @Nullable IBlockState blockState) {
		return 4;
	}

}
