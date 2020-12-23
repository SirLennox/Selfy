package me.sirlennox.selfy.util;

import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.module.Module;

public class ModuleUtils {

    public Selfy selfy;
    public ModuleUtils(Selfy selfy) {
        this.selfy = selfy;
    }

    public Module getModuleByName(String name) {
        return selfy.moduleManager.modules.stream().filter(m -> m.name.equals(name)).findFirst().orElse(null);
    }


}
