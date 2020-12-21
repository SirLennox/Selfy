package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import me.sirlennox.selfy.util.Utils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

            MessageUtils.editMessage("Meme", Utils.getMeme(), "A meme for you", MathUtils.randomColor().getRGB(), event.getMessage());
        } catch (Exception e) {
            MessageUtils.editMessage("Error", "An error occurred while reading meme!", Color.RED.getRGB(), event.getMessage());
        }

    }
}
