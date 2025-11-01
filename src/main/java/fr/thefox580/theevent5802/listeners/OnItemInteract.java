package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.Game;
import fr.thefox580.theevent5802.utils.Spectators;
import fr.thefox580.theevent5802.utils.Timer;
import fr.thefox580.theevent5802.utils.Variables;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import java.util.List;

public class OnItemInteract implements Listener {

    public OnItemInteract(TheEvent580_2 plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onItemDropEvent(PlayerDropItemEvent event){
        if (event.getItemDrop().getItemStack().getType() == Material.LIME_STAINED_GLASS_PANE || event.getItemDrop().getItemStack().getType() == Material.YELLOW_STAINED_GLASS_PANE){
            if (event.getPlayer().getWorld().getName().equals("world") || event.getPlayer().getAllowFlight()){
                event.setCancelled(true);
            }
        } else if (event.getPlayer().getAllowFlight()){
            event.setCancelled(true);
        } else if (List.of(Game.MULTILAP.getGameCondition(), Game.BUILD_MASTERS.getGameCondition(),
                Game.BOW_PVP.getGameCondition(), Game.ARMS_RACE.getGameTime()).contains((int) Variables.getVariable("jeu_condi"))){
            event.setCancelled(true);
        } else if (Timer.getEnum() == Timer.TimerEnum.VOTING){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickupEvent(PlayerAttemptPickupItemEvent event){
        Player player = event.getPlayer();
        if (Spectators.isSpectator(player) && !Variables.equals("jeu_condi", 0)){
            event.setCancelled(true);
        } else {
            if (player.getAllowFlight()){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onProjectilePickupEvent(PlayerPickupArrowEvent event){
        if (Variables.equals("jeu_condi", 8)){
            event.setCancelled(true);
            event.getArrow().remove();
        }
    }

}
