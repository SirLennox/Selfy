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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.command.Command;
import org.javacord.api.event.message.MessageCreateEvent;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandScript extends Script {

    public Command command;


    public CommandScript(File file, Selfy selfy) throws FileNotFoundException, ScriptException {
        super(file, selfy);
        try {
            command = new Command((String) getVar("cmd"), (String) getVar("desc"), Category.valueOf(((String) getVar("category")).toUpperCase()), false) {
                @Override
                public void onCommand(String[] args, MessageCreateEvent event) {
                    try {
                        System.out.println(invokeFunction("onCommand", args, event));
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                        System.err.println("Error while trying to execute command: " + this.cmd);
                    }
                }
            };
            try {
                command.aliases = (ArrayList<String>) convertIntoJavaObject(getVar("aliases"));
            } catch (Throwable t) {}
            selfy.commandManager.registerCommand(command);
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to create command: " + file.getName());
        }
    }

    private static Object convertIntoJavaObject(Object scriptObj) {
        if (scriptObj instanceof ScriptObjectMirror) {
            ScriptObjectMirror scriptObjectMirror = (ScriptObjectMirror) scriptObj;
            if (scriptObjectMirror.isArray()) {
                List<Object> list = Lists.newArrayList();
                for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
                    list.add(convertIntoJavaObject(entry.getValue()));
                }
                return list;
            } else {
                Map<String, Object> map = Maps.newHashMap();
                for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
                    map.put(entry.getKey(), convertIntoJavaObject(entry.getValue()));
                }
                return map;
            }
        } else {
            return scriptObj;
        }
    }

}
