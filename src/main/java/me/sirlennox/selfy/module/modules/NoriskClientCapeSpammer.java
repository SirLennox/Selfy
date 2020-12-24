/*
 * Copyright (c) 2020 SirLennox & f1nniboy.
 *
 * This code is copyrighted to SirLennox and f1nniboy.
 *
 * Using this code without privileges is not allowed.
 *
 *
 *
 */

package me.sirlennox.selfy.module.modules;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.module.Module;
import me.sirlennox.selfy.util.Utils;
import org.javacord.api.event.message.MessageCreateEvent;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Scanner;

public class NoriskClientCapeSpammer extends Module {
    public NoriskClientCapeSpammer() {
        super("noriskclientcapespammer", "Spam the noriskclient api with capes", Category.FUN);
    }

    @Override
    public void initSettings() {
        registerSetting("channel", 791604873438822431L);
        registerSetting("prefix", "[NoRiskCosmetics]");
        registerSetting("cape", "/home/sirlennox/Desktop/512x256.png");
        super.initSettings();
    }

    public static int spammed = 1;
    @Override
    public void onChatMessage(MessageCreateEvent event) {

        if(event.getChannel().getId() == (Long)this.getSettingByName("channel").value) {
            if(event.getMessageContent().startsWith((String) this.getSettingByName("prefix").value)) {
                String[] split = event.getMessageContent().split(" {2}");
                if(split.length >= 2) {
                    String msg = split[1].replaceAll("\\\\", "");
                    //System.out.println(msg);
		    
                    if (Utils.uploadNoriskClientCape(msg, (String) this.getSettingByName("cape").value)) {
                        System.out.println("[NoriskClientCapeSpammer - " + spammed + "] Successfully spammed cape!");
                        spammed++;
                    }
                }
            }
        }
        super.onChatMessage(event);
    }
}

/*
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
            //writer.append("Content-Transfer-Encoding: binary").append(CRLF);
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
 */
