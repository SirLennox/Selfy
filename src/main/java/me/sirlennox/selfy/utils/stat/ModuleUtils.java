package me.sirlennox.selfy.utils.stat;

import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.module.Module;

public class ModuleUtils {

    public Selfy selfy;
    public ModuleUtils(Selfy selfy) {
        this.selfy = selfy;
    }

    public Module getModuleByName(String name) {
        return selfy.moduleManager.modules.stream().filter(m -> m.name.equalsIgnoreCase(name) ).findFirst().orElse(null);
    }


}
