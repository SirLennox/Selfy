package me.sirlennox.selfy.command.commands.ping;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import org.javacord.api.event.message.MessageCreateEvent;

public class PingCommand extends Command {
    public PingCommand() {
        super("ping", "Ping a server", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
   /*     if(args.length < 2) {
            sendUsage(";
            return;
        }*/
    }

}
