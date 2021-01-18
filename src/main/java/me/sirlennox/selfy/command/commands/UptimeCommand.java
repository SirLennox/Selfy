package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class UptimeCommand extends Command {
    public UptimeCommand() {
        super("uptime", "Get the bot uptime", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        MessageUtils.editMessage("Uptime", "You are running the bot for **" +MathUtils.getTime(System.currentTimeMillis() - Main.selfy.startedMS)+ "**", Color.GREEN.getRGB(), event.getMessage());
    }


}
