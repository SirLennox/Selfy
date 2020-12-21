package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class AdCommand extends Command {
    public AdCommand() {
        super("ad", "Send an ad for this selfbot", Category.OTHER);
        this.aliases.add(Main.NAME);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("I am running **" + Main.selfy.NAME + " " + Main.selfy.getVersion() + "**\n");
        sb.append("With this program you can add some really nice commands and modules to discord.\n");
        sb.append("Only type in " + Main.selfy.PREFIX + "help to get a view of all commands!\n");
        sb.append("Made by: **" + Main.selfy.getDevelopers() + "**\n");
        sb.append("[Our website](http://comming-soon.online)");
        MessageUtils.editMessage(Main.selfy.NAME + " " + Main.selfy.getVersion(), sb.toString(), Color.DARK_GRAY.getRGB(), event.getMessage());
    }
}
