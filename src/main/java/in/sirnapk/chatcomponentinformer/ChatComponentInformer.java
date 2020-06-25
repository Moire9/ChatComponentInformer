package in.sirnapk.chatcomponentinformer;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "ChatComponentInformer", name = "ChatComponent Informer", version = ChatComponentInformer.VERSION)
public class ChatComponentInformer {
	public static final String VERSION = "1.0";
	public boolean enabled = false;
    public final Logger logger = LogManager.getLogger("ChatComponent Informer");

	@Mod.Instance("ChatComponentInformer")
	public static ChatComponentInformer instance;

    @EventHandler
    public void init(FMLInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new ChatComponentCommand());
		MinecraftForge.EVENT_BUS.register(new ChatComponentListener());
    }
}

