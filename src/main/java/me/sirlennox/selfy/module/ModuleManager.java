package me.sirlennox.selfy.module;

import me.sirlennox.selfy.module.modules.Bot4Everyone;

import java.util.ArrayList;

public class ModuleManager {

    public ModuleManager() {
        this.init();
        this.startAutostart();
    }

    public ArrayList<Module> modules = new ArrayList<>();

    public void init() {
        registerModule(new Bot4Everyone());
    }

    public void startAutostart() {
        modules.stream().forEach(m -> {
            if(m.autoStart) {
                m.toggle(null);
            }
        });
    }

    public void registerModule(Module m) {
        this.modules.add(m);
    }
    public void registerModule(Module m, boolean toggled) {
        m.toggled = toggled;
        m.autoStart = toggled;
        this.modules.add(m);
    }
}
