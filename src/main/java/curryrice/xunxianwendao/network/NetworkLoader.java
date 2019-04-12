package curryrice.xunxianwendao.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkLoader {
	public static SimpleChannel instance = NetworkRegistry.newSimpleChannel(new ResourceLocation("xunxianwendao"), 
			() -> "1.0", (X) ->X.equalsIgnoreCase("1.0"), (X) ->X.equalsIgnoreCase("1.0"));
	
	public NetworkLoader()
    {
        //instance.registerMessage(index, messageType, encoder, decoder, messageConsumer)
    }

}
