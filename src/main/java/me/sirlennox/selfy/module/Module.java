package me.sirlennox.selfy.module;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;

public class Module {

    public boolean autoStart = false;
    public boolean onlyPremium = false;
    public String name;
    public String desc;
    public boolean toggled = false;
    public long lastMS;
    public Category category;
    public ArrayList<String> modes = new ArrayList<String>();



    public Module(String name, String desc, Category category) {
        this.name = name;
        this.desc = desc;
        this.toggled = false;
        this.lastMS = 0;
        this.category = category;
    }


    public Module(String name, String desc, Category category, boolean onlyPremium) {
        this.name = name;
        this.desc = desc;
        this.toggled = false;
        this.lastMS = 0;
        this.category = category;
        this.onlyPremium = onlyPremium;
    }



    public void onEnable(MessageCreateEvent e) {

    }

    public void onUpdate() {

    }


    public void onChatMessage(MessageCreateEvent event) {

    }

    public void onDisable(MessageCreateEvent e) {

    }


    public void toggle(MessageCreateEvent e) {
        this.toggled = !this.toggled;
        if(e != null) {
            MessageUtils.editMessage("Toggled Module", (toggled ? "Enabled" : "Disabled") + " **" + this.name + "**", (toggled ? Color.GREEN.getRGB() : Color.RED.getRGB()), e.getMessage());
        }
        if(this.toggled) {
            this.onEnable(e);
        }else {
            this.onDisable(e);
        }
    }










}
