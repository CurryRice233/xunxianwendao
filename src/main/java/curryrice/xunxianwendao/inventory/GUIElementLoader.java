package curryrice.xunxianwendao.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GUIElementLoader implements IGuiHandler {
    public static final int GUI_DrugRefiningFurnace = 1;

    public GUIElementLoader()
    {
        //NetworkRegistry.INSTANCE.registerGuiHandler(WenDao.instance, this);
    }
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}
