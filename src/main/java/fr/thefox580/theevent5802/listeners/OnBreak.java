package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.Variables;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

public class OnBreak implements Listener {

    private final TheEvent580_2 plugin;

    public OnBreak(TheEvent580_2 plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if (Objects.equals(Variables.getVariable("jeu_condi"), 13)){
            Location blockLocation = event.getBlock().getLocation();
            blockLocation.setY(-64);

            if (blockLocation.getBlock().getType() == Material.BARRIER){
                if (event.getPlayer().getGameMode() != GameMode.CREATIVE && event.getPlayer().isOp()){
                    event.setCancelled(true);
                }
            }
        }
    }
}
