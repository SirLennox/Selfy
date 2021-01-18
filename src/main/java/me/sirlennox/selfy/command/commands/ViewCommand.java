package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.utils.stat.HttpUtils;
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;

public class ViewCommand extends Command {
    public ViewCommand() {
        super("view", "View a website", Category.UTIL);
        this.aliases.add("curl");
        this.aliases.add("get");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        String link = args[0];
        try {
            String response = HttpUtils.get(link);
            try {
                JSONObject jsonContent = (JSONObject) JSONValue.parse(response);
                StringBuilder stringBuilder = new StringBuilder();

                for(Object objectKey : jsonContent.keySet()) {
                    Object objectValue = jsonContent.get(objectKey);
                    stringBuilder
                            .append("**")
                            .append(objectKey)
                            .append("**")
                            .append(" ")
                            .append("»")
                            .append(" ")
                            .append("`")
                            .append(objectValue)
                            .append("`")
                            .append("\n");
                }

                MessageUtils.editMessage(event.getMessage(), "Content of " + link, stringBuilder.toString(), MathUtils.getRandomColor().getRGB());
            } catch (Exception exception) {
                MessageUtils.editMessage(event.getMessage(), "Content of " + link, response, MathUtils.getRandomColor().getRGB());
            }
        } catch (IOException e) {
            MessageUtils.editMessage(event.getMessage(), "Error", "An error occurred while trying to view the website? Do you use http/s?", MathUtils.getRandomColor().getRGB());
        }


    }
}
