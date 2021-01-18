package me.sirlennox.selfy;

import me.sirlennox.selfy.utils.stat.TokenUtils;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

// Main class where the instance of selfy will be created
public class Main {
    // The instance of Selfy
    public static Selfy selfy;
    // The name of Selfy
    public static final String NAME = "Selfy";
    // The version of Selfy
    public static final String VERSION = "2.0";
    // The prefix of Selfy
    public static final String PREFIX = ".";

    // The main / start class
    public static void main(String[] args) {

        System.setProperty("org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");
        ArrayList<String> tokens = new ArrayList<>();
        if(args.length == 1) {
            tokens.add(args[0]);
        }else {
            System.out.println("No token defined. Trying to find your token in your level.db's...");
            tokens = TokenUtils.findTokens();
        }
            initSelfy(tokens);
    }

    // Initialize Selfy
    public static void initSelfy(ArrayList<String> tokens) {
        ArrayList<String> devs = new ArrayList<>();
        devs.add("SirLennox");
        devs.add("f1nniboy");
        for(String token : tokens) {
            try {
                selfy = new Selfy(NAME, VERSION, PREFIX, token, devs, AccountType.PREMIUM);
                System.out.println(selfy.getStartupMessage());

                break;
            }catch (LoginException e) {
                selfy = null;
                System.err.println("Failed to login with token: " + token);
                try {
                    Thread.sleep(2500L);
                } catch (InterruptedException interruptedException) {
                }
            }
        }

    }

}
