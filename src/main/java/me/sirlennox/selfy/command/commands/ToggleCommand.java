package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.module.Module;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import me.sirlennox.selfy.utils.stat.Utils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", "Toggle a module", Category.UTIL);
        this.aliases.add("t");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length != 1) {
            sendUsage("<Module>", event.getMessage());
            return;
        }
        Module m = Main.selfy.moduleUtils.getModuleByName(args[0]);
        if(m == null) {
            MessageUtils.editMessage("Error", "Module not found", Color.RED.getRGB(), event.getMessage());
            return;
        }
        if(m.onlyPremium && !Utils.hasAccessToPremiumFeatures()) {
            MessageUtils.editMessage("Error", "This module is premium only and you don't have premium!", Color.RED.getRGB(), event.getMessage());
            return;
        }
        m.toggle(event);
    }
}
