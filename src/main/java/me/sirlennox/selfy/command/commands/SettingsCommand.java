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
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.module.Module;
import me.sirlennox.selfy.module.Setting;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class SettingsCommand extends Command {
    public SettingsCommand() {
        super("settings", "Get all settings of a module", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 1) {
            sendUsage("<Module>", event);
            return;
        }
        Module module = Main.selfy.moduleUtils.getModuleByName(args[0]);

        if(module == null) {
            MessageUtils.editMessage("Error", "Module not found (Type .modules for a list of modules)", Color.RED.getRGB(), event.getMessage());
            return;
        }
        StringBuilder sb = new StringBuilder();
        if(module.settings.isEmpty()) {
            sb.append("Nothing to see here.");
        }else {
            for(Setting setting : module.settings) {
                sb.append(setting.name).append(" » `").append(setting.value).append("`\n");
            }
        }
        MessageUtils.editMessage("Settings", sb.toString(), Color.BLUE.getRGB(), event.getMessage());


    }
}
