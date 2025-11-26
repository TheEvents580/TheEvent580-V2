package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.build_masters.BuildMasters;
import fr.thefox580.theevent5802.utils.*;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBreak implements Listener {

    private final TheEvent580_2 plugin;

    public OnBreak(TheEvent580_2 plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){

        Player player = event.getPlayer();

        if (player.getGameMode() != GameMode.CREATIVE){

            if (Variables.equals("jeu_condi", Game.FINDER.getGameCondition())){
                Location blockLocation = event.getBlock().getLocation();
                blockLocation.setY(-64);

                if (blockLocation.getBlock().getType() == Material.BARRIER){
                    if (!player.isOp()){
                        event.setCancelled(true);
                    }
                }
            } else if (Variables.equals("jeu_condi", Game.BUILD_MASTERS.getGameCondition())){
                if (!BuildMasters.getRemainingPlayers().contains(event.getPlayer())){
                    event.setCancelled(true);
                } else {
                    if (BuildMasters.getUnbreakableBlocks().contains(event.getBlock().getType())){
                        event.setCancelled(true);
                    } else if (event.getBlock().getZ() < 3000){
                        if (event.getBlock().getY() != 128){
                            event.setCancelled(true);
                        }
                    }
                }
            } else if (Variables.equals("jeu_condi", Game.SPLEEF.getGameCondition())){
                event.setDropItems(false);
                if (!Online.getPlayerManager(player).isAlive(plugin)){
                    event.setCancelled(true);
                } else {
                    if (Timer.getEnum() != Timer.TimerEnum.IN_GAME){
                        event.setCancelled(true);
                    } else {
                        event.setDropItems(false);
                    }
                }
            }
        }
    }
}
