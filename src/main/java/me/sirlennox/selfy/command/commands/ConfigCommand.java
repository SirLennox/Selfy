/*
 * Copyright (c) 2020 SirLennox & f1nniboy.
 *
 * This code is copyrighted to SirLennox and f1nniboy.
 *
 * Using this code without privileges is not allowed.
 *
 *
 *
 */

package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.config.ConfigAction;
import me.sirlennox.selfy.module.Setting;
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;
import me.sirlennox.selfy.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super("config", "WRITE / READ a config", Category.UTIL);
        this.aliases.add("cfg");
        this.aliases.add("conf");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 1) {
            sendUsage("<ConfigAction> <Key:Value>", event.getMessage());
            return;
        }

        String action = args[0];

        try {
            ConfigAction foundAction = ConfigAction.valueOf(action.toUpperCase());

            if(foundAction == ConfigAction.READ) {

                ArrayList<Setting> configObjects = Main.selfy.configManager.configs;
                StringBuilder stringBuilder = new StringBuilder();

                for(Setting configObject : configObjects) {
                    stringBuilder
                            .append("**")
                            .append(configObject.name)
                            .append("**")
                            .append(" ")
                            .append("»")
                            .append(" ")
                            .append("`")
                            .append(configObject.value)
                            .append("`")
                            .append("\n");
                }

                MessageUtils.editMessage(event.getMessage(), foundAction.name(), stringBuilder.toString(), MathUtils.randomColor().getRGB());

            } else if(foundAction == ConfigAction.WRITE) {

                if(args.length < 2) {
                    sendUsage("<ConfigAction> <Key:Value>", event.getMessage());
                    return;
                }

                String[] cutArgs = Arrays.copyOfRange(args, 1, args.length);
                String joinedCutArgs = String.join(" ", cutArgs);

                String[] objects = joinedCutArgs.split(" ");
                ArrayList<Setting> objectList = new ArrayList<>();

                for(String object : objects) {
                    try {
                        String[] splitObject = object.split(":");

                        String key = splitObject[0];
                        String value = splitObject[1];

                        objectList.add(new Setting(key, value));
                    } catch (Exception exception) {
                        sendUsage("<ConfigAction> <Key:Value>", event.getMessage());
                        return;
                    }
                }

                for(Setting s : objectList) {
                    Main.selfy.configManager.registerConfig(s);
                }
                MessageUtils.editMessage(event.getMessage(), foundAction.name(), "Added " + objectList.size() + " changes to the config.", MathUtils.randomColor().getRGB());

            }
        } catch (IllegalArgumentException exception) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Possible actions: \n");

            for(ConfigAction configAction : ConfigAction.values()) {
                stringBuilder.append("`")
                        .append(configAction.name())
                        .append("`\n");
            }

            MessageUtils.editMessage(event.getMessage(), "Actions", stringBuilder.toString(), MathUtils.randomColor().getRGB());
        }
    }
}
