package me.sirlennox.selfy;

import com.google.common.reflect.Invokable;
import me.sirlennox.selfy.documentation.Documentated;
import org.reflections.Reflections;

import javax.script.*;
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
        if(args.length != 1) {
            System.err.println("Usage: <token>");
            return;
        }
        ArrayList<String> devs = new ArrayList<>();
        devs.add("SirLennox");
        devs.add("f1nniboy");

        selfy = new Selfy(NAME, VERSION, PREFIX, args[0], devs, AccountType.PREMIUM);
      /*  try {
            System.out.println(MessageUtils.getASCII(selfy.NAME));
        } catch (IOException e) { }*/
        System.out.println(selfy.getStartupMessage());
    }

}
