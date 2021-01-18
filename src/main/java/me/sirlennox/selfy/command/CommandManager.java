package me.sirlennox.selfy.command;

import me.sirlennox.selfy.command.commands.*;
import me.sirlennox.selfy.command.commands.ConfigCommand;
import me.sirlennox.selfy.command.commands.AvatarCommand;
import me.sirlennox.selfy.command.commands.base64.Base64Command;
import me.sirlennox.selfy.documentation.Documentated;

import java.util.ArrayList;
// Initialize Commands here
public class CommandManager {
    // The command arraylist where every command will be in
    public ArrayList<Command> commands;

    // The constructor of CommandManager, where the commands arraylist will be initialized and the init method executed.
    public CommandManager() {
        this.commands = new ArrayList<>();
        this.init();
    }
    // The initialize method of the Commands (here you can register commands)
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
        registerCommand(new ProxiesCommand());
        registerCommand(new DocumentationCommand());
    }

    // With that you can register commands
    public void registerCommand(Command c) {
        this.commands.add(c);
    }

}
