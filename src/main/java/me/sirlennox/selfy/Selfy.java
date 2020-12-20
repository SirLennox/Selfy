package me.sirlennox.selfy;

import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.command.CommandManager;
import me.sirlennox.selfy.module.ModuleManager;
import org.javacord.api.AccountType;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class Selfy {
    public final String VERSION;
    public final String NAME;
    public final String PREFIX;
    public final String TOKEN;
    public final ArrayList<String> DEVELOPERS;
    public final DiscordApi API;
    public final CommandManager commandManager;
    public final ModuleManager moduleManager;

    public Selfy(String name, String version, String prefix, String token, ArrayList<String> developers) {
        this.NAME = name;
        this.VERSION = version;
        this.PREFIX = prefix;
        this.TOKEN = token;
        this.DEVELOPERS = developers;
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
        this.API = addEventListeners(buildBot(token));
    }

    public DiscordApi buildBot(String token) {
        return new DiscordApiBuilder().setAccountType(AccountType.CLIENT).setToken(token).login().join();
    }

    public String getStartupMessage() {
        return "Successfully started " + NAME + " v" + VERSION + " (by " + getDevelopers() + ")";
    }

    public DiscordApi addEventListeners(DiscordApi api) {
        api.addMessageCreateListener(event -> {
            if(event.getMessage().getAuthor().getId() == api.getYourself().getId()) {
                String msg = event.getMessageContent();
                if(msg.startsWith(PREFIX)) {
                    String msgWithoutPrefix = msg.substring(PREFIX.length());
                    String[] args = new String[msgWithoutPrefix.split(" ").length - 1];
                    for(int i = 1; i < msgWithoutPrefix.split(" ").length; i++) {
                        args[i - 1] = msgWithoutPrefix.split(" ")[i];
                    }
                    String c = msgWithoutPrefix.split(" ")[0];
                    for(Command cmd : this.commandManager.commands) {
                        if(cmd.cmd.equalsIgnoreCase(c) || isAliasOfCommand(cmd, c)) {
                            cmd.onCommand(args, event.getChannel(), event.getMessage());
                        }
                    }
                }
            }
        });
        return api;
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
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < DEVELOPERS.size(); i++) {
            sb.append((i == 0 ? DEVELOPERS.get(i) : ", " + DEVELOPERS.get(i)));
        }
        return sb.toString();
    }


}
