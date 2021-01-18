package me.sirlennox.selfy.script;

import java.io.File;

public enum ScriptType {

    MODULE(ModuleScript.class, "modules"), COMMAND(CommandScript.class, "commands");

    Class<? extends Script> c;
    String fileName;
    ScriptType(Class<? extends Script> c, String fileName) {
        this.c = c;
        this.fileName = fileName;
    }

}
