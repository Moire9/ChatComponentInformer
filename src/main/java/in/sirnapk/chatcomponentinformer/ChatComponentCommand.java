package in.sirnapk.chatcomponentinformer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class ChatComponentCommand extends CommandBase {
    @Override
    public String getCommandName() { return "cci"; }

    @Override
    public String getCommandUsage(ICommandSender sender) { return "/" + getCommandName() + " [toggle|read|max]. Run with no args for info."; }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("ChatComponent Informer, Version " + ChatComponentInformer.VERSION + "\nMade by SirNapkin1334. Licensed under GNU GPLv3."));
        } else {
            switch (args[0]) {
                case "toggle":
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText((ChatComponentInformer.instance.enabled ? "Disabled" : "Enabled") + " ChatComponent Informer!"));
                    ChatComponentInformer.instance.enabled = !ChatComponentInformer.instance.enabled;
                    break;
                case "read":
                    EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
                    System.out.println(ChatComponentListener.history.toString());
                    for (String i : ChatComponentListener.history) {
                        player.addChatComponentMessage(new ChatComponentText(i));
                    }
                    break;
                case "max":
                    try {
                        ChatComponentListener.setMaxLength(Long.parseLong(args[1]));
                        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Set max length to " + args[1] + "!"));
                    } catch (NumberFormatException e) {
                        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Invalid Number"));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Current max: " + ChatComponentListener.maxLength));
                    }
                    break;
                default:
                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("Error: Invalid usage!"));
            }
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "toggle", "read", "max", "help") : null;
    }

    @Override
    public int getRequiredPermissionLevel() { return -1; }
}
