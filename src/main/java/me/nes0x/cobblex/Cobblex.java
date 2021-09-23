package me.nes0x.cobblex;

import me.nes0x.cobblex.commands.CommandCobblex;
import me.nes0x.cobblex.commands.CommandReload;
import me.nes0x.cobblex.events.OnPlaceEventCobblex;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Cobblex extends JavaPlugin {
    private static Cobblex instance;

    public static int getAmount(Player p, ItemStack item) {
        if (p == null)
            return 0;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack slot = p.getInventory().getItem(i);
            if (slot == null || !slot.isSimilar(item))
                continue;
            amount += slot.getAmount();
        }
        return amount;
    }

    public static ItemStack createCobblex() {
        ItemStack cobblex = new ItemStack(Material.valueOf(Cobblex.getInstance().getConfig().getString("cobblex-settings.material").toUpperCase()));
        ItemMeta meta = cobblex.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                Cobblex.getInstance().getConfig().getString("cobblex-settings.name")));
        cobblex.setItemMeta(meta);
        return cobblex;
    }

    public static String createMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }


    @Override
    public void onEnable() {
        Logger logger = this.getLogger();

        new Updater(this, 94626).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("There is a new update available. Download it on: https://www.spigotmc.org/resources/cobblex.94626/");
            }
        });

        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();


        this.getCommand("cobblex").setExecutor(new CommandCobblex());
        this.getCommand("cobble").setExecutor(new CommandReload());

        getServer().getPluginManager().registerEvents(new OnPlaceEventCobblex(), this);

    }


    public static Cobblex getInstance() {
        return instance;
    }

}
