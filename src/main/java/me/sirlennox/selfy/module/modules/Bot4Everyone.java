package me.sirlennox.selfy.module.modules;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.module.Module;
import org.javacord.api.event.message.MessageCreateEvent;

public class Bot4Everyone extends Module {
    public Bot4Everyone() {
        super("bot4everyone", "A bot for everyone", Category.FUN);
    }

    @Override
    public void onChatMessage(MessageCreateEvent event) {
        if(event.getMessage().getAuthor().getId() != event.getApi().getYourself().getId()) {
            Main.selfy.onMessage(event);
        }
        super.onChatMessage(event);
    }
}
