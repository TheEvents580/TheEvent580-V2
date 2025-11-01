package fr.thefox580.theevent5802.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.build_masters.BuildMasters;
import fr.thefox580.theevent5802.games.trials.Trials;
import fr.thefox580.theevent5802.games.trials.TrialsTasks;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;

public class OnPlayerMove implements Listener {

    public OnPlayerMove(TheEvent580_2 plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerSneakEvent(PlayerToggleSneakEvent event){
        if (event.isSneaking()){
            Player player = event.getPlayer();
            if (Objects.equals(Variables.getVariable("jeu_condi"), 0)
                    && player.getLocation().add(0, -1, 0).getBlock().getType() == Material.DEEPSLATE_COAL_ORE
                    && player.getY() >= 60){
                player.teleport(new Location(player.getWorld(), 315.5, -4.5, 323.5, -90, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 255, false, false, false));
            } else if (Timer.getEnum() == Timer.TimerEnum.VOTING){
                Entity passengerOf = null;

                for (Entity entity : player.getWorld().getEntities()){
                    if (entity.getPassengers().contains(player)){
                        passengerOf = entity;
                        break;
                    }
                }

                if (passengerOf != null){
                    event.setCancelled(false);
                    player.showTitle(Title.title(Component.text(":(", ColorType.MC_RED.getColor()),
                            Component.text("You're stuck here until voting is over.",
                                    ColorType.MC_RED.getColor()), Title.Times.times(Duration.ZERO, Duration.ofSeconds(3), Duration.ZERO)));
                }
            } else if (Variables.equals("jeu_condi", Game.TRIALS.getGameCondition())){

                Map<Player, Integer> sneaks = TrialsTasks.getStats();

                sneaks.put(player, sneaks.get(player)+1);

                switch (TrialsTasks.getCurrentTrial()){
                    case 3 -> {
                        if (!Trials.hasPlayerCompleted(player) && sneaks.get(player) == 1){
                            Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));
                        } else if (Trials.hasPlayerCompleted(player) && sneaks.get(player) != 1){
                            Trials.setRoundPoints(player, 0);
                        }
                    }
                    case 4 -> {
                        if (!Trials.hasPlayerCompleted(player) && sneaks.get(player) == 2){
                            Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));
                        } else if (Trials.hasPlayerCompleted(player) && sneaks.get(player) != 2){
                            Trials.setRoundPoints(player, 0);
                        }
                    }

                    case 23 -> {
                        if (Trials.hasPlayerCompleted(player) && sneaks.get(player) > 0){
                            Trials.setRoundPoints(player, 0);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJumpEvent(PlayerJumpEvent event){
        Player player = event.getPlayer();
        if (player.getWorld().getName().equals("world")){
            if (Objects.equals(Variables.getVariable("jeu_condi"), 0)
                    && player.getLocation().add(0, -1, 0).getBlock().getType() == Material.DEEPSLATE_COAL_ORE
                    && player.getY() <= 0){
                player.teleport(new Location(player.getWorld(), 328.5, 69.5, 316.5, 150, 0));
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
        } else if (Variables.equals("jeu_condi", Game.TRIALS.getGameCondition())){

            Map<Player, Integer> jumps = TrialsTasks.getStats();

            jumps.put(player, jumps.get(player)+1);

            switch (TrialsTasks.getCurrentTrial()){
                case 1 -> {
                    if (!Trials.hasPlayerCompleted(player) && jumps.get(player) == 1){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));
                    } else if (Trials.hasPlayerCompleted(player) && jumps.get(player) != 1){
                        Trials.setRoundPoints(player, 0);
                    }
                }
                case 2 -> {
                    if (!Trials.hasPlayerCompleted(player) && jumps.get(player) == 2){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));
                    } else if (Trials.hasPlayerCompleted(player) && jumps.get(player) != 2){
                        Trials.setRoundPoints(player, 0);
                    }
                }

                case 22 -> {
                    if (Trials.hasPlayerCompleted(player) && jumps.get(player) > 0){
                        Trials.setRoundPoints(player, 0);
                    }
                }
            }
        } else if (player.getWorld().equals(BuildMasters.getWorld())){
            if (player.getZ() > 3000){
                Material launcher = player.getLocation().clone().add(0, -0.06250, 0).getBlock().getType();
                if (BuildMasters.getLaunchersBlocks().contains(launcher)){
                    player.removePotionEffect(PotionEffectType.JUMP_BOOST);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 2, 1, false, false, false));

                    Vector upVector = new Vector(0, 1, 0);

                    Vector northVector = new Vector(0, 0, -1);
                    Vector eastVector = new Vector(1, 0, 0);
                    Vector southVector = new Vector(0, 0, 1);
                    Vector westVector = new Vector(-1, 0, 0);

                    if (launcher == Material.GREEN_CARPET || launcher == Material.CYAN_CARPET){
                        player.setVelocity(upVector.normalize());

                    } else if (launcher == Material.YELLOW_CARPET){
                        player.setVelocity(eastVector.normalize().multiply(3));

                    } else if (launcher == Material.RED_CARPET){
                        player.setVelocity(westVector.normalize().multiply(3));

                    } else if (launcher == Material.ORANGE_CARPET){
                        player.setVelocity(northVector.normalize().multiply(3));

                    } else if (launcher == Material.LIME_CARPET){
                        player.setVelocity(southVector.normalize().multiply(3));

                    }
                }
            }
        }
    }

}
