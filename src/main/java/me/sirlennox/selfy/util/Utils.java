package me.sirlennox.selfy.util;

import me.sirlennox.selfy.AccountType;
import me.sirlennox.selfy.Main;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.apache.http.client.methods.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Utils {

    public static boolean hasAccessToPremiumFeatures() {
        return Main.selfy.accountType == AccountType.PREMIUM;
    }


    public static JSONObject resolveIP(String ip) throws Exception {
        return (JSONObject) JSONValue.parse(HttpUtils.get("http://ip-api.com/json/" + ip));
    }


    public static String getMeme() throws Exception {
        return String.valueOf(((JSONObject) ((JSONArray) ((JSONObject) JSONValue.parse(HttpUtils.get("https://meme-api.herokuapp.com/gimme/1"))).get("memes")).get(0)).get("url"));
    }

    public static String createHastebin(String text) throws IOException {

        byte[] postData = text.getBytes(StandardCharsets.UTF_8);
        URL url = new URL("https://hastebin.com/documents");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:84.0) Gecko/20100101 Firefox/84.0");
        conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
        conn.setUseCaches(false);

        DataOutputStream wr;
        wr = new DataOutputStream(conn.getOutputStream());
        wr.write(postData);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = reader.readLine();
        return String.valueOf(((JSONObject) JSONValue.parse(response)).get("key"));
    }



}
