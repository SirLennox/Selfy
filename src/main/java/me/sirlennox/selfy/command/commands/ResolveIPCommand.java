package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.MessageUtils;
import me.sirlennox.selfy.util.Utils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.simple.JSONObject;

import java.awt.*;

public class ResolveIPCommand extends Command {
    public ResolveIPCommand() {
        super("resolveip", "Resolve an IP address");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length != 1) {
            printUsage("<IP>", event.getMessage());
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            JSONObject object = Utils.resolveIP(args[0]);
            for(Object o : object.keySet()) {
                Object obj = object.get(o);
                sb.append(o + " ─ **" + (obj.equals("") ? "N/A" : obj) + "**\n");
            }
            MessageUtils.editMessage("IP Resolve of " + args[0], sb.toString(), Color.BLUE.getRGB(), event.getMessage());
        } catch (Exception e) {
            MessageUtils.editMessage("Error", "An error occurred while trying to resolve the IP", Color.RED.getRGB(), event.getMessage());
        }
    }
}
