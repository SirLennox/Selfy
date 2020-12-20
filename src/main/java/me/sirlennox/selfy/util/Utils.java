package me.sirlennox.selfy.util;

import me.sirlennox.selfy.AccountType;
import me.sirlennox.selfy.Main;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;

public class Utils {

    public static boolean hasAccessToPremiumFeatures() {
        return Main.selfy.accountType == AccountType.PREMIUM;
    }


    public static JSONObject resolveIP(String ip) throws Exception {
        return (JSONObject) JSONValue.parse(HttpUtils.get("http://extreme-ip-lookup.com/json/" + ip));
    }

}
