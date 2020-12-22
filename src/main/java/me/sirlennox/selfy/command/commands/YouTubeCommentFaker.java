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
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class YouTubeCommentFaker extends Command {
    public YouTubeCommentFaker() {
        super("youtube", "Fake a youtube comment", Category.FUN);
        this.aliases.add("ytcomment");
        this.aliases.add("yt");
    }

    public static String api = "https://some-random-api.ml/canvas/youtube-comment/";

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 2) {
            sendUsage("<Username> <Avatar> / <Mentioned-User> <Comment>", event);
            return;
        }
        User user = event.getMessage()
                .getMentionedUsers()
                .stream()
                .findFirst()
                .orElse(
                        null
                );

        if(user == null && args.length < 3) {
            sendUsage("<Username> <Avatar> / <Mentioned-User> <Comment>", event);
            return;
        }
        String username = null;
        String avatar = null;
        String comment = null;
        if(user != null) {
            username = user.getName().replaceAll(" ", "+");
            avatar = user.getAvatar().getUrl().toString();
            comment = ArrayUtils.bindString(args, 1, args.length).replaceAll(" ", "+");
        }else {
            username = args[0];
            avatar = (args[1].equalsIgnoreCase("-") ? event.getApi().getYourself().getAvatar().getUrl().toString() : args[1]);
            comment = ArrayUtils.bindString(args, 2, args.length).replaceAll(" ", "+");
        }
        try {
            String url = api + "?username=" + username + "&avatar=" + avatar + "&comment=" + comment;
            //System.out.println(url);
            MessageUtils.editMessage(null,url, null, Color.RED.getRGB(), event.getMessage());
        } catch (Exception e) {
            MessageUtils.editMessage("Error", "An error occurred while trying to fake youtube comment.", Color.RED.getRGB(), event.getMessage());
        }
    }
}
