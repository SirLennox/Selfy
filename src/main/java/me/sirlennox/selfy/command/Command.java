package me.sirlennox.selfy.command;


import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;

// This is the Command class. If you extend it you can create your own command. Don't forget to register it.
public abstract class Command {

    // This is the command you can type in
    public String cmd;
    // This is the description of the command
    public String desc;
    // Here are the aliases saved
    public ArrayList<String> aliases = new ArrayList<String>();
    // This is the category of the command
    public Category category;
    // If the command is premium only
    public boolean onlyPremium = false;

    // This is the constructor of a command where you can specify the command, the description and the category
    public Command(String cmd, String desc, Category category) {
        this.cmd = cmd;
        this.desc = desc;
        this.category = category;
    }
    // This is the constructor of a command where you can specify the command, the description, the category and onlyPremium (if the command is premium only)
    public Command(String cmd, String desc, Category category, boolean onlyPremium) {
        this.cmd = cmd;
        this.desc = desc;
        this.onlyPremium = onlyPremium;
        this.category = category;
    }

    // If the command got executed this method will be called. The parameters are the arguments of the command (splitted with \" \ and the event, so you can get the message, the channel and much more information about the event
    public abstract void onCommand(String[] args, MessageCreateEvent event);

    // This is a simplified method to send usages. The parameters are: args (The args you have to specify) and the Message, because the method edit your message
    public void sendUsage(String args, Message msg) {
        MessageUtils.editMessage("Usage", "Usage: " + Main.selfy.PREFIX + this.cmd + " " + args, Color.RED.getRGB(), msg);
    }

    // A method where you only have to specify the event and not the Message
    public void sendUsage(String args, MessageCreateEvent e) {
        sendUsage(args, e.getMessage());
    }


}
