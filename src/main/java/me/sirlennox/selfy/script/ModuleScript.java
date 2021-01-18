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
import me.sirlennox.selfy.module.Module;
import org.javacord.api.event.Event;
import org.javacord.api.event.message.MessageCreateEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ModuleScript extends Script {

    public Module module;


    public ModuleScript(File file, ArrayList<Class<?>> classes, ScriptEngine engine, Selfy selfy) throws FileNotFoundException, ScriptException {
        super(file, classes, engine, selfy);
        try {
            this.module = new Module((String) getVar("name"), (String) getVar("desc"), Category.valueOf((String) getVar("category"))) {
                @Override
                public void onEnable(MessageCreateEvent event) {
                    try {
                       /* System.out.println(*/invokeFunction("onEnable", event)/*)*/;
                    } catch (Throwable e) {
                        if(e instanceof NoSuchMethodException) return;
                        e.printStackTrace();
                        System.err.println("Error while trying to enable module: " + this.name);
                    }
                }



                @Override
                public void onUpdate()  {
                    try {
                        /*System.out.println(*/invokeFunction("onUpdate")/*)*/;
                    } catch (Throwable e) {
                        if(e instanceof NoSuchMethodException) return;
                        e.printStackTrace();
                        System.err.println("Error while trying to update module: " + this.name);
                    }
                }

                @Override
                public void onDisable(MessageCreateEvent event) {
                    try {
                       /* System.out.println(*/invokeFunction("onDisable", event)/*)*/;
                    } catch (Throwable e) {
                        if(e instanceof NoSuchMethodException) return;
                        e.printStackTrace();
                        System.err.println("Error while trying to disable module: " + this.name);
                    }
                }
                @Override
                public void onChatMessage(MessageCreateEvent event) {
                    try {
                        /*System.out.println(*/invokeFunction("onChatMessage", event)/*)*/;
                    } catch (Throwable e) {
                        if(e instanceof NoSuchMethodException) return;
                        e.printStackTrace();
                        System.err.println("Error while trying to run onEvent of module: " + this.name);
                    }
                }
            };
            this.setVar("module", module);
            selfy.moduleManager.registerModule(module);
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to create module from script: " + file.getName());
        }
    }

}
