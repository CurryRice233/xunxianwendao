package curryrice.xunxianwendao.item;

import curryrice.xunxianwendao.XunXianWenDao;
import curryrice.xunxianwendao.init.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemMedicine extends ItemFood {
    private int meta;
    public ItemMedicine(int meta,String name){
        super(7, 0.7F, false, new Properties().group(CreativeTabs.MAIN));
        this.setAlwaysEdible();
        this.meta=meta;
        this.setRegistryName(new ResourceLocation(XunXianWenDao.MODID, "item_medicine_"+name));
    }

    @Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        switch (this.meta){
            case 0:player.addPotionEffect(new PotionEffect(Potion.getPotionById(10),1200));
                break;
            case 1:player.addPotionEffect(new PotionEffect(Potion.getPotionById(5),1200));
                break;
            case 2:player.addPotionEffect(new PotionEffect(Potion.getPotionById(22),1200));
                break;
            case 3:player.addPotionEffect(new PotionEffect(Potion.getPotionById(11),1200));
                break;
            case 4:player.addPotionEffect(new PotionEffect(Potion.getPotionById(3),4800));
                break;
            case 5:player.addPotionEffect(new PotionEffect(Potion.getPotionById(1),4800));
                break;
            case 6:player.addPotionEffect(new PotionEffect(Potion.getPotionById(8),4800));
                break;
            case 7:player.addPotionEffect(new PotionEffect(Potion.getPotionById(12),4800));
                break;
            case 8:player.addPotionEffect(new PotionEffect(Potion.getPotionById(16),4800));
                break;
            case 9:player.addPotionEffect(new PotionEffect(Potion.getPotionById(13),4800));
                break;
        }
    }

    /*public int getUseDuration(ItemStack p_77626_1_) {
        return 1;
    }*/

}
