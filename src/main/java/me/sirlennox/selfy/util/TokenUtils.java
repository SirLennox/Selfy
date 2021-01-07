package me.sirlennox.selfy.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenUtils {
    // Get the token of the user that ran the program.

    public static String[] discordTokenLocations = (String[]) Arrays
            .asList(
                    "/AppData/Roaming/Opera Software/Opera Stable/Local Storage/leveldb",
                    "/AppData/Local/Google/Chrome/User Data/Default/Local Storage/leveldb",
                    "/AppData/Roaming/discord/Local Storage/leveldb/",
                    "/AppData/Roaming/discordcanary/Local Storage/leveldb/",
                    "/snap/discord/current/.config/discord/Local Storage/leveldb/",
                    "/snap/discordcanary/current/.config/discordcanary/Local Storage/leveldb/"
            ).toArray();
    public static String discordTokenExpression = "[nNmM][\\w\\W]{23}\\.[xX][\\w\\W]{5}\\.[\\w\\W]{27}|mfa\\.[\\w\\W]{84}";


    public static ArrayList<String> findTokens() {
        ArrayList<String> foundTokens = new ArrayList<>();
       // if(!OSUtils.isUsingWindows()) return foundTokens;

        for(String location : getDiscordInstallations()) {
            File locationPath = new File(location);
            String[] files = locationPath.list();

            // Loop through the files in the token location.
            for(String file : files) {
                try(Scanner scanner = new Scanner(new FileReader(new File(locationPath, file)))) {

                    // Loop through every line of the file.
                    while(scanner.hasNextLine()) {
                        String line = scanner.nextLine();

                        Pattern pattern = Pattern.compile(discordTokenExpression);
                        Matcher matcher = pattern.matcher(line);

                        // Loop through every found token.
                        while(matcher.find()) foundTokens.add(matcher.group());
                    }

                } catch (IOException exception) {
                    return foundTokens; // Return the already found tokens.
                }
            }
        }

        // Return the tokens.
        return foundTokens;
    }

    // Get the existing Discord installations on this system.
    public static ArrayList<String> getDiscordInstallations() {
        ArrayList<String> foundInstallations = new ArrayList<>();
        //if(!OSUtils.isUsingWindows()) return foundInstallations;

        for(String location : getTokenLocations()) {
            File locationPath = new File(System.getProperty("user.home") + location);

            String[] files = locationPath.list();
            if (files != null) foundInstallations.add(locationPath.getAbsolutePath());
        }

        return foundInstallations;
    }

    // Get the location where the token of the user is saved.
    public static String[] getTokenLocations() {
        return discordTokenLocations;
    }
}
