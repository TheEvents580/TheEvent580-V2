package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Spawn implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public Spawn(TheEvent580_2 plugin){
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("spawn")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player){
            if (Objects.equals(Variables.getVariable("jeu_condi"), 0)){
                if (List.of(0, 1, 7, 8, 9, 10, 11).contains(Timer.getEnum().getMode())){
                    if (Players.isPlayer(player)){
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.teleport(new Location(Bukkit.getWorld("world"), 310.5, 66, 323.5, 180, 0));
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.setGameMode(GameMode.ADVENTURE);
                            }
                        }.runTaskLater(plugin, 2*20L);
                    } else {
                        player.teleport(new Location(Bukkit.getWorld("world"), 321.5, 75, 340.5, 147.25f, 25));
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.setGameMode(GameMode.ADVENTURE);
                                Spectators.readySpectatorLobby(Objects.requireNonNull(Spectators.getPlayerManager(player)));
                            }
                        }.runTaskLater(plugin, 2*20L);
                    }
                    player.setFlySpeed(0.1f);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, false, false, false));
                    AttributeInstance maxHealth = player.getAttribute(Attribute.MAX_HEALTH);
                    assert maxHealth != null;
                    maxHealth.setBaseValue(maxHealth.getDefaultValue());
                    player.heal(20, EntityRegainHealthEvent.RegainReason.CUSTOM);
                    return true;
                }
            }
        }

        sender.sendMessage(Component.text("You cannot go to spawn yet!", ColorType.MC_RED.getColor()));

        return true;
    }

    public static void tp(Player player){
        if (Players.isPlayer(player)){
            player.setAllowFlight(false);
            player.setFlying(false);
            player.teleport(new Location(Bukkit.getWorld("world"), 310.5, 66, 323.5, 180, 0));
        } else {
            player.teleport(new Location(Bukkit.getWorld("world"), 321.5, 75, 340.5, 147.25f, 25));
            Spectators.readySpectatorLobby(Objects.requireNonNull(Spectators.getPlayerManager(player)));
        }
        player.setGameMode(GameMode.ADVENTURE);
    }

}
