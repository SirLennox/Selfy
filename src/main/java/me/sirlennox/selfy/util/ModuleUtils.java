package me.sirlennox.selfy.util;

import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.module.Module;

public class ModuleUtils {


    public static Module getModuleByName(String name) {
        for(Module m : Main.selfy.moduleManager.modules) {
            if(m.name.equalsIgnoreCase(name)) {
                return m;
            }
        }

        return null;
    }


}
