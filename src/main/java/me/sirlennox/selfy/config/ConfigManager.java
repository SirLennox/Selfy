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

package me.sirlennox.selfy.config;

import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.module.Setting;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ConfigManager {

    public ArrayList<Setting> configs;

    public Selfy selfy;
    public File file;
    public ConfigManager(File file, Selfy selfy) {
        this.file = file;
        this.selfy = selfy;
        this.configs = new ArrayList<>();
        this.init();
        try {
            this.read((JSONObject) JSONValue.parse(new FileReader(file)));
        } catch (FileNotFoundException e) {

        }
    }

    public void init() {

    }

    public void registerConfig(Setting setting) {
        this.configs.add(setting);
    }

    public void registerConfig(String name, Object value) {
        this.configs.add(new Setting(name, value));
    }

    public Setting getConfigByName(String name) {
        return this.configs.stream().filter(c -> c.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }


    public JSONObject configsToJSONObject() {
        JSONObject obj = new JSONObject();
        JSONArray configsArray = new JSONArray();

            for(Setting setting : this.configs) {
                JSONObject stg = new JSONObject();
                stg.put("name", setting.name);
                stg.put("value", setting.value);
                configsArray.add(stg);
            }

        obj.put("configs", configsArray);

        return obj;
    }

    public void read(JSONObject obj) {
        JSONArray configsArray = (JSONArray) obj.get("configs");
        for(Object o : configsArray) {
            JSONObject stg = (JSONObject) o;
            for(Setting s : configs) {
                if(s.name.equalsIgnoreCase((String) stg.get("name"))) {
                    s.value = stg.get("value");
                }
            }
        }
    }

}
