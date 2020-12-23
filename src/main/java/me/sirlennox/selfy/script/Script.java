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
import me.sirlennox.selfy.util.MessageUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Script {

    public ScriptEngine engine;
    public File file;
    public Selfy selfy;

    public Script(File file, Selfy selfy) throws FileNotFoundException, ScriptException {
        this.file = file;
        this.selfy = selfy;
        this.engine = readScript(this.file);
        this.setVar("selfy", selfy);
        this.setVar("messageUtils", new MessageUtilsWithConstructor());
    }

    private ScriptEngine readScript(File f) throws FileNotFoundException, ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("js");
        engine.eval(new FileReader(f));
        return engine;
    }

    public Object invokeFunction(String function, Object... args) throws ScriptException, NoSuchMethodException {
        return ((Invocable) this.engine).invokeFunction(function, args);
    }

    public Object getVar(String var) {
        return this.engine.get(var);
    }


    public void setVar(String var, Object val) {
        this.engine.put(var, val);
    }

}
