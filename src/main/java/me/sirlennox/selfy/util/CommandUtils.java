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

package me.sirlennox.selfy.util;

import me.sirlennox.selfy.Main;
import me.sirlennox.selfy.Selfy;
import me.sirlennox.selfy.command.Command;
import me.sirlennox.selfy.module.Module;

public class CommandUtils {

    public Selfy selfy;

    public CommandUtils(Selfy selfy) {
        this.selfy = selfy;
    }

    public Command getCommandByName(String name) {
        return selfy.commandManager.commands.stream().filter(m -> m.cmd.equals(name)).findFirst().orElse(null);
    }


}
