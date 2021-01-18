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
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

public class AvatarCommand extends Command {
    public AvatarCommand() {
        super("avatar", "Get the avatar of an user", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        User user = event.getMessage()
                .getMentionedUsers()
                .stream()
                .findFirst()
                .orElse(
                        event.getApi().getYourself()
                );
        MessageUtils.editMessage("Avatar of " + user.getDiscriminatedName(), user.getAvatar().getUrl().toString(), /*"Here is " + user.getDiscriminatedName() + " avatar:"*/null, MathUtils.getRandomColor().getRGB(), event.getMessage());
    }
}
