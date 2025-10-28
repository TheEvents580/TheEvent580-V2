package fr.thefox580.theevent5802.games.build_masters;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.Objects;

public class BuildMastersTasks {

    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 60){
                    Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                            Component.text(Game.BUILD_MASTERS.getIcon(), ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor())),
                            Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                            Title.Times.times(
                                    Duration.ofMillis(500),
                                    Duration.ofSeconds(5),
                                    Duration.ofMillis(500)
                            )
                    )));
                } else if (Timer.getSeconds() == 40){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (Timer.getSeconds() == 35){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] Your goal is to stay hidden as long as possible.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 30){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] One player will be randomly chosen to be the seeker, they must tag someone else before their time runs out, or they will lose a life!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 25){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] You only have 3 lives, so don't get tagged!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 20){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] If you stay outside of the border for more than 3 seconds, you'll get teleported to the center of the map!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.VOICE, 0.25f, 1));

                    Players.getOnlinePlayerList().forEach(playerManager -> Points.addGamePoints(Objects.requireNonNull(playerManager.getOnlinePlayer()), Math.round(100*Points.getMultiplier())));

                    // mainGameTask(plugin);
                    // checkWorldBorder(plugin);
                    // actionBarTask(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }
}
