package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OnCommand implements Listener {

    @EventHandler
    public void onCommandEvent(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if (!player.isOp()){
            if (event.getMessage().startsWith("pl") || event.getMessage().startsWith("plugins")){
                event.setCancelled(true);
                player.sendMessage(Component.text("You don't have access to the plugins list...", ColorType.MC_RED.getColor()));
            }
        }
    }

}
