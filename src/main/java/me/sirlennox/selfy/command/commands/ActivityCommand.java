

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
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import me.sirlennox.selfy.util.ModuleUtils;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Arrays;

public class ActivityCommand extends Command {
    public ActivityCommand() {
        super("activity", "Set your activity / richpresence", Category.FUN);
        this.aliases.add("setactivity");
        this.aliases.add("setpresence");
        this.aliases.add("setrpc");
        this.aliases.add("rpc");
        this.aliases.add("richpresence");
        this.aliases.add("presence");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 2) {
            sendUsage("<Type> <Message>", event.getMessage());
            return;
        }


        String activity = args[0];

        String[] cutArgs = Arrays.copyOfRange(args, 1, args.length);
        String text = String.join(" ", cutArgs);

        try {
            ActivityType foundActivity = ActivityType.valueOf(activity.toUpperCase());
            event.getApi().updateActivity(foundActivity, text);

            MessageUtils.editMessage("Activity", "The activity was set to `" + foundActivity.name() + "` with the text `" + text + "`.", MathUtils.randomColor().getRGB(), event.getMessage());
        } catch (IllegalArgumentException exception) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Possible activities: \n");

            for(ActivityType activityType : ActivityType.values()) {
                stringBuilder.append("`")
                        .append(activityType.name())
                        .append("`\n");
            }

            MessageUtils.editMessage( "Activities", stringBuilder.toString(), MathUtils.randomColor().getRGB(),event.getMessage());
        }
    }
}
