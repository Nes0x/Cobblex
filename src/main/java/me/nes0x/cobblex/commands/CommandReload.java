package me.nes0x.cobblex.commands;

import me.nes0x.cobblex.Cobblex;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // sprawdzanie czy 1 argument to reload jeśli tak to przeładuje config
        if (args.length == 0) {
            sender.sendMessage(Cobblex.createMessage(
                        Cobblex.
                        getInstance().
                        getConfig().
                        getString("messages.reload-unknown-argument").
                        replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix"))));
        } else if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(Cobblex.createMessage(
                        Cobblex.
                        getInstance().
                        getConfig().
                        getString("messages.reload-success")
                        .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix"))));
                Cobblex.getInstance().reloadConfig();
                Cobblex.getInstance().saveConfig();
            } else {
                sender.sendMessage(Cobblex.createMessage(
                        Cobblex.
                        getInstance().
                        getConfig().
                        getString("messages.reload-unknown-argument")
                        .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix"))));
            }
        }

        return true;
    }


}
