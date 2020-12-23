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
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MessageUtils;
import me.sirlennox.selfy.util.ParseUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;


public class SetCommand extends Command {
    public SetCommand() {
        super("set", "Set a setting of a module", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 3) {
            sendUsage("<Module> <Setting> <Value>", event);
            return;
        }
        String moduleName = args[0];
        String settingName = args[1];
        String valueStr = ArrayUtils.bindString(args, 2, args.length);
        Module module = Main.selfy.moduleUtils.getModuleByName(moduleName);
        if(module == null) {
            MessageUtils.editMessage("Error", "Module not found (Type .modules for a list of modules)", Color.RED.getRGB(), event.getMessage());
            return;
        }
        Setting setting = module.getSettingByName(settingName);
        if(setting == null) {
            MessageUtils.editMessage("Error", "Setting not found. (Type .settings <Module> to get a list of all settings)", Color.RED.getRGB(), event.getMessage());
            return;
        }
        final String valueBefore = String.valueOf(setting.value);
        useVal(valueBefore);
        try {
            setting.value = ParseUtils.parseObjectFromString(valueStr, setting.value);
            MessageUtils.editMessage("Success", "Changed setting `" + setting.name + "` from `" + valueBefore + "` to `" + setting.value + "` of the module `" + module.name + "`.", Color.GREEN.getRGB(), event.getMessage());
        } catch (Throwable throwable) {
            MessageUtils.editMessage("Error", "Cannot parse.", Color.RED.getRGB(), event.getMessage());
        }
    }

    public Object useVal(Object o) {
        return o;
    }
}
