package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.HttpUtils;
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.*;

public class AnimalCommand extends Command {
    public AnimalCommand() {
        super("animal", "Get an animal", Category.FUN);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length != 1) {
            sendUsage("<Animal-Type>", event.getMessage());
            return;
        }

        String animal = args[0].toLowerCase();

        try {
            JSONObject animalInfo = (JSONObject) JSONValue.parse(HttpUtils.get("https://some-random-api.ml/img/" + animal));
            MessageUtils.editMessage(event.getMessage(), "Here's a `" + animal + "`.", animalInfo.get("link").toString(), null, MathUtils.getRandomColor().getRGB());
        } catch (Exception exception) {
            MessageUtils.editMessage(event.getMessage(), "Meme", "Something went wrong while trying to get a `" + animal + "` for you.", Color.RED.getRGB());
        }
    }

}
