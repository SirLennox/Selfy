package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.MessageUtils;
import me.sirlennox.selfy.util.Utils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.simple.JSONObject;

import java.awt.*;

public class ResolveIPCommand extends Command {
    public ResolveIPCommand() {
        super("resolveip", "Resolve an IP address", Category.UTIL);
        this.aliases.add("lookup");
        this.aliases.add("lookupip");
        this.aliases.add("iplookup");
        this.aliases.add("ipresolve");
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length != 1) {
            sendUsage("<IP>", event.getMessage());
            return;
        }
        try {
            StringBuilder sb = new StringBuilder();
            JSONObject object = Utils.resolveIP(args[0]);
            String status = String.valueOf(object.get("status"));
            if(status.equalsIgnoreCase("fail")) {
                MessageUtils.editMessage("Error", "Can't resolve IP! \nReason: **" + String.valueOf(object.get("message")) + "**", Color.RED.getRGB(), event.getMessage());
                return;
            }
            sb.append("Country » `" + getStringFromJSONObj(object, "country") + "` :flag_" + String.valueOf(object.get("countryCode")).toLowerCase() + ":\n");
            sb.append("City » `" + getStringFromJSONObj(object, "city") + "`\n");
            sb.append("ZIP » `" + getStringFromJSONObj(object, "zip") + "`\n");
            sb.append("Timezone » `" + getStringFromJSONObj(object, "timezone") + "`\n");
            sb.append("ISP » `" + getStringFromJSONObj(object, "isp") + "`\n");
            MessageUtils.editMessage("IP-Lookup of " + args[0], sb.toString(), Color.BLUE.getRGB(), event.getMessage());
        } catch (Exception e) {
            MessageUtils.editMessage("Error", "An error occurred while trying to lookup the IP", Color.RED.getRGB(), event.getMessage());
        }
    }

    public static String getStringFromJSONObj(JSONObject object, String key) {
        return (object.containsKey(key) ? String.valueOf(object.get(key)) : "N/A");
    }
}
