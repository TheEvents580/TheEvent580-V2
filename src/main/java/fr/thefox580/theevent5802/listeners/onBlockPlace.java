package fr.thefox580.theevent5802.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onBlockPlace implements Listener {

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event){
        if (event.getBlock().getType() == Material.LIME_STAINED_GLASS_PANE || event.getBlock().getType() == Material.YELLOW_STAINED_GLASS_PANE){
            if (event.getPlayer().getWorld().getName().equals("world") || event.getPlayer().getAllowFlight()){
                if (event.getPlayer().getGameMode() != GameMode.CREATIVE){
                    event.setCancelled(true);
                }
            }
        }
    }

}
