package fr.thefox580.theevent5802.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class onItemDrop implements Listener {

    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent event){
        if (event.getItemDrop().getItemStack().getType() == Material.LIME_STAINED_GLASS_PANE || event.getItemDrop().getItemStack().getType() == Material.YELLOW_STAINED_GLASS_PANE){
            if (event.getPlayer().getWorld().getName().equals("world") || event.getPlayer().getAllowFlight()){
                event.setCancelled(true);
            }
        }
    }

}
