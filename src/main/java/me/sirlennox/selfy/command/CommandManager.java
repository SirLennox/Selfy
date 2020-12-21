package me.sirlennox.selfy.command;

import me.sirlennox.selfy.command.commands.*;

import java.util.ArrayList;

public class CommandManager {
    public ArrayList<Command> commands;

    public CommandManager() {
        this.commands = new ArrayList<>();
        this.init();
    }

    public void init() {
        registerCommand(new EmbedCommand());
        registerCommand(new HelpCommand());
        registerCommand(new ASCIICommand());
        registerCommand(new ToggleCommand());
        registerCommand(new ModulesCommand());
        registerCommand(new AdCommand());
        registerCommand(new ResolveIPCommand());
        registerCommand(new UptimeCommand());
        registerCommand(new MemeCommand());
        registerCommand(new AvatarCommand());
        registerCommand(new HastebinCommand());
        registerCommand(new MediaCommand());
    }


    public void registerCommand(Command c) {
        this.commands.add(c);
    }

}
