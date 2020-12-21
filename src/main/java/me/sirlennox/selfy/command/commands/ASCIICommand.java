package me.sirlennox.selfy.command.commands;

import com.google.common.base.Splitter;
import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.IOException;

public class ASCIICommand extends Command {
    public ASCIICommand() {
        super("ascii", "Send an ASCII message", Category.FUN);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length <= 0) {
            printUsage("<ASCII>", event.getMessage());
            return;
        }
        try {
            String text = MessageUtils.getASCII(ArrayUtils.bindString(args, 0, args.length, " "));
            MessageUtils.editMessage("```" + text + "```", event.getMessage());
        } catch (IOException e) {

        }
    }
}
