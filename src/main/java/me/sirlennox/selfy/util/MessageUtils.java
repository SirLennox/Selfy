package me.sirlennox.selfy.util;

import com.google.common.base.Splitter;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MessageUtils {

    public static void sendMessage(String title, String desc, int color, TextChannel channel) {
        Iterable<String> pieces = Splitter.fixedLength(1024).split(desc);
        int index = 0;
        if(channel.canYouEmbedLinks()) {
            for(String s : pieces) {
                if(index == 0) {
                    channel.sendMessage(new EmbedBuilder().setTitle(title).setColor(new Color(color)).setDescription(s)).join();
                } else {
                    channel.sendMessage(new EmbedBuilder().setColor(new Color(color)).setDescription(s)).join();
                }
                index++;
            }
            return;
        }
        pieces = Splitter.fixedLength(2000 - (title != null ? "**" + title + ":**" : "").length()).split(desc);
        for(String s : pieces) {
            if(index == 0) {
                channel.sendMessage((title != null ? "**" + title + ":**\n" : "") + s).join();
            }else {
                channel.sendMessage(s).join();
            }
            index++;
        }

    }

    public static void editMessage(String title, String desc, int color, Message msg) {
        if(msg.getChannel().canYouEmbedLinks()) {
            Iterable<String> pieces = Splitter.fixedLength(1024).split(desc);
            int index = 0;
            for(String s : pieces) {
                if(index == 0) {
                    msg.edit(new EmbedBuilder().setTitle(title).setColor(new Color(color)).setDescription(s)).join();
                  //  msg.edit("").join();
                    msg.removeContent().join();
                }else {
                    msg.getChannel().sendMessage(new EmbedBuilder().setColor(new Color(color)).setDescription(s)).join();
                }
                index++;
            }


            return;
        }
        Iterable<String> pieces = Splitter.fixedLength(1024).split(desc);
        int index = 0;
        for(String s : pieces) {
            if(index == 0) {
                msg.edit((title != null ? "**" + title + ":**\n" : "") + s).join();
            }else {
                msg.edit(s).join();
            }
            index++;
        }
    }

    public static void editMessage(String content, Message msg) {
        Iterable<String> pieces = Splitter.fixedLength(2000).split(content);
        int index = 0;
        for(String s : pieces) {
            if(index == 0) {
                msg.edit(s).join();
            }else {
                msg.getChannel().sendMessage(s).join();
            }
            index++;
        }

    }

    public static void sendMessage(String content, TextChannel channel) {
        Iterable<String> pieces = Splitter.fixedLength(2000).split(content);
        for(String s : pieces) {
            channel.sendMessage(s).join();
        }

    }

    public static String getASCII(String ascii) throws IOException {
        return HttpUtils.get("https://artii.herokuapp.com/make?text=" + ascii);
    }

    public static String getTitle(String title) {
        return "«──────── **" + title + "** ────────»\n";
    }

}
