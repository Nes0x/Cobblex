package me.nes0x.cobblex.events;

import me.nes0x.cobblex.Cobblex;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class OnPlaceEventCobblex implements Listener {
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();


        if (event.getBlock().getType() == Material.valueOf(Cobblex.getInstance().getConfig().getString("cobblex-settings.material").toUpperCase())) {
            if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&',
                                                                                                            Cobblex.getInstance().getConfig().getString("cobblex-settings.name"))))

                if (player.getInventory().firstEmpty() == -1) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            Cobblex.getInstance().getConfig().getString("messages.full-inventory-to-open-cobblex")
                                    .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix").toString())));
                    event.setCancelled(true);
                } else {
                    Random random = new Random();
                    ArrayList<ItemStack> items = new ArrayList<>();

                    for (String drop : Cobblex.getInstance().getConfig().getStringList("cobblex-settings.items-drop")) {
                        items.add(new ItemStack(Material.valueOf(drop), random.nextInt(Cobblex.getInstance().getConfig().getInt("cobblex-settings.max-amount-of-drop"))));
                    }


                    int randomItem = random.nextInt(items.size());
                    event.getBlock().setType(Material.AIR);
                    ItemStack cobblex = Cobblex.createCobblex();

                    if (items.get(randomItem).getAmount() == 0) {
                        items.get(randomItem).setAmount(1);
                    }

                    player.getInventory().removeItem(cobblex);
                    player.getInventory().addItem(items.get(randomItem));

                    if (Cobblex.getInstance().getConfig().getBoolean("options-messages.chat")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                Cobblex.getInstance().getConfig().getString("messages.drawn-item")
                                        .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix").toString())
                                        .replace("%item%", items.get(randomItem).getType().toString())
                                        .replace("%amount%", "" + items.get(randomItem).getAmount())));
                    }

                    if (Cobblex.getInstance().getConfig().getBoolean("options-messages.action-bar")) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                                new TextComponent(ChatColor.translateAlternateColorCodes('&', Cobblex.getInstance().getConfig().getString("messages.drawn-item")
                                        .replace("%prefix%", Cobblex.getInstance().getConfig().getString("messages.prefix").toString())
                                        .replace("%item%", items.get(randomItem).getType().toString())
                                        .replace("%amount%", "" + items.get(randomItem).getAmount()))));
                    }



            }
        }

    }


}
