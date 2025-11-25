package fr.thefox580.theevent5802.games.spleef;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.tasks.timer.Mode10;
import fr.thefox580.theevent5802.tasks.timer.Mode7;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.Objects;

public class SpleefTasks {

    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Spleef.getCurrentRound() == 1){

                    if (Timer.getSeconds() == 60){
                        Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                                Component.text(Game.SPLEEF.getIcon(), ColorType.NO_SHADOW.getColor())
                                        .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor())),
                                Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                                Title.Times.times(
                                        Duration.ofMillis(500),
                                        Duration.ofSeconds(5),
                                        Duration.ofMillis(500)
                                )
                        )));
                    } else if (Timer.getSeconds() == 30){
                        Spectators.getSpectatorOnlineList().forEach(Spectators::readySpectatorGame);
                    } else if (Timer.getSeconds() == 25){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor(), TextDecoration.BOLD))
                                .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                    } else if (Timer.getSeconds() == 20){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text("] You'll be playing 3 rounds, the last one standing wins!", ColorType.TEXT.getColor())));
                    } else if (Timer.getSeconds() == 15){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text("] This is a special spleef! Every layer (except the 1st one) have a special power up which will apply as soon as you get on it.", ColorType.TEXT.getColor())));
                    } else if (Timer.getSeconds() == 10){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text("] While there are no kill credits, you'll still get points when someone is eliminated.", ColorType.TEXT.getColor())));
                    }
                }

                if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" | Round " + Spleef.getCurrentRound() + "/3 |  is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" | Round " + Spleef.getCurrentRound() + "/3 |  is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" | Round " + Spleef.getCurrentRound() + "/3 |  is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" | Round " + Spleef.getCurrentRound() + "/3 |  is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    mainGameTask(plugin);
                    actionBarTask(plugin);
                    otherTasks(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private static void mainGameTask(TheEvent580_2 plugin){
        Timer.setSeconds(Game.SPLEEF.getGameTime());
        Timer.setMaxSeconds(Game.SPLEEF.getGameTime());
        Timer.setEnum(Timer.TimerEnum.IN_GAME);

        new BukkitRunnable(){

            @Override
            public void run() {


                if (Timer.getSeconds() == 0){

                    if (Spleef.getCurrentRound() == 3){

                        if (Variables.equals("jeu", 6)){
                            new Mode10(plugin);
                        } else {
                            new Mode7(plugin);
                        }
                    } else {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Spleef.startPreGame(plugin);
                            }
                        }.runTaskLater(plugin, 10*20L);
                    }

                    this.cancel();
                } else {
                    if (Timer.getSeconds() == 3*60+30){
                        Spleef.decay(0, 230, 0, 20);

                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text("⚠ Layer 1 is now decaying. ⚠", ColorType.MC_RED.getColor())));
                    } else if (Timer.getSeconds() == 2*60+30){
                        Spleef.decay(0, 220, 0, 30);

                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text("⚠ Layer 2 is now decaying. ⚠", ColorType.MC_RED.getColor())));
                    } else if (Timer.getSeconds() == 60+30){
                        Spleef.decay(0, 210, 0, 40);

                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text("⚠ Layer 3 is now decaying. ⚠", ColorType.MC_RED.getColor())));
                    } else if (Timer.getSeconds() == 30){
                        Spleef.decay(0, 200, 0, 50);

                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.SPLEEF.getName(), Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text("⚠ Layer 4 is now decaying. ⚠", ColorType.MC_RED.getColor())));
                    }
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void actionBarTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                //if (Timer.getSeconds() == 0){
                //    this.cancel();
                //}

                for (PlayerManager playerManager : Online.getOnlinePlayers()){
                    Player player = playerManager.getOnlinePlayer();
                    if (player != null){
                        player.sendActionBar(Component.text("Current Layer : ", ColorType.MC_AQUA.getColor())
                                .append(Component.text(Spleef.getPlayerLayer(player), ColorType.MC_AQUA.getColor(), TextDecoration.BOLD))
                                .append(Component.text(" | Current Layer Powerup : ", ColorType.MC_AQUA.getColor()))
                                .append(Component.text(Spleef.getPlayerLayerType(player).getName(), ColorType.MC_AQUA.getColor(), TextDecoration.BOLD)));
                    }
                }

            }
        }.runTaskTimer(plugin, 0L, 2L);

    }

    private static void otherTasks(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                //if (Timer.getSeconds() == 0){
                //    this.cancel();
                //} else if (Players.nbPlayersAlive() <= 1){
                //    Timer.setSeconds(0);
                //}

                for (PlayerManager playerManager : Online.getOnlinePlayers()){
                    Player player = playerManager.getOnlinePlayer();

                    if (player != null){
                        if (playerManager.isAlive(plugin)){
                            if (Spleef.getPlayerLayer(player) == 0){
                                Spectators.readySpectatorGame(playerManager);

                                for (PlayerManager otherPlayerManager : Players.getOnlinePlayerList()){
                                    if (otherPlayerManager.isAlive(plugin)){
                                        Player loopPlayer = otherPlayerManager.getOnlinePlayer();

                                        if (loopPlayer != null && loopPlayer.getUniqueId() != player.getUniqueId()){
                                            Points.addGamePoints(loopPlayer, Math.round(20*Points.getMultiplier()));
                                        }
                                    }

                                }

                                Bukkit.broadcast(Component.text('[')
                                                .append(Component.text('☠', ColorType.MC_RED.getColor()))
                                                .append(Component.text("] "))
                                                .append(playerManager.playerComponent())
                                                .append(Component.text(" has been eliminated.", ColorType.MC_RED.getColor())));

                                player.showTitle(Title.title(
                                        Component.text("☠ You died...", ColorType.MC_RED.getColor()),
                                        Component.text("Better luck next time!", ColorType.MC_AQUA.getColor()),
                                        Title.Times.times(
                                                Duration.ZERO,
                                                Duration.ofSeconds(2),
                                                Duration.ofMillis(250)
                                        )
                                ));
                            }
                        }

                        switch (Spleef.getPlayerLayerType(player)){
                            case BIGGER -> {
                                if (Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).getBaseValue() != Spleef.LayerType.BIGGER.getAttributeValue()){
                                    Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).setBaseValue(Spleef.LayerType.BIGGER.getAttributeValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).setBaseValue(Spleef.LayerType.BIGGER.getExtraValue());

                                    Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(0.1d);
                                    Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).getDefaultValue());
                                }
                            }
                            case SMALLER -> {
                                if (Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).getBaseValue() != Spleef.LayerType.SMALLER.getAttributeValue()){
                                    Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).setBaseValue(Spleef.LayerType.SMALLER.getAttributeValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).setBaseValue(Spleef.LayerType.SMALLER.getExtraValue());

                                    Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(0.1d);
                                    Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).getDefaultValue());
                                }
                            }
                            case FASTER -> {
                                if (Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).getBaseValue() != Spleef.LayerType.FASTER.getAttributeValue()){
                                    Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(Spleef.LayerType.FASTER.getAttributeValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).setBaseValue(Spleef.LayerType.FASTER.getAttributeValue());

                                    Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).getDefaultValue());
                                }
                            }
                            case SLOWER -> {
                                if (Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).getBaseValue() != Spleef.LayerType.SLOWER.getAttributeValue()){
                                    Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(Spleef.LayerType.SLOWER.getAttributeValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).setBaseValue(Spleef.LayerType.SLOWER.getAttributeValue());


                                    Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).getDefaultValue());
                                }
                            }
                            case BOING -> {
                                if (Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).getBaseValue() != Spleef.LayerType.BOING.getAttributeValue()){
                                    Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).setBaseValue(Spleef.LayerType.BOING.getAttributeValue());


                                    Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(0.1d);
                                    Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).getDefaultValue());
                                }
                            }
                            case GNIOB -> {
                                if (Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).getBaseValue() != Spleef.LayerType.GNIOB.getAttributeValue()){
                                    Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).setBaseValue(Spleef.LayerType.GNIOB.getAttributeValue());


                                    Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).getDefaultValue());
                                    Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(0.1d);
                                    Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).getDefaultValue());
                                }
                            }
                            case null, default -> {
                                Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SCALE)).getDefaultValue());
                                Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE)).getDefaultValue());
                                Objects.requireNonNull(player.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(0.1d);
                                Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.SNEAKING_SPEED)).getDefaultValue());
                                Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.JUMP_STRENGTH)).getDefaultValue());
                            }
                        }

                    }
                }

            }
        }.runTaskTimer(plugin, 0L, 2L);

    }
}
