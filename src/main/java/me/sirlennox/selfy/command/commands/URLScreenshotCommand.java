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
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class URLScreenshotCommand extends Command {
    public URLScreenshotCommand() {
        super("urlscreenshot", "Take a screenshot of an URL", Category.UTIL);
        this.aliases.add("screenshoturl");
    }


    public static String api = "https://api.screensoap.com/screenshot?width=1280&height=720&asFile=true&url=";

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length != 1) {
            sendUsage("<URL>", event);
            return;
        }



        try {
            String url = api + args[0];
            //System.out.println(url);
            MessageUtils.editMessage(url, event.getMessage());
        } catch (Exception e) {
            MessageUtils.editMessage("Error", "An error occurred while trying to take screenshot of website.", Color.RED.getRGB(), event.getMessage());
        }
    }
}
