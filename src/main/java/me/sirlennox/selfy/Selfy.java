package me.sirlennox.selfy;

import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.command.CommandManager;
import me.sirlennox.selfy.config.ConfigManager;
import me.sirlennox.selfy.documentation.Documentated;
import me.sirlennox.selfy.module.ModuleManager;
import me.sirlennox.selfy.script.ScriptManager;
import me.sirlennox.selfy.util.*;
import org.javacord.api.AccountType;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
@Documentated("Selfy initialize class")
public class Selfy {
    public final String VERSION;
    public final String NAME;
    public final String PREFIX;
    private final String TOKEN;
    public final ArrayList<String> DEVELOPERS;
    public final DiscordApi API;
    public final CommandManager commandManager;
    public final ModuleManager moduleManager;
    public final me.sirlennox.selfy.AccountType accountType;
    public final long startedMS;
    public ConfigManager configManager;

    //Utils
    public CommandUtils commandUtils;
    public ModuleUtils moduleUtils;
    public ModulesConfigUtils modulesConfigUtils;
    public ScriptManager scriptManager;

    public File dir;
    public File modulesConfigFile;
    public File configFile;

    //Scripts



    @Documentated("This will initialize Selfy")
    public Selfy(String name, String version, String prefix, String token, ArrayList<String> developers, me.sirlennox.selfy.AccountType accountType) {

        this.NAME = name;
        this.VERSION = version;
        this.PREFIX = prefix;
        this.TOKEN = token;
        this.DEVELOPERS = developers;
        this.dir = this.createDir(new File(System.getProperty("user.home"), name));
        this.configFile = new File(this.dir, "config.json");
        this.configManager = new ConfigManager(this.configFile, this);
        this.commandManager = new CommandManager();
        this.commandUtils = new CommandUtils(this);
        this.moduleManager = new ModuleManager();
        this.moduleUtils = new ModuleUtils(this);

        this.modulesConfigUtils = new ModulesConfigUtils(this);
        try {
            this.modulesConfigFile = new File(this.dir, "moduleConfigs.json");
            createModuleConfigFileAndSetModuleConfigs(this.modulesConfigFile);
            createShutdownHook();
        } catch (IOException e) {
        }

        this.scriptManager = new ScriptManager(new File(this.dir, "scripts"), this);

        this.API = addEventListeners(buildBot(token));
        this.accountType = accountType;
        this.startedMS = System.currentTimeMillis();
    }
    @Documentated("This will create the Selfy dir in your user.home directory")
    public File createDir(File dir) {
        if(!dir.exists()) dir.mkdir();
        return dir;
    }

    @Documentated("Will create a shutdown hook to save everything")
    public void createShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }
    @Documentated("Everything that will be executed if the program shutdown is here")
    public void shutdown() {
        try {
            FileWriter fw = new FileWriter(this.modulesConfigFile);
            fw.write(this.modulesConfigUtils.modulesToJSONObject().toJSONString());
            fw.close();
            fw = new FileWriter(this.configFile);
            fw.write(this.configManager.configsToJSONObject().toJSONString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Documentated("This will create the moduleConfigs.json and read it")
    public JSONObject createModuleConfigFileAndSetModuleConfigs(File f) throws IOException {
        if(f.exists()) {
            return setModuleConfigs(f);
        }else {
            f.createNewFile();
        }
        return null;
    }
    @Documentated("This will read the moduleConfigs.json file")
    public JSONObject setModuleConfigs(File f) throws FileNotFoundException {
        JSONObject obj;
        this.modulesConfigUtils.setModuleConfigs(obj = (JSONObject) JSONValue.parse(new FileReader(f)));
        return obj;
    }

    @Documentated("The bot will be built here")
    public DiscordApi buildBot(String token) {
        return new DiscordApiBuilder().setAccountType(AccountType.CLIENT).setToken(token).login().join();
    }
    @Documentated("Gets the startup message")
    public String getStartupMessage() {
        return "Successfully started " + NAME + " " + getVersion() + " (by " + getDevelopers() + ")";
    }
    @Documentated("Add the event listeners")
    public DiscordApi addEventListeners(DiscordApi api) {
        api.addMessageCreateListener(event -> {
            this.scriptManager.notifyAllScripts("onMessage", event);
            this.moduleManager.modules.forEach(m -> {
                if(m.toggled) m.onChatMessage(event);
            });
            if(event.getMessage().getAuthor().getId() == api.getYourself().getId()) {
                onMessageSent(event);
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
    @Documentated("This method will be called if a message got sent from you")
    public void onMessageSent(MessageCreateEvent event) {
        String msg = event.getMessageContent();
        boolean isCommand = msg.startsWith(PREFIX);
        this.scriptManager.notifyAllScripts("onSendMessage", event, isCommand);
        if(isCommand) {
            String msgWithoutPrefix = msg.substring(PREFIX.length());
            this.onCommand(msgWithoutPrefix, event);
        }
    }
    @Documentated("This would be executed if something starts with the command prefix")
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


    @Documentated("Gets the version with the v- prefix")
    public String getVersion() {
        return "v" + this.VERSION;
    }

    @Documentated("Checks if the string is an alias of the command")
    public static boolean isAliasOfCommand(Command c, String alias) {
        for(String s : c.aliases) {
            if(s.equalsIgnoreCase(alias)) {
                return true;
            }
        }
        return false;
    }

    @Documentated("Gets the developers of the selfbot, joined with \",\"")
    public String getDevelopers() {
        return String.join(", ", DEVELOPERS);
    }


}
