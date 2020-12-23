package me.sirlennox.selfy.script;

import com.google.common.base.Splitter;
import me.sirlennox.selfy.util.HttpUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.io.IOException;

public class MessageUtilsWithConstructor {

    public void sendMessage(String title, String image, String desc, int color, TextChannel channel) {
        if(!channel.canYouWrite()) return;
        if(desc == null) {
            channel.sendMessage(new EmbedBuilder().setTitle(title).setImage(image).setColor(new Color(color))).join();
            return;
        }
        Iterable<String> pieces = Splitter.fixedLength(1024).split(desc);
        int index = 0;
        if(channel.canYouEmbedLinks()) {
            for(String s : pieces) {
                if(index == 0) {
                    channel.sendMessage(new EmbedBuilder().setTitle(title).setImage(image).setColor(new Color(color)).setDescription(s)).join();
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

    public void sendMessage(String title, String desc, int color, TextChannel channel) {
        sendMessage(title, null, desc, color, channel);
    }

    public void editMessage(String title, String desc, int color, Message msg) {
        editMessage(title, null, desc, color, msg);
    }


    public void editMessage(String title, String image, String desc, int color, Message msg) {
        if(!msg.getChannel().canYouWrite()) return;
        if(msg.getAuthor().getId() != msg.getApi().getYourself().getId()) {
            sendMessage(title, image, desc, color, msg.getChannel());
            return;
        }
        if(desc == null) {
            msg.edit(new EmbedBuilder().setTitle(title).setImage(image).setColor(new Color(color))).join();
            msg.removeContent().join();
            return;
        }
        if(msg.getChannel().canYouEmbedLinks()) {
            Iterable<String> pieces = Splitter.fixedLength(1024).split(desc);
            int index = 0;
            for(String s : pieces) {
                if(index == 0) {
                    msg.edit(new EmbedBuilder().setTitle(title).setImage(image).setColor(new Color(color)).setDescription(s)).join();
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

    public void editMessage(Message msg, String title, String image, String desc, int color) {
        editMessage(title, image, desc, color, msg);
    }


    public void editMessage(Message msg, String title, String desc, int color) {
        editMessage(title, null, desc, color, msg);
    }



    public void editMessage(String content, Message msg) {
        if(!msg.getChannel().canYouWrite()) return;
        if(msg.getAuthor().getId() != msg.getApi().getYourself().getId()) {
            sendMessage(content, msg.getChannel());
            return;
        }
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

    public void sendMessage(String content, TextChannel channel) {
        if(!channel.canYouWrite()) return;
        Iterable<String> pieces = Splitter.fixedLength(2000).split(content);
        for(String s : pieces) {
            channel.sendMessage(s).join();
        }

    }

    public String getASCII(String ascii) throws IOException {
        return HttpUtils.get("https://artii.herokuapp.com/make?text=" + ascii);
    }

    public String getTitle(String title) {
        return "«──────── **" + title + "** ────────»\n";
    }

}
