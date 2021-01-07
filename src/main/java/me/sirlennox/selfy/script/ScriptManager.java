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

import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.script.Script;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class ScriptManager {

    public ArrayList<Script> scripts;

    public File scriptsDir;
    public File commandScriptsDir;
    public File modulesScriptsDir;
    public Selfy selfy;

    public ScriptManager(File dir, Selfy selfy) {
        this.scriptsDir = createDir(dir);
        this.commandScriptsDir = createDir(new File(dir, "commands"));
        this.modulesScriptsDir = createDir(new File(dir, "modules"));
        this.selfy = selfy;
        this.scripts = new ArrayList<>();
        this.readAll();
    }

    public File createDir(File dir) {
        if(!dir.exists()) dir.mkdir();

        return dir;
    }

    public void readAll() {
        try {
            for (File f : Objects.requireNonNull(this.scriptsDir.listFiles())) {
                if (!f.isDirectory()) {
                    if (f.getName().endsWith(".js")) {
                        try {
                            this.scripts.add(readScript(f));
                        } catch (FileNotFoundException | ScriptException e) {
                            e.printStackTrace();
                            System.err.println("Error while reading script -> " + f.getName());
                        }
                    }
                }
            }

            for (File f : Objects.requireNonNull(this.commandScriptsDir.listFiles())) {
                if (!f.isDirectory()) {
                    if (f.getName().endsWith(".js")) {
                        try {
                            this.scripts.add(readCommandScript(f));
                        } catch (FileNotFoundException | ScriptException e) {
                            e.printStackTrace();
                            System.err.println("Error while reading command script -> " + f.getName());
                        }
                    }
                }
            }

            for (File f : Objects.requireNonNull(this.modulesScriptsDir.listFiles())) {
                if (!f.isDirectory()) {
                    if (f.getName().endsWith(".js")) {
                        try {
                            this.scripts.add(readModuleScript(f));
                        } catch (FileNotFoundException | ScriptException e) {
                            e.printStackTrace();
                            System.err.println("Error while reading module script -> " + f.getName());
                        }
                    }
                }
            }
        }catch (Exception e) {}
    }


    public Script readScript(File f) throws FileNotFoundException, ScriptException {
        return new Script(f, selfy);
    }


    public CommandScript readCommandScript(File f) throws FileNotFoundException, ScriptException {
        return new CommandScript(f, selfy);
    }


    public ModuleScript readModuleScript(File f) throws FileNotFoundException, ScriptException {
        return new ModuleScript(f, selfy);
    }


    public void notifyAllScripts(String func, Object... args) {
        for(Script s : this.scripts) {
            try {
                System.out.println(s.invokeFunction(func, args));
            } catch (ScriptException | NoSuchMethodException e) {
            }
        }
    }



}
