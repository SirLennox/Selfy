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

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtils {

    public static void writeJSONtoFile(JSONObject json, File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        fw.write(json.toJSONString());
        fw.close();
    }

}
