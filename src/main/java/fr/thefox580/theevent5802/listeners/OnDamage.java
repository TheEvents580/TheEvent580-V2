package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class OnDamage implements Listener {
    private final TheEvent580_2 plugin;

    public OnDamage(TheEvent580_2 plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if (Timer.isPaused()){
            event.setCancelled(true);
        } else if (List.of(TimerEnum.TP_TO_GAME, TimerEnum.PRE_GAME).contains(Timer.getEnum())){
            event.setCancelled(true);
        } else if (event.getEntity() instanceof Player player){
          if (Spectators.isSpectator(player)){
              event.setCancelled(true);
          }
        } else if (event.getEntity().getType() == EntityType.CHICKEN || event.getEntity().getType() == EntityType.FOX){
            if (event.getDamager().getType() == EntityType.PLAYER){
                event.getEntity().remove();
                for (Player loopedPlayer : Bukkit.getOnlinePlayers()){
                    loopedPlayer.stopSound(Sound.ENTITY_CHICKEN_HURT);
                    loopedPlayer.stopSound(Sound.ENTITY_CHICKEN_DEATH);
                }
            }
        } else if (event.getEntity().getType() == EntityType.MINECART || (event.getEntity() instanceof Player player && Players.isPlayer(player))){
            if (Variables.equals("jeu_condi", 0)){
                event.setCancelled(true);
            }
        } else if (event.getEntity().getType() == EntityType.VILLAGER){
            event.setCancelled(true);
        } else if (event.getDamageSource() == DamageSource.builder(DamageType.FALL).build()){
            event.setCancelled(true);
        } else if (!event.getEntity().getWorld().getName().equals("trials")){
            event.getEntity().stopSound(net.kyori.adventure.sound.Sound.sound(Sound.ENTITY_PLAYER_ATTACK_CRIT, net.kyori.adventure.sound.Sound.Source.PLAYER, 1f, 1f));
            event.getEntity().stopSound(net.kyori.adventure.sound.Sound.sound(Sound.ENTITY_PLAYER_ATTACK_NODAMAGE, net.kyori.adventure.sound.Sound.Source.PLAYER, 1f, 1f));
        }
    }

    @EventHandler
    public void onShootEvent(EntityShootBowEvent event){
        if (event.getEntity() instanceof Player player){
            assert event.getBow() != null;
            if (event.getBow().displayName() == Component.text("Kill a player").decoration(TextDecoration.ITALIC, false)){
                player.getInventory().clear();
            } else if (Timer.getEnum() == TimerEnum.VOTING){
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
