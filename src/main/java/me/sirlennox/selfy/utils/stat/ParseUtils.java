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

package me.sirlennox.selfy.utils.stat;

public class ParseUtils {

    public static Object parseObjectFromString(String s, Object o) throws Throwable {

        if(o instanceof String) return s;


        if(o instanceof Number) {
            if (o instanceof Integer) return Integer.parseInt(s);
            if (o instanceof Double) return Double.parseDouble(s);
            if (o instanceof Float) return Float.parseFloat(s);
            if (o instanceof Byte) return Byte.parseByte(s);
            if (o instanceof Long) return Long.parseLong(s);
            if (o instanceof Short) return Short.parseShort(s);
        }

        if(o instanceof Boolean) return Boolean.parseBoolean(s);

        return s;
    }

}
