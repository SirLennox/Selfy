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
import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.module.Module;
import org.javacord.api.event.message.MessageCreateEvent;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;

public class ModuleScript extends Script {

    public Module module;


    public ModuleScript(File file, Selfy selfy) throws FileNotFoundException, ScriptException {
        super(file, selfy);
        try {
            selfy.moduleManager.registerModule(module = new Module((String) getVar("name"), (String) getVar("desc"), Category.valueOf((String) getVar("category")), false) {
                @Override
                public void onEnable(MessageCreateEvent event) {
                    try {
                        System.out.println(invokeFunction("onEnable", event));
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                        System.err.println("Error while trying to enable module: " + this.name);
                    }
                }

                @Override
                public void onUpdate() {
                    try {
                        System.out.println(invokeFunction("onUpdate"));
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                        System.err.println("Error while trying to update module: " + this.name);
                    }
                }

                @Override
                public void onDisable(MessageCreateEvent event) {
                    try {
                        System.out.println(invokeFunction("onDisable", event));
                    } catch (ScriptException | NoSuchMethodException e) {
                        e.printStackTrace();
                        System.err.println("Error while trying to disable module: " + this.name);
                    }
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to create module from script: " + file.getName());
        }
    }

}
