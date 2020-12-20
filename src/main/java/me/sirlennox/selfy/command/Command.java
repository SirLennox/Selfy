package me.sirlennox.selfy.command;


import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

import java.awt.*;
import java.util.ArrayList;

public abstract class Command {

    public String cmd;
    public String desc;
    public ArrayList<String> aliases = new ArrayList<String>();
    public boolean onlyPremium = false;

    public Command(String cmd, String desc) {
        this.cmd = cmd;
        this.desc = desc;
    }

    public Command(String cmd, String desc, boolean onlyPremium) {
        this.cmd = cmd;
        this.desc = desc;
        this.onlyPremium = onlyPremium;
    }

    public abstract void onCommand(String[] args, TextChannel channel, Message msg);

    public void printUsage(String args, Message msg) {
        MessageUtils.editMessage("Usage", "Usage: " + Main.selfy.PREFIX + this.cmd + " " + args, Color.RED.getRGB(), msg);
    }

}
