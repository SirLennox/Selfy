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

package me.sirlennox.selfy.command.commands;

import me.sirlennox.selfy.Category;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.utils.stat.HttpUtils;
import me.sirlennox.selfy.utils.stat.MathUtils;
import me.sirlennox.selfy.utils.stat.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.io.IOException;

public class ProxiesCommand extends Command {
    public ProxiesCommand() {
        super("proxies", "Get proxies", Category.UTIL);
    }

    @Override
    public void onCommand(String[] args, MessageCreateEvent event) {

        try {
            MessageUtils.editMessage("Proxies", HttpUtils.get("https://api.proxyscrape.com/v2/?request=getproxies&protocol=http&timeout=10000&country=all&ssl=all&anonymity=anonymous"), MathUtils.getRandomColor().getRGB(), event.getMessage());
        } catch (IOException e) {
            MessageUtils.editMessage("Error", "An error occurred while trying to get proxies.", Color.RED.getRGB(), event.getMessage());
        }
    }
}
