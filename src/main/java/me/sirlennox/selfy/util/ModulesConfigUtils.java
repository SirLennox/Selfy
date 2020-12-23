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

package me.sirlennox.selfy.util;

import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.module.Module;
import me.sirlennox.selfy.module.Setting;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ModulesConfigUtils {

    public Selfy selfy;

    public ModulesConfigUtils(Selfy selfy) {
        this.selfy = selfy;
    }


    public JSONObject modulesToJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray modules = new JSONArray();
        for(Module m : selfy.moduleManager.modules) {
            JSONObject module = new JSONObject();
            JSONArray settings = new JSONArray();
            for(Setting setting : m.settings) {
                JSONObject stg = new JSONObject();
                stg.put("name", setting.name);
                stg.put("value", setting.value);
                settings.add(stg);
            }
            module.put("name", m.name);
            module.put("settings", settings);
            modules.add(module);
        }
        obj.put("modules", modules);

        return obj;
    }

    public Setting readSettingOfModule(JSONObject jsonObject, Module m, String settingStr) {
        JSONArray modules = (JSONArray) jsonObject.get("modules");
        for(Object o : modules) {
            JSONObject moduleObj = (JSONObject) o;

            if(((String) moduleObj.get("name")).equalsIgnoreCase(m.name)) {
                JSONArray settings = (JSONArray) moduleObj.get("settings");
                for(Object so : settings) {
                    JSONObject settingObj = (JSONObject) so;
                    if(((String) settingObj.get("name")).equalsIgnoreCase(settingStr)) {
                        return new Setting((String) settingObj.get("name"), settingObj.get("value"));
                    }
                }
            }
        }
        return null;
    }

    public void setModuleConfigs(JSONObject jsonObject) {
        JSONArray modules = (JSONArray) jsonObject.get("modules");
        for(Object o : modules) {
            JSONObject moduleObj = (JSONObject) o;
            String name = (String) moduleObj.get("name");
            Module module = selfy.moduleUtils.getModuleByName(name);
            if(module != null) {
                for(Object settingObj : (JSONArray) moduleObj.get("settings")) {
                    Setting setting = module.getSettingByName((String) ((JSONObject) settingObj).get("name"));
                    if(setting != null) {
                        setting.value = ((JSONObject) settingObj).get("value");
                    }
                }
            }
        }
    }

}
