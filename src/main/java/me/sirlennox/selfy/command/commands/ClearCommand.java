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
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageType;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            int amountToDelete = Integer.parseInt(args[0]) + 1;
            AtomicInteger amount = new AtomicInteger();

            Stream<Message> messageStream = event.getChannel().getMessagesAsStream().filter(message -> {
                boolean isOwnMessageAndNormal = message.getAuthor().getIdAsString().equals(event.getApi().getYourself().getIdAsString()) && message.getType() == MessageType.NORMAL;
                if(isOwnMessageAndNormal) { amount.getAndIncrement(); }

                return amount.get() <= amountToDelete && isOwnMessageAndNormal;
            });

            List<Message> messages = messageStream.collect(Collectors.toList());
            new Thread(() -> {
                messages.forEach(message -> {
                    try {
                        message.delete().join();
                    } catch (Exception ignored) { }
                });
                try {
                    MessageUtils.sendMessage("Clear", "Cleared " + (messages.size()) + " messages.", MathUtils.getRandomColor().getRGB(), event.getChannel());
                }catch (Exception e) { }
                }, "MessageDeleteThread").start();



        } catch (Exception exception) {
            MessageUtils.editMessage(event.getMessage(), null, "Couldn't parse the amount of messages to delete.", Color.RED.getRGB());
        }
    }
}
