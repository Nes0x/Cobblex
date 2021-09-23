package me.nes0x.cobblex.commands;

import me.nes0x.cobblex.Cobblex;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import static org.bukkit.Bukkit.getLogger;

public class CommandCobblex implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // sprawdzanie czy wykonawca komendy to gracz
        if (!(sender instanceof Player)) {
            getLogger().info(Cobblex.createMessage(
                    Cobblex.
                    getInstance().
                    getConfig().
                    getString("messages.command-in-console")
                    .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix"))));
            return true;
        }

        Player player = (Player) sender;


        // sprawdzanie czy gracz ma 9 stacków cobblestona w ekwipunku
        if (Cobblex.getAmount(player, new ItemStack(Material.COBBLESTONE)) >= 576) {
                ItemStack cobblex = Cobblex.createCobblex();
                player.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 576));
                player.getInventory().addItem(cobblex);
                player.sendMessage(Cobblex.createMessage(
                        Cobblex.
                        getInstance().
                        getConfig().
                        getString("messages.create-cobblex-success")
                        .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix"))));
        } else {
            player.sendMessage(Cobblex.createMessage(
                    Cobblex.
                    getInstance().
                    getConfig().
                    getString("messages.no-items-to-create-cobblex")
                    .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix"))));
        }

        return true;
    }
}
