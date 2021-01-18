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

package me.sirlennox.selfy.utils.stat;

import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.command.Command;

public class CommandUtils {

    public Selfy selfy;

    public CommandUtils(Selfy selfy) {
        this.selfy = selfy;
    }

    public Command getCommandByName(String name) {
        return selfy.commandManager.commands.stream().filter(m -> m.cmd.equalsIgnoreCase(name)).findFirst().orElse(null);
    }


}
