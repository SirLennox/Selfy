package me.sirlennox.selfy;

import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.command.CommandManager;
import me.sirlennox.selfy.module.ModuleManager;
import me.sirlennox.selfy.util.*;
import org.javacord.api.AccountType;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
    public final long startedMS;

    //Utils
    public CommandUtils commandUtils;
    public ModuleUtils moduleUtils;
    public ModulesConfigUtils modulesConfigUtils;

    public File dir;
    public File modulesConfigFile;



    public Selfy(String name, String version, String prefix, String token, ArrayList<String> developers, me.sirlennox.selfy.AccountType accountType) {
        this.dir = this.createDir(new File(System.getProperty("user.home"), name));
        this.NAME = name;
        this.VERSION = version;
        this.PREFIX = prefix;
        this.TOKEN = token;
        this.DEVELOPERS = developers;
        this.commandManager = new CommandManager();
        this.commandUtils = new CommandUtils(this);
        this.moduleManager = new ModuleManager();
        this.moduleUtils = new ModuleUtils(this);
        this.modulesConfigUtils = new ModulesConfigUtils(this);
        try {
            this.modulesConfigFile = new File(this.dir, "moduleConfigs.json");
            createModuleConfigFileAndSetModuleConfigs(this.modulesConfigFile);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                JSONObject writeJSON = this.modulesConfigUtils.modulesToJSONObject();
                try {
                    FileWriter fw = new FileWriter(this.modulesConfigFile);
                    fw.write(writeJSON.toJSONString());
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        } catch (IOException e) {
        }

        this.API = addEventListeners(buildBot(token));
        this.accountType = accountType;
        this.startedMS = System.currentTimeMillis();
    }

    public File createDir(File dir) {
        if(!dir.exists()) dir.mkdir();
        return dir;
    }

    public JSONObject createModuleConfigFileAndSetModuleConfigs(File f) throws IOException {
        if(f.exists()) {
            return setModuleConfigs(f);
        }else {
            f.createNewFile();
        }
        return null;
    }

    public JSONObject setModuleConfigs(File f) throws FileNotFoundException {
        JSONObject obj;
        this.modulesConfigUtils.setModuleConfigs(obj = (JSONObject) JSONValue.parse(new FileReader(f)));
        return obj;
    }


    public File createJSONFile(File f) throws IOException {
        if(!f.exists()) f.createNewFile();
        return f;
    }

    public DiscordApi buildBot(String token) {
        return new DiscordApiBuilder().setAccountType(AccountType.CLIENT).setToken(token).login().join();
    }

    public String getStartupMessage() {
        return "Successfully started " + NAME + " " + getVersion() + " (by " + getDevelopers() + ")";
    }

    public DiscordApi addEventListeners(DiscordApi api) {
        api.addMessageCreateListener(event -> {
            this.moduleManager.modules.forEach(m -> {
                if(m.toggled) m.onChatMessage(event);
            });
            if(event.getMessage().getAuthor().getId() == api.getYourself().getId()) {
                onMessage(event);
            }
        });
        new Thread(() -> {
            while (true) {
                moduleManager.modules.forEach(m -> {
                    if(m.toggled) m.onUpdate();
                });
            }
        }, "ModuleUpdateThread").start();
        return api;
    }

    public void onMessage(MessageCreateEvent event) {
        String msg = event.getMessageContent();
        if(msg.startsWith(PREFIX)) {
            String msgWithoutPrefix = msg.substring(PREFIX.length());
            this.onCommand(msgWithoutPrefix, event);
        }
    }

    public void onCommand(String cmdStr, MessageCreateEvent event) {
        String[] args = new String[cmdStr.split(" ").length - 1];
        if (cmdStr.split(" ").length - 1 >= 0) System.arraycopy(cmdStr.split(" "), 1, args, 0, cmdStr.split(" ").length - 1);
        String c = cmdStr.split(" ")[0];
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
        return String.join(", ", DEVELOPERS);
    }


}
