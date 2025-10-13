package fr.thefox580.theevent5802.games.finder;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.Game;
import fr.thefox580.theevent5802.utils.Variables;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class FinderListeners implements Listener {

    public FinderListeners(TheEvent580_2 plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if (cannotInteract(event.getBlock().getLocation(), event.getPlayer())){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRighClickEvent(PlayerInteractEvent event){
        if (Objects.equals(Variables.getVariable("jeu_condi"), Game.FINDER.getGameCondition())){
            if (event.getAction() == Action.RIGHT_CLICK_AIR){
                if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.FIREWORK_ROCKET){
                    ItemStack rocket = event.getPlayer().getInventory().getItemInMainHand();
                    rocket.setAmount(1);
                    event.getPlayer().give(rocket);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event){
        if (cannotInteract(event.getBlock().getLocation(), event.getPlayer())){
            event.setCancelled(true);
        }
    }

    private boolean cannotInteract(Location blockLocation, Player player){
        if (Objects.equals(Variables.getVariable("jeu_condi"), Game.FINDER.getGameCondition())){
            blockLocation.setY(-64);

            if (blockLocation.getBlock().getType() == Material.BARRIER){
                if (player.getGameMode() != GameMode.CREATIVE && player.isOp()){
                    return true;
                }
            }
        }
        return false;
    }

}
