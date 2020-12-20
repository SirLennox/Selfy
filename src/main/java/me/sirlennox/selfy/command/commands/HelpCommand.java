package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Help page");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent e) {
        StringBuilder sb = new StringBuilder();
        if(args.length == 0) {
            for (Command cmd : Main.selfy.commandManager.commands) {
                sb.append("**" + cmd.cmd + "** ─ " + cmd.desc + "\n");
            }
            MessageUtils.editMessage("Help", sb.toString(), Color.DARK_GRAY.getRGB(), e.getMessage());
        }else {
            Command command = null;
            for(Command cmd : Main.selfy.commandManager.commands) {
                if(cmd.aliases.contains(args[0]) || cmd.cmd.equalsIgnoreCase(args[0])) {
                    command = cmd;
                }
            }

            if(command != null) {
                sb.append("Command » " + command.cmd + "\n");
                sb.append("Alias/es » " +   ArrayUtils.bindString(command.aliases.toArray(new String[100]), 0, command.aliases.size(), ";") + "\n");
                sb.append("Description » " + command.desc + "\n");
                MessageUtils.editMessage(MessageUtils.getTitle("Help for " + command.cmd), sb.toString(), Color.DARK_GRAY.getRGB(), e.getMessage());
            }else {
                MessageUtils.editMessage(null, "Command not found!", Color.RED.getRGB(), e.getMessage());
            }
        }
    }
}
