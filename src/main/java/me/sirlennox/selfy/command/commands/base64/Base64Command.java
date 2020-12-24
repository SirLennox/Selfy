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

package me.sirlennox.selfy.command.commands.base64;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.util.ArrayUtils;
import me.sirlennox.selfy.util.MathUtils;
import me.sirlennox.selfy.util.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Base64;

public class Base64Command extends Command {
    public Base64Command() {
        super("base64", "Encode/Decode Base64", Category.UTIL);
        this.aliases.add("b64");
    }
;
    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {
        if(args.length < 1) {
            sendUsage("<DEOCDE/ENCODE> <Text>", event.getMessage());
            return;
        }
        String methodStr = args[0].toUpperCase();
        String text = ArrayUtils.bindString(args, 1, args.length);

        try {
            Base64ActionType type = Base64ActionType.valueOf(methodStr);
            String out = null;
            switch (type) {
                case DECODE:
                    out = new String(Base64.getDecoder().decode(text));
                    break;
                case ENCODE:
                    out = new String(Base64.getEncoder().encode(text.getBytes()));
            }
            MessageUtils.editMessage(event.getMessage(), type.name() + " BASE64", out, MathUtils.getRandomColor().getRGB());
        }catch (IllegalArgumentException e) {
            sendUsage("<DEOCDE/ENCODE> <Text>", event.getMessage());
        }

    }
}
