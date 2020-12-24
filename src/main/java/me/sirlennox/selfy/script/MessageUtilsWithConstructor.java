package me.sirlennox.selfy.script;

import com.google.common.base.Splitter;
import me.sirlennox.selfy.util.HttpUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.io.IOException;

public class MessageUtilsWithConstructor {

    public void sendMessage(String title, String image, String desc, int color, TextChannel channel) {
        sendMessage(title, image, desc, color, channel);
    }

    public void sendMessage(String title, String desc, int color, TextChannel channel) {
        sendMessage(title, null, desc, color, channel);
    }

    public void editMessage(String title, String desc, int color, Message msg) {
        editMessage(title, null, desc, color, msg);
    }


    public void editMessage(String title, String image, String desc, int color, Message msg) {
        editMessage(title, image, desc, color, msg);
    }

    public void editMessage(Message msg, String title, String image, String desc, int color) {
        editMessage(title, image, desc, color, msg);
    }


    public void editMessage(Message msg, String title, String desc, int color) {
        editMessage(title, null, desc, color, msg);
    }



    public void editMessage(String content, Message msg) {
        editMessage(content, msg);
    }

    public void sendMessage(String content, TextChannel channel) {
        sendMessage(content, channel);
    }

    public String getASCII(String ascii) throws IOException {
        return MessageUtils.getASCII(ascii);
    }

    public String getTitle(String title) {
        return MessageUtils.getTitle(title);
    }

}
