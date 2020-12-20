package me.sirlennox.selfy.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HttpUtils {

    public static String get(String link) throws IOException {
        URLConnection con = new URL(link.replaceAll(" ", "+")).openConnection();
        Scanner scanner = new Scanner(con.getInputStream());
        String back = "";
        while (scanner.hasNextLine()) {
            back += scanner.nextLine() + "\n";
        }
        return back;
    }

}
