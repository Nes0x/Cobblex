package me.nes0x.cobblex.commands;

import me.nes0x.cobblex.Cobblex;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    Cobblex.getInstance().getConfig().getString("messages.reload-unknown-argument")
                    .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix").toString())));
        } else if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Cobblex.getInstance().getConfig().getString("messages.reload-success")
                        .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix").toString())));
                Cobblex.getInstance().reloadConfig();
                Cobblex.getInstance().saveConfig();
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        Cobblex.getInstance().getConfig().getString("messages.reload-unknown-argument")
                        .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix").toString())));
            }
        }

        return true;
    }


}
