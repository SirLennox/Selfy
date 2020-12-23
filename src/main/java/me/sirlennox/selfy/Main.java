package me.sirlennox.selfy;

import com.google.common.reflect.Invokable;

import javax.script.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Selfy selfy;

    public static final String NAME = "Selfy";
    public static final String VERSION = "1.0";
    public static final String PREFIX = ".";

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
