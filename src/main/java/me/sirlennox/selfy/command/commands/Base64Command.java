/*
 * Copyright (c) 2020 SirLennox & f1nniboy.
 *
 * This code is copyrighted to SirLennox and f1nniboy.
 *
 * Using this code without privileges is not allowed.
 *
 *
 *
 */

package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64Command extends Command {
    public Base64Command() {
        super("base64", "Encode/Decode Base64", Category.UTIL);
    }
    public static String base64Expression = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$";
    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 1) {
            sendUsage("<Text>", event.getMessage());
            return;
        }

        String base64 = args[0];
        String text = String.join(" ", args);

        Pattern pattern = Pattern.compile(base64Expression);
        Matcher matcher = pattern.matcher(base64);

        boolean isEncoded = matcher.find();
        String finishedText;

        if(isEncoded) {
            // Decode the text.
            finishedText = new String(Base64.getDecoder().decode(text));
        } else {
            // Encode the text.
            finishedText = Base64.getEncoder().encodeToString(text.getBytes());
        }

        MessageUtils.editMessage(event.getMessage(), (isEncoded ? "Decode" : "Encode") + " Base64", finishedText, MathUtils.randomColor().getRGB());
    }
}
