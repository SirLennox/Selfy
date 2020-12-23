package me.sirlennox.selfy.command;

import me.sirlennox.selfy.command.commands.*;
import me.sirlennox.selfy.command.commands.ConfigCommand;

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
        registerCommand(new Base64Command());
        registerCommand(new YouTubeCommentFaker());
        //registerCommand(new FakeUserCommand());
        registerCommand(new ActivityCommand());
        registerCommand(new RemoveActivityCommand());
        registerCommand(new JokeCommand());
        registerCommand(new ViewCommand());
        registerCommand(new AnimalCommand());
        registerCommand(new ClearCommand());
        registerCommand(new URLScreenshotCommand());
        registerCommand(new SetCommand());
        registerCommand(new SettingsCommand());
        registerCommand(new ConfigCommand());
    }


    public void registerCommand(Command c) {
        this.commands.add(c);
    }

}
