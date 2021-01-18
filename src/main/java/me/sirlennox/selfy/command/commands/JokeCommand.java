package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.utils.stat.HttpUtils;
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.*;

public class JokeCommand extends Command {
    public JokeCommand() {
        super("joke", "Send a joke", Category.FUN);
    }

    public void onCommand(String[] args, MessageCreateEvent event) {

        try {
            JSONObject joke = (JSONObject) JSONValue.parse(HttpUtils.get("https://official-joke-api.appspot.com/jokes/random/"));
            MessageUtils.editMessage(event.getMessage(), joke.get("setup").toString(), "||" + joke.get("punchline") + "||", MathUtils.getRandomColor().getRGB());
        } catch (Exception exception) {
            MessageUtils.editMessage(event.getMessage(), "Joke", "Something went wrong while trying to get a funny joke for you.", Color.RED.getRGB());
        }
    }
}
