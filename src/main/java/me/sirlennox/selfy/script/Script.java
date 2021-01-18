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

import jdk.internal.dynalink.beans.StaticClass;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.Selfy;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Script {

    public ScriptEngine engine;
    public File file;

    public Script(File file, ArrayList<Class<?>> classes, ScriptEngine engine, Selfy selfy) throws FileNotFoundException, ScriptException {
        this.file = file;
        this.engine = readScript(this.file, engine);
        this.setVar(Selfy.class.getSimpleName(), selfy);

        if(engine.getFactory().getEngineName().equalsIgnoreCase(new NashornScriptEngineFactory().getEngineName())) for(Class<?> c : classes) this.setVar(c.getSimpleName(), StaticClass.forClass(c));
    }

    private ScriptEngine readScript(File f, ScriptEngine engine) throws FileNotFoundException, ScriptException {
        engine.eval(new FileReader(f));
        return engine;
    }

    public Object invokeFunction(String function, Object... args) throws Throwable {
        return ((Invocable) this.engine).invokeFunction(function, args);
    }

    public Object getVar(String var) {
        return this.engine.get(var);
    }


    public void setVar(String var, Object val) {
        this.engine.put(var, val);
    }

}
