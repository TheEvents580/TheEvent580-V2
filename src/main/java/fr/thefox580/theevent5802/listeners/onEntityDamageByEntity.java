package fr.thefox580.theevent5802.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class onEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if (event.getEntity().getType() == EntityType.CHICKEN || event.getEntity().getType() == EntityType.FOX){
            if (event.getDamager().getType() == EntityType.PLAYER){
                event.getEntity().remove();
                for (Player loopedPlayer : Bukkit.getOnlinePlayers()){
                    loopedPlayer.stopSound(Sound.ENTITY_CHICKEN_HURT);
                    loopedPlayer.stopSound(Sound.ENTITY_CHICKEN_DEATH);
                }
            }
        }
    }

}
