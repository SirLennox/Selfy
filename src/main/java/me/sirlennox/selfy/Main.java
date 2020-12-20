package me.sirlennox.selfy;

import me.sirlennox.selfy.util.MessageUtils;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static Selfy selfy;

    public static void main(String[] args) {
        ArrayList<String> devs = new ArrayList<>();
        devs.add("SirLennox");
        devs.add("f1nniboy");
        selfy = new Selfy("Selfy", "1.0", ".", "NzkwMjE0Mjk3NzA3NzQxMjA0.X99Wcg.vpCnwBVoNRS3YJisbYFTguRHv6I", devs, AccountType.PREMIUM);
      /*  try {
            System.out.println(MessageUtils.getASCII(selfy.NAME));
        } catch (IOException e) { }*/
        System.out.println(selfy.getStartupMessage());
    }

}
