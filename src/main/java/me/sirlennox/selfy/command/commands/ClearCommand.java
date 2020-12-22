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
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", "Clear messages", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length != 1) {
            sendUsage("<Message-Count>", event.getMessage());
            return;
        }

        try {
            CompletableFuture<MessageSet> messages = event.getChannel().getMessages(Integer.parseInt(args[0]));
            AtomicInteger amount = new AtomicInteger();

            messages.join().forEach(message -> {
                if(message.getAuthor().getIdAsString()
                        .equals(event.getApi().getYourself().getIdAsString())
                ) {
                    try {
                        message.delete();
                        amount.getAndIncrement();
                    } catch (Exception ignored) { }
                }
            });

            MessageUtils.editMessage(event.getMessage(), "Clear", "Clearing " + messages.join().size() + " messages ...", MathUtils.randomColor().getRGB());
        } catch (Exception exception) {
            MessageUtils.editMessage(event.getMessage(),null, "Couldn't parse the amount of messages to delete.", Color.RED.getRGB());
        }
    }
}
