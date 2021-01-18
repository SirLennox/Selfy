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

package me.sirlennox.selfy.module;

import me.sirlennox.selfy.documentation.Documentated;

// A class for settings
public class Setting {

    // Name of setting
    public String name;
    // Value of setting
    public Object value;

    // Constructor to create a setting (name, value)
    public Setting(String name, Object value) {
        this.name = name;
        this.value = value;
    }

}
