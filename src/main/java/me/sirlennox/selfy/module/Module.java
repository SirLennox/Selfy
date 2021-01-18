package me.sirlennox.selfy.module;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.event.Event;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;

public class Module {

    public boolean autoStart;
    public boolean onlyPremium;
    public String name;
    public String desc;
    public boolean toggled;
    public long lastMS;
    public Category category;
    public ArrayList<Setting> settings;



    public Module(String name, String desc, Category category) {
        this(name, desc, category, false);
    }


    public Module(String name, String desc, Category category, boolean onlyPremium) {
        this.name = name;
        this.desc = desc;
        this.toggled = false;
        this.lastMS = 0;
        this.category = category;
        this.onlyPremium = onlyPremium;
        this.settings = new ArrayList<>();
        this.autoStart = false;
        this.initSettings();
    }

    public void initSettings() {

    }

    public void registerSetting(Setting setting) {
        this.settings.add(setting);
    }


    public void registerSetting(String name, Object value) {
        this.settings.add(new Setting(name, value));
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

    public Setting getSettingByName(String name) {
      /*  for(Setting s : this.settings) {
            if(s.name.equalsIgnoreCase(name)) {
                return s;
            }
        }*/
        return this.settings.stream().filter(s -> s.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Setting setSetting(String name, Object value) {
        Setting s = getSettingByName(name);
        if(s == null) return null;
        s.value = value;
        return s;
    }












}
