package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import me.sirlennox.selfy.utils.stat.Utils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class MemeCommand extends Command {
    public MemeCommand() {
        super("memes", "Get memes", Category.FUN);
        this.aliases.add("memez");
        this.aliases.add("meme");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        try {

            MessageUtils.editMessage("Meme", Utils.getMeme(), "A meme for you", MathUtils.getRandomColor().getRGB(), event.getMessage());
        } catch (Exception e) {
            MessageUtils.editMessage("Error", "An error occurred while reading meme!", Color.RED.getRGB(), event.getMessage());
        }

    }
}
