package me.sirlennox.selfy.util;

import me.sirlennox.selfy.AccountType;
import me.sirlennox.selfy.Main;

public class Utils {

    public static boolean hasAccessToPremiumFeatures() {
        return Main.selfy.accountType == AccountType.PREMIUM;
    }

}
