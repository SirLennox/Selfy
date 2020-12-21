package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
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
        MessageUtils.editMessage("Avatar of " + user.getDiscriminatedName(), user.getAvatar().getUrl().toString(), /*"Here is " + user.getDiscriminatedName() + " avatar:"*/null, MathUtils.randomColor().getRGB(), event.getMessage());
    }
}
