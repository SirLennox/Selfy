package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;

public class FakeUserCommand extends Command {
    public FakeUserCommand() {
        super("fakeuser", "Fake a user", Category.TROLL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        User user = event.getMessage()
                .getMentionedUsers()
                .stream()
                .findFirst()
                .orElse(
                        null
                );
        if(user == null) {
            sendUsage("<Mentioned-User>", event.getMessage());
            return;
        }
        event.getApi().createAccountUpdater().setAvatar(user.getAvatar()).setUsername(user.getName()).update().join();
        MessageUtils.sendMessage("Success", "You are now looking like: " + user.getDiscriminatedName(), Color.GREEN.getRGB(), event.getChannel());
    }
}
