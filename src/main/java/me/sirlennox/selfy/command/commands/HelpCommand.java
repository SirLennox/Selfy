package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Help page", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent e) {
        StringBuilder sb = new StringBuilder();
        if(args.length == 0) {
            for(Category c : Category.values()) {
                Stream<Command> streamCmds = Main.selfy.commandManager.commands.stream().filter(m -> m.category == c);
                ArrayList<Command> cmds = new ArrayList<>();
                streamCmds.forEach(cmds::add);
                if(!cmds.isEmpty()) {
                    sb.append("\n**" + c.name + "**\n");
                    for (Command cmd : Main.selfy.commandManager.commands) {
                        if(cmd.category == c) {
                            sb.append("**" + cmd.cmd + "** » " + cmd.desc + "\n");
                        }
                    }
                }

            }

            MessageUtils.editMessage("Help", sb.toString(), Color.DARK_GRAY.getRGB(), e.getMessage());
        }else {
            Command command = Main.selfy.commandUtils.getCommandByName(args[0]);
            if(command != null) {
                sb.append("Command » `" + command.cmd + "`\n");
                sb.append("Alias/es » `" +  getAliases(command) + "`\n");
                sb.append("Description » `" + command.desc + "`\n");
                sb.append("Category » `" + command.category.name + "`\n");
                MessageUtils.editMessage("Help for " + command.cmd, sb.toString(), Color.DARK_GRAY.getRGB(), e.getMessage());
            }else {
                MessageUtils.editMessage(null, "Command not found!", Color.RED.getRGB(), e.getMessage());
            }
        }
    }

    public static String getAliases(Command cmd) {
        if(cmd.aliases.size() <= 0) return "N/A";
        return ArrayUtils.bindString(cmd.aliases.toArray(new String[100]), 0, cmd.aliases.size(), ";");
    }
}
