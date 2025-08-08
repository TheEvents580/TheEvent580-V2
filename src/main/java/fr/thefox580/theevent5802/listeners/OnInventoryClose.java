package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class OnInventoryClose implements Listener {

    private final TheEvent580_2 plugin;

    public OnInventoryClose(TheEvent580_2 plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        HumanEntity player = event.getPlayer();
        if (event.getInventory().getType() == InventoryType.WORKBENCH){
            if (player.getWorld().getName().equals("world")){
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    ItemStack helmet = player.getInventory().getHelmet();
                    player.getInventory().clear();
                    player.getInventory().setHelmet(helmet);
                }, 3L);
            }
        }
    }
}
