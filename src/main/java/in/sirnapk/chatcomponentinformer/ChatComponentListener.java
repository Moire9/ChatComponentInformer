package in.sirnapk.chatcomponentinformer;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatComponentListener {
	public static long maxLength = 100;
	public static final List<String> history = new ArrayList<>();

	@SubscribeEvent
	public void chatComponentPrinter(ClientChatReceivedEvent event) {
		if (ChatComponentInformer.instance.enabled) {
			if (history.size() > maxLength) {
				history.remove(0);
			}
			history.add(event.message.getUnformattedText() + " : " + event.message.getChatStyle().toString());
		}

		if (ChatComponentInformer.instance.enabled) {
			ChatComponentInformer.instance.logger.info(event.message.getChatStyle());//.getChatClickEvent().getValue());
			// TODO: every time chat message is sent, if enabled, add to arraylist. if > 10, yeet the first and add message (so it never exceeds 10 or some other value). then "/cci read" lists out the data of all of the items in the list (perhaps chat msg + chat command click text stuff). "/cci max" changes max arraylist length.
		}
	}

	public static void setMaxLength(long _maxLength) {
		maxLength = _maxLength;
		while (history.size() > maxLength) {
			history.remove(0);
		}
	}

}
