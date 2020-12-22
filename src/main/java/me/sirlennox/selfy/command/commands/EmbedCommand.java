package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class EmbedCommand extends Command {
    public EmbedCommand() {
        super("embed", "Send an embed message", Category.FUN);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if (args.length < 2) {
            sendUsage("<Color> <Message>", event.getMessage());
            return;
        }
        try {
            MessageUtils.editMessage(null, ArrayUtils.bindString(args, 1, args.length).replaceAll("\\n", "\n"), Long.decode(args[0]).intValue(), event.getMessage());
        }catch (Exception e) {
            MessageUtils.editMessage("Error", "Can't parse color", Color.RED.getRGB(), event.getMessage());
        }
    }
}
