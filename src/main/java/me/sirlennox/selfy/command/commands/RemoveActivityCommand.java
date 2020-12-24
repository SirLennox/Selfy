package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class RemoveActivityCommand extends Command {
    public RemoveActivityCommand() {
        super("removeactivity", "Remove your current activity / richpresence", Category.FUN);
        this.aliases.add("unsetactivity");
        this.aliases.add("unsetpresence");
        this.aliases.add("unsetrpc");
        this.aliases.add("removerpc");
        this.aliases.add("unsetrichpresence");
        this.aliases.add("unsetpresence");

        this.aliases.add("removeactivity");
        this.aliases.add("removepresence");
        this.aliases.add("removerichpresence");
        this.aliases.add("removepresence");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length != 0) {
            sendUsage("", event.getMessage());
            return;
        }

        if(event.getApi().getActivity().isPresent()) {
            event.getApi().unsetActivity();
            MessageUtils.editMessage(event.getMessage(), "Remove Activity", "Removed the activity.", MathUtils.getRandomColor().getRGB());
        } else {
            MessageUtils.editMessage(event.getMessage(), "Remove Activity", "No activity is set.", Color.RED.getRGB());
        }
    }
}
