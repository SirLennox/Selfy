package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.module.Module;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ModulesCommand extends Command {
    public ModulesCommand() {
        super("modules", "List all modules", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        StringBuilder sb = new StringBuilder();
        for(Category c : Category.values()) {
            Stream<Module> streamModules = Main.selfy.moduleManager.modules.stream().filter(m -> m.category == c);
            ArrayList<Module> modules = new ArrayList<>();
            streamModules.forEach(modules::add);
            if(!modules.isEmpty()) {
                sb.append("\n**" + c.name + "**\n");

                Main.selfy.moduleManager.modules.forEach(m -> {
                    if (m.category == c) {
                        sb.append("» " + m.name + " » " + m.desc + "\n");
                    }
                });
            }
        }
        MessageUtils.editMessage("Modules", sb.toString(), Color.DARK_GRAY.getRGB(), event.getMessage());
    }
}
