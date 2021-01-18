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
import me.sirlennox.selfy.utils.stat.ScriptUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ScriptManager {

    public ArrayList<Script> scripts;

    public File scriptsDir;
    public ArrayList<Class<?>> classes;
    public Selfy selfy;
    public ScriptManager(File dir, Selfy selfy) {
        this.selfy = selfy;
        this.initClasses();
        this.scriptsDir = createDir(dir);
        for(ScriptType st : ScriptType.values()) createDir(new File(dir, st.fileName));

        this.scripts = new ArrayList<>();
        this.readAll();
    }

    public void initClasses() {
        this.classes = new ArrayList<>();
        this.classes.add(Math.class);
        this.classes.add(Class.class);
        List<ClassLoader> classLoadersList = new LinkedList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("me.sirlennox.selfy.utils.stat"))));

        this.classes.addAll(reflections.getSubTypesOf(Object.class));

    }

    public File createDir(File dir) {
        if(!dir.exists()) dir.mkdir();
        return dir;
    }

    public void readAll() {
        for(ScriptType st : ScriptType.values()) {
            for (File f : Objects.requireNonNull(new File(scriptsDir, st.fileName).listFiles())) {
                if (!f.isDirectory()) {
                        try {
                            this.scripts.add(readScript(f, st, ScriptUtils.getExtensionFromFileName(f.getName())));
                        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException | FileNotFoundException | ScriptException e) {
                            e.printStackTrace();
                            System.err.println("Error while reading command script -> " + f.getName());
                        }
                }
            }
        }
    }

    public static ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    public Script readScript(File f, ScriptType st, String scriptEngineName) throws FileNotFoundException, ScriptException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Script) st.c.getDeclaredConstructors()[0].newInstance(f, this.classes, scriptEngineManager.getEngineByName(scriptEngineName), this.selfy);
    }


    public void notifyAllScripts(String func, Object... args) {
        for(Script s : this.scripts) {
            try {
                s.invokeFunction(func, args);
            } catch (Throwable throwable) {
                if(throwable instanceof NoSuchMethodException) return;
                throwable.printStackTrace();
            }
        }
    }



}
