package me.sirlennox.selfy.command;


import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.documentation.Documentated;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;

@Documentated("This is the Command class. If you extend it you can create your own command. Don't forget to register it.")
public abstract class Command {

    @Documentated("This is the command you can type in")
    public String cmd;
    @Documentated("This is the description of the command")
    public String desc;
    @Documentated("Here are the aliases saved")
    public ArrayList<String> aliases = new ArrayList<String>();
    @Documentated("This is the category of the command")
    public Category category;
    @Documentated("If the command is premium only")
    public boolean onlyPremium = false;

    @Documentated("This is the constructor of a command where you can specify the command, the description and the category")
    public Command(String cmd, String desc, Category category) {
        this.cmd = cmd;
        this.desc = desc;
        this.category = category;
    }
    @Documentated("This is the constructor of a command where you can specify the command, the description, the category and onlyPremium (if the command is premium only)")
    public Command(String cmd, String desc, Category category, boolean onlyPremium) {
        this.cmd = cmd;
        this.desc = desc;
        this.onlyPremium = onlyPremium;
        this.category = category;
    }

    @Documentated("If the command got executed this method will be called. The parameters are the arguments of the command (splitted with \" \") and the event, so you can get the message, the channel and much more information about the event")
    public abstract void onCommand(String[] args, MessageCreateEvent event);

    @Documentated("This is a simplified method to send usages. The parameters are: args (The args you have to specify) and the Message, because the method edit your message")
    public void sendUsage(String args, Message msg) {
        MessageUtils.editMessage("Usage", "Usage: " + Main.selfy.PREFIX + this.cmd + " " + args, Color.RED.getRGB(), msg);
    }

    @Documentated("A method where you only have to specify the event and not the Message")
    public void sendUsage(String args, MessageCreateEvent e) {
        sendUsage(args, e.getMessage());
    }


}
