package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.tag.Tag;
import fr.thefox580.theevent5802.games.trials.Trials;
import fr.thefox580.theevent5802.games.trials.TrialsTasks;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class OnDamage implements Listener {
    private final TheEvent580_2 plugin;

    public OnDamage(TheEvent580_2 plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event){
        if (event.getEntity() instanceof Player player){
            if (Spectators.isSpectator(player)) {
                event.setCancelled(true);
            } else if (List.of(Timer.TimerEnum.SHOW_GAMES, Timer.TimerEnum.TP_TO_GAME, Timer.TimerEnum.PRE_GAME, Timer.TimerEnum.STARTING_SOON).contains(Timer.getEnum())){
                event.setCancelled(true);
            } else if (Timer.isPaused()){
                event.setCancelled(true);
            } else if (event.getCause() == EntityDamageEvent.DamageCause.WORLD_BORDER){
                event.setCancelled(true);
            } else if (Variables.equals("jeu_condi", Game.PARKOUR.getGameCondition())){
                event.setCancelled(true);
            } else if (Variables.equals("jeu_condi", Game.TAG.getGameCondition()) && event.getCause() == EntityDamageEvent.DamageCause.FALL){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player player){
            if (Spectators.isSpectator(player)) {
                event.setCancelled(true);
            } else if (Timer.getEnum() == Timer.TimerEnum.VOTING){
                if (event.getDamager() instanceof Player damager){
                    if (damager.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD ||
                            damager.getInventory().getItemInMainHand().getType() == Material.CROSSBOW){
                        damager.getInventory().remove(Material.NETHERITE_SWORD);
                        damager.getInventory().remove(Material.CROSSBOW);

                        Spectators.readySpectatorDecision(Online.getPlayerManager(player));
                        player.getInventory().remove(Material.CARROT_ON_A_STICK);
                        player.getInventory().remove(Material.MINECART);
                        player.getInventory().remove(Material.NETHERITE_SWORD);
                        player.getInventory().remove(Material.CROSSBOW);
                        player.getInventory().remove(Material.FOX_SPAWN_EGG);
                    }
                }
            } else if (Variables.equals("jeu_condi", Game.TRIALS.getGameCondition())){
                if (event.getDamager() instanceof Player damager){
                    Map<Player, Integer> punchs = TrialsTasks.getStats();

                    punchs.put(damager, punchs.get(damager)+1);

                    switch (TrialsTasks.getCurrentTrial()){
                        case 6 -> {
                            if (!Trials.hasPlayerCompleted(damager) && punchs.get(damager) == 1){
                                Trials.setRoundPoints(damager, Math.round(TrialsTasks.getCurrentTrialPoints()));
                            } else if (Trials.hasPlayerCompleted(damager) && punchs.get(damager) != 1){
                                Trials.setRoundPoints(damager, 0);
                            }
                        }

                        case 24 -> {
                            if (Trials.hasPlayerCompleted(damager) && punchs.get(damager) > 0){
                                Trials.setRoundPoints(damager, 0);
                            }
                        }
                    }
                }
            } else if (Variables.equals("jeu_condi", Game.TAG.getGameCondition())){
                if (event.getDamager() instanceof Player damager){
                    event.setCancelled(true);
                    if (Tag.getTagger().getUniqueId() == damager.getUniqueId()){
                        Tag.setTagger(player);
                    }
                }
            }
        } else if (List.of(Timer.TimerEnum.SHOW_GAMES, Timer.TimerEnum.TP_TO_GAME, Timer.TimerEnum.PRE_GAME).contains(Timer.getEnum())){
                event.setCancelled(true);
        } else if (Timer.isPaused()){
            event.setCancelled(true);
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
        } else if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
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
