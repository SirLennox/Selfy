package me.sirlennox.selfy;

import com.google.common.reflect.Invokable;
import me.sirlennox.selfy.documentation.Documentated;
import me.sirlennox.selfy.util.TokenUtils;
import org.reflections.Reflections;

import javax.script.*;
import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Documentated("Main class where the instance of selfy will be created")
public class Main {
    @Documentated("The instance of Selfy")
    public static Selfy selfy;
    @Documentated("The name of Selfy")
    public static final String NAME = "Selfy";
    @Documentated("The version of Selfy")
    public static final String VERSION = "2.0";
    @Documentated("The prefix of Selfy")
    public static final String PREFIX = ".";

    @Documentated("The main / start class")
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
    //    try {
            initSelfy(tokens);
  /*      } catch (LoginException e) {
            System.err.println("Can't login to bot. Is the token invalid?");
            System.exit(-1);
        }*/
      /*  try {
            System.out.println(MessageUtils.getASCII(selfy.NAME));
        } catch (IOException e) { }*/

    }

    @Documentated("Initialize Selfy")
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
            }
        }

    }

}
