/*
 * Copyright (c) 2020 SirLennox & f1nniboy.
 *
 * This code is copyrighted to SirLennox and f1nniboy.
 *
 * Using this code without privileges is not allowed.
 *
 *
 *
 */

package me.sirlennox.selfy.script;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.utils.stat.ScriptUtils;
import org.javacord.api.event.message.MessageCreateEvent;


import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Arrays;

public class CommandScript extends Script {

    public Command command;


    public CommandScript(File file, ArrayList<Class<?>> classes, ScriptEngine engine, Selfy selfy) throws FileNotFoundException, ScriptException {
        super(file, classes, engine, selfy);
        try {
            command = new Command((String) getVar("cmd"),  (String) getVar("desc"), Category.valueOf((String) getVar("category"))) {
                @Override
                public void onCommand(String[] args, MessageCreateEvent e) {
                    try {
                        invokeFunction("onCommand", Arrays.asList(args), e);
                    } catch (Throwable t) {
                        if(t instanceof NoSuchMethodException) return;
                        t.printStackTrace();
                        System.err.println("Error while trying to execute command: " + this.cmd);
                    }
                }
            };

            try {
                command.aliases = (ArrayList<String>) ScriptUtils.convertIntoJavaObject(getVar("aliases"));
            } catch (Throwable t) {}
            if(command.aliases == null)  command.aliases = new ArrayList<>();
            this.setVar("command", command);
            selfy.commandManager.registerCommand(command);
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to create command: " + file.getName());
        }
    }



}
