package me.sirlennox.selfy.utils.stat;

import java.util.Arrays;

public class ArrayUtils {

    public static String bindString(String[] s, int f, int t) {
        return bindString(s, f, t, " ");
    }
    public static String bindString(String[] s, int f, int t, String split) {
        return String.join(split, Arrays.copyOfRange(s,f, t));
    }
}
