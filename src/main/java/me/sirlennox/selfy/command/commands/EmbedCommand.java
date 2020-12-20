package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.math.BigInteger;

public class EmbedCommand extends Command {
    public EmbedCommand() {
        super("embed", "Send an embed message");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if (args.length != 2) {
            printUsage("<Color> <Message>", event.getMessage());
            return;
        }
        try {
            MessageUtils.editMessage(null, ArrayUtils.bindString(args, 1, args.length), Long.decode(args[0]).intValue(), event.getMessage());
        }catch (Exception e) {
            MessageUtils.editMessage("Error", "Can't parse color", Color.RED.getRGB(), event.getMessage());
        }
    }
}
