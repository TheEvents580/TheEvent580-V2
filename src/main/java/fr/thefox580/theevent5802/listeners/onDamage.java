package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class onDamage implements Listener {
    private final TheEvent580_2 plugin;

    public onDamage(TheEvent580_2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){

        FileConfiguration config = plugin.getConfig();

        if (event.getEntity() instanceof Player player){
            if (!config.getBoolean("damage_enabled")){
                event.setCancelled(true);
            }
        }

    }

}
