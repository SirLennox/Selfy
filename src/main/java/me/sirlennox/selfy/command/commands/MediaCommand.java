package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

public class MediaCommand extends Command {
    public MediaCommand() {
        super("media", "Send media content in embed", Category.FUN);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 1) {
            sendUsage("<Link>", event);
            return;
        }
        MessageUtils.editMessage(null, args[0], null, MathUtils.getRandomColor().getRGB(), event.getMessage());
    }
}
