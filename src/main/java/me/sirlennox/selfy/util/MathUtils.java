package me.sirlennox.selfy.util;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MathUtils {

    public static double round(double value, int places) {
        if (places < 0) return 0;

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String getTime(long ms) {
        final long hours = TimeUnit.MILLISECONDS.toHours(ms);
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(ms - TimeUnit.HOURS.toMillis(hours));
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(ms - TimeUnit.HOURS.toMillis(hours) - TimeUnit.MINUTES.toMillis(minutes));
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static Color randomColor() {
        Random rndm = new Random();
        return Color.getHSBColor(rndm.nextFloat(), rndm.nextFloat(), rndm.nextFloat());
    }

}
