package me.sirlennox.selfy.util;

public class ArrayUtils {

    public static String bindString(String[] s, int f, int t) {
        String out = "";
        for(int i = f; i < t; i++) {
            if(out.equalsIgnoreCase("")) {
                out = s[i];
            }else {
                out += " " + s[i];
            }
        }
        return out;
    }
    public static String bindString(String[] s, int f, int t, String split) {
        String out = "";
        for(int i = f; i < t; i++) {
            if(out.equalsIgnoreCase("")) {
                out = s[i];
            }else {
                out += split + s[i];
            }
        }
        return out;
    }
}
