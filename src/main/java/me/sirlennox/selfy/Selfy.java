package me.sirlennox.selfy;

import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.command.CommandManager;
import me.sirlennox.selfy.module.ModuleManager;
import me.sirlennox.selfy.util.MessageUtils;
import me.sirlennox.selfy.util.Utils;
import org.javacord.api.AccountType;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.awt.*;
import java.util.ArrayList;

public class Selfy {
    public final String VERSION;
    public final String NAME;
    public final String PREFIX;
    public final String TOKEN;
    public final ArrayList<String> DEVELOPERS;
    public final DiscordApi API;
    public final CommandManager commandManager;
    public final ModuleManager moduleManager;
    public final me.sirlennox.selfy.AccountType accountType;

    public Selfy(String name, String version, String prefix, String token, ArrayList<String> developers, me.sirlennox.selfy.AccountType accountType) {
        this.NAME = name;
        this.VERSION = version;
        this.PREFIX = prefix;
        this.TOKEN = token;
        this.DEVELOPERS = developers;
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
        this.API = addEventListeners(buildBot(token));
        this.accountType = accountType;
    }

    public DiscordApi buildBot(String token) {
        return new DiscordApiBuilder().setAccountType(AccountType.CLIENT).setToken(token).login().join();
    }

    public String getStartupMessage() {
        return "Successfully started " + NAME + " " + getVersion() + " (by " + getDevelopers() + ")";
    }

    public DiscordApi addEventListeners(DiscordApi api) {
        api.addMessageCreateListener(event -> {
            if(event.getMessage().getAuthor().getId() == api.getYourself().getId()) {
                String msg = event.getMessageContent();
                if(msg.startsWith(PREFIX)) {
                    String msgWithoutPrefix = msg.substring(PREFIX.length());
                    String[] args = new String[msgWithoutPrefix.split(" ").length - 1];
                    if (msgWithoutPrefix.split(" ").length - 1 >= 0) System.arraycopy(msgWithoutPrefix.split(" "), 1, args, 0, msgWithoutPrefix.split(" ").length - 1);
                    String c = msgWithoutPrefix.split(" ")[0];
                    for(Command cmd : this.commandManager.commands) {
                        if(cmd.cmd.equalsIgnoreCase(c) || isAliasOfCommand(cmd, c)) {
                            if(cmd.onlyPremium && !Utils.hasAccessToPremiumFeatures()) {
                                MessageUtils.editMessage("Error", "This command is premium only and you don't have premium!", Color.RED.getRGB(), event.getMessage());
                                return;
                            }
                            cmd.onCommand(args, event);
                            return;
                        }
                    }
                }
            }
        });
        return api;
    }



    public String getVersion() {
        return "v" + this.VERSION;
    }

    public static boolean isAliasOfCommand(Command c, String alias) {
        for(String s : c.aliases) {
            if(s.equalsIgnoreCase(alias)) {
                return true;
            }
        }
        return false;
    }


    public String getDevelopers() {
       /* StringBuilder sb = new StringBuilder();
        for(int i = 0; i < DEVELOPERS.size(); i++) {
            sb.append((i == 0 ? DEVELOPERS.get(i) : ", " + DEVELOPERS.get(i)));
        }
        return sb.toString();*/
        return String.join(", ", DEVELOPERS);
    }


}
