package me.sirlennox.selfy.module;

import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Module {

    public boolean autoStart = false;

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



    public void onEnable(TextChannel channel) {

    }

    public void onUpdate() {

    }


    public void onChatMessage(MessageCreateEvent event) {

    }

    public void onDisable(TextChannel channel) {

    }


    public void toggle(TextChannel channel) {
        this.toggled = !this.toggled;
        MessageUtils.sendMessage("Toggled", (toggled ? "§aEnabled " : "§cDisabled ") + "§8" + this.name, (toggled ? Color.GREEN.getRGB() : Color.RED.getRGB()),channel);
        if(this.toggled) {
            this.onEnable(channel);
        }else {
            this.onDisable(channel);
        }
    }










}
