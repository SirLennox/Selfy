package me.sirlennox.selfy.command;


import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;

public abstract class Command {

    public String cmd;
    public String desc;
    public ArrayList<String> aliases = new ArrayList<String>();
    public Category category;
    public boolean onlyPremium = false;

    public Command(String cmd, String desc, Category category) {
        this.cmd = cmd;
        this.desc = desc;
        this.category = category;
    }

    public Command(String cmd, String desc, Category category, boolean onlyPremium) {
        this.cmd = cmd;
        this.desc = desc;
        this.onlyPremium = onlyPremium;
        this.category = category;
    }

    public abstract void onCommand(String[] args, MessageCreateEvent event);

    public void sendUsage(String args, Message msg) {
        MessageUtils.editMessage("Usage", "Usage: " + Main.selfy.PREFIX + this.cmd + " " + args, Color.RED.getRGB(), msg);
    }


    public void sendUsage(String args, MessageCreateEvent e) {
        MessageUtils.editMessage("Usage", "Usage: " + Main.selfy.PREFIX + this.cmd + " " + args, Color.RED.getRGB(), e.getMessage());
    }


}
