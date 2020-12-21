package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.module.Category;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class ModulesCommand extends Command {
    public ModulesCommand() {
        super("modules", "List all modules");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        StringBuilder sb = new StringBuilder();
        for(Category c : Category.values()) {
            sb.append("**" + c.name() + "**\n");

            Main.selfy.moduleManager.modules.forEach(m -> {
                if(m.category == c) {
                    sb.append("-> " + m.name + " ─ " + m.desc + "\n");
                }
            });
        }
        MessageUtils.editMessage("Modules", sb.toString(), Color.DARK_GRAY.getRGB(), event.getMessage());
    }
}
