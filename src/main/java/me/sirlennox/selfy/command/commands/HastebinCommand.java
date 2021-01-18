package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.utils.stat.ArrayUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import me.sirlennox.selfy.utils.stat.Utils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.io.IOException;

public class HastebinCommand extends Command {
    public HastebinCommand() {
        super("hastebin", "Create a hastebin", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 1) {
            sendUsage("<Text>", event.getMessage());
            return;
        }
        String text = ArrayUtils.bindString(args, 0, args.length).replaceAll("\\n", "\n");
        try {
            MessageUtils.editMessage("Success", "Created hastebin: https://hastebin.com/" + Utils.createHastebin(text), Color.GREEN.getRGB(), event.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            MessageUtils.editMessage("Error", "An error occurred while creating hastebin.", Color.RED.getRGB(), event.getMessage());
        }
    }
}
