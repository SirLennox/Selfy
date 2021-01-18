package me.sirlennox.selfy.utils.stat;

import me.sirlennox.selfy.AccountType;
import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.utils.stat.HttpUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


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

    public static boolean uploadNoriskClientCape(String link, String cape) {
        try {
            String uuidAndToken = link.replaceAll("https://noriskclient.de/cape/", "").replaceAll(" ", "");
            String uuid = uuidAndToken.split("/")[0];
            String token = uuidAndToken.split("/")[1];
         //   System.out.println(uuid);
           // System.out.println(token);
            String url = "https://api.noriskclient.de/cape/upload/";
            String charset = "UTF-8";
            File binaryFile = new File(cape);
            String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
            String CRLF = "\r\n"; // Line separator required by multipart/form-data.
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:84.0) Gecko/20100101 Firefox/84.0");

            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---------------------------" + boundary);
            OutputStream output = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
            // Send normal param.
            writer.append("-----------------------------" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"uuid\"").append(CRLF);
            //     writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(uuid).append(CRLF).flush();


            // Send binary file.
            writer.append("-----------------------------" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
            //writer.append("Content-Transfer-Encoding: binary.append(CRLF);
            writer.append(CRLF).flush();
            Files.copy(binaryFile.toPath(), output);
            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            // End of multipart/form-data.
            writer.append("-----------------------------" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"token\"").append(CRLF);
            //     writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(token).append(CRLF).flush();

            writer.append("-----------------------------" + boundary + "--").append(CRLF).flush();
            /*StringBuilder sb = new StringBuilder();
            // Send normal param.
            sb.append("-----------------------------" + boundary).append(CRLF);
            sb.append("Content-Disposition: form-data; name=\"uuid\".append(CRLF);
            //     writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            sb.append(CRLF).append(uuid).append(CRLF);


            // Send binary file.
            sb.append("-----------------------------" + boundary).append(CRLF);
            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + binaryFile.getName() + "\".append(CRLF);
            sb.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
            //writer.append("Content-Transfer-Encoding: binary.append(CRLF);
            sb.append(CRLF);
            Files.copy(binaryFile.toPath(), output);
            output.flush(); // Important before continuing with writer!
            sb.append(CRLF); // CRLF is important! It indicates end of boundary.

            // End of multipart/form-data.
            sb.append("-----------------------------" + boundary).append(CRLF);
            sb.append("Content-Disposition: form-data; name=\"token\".append(CRLF);
            //     writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            sb.append(CRLF).append(token).append(CRLF);

            sb.append("-----------------------------" + boundary + "--.append(CRLF);
            connection.setRequestProperty("Content-Length", "" + sb.toString().getBytes().length);
            output.write(sb.toString().getBytes());
            output.flush();*/
           return true;
        }catch (Throwable t) {
        }
        return false;
    }



}
