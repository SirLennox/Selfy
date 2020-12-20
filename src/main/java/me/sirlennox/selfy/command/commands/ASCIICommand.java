package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;

import java.io.IOException;

public class ASCIICommand extends Command {
    public ASCIICommand() {
        super("ascii", "Send an ASCII message");
    }

    @Override
    public void onCommand(String[] args, TextChannel channel, Message msg) {
        if(args.length <= 0) {
            printUsage("<ASCII>", msg);
            return;
        }
        try {
            MessageUtils.editMessage("```" + MessageUtils.getASCII(ArrayUtils.bindString(args, 0, args.length, " ")) + "```", msg);
        } catch (IOException e) {

        }
    }
}
