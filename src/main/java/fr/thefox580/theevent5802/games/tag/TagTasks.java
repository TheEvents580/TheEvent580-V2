package fr.thefox580.theevent5802.games.tag;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.tasks.timer.Mode7;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TagTasks {

    private static final Map<Player, Integer> outsideBorder = new HashMap<>();
    private static final Map<Player, Integer> timeHider = new HashMap<>();

    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 60){
                    Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                            Component.text(Game.TAG.getIcon(), ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor())),
                            Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                            Title.Times.times(
                                    Duration.ofMillis(500),
                                    Duration.ofSeconds(5),
                                    Duration.ofMillis(500)
                            )
                    )));
                } else if (Timer.getSeconds() == 40){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (Timer.getSeconds() == 35){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] Your goal is to stay hidden as long as possible.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 30){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] One player will be randomly chosen to be the seeker, they must tag someone else before their time runs out, or they will lose a life!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 25){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] You only have 3 lives, so don't get tagged!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 20){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] If you stay outside of the border for more than 3 seconds, you'll get teleported to the center of the map!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.VOICE, 0.25f, 1));

                    Players.getOnlinePlayerList().forEach(playerManager -> Points.addGamePoints(Objects.requireNonNull(playerManager.getOnlinePlayer()), Math.round(100*Points.getMultiplier())));

                    mainGameTask(plugin);
                    checkWorldBorder(plugin);
                    actionBarTask(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void mainGameTask(TheEvent580_2 plugin){

        Timer.setSeconds(Game.TAG.getGameTime());
        Timer.setMaxSeconds(Game.TAG.getGameTime());
        Timer.setEnum(Timer.TimerEnum.IN_GAME);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Timer.getSeconds() == 0){
                    if (Variables.equals("jeu", 6)){
                        //new Mode10(plugin);
                    } else {
                        new Mode7(plugin);
                    }

                    this.cancel();
                } else if (Timer.getSeconds() == Math.round(Game.TAG.getGameTime() * 0.75)){
                    Tag.setWorldSize(91*0.75);
                } else if (Timer.getSeconds() == Math.round(Game.TAG.getGameTime() * 0.5)){
                    Tag.setWorldSize(91*0.5);
                } else if (Timer.getSeconds() == Math.round(Game.TAG.getGameTime() * 0.25)){
                    Tag.setWorldSize(91*0.25);
                }

                if (Tag.getTimeUntilExplosion() <= 0){
                    Tag.bombExploded();
                }

                for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                    if (playerManager.isAlive(plugin)){
                        if (Tag.getTagger().getUniqueId() == playerManager.getUniqueId()){
                            if (timeHider.get(playerManager.getOnlinePlayer()) != 0){
                                timeHider.put(playerManager.getOnlinePlayer(), 0);
                            }
                        } else {
                            if (timeHider.get(playerManager.getOnlinePlayer()) == 15){
                                timeHider.put(playerManager.getOnlinePlayer(), -1);
                                Points.addGamePoints(Objects.requireNonNull(playerManager.getOnlinePlayer()), Math.round(15*Points.getMultiplier()));
                            }
                            timeHider.put(playerManager.getOnlinePlayer(), timeHider.get(playerManager.getOnlinePlayer())+1);
                        }
                    }
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void checkWorldBorder(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 0){
                    this.cancel();
                } else {
                    for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                        if (playerManager.isAlive(plugin)){
                            if (outsideBorder.get(playerManager.getOnlinePlayer()) >= 30L){
                                Objects.requireNonNull(playerManager.getOnlinePlayer()).teleport(new Location(Bukkit.getWorld("tag"), 7.5, 142, -6.5, 180, 0));
                                playerManager.getOnlinePlayer().sendMessage(Component.text("[")
                                        .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                                        .append(Component.text("You were teleported to the center of the map for staying too long outside of the border", ColorType.MC_RED.getColor())));
                                outsideBorder.put(playerManager.getOnlinePlayer(), 0);
                            } else if (!Tag.isInBorder(playerManager.getOnlinePlayer())){
                                outsideBorder.put(playerManager.getOnlinePlayer(), outsideBorder.get(playerManager.getOnlinePlayer())+1);
                                Objects.requireNonNull(playerManager.getOnlinePlayer()).sendActionBar(Component.text("Come back within the borders or you will be TPed to the center in " + (double) (outsideBorder.get(playerManager.getOnlinePlayer()) / 10) + "s", ColorType.MC_RED.getColor()));
                            } else if (outsideBorder.get(playerManager.getOnlinePlayer()) != 0){
                                outsideBorder.put(playerManager.getOnlinePlayer(), 0);
                            }
                        }
                    }
                }

            }
        }.runTaskTimer(plugin, 0L, 2L);

    }

    private static void actionBarTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 0){
                    this.cancel();
                } else {
                    for (PlayerManager playerManager : Online.getOnlinePlayers()){
                        if (playerManager.getUniqueId() == Tag.getTagger().getUniqueId() || !playerManager.isAlive(plugin)){
                            Objects.requireNonNull(playerManager.getOnlinePlayer()).sendActionBar(
                                    Component.text("\uD83D\uDCA3", ColorType.MC_RED.getColor())
                                            .append(Component.text(" The bomb will explode in ", ColorType.TEXT.getColor()))
                                            .append(Component.text(Tag.getTimeUntilExplosion(), ColorType.MC_RED.getColor(), TextDecoration.BOLD))
                                            .append(Component.text(" seconds OR in ", ColorType.TEXT.getColor()))
                                            .append(Component.text(Tag.getSwitchUntilExplosion(), ColorType.MC_RED.getColor(),TextDecoration.BOLD))
                                            .append(Component.text(" switches ", ColorType.TEXT.getColor()))
                                            .append(Component.text("\uD83D\uDCA3", ColorType.MC_RED.getColor()))
                            );
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 2L);

    }

}
