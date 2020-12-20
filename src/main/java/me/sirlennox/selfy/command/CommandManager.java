package me.sirlennox.selfy.command;

import me.sirlennox.selfy.command.commands.ASCIICommand;
import me.sirlennox.selfy.command.commands.EmbedCommand;
import me.sirlennox.selfy.command.commands.HelpCommand;

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
    }


    public void registerCommand(Command c) {
        this.commands.add(c);
    }

}
