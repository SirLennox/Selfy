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
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import me.sirlennox.selfy.utils.stat.ParseUtils;
import org.javacord.api.event.message.MessageCreateEvent;
import me.sirlennox.selfy.Main;

import java.awt.*;
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
                if(configObjects.isEmpty()) stringBuilder.append("Nothing to see here.");
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

                MessageUtils.editMessage(event.getMessage(), foundAction.name(), stringBuilder.toString(), MathUtils.getRandomColor().getRGB());

            } else if(foundAction == ConfigAction.WRITE) {

                if(args.length < 2) {
                    sendUsage("<ConfigAction> <Key:Value>", event.getMessage());
                    return;
                }

                String[] cutArgs = Arrays.copyOfRange(args, 1, args.length);
                String joinedCutArgs = String.join(" ", cutArgs);

                String[] objects = joinedCutArgs.split(" ");

                for(String object : objects) {
                    try {
                        String[] splitObject = object.split(":");

                        String key = splitObject[0];
                        String value = splitObject[1];
                        Setting obj = Main.selfy.configManager.getConfigByName(key);
                        if(obj == null) {
                            MessageUtils.editMessage(event.getMessage(), "Error", "Configuration not found!", Color.RED.getRGB());
                            return;
                        }
                        try {
                            obj.value = ParseUtils.parseObjectFromString(value, obj.value);
                            MessageUtils.editMessage(event.getMessage(), foundAction.name(), "Successfully changed config object.", Color.GREEN.getRGB());
                        } catch (Throwable throwable) {
                            MessageUtils.editMessage(event.getMessage(), "Error", "Cannot parse.", Color.RED.getRGB());
                        }
                    } catch (Exception exception) {
                        sendUsage("<ConfigAction> <Key:Value>", event.getMessage());
                        return;
                    }
                }



            }
        } catch (IllegalArgumentException exception) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Possible actions: \n");

            for(ConfigAction configAction : ConfigAction.values()) {
                stringBuilder.append("`")
                        .append(configAction.name())
                        .append("`\n");
            }

            MessageUtils.editMessage(event.getMessage(), "Actions", stringBuilder.toString(), MathUtils.getRandomColor().getRGB());
        }
    }
}
