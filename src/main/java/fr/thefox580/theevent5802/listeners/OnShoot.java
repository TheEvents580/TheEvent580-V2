package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.Timer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OnShoot implements Listener {

    private final TheEvent580_2 plugin;

    public OnShoot(TheEvent580_2 plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onShootEvent(EntityShootBowEvent event){
        if (event.getEntity() instanceof Player player){
            assert event.getBow() != null;
            if (event.getBow().displayName() == Component.text("Kill a player").decoration(TextDecoration.ITALIC, false)){
                player.getInventory().clear();
            } else if (Timer.getEnum() == Timer.TimerEnum.VOTING){
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getProjectile().remove();
                    }
                }.runTaskLater(plugin, 3*20L);
            }
        }
    }

}
