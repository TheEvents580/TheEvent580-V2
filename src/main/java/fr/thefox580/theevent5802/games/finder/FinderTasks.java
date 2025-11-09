package fr.thefox580.theevent5802.games.finder;

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
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;

public class FinderTasks {

    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 60){
                    Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                            Component.text(Game.FINDER.getIcon(), ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor())),
                            Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                            Title.Times.times(
                                    Duration.ofMillis(500),
                                    Duration.ofSeconds(5),
                                    Duration.ofMillis(500)
                            )
                    )));
                } else if (Timer.getSeconds() == 40){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (Timer.getSeconds() == 35){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] Your goal is to collect as many items as possible in 15 minutes!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 30){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] The more items you collect, the more points you'll have!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 25){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] Every 3 minutes, the item set will rotate for a new one!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 20){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] In the last minute of each set, a Golden Item (worth 3x more) will appear!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 15){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] You'll get points based on how many times you've given an item compared to everyone else..", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.VOICE, 0.25f, 1));

                    mainGameTask(plugin);
                    actionBarTask(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    public static void mainGameTask(TheEvent580_2 plugin){

        Timer.setSeconds(Game.FINDER.getGameTime());
        Timer.setMaxSeconds(Game.FINDER.getGameTime());
        Timer.setEnum(Timer.TimerEnum.IN_GAME);

        new BukkitRunnable(){

            @Override
            public void run() {
                if (Timer.getSeconds() == 0){
                    if (Variables.equals("jeu", 6)){
                        new Mode10(plugin);
                    } else {
                        new Mode7(plugin);
                    }

                    this.cancel();
                } else if ((Timer.getSeconds() % (60*3) == 60*2)){

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] The ", ColorType.TEXT.getColor()))
                            .append(Component.text("golden item", ColorType.RAINBOW.getColor()))
                            .append(Component.text(" will be unlocked in ", ColorType.TEXT.getColor()))
                            .append(Component.text("60 seconds", ColorType.MC_GREEN.getColor())));

                } else if ((Timer.getSeconds() % (60*3) == 60+30)){

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] The ", ColorType.TEXT.getColor()))
                            .append(Component.text("golden item", ColorType.RAINBOW.getColor()))
                            .append(Component.text(" will be unlocked in ", ColorType.TEXT.getColor()))
                            .append(Component.text("30 seconds", ColorType.MC_LIME.getColor())));

                } else if ((Timer.getSeconds() % (60*3)) == 60+5){

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] The ", ColorType.TEXT.getColor()))
                            .append(Component.text("golden item", ColorType.RAINBOW.getColor()))
                            .append(Component.text(" will be unlocked in ", ColorType.TEXT.getColor()))
                            .append(Component.text("5 seconds", ColorType.MC_YELLOW.getColor())));

                } else if ((Timer.getSeconds() % (60*3)) == 60+3){

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] The ", ColorType.TEXT.getColor()))
                            .append(Component.text("Golden Item", ColorType.RAINBOW.getColor()))
                            .append(Component.text(" will be unlocked in ", ColorType.TEXT.getColor()))
                            .append(Component.text("3 seconds", ColorType.MC_YELLOW.getColor())));

                } else if ((Timer.getSeconds() % (60*3)) == 60+2){

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] The ", ColorType.TEXT.getColor()))
                            .append(Component.text("Golden Item", ColorType.RAINBOW.getColor()))
                            .append(Component.text(" will be unlocked in ", ColorType.TEXT.getColor()))
                            .append(Component.text("2 seconds", ColorType.MC_YELLOW.getColor())));

                } else if ((Timer.getSeconds() % (60*3)) == 60+1){

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] The ", ColorType.TEXT.getColor()))
                            .append(Component.text("Golden Item", ColorType.RAINBOW.getColor()))
                            .append(Component.text(" will be unlocked in ", ColorType.TEXT.getColor()))
                            .append(Component.text("1 second", ColorType.MC_YELLOW.getColor())));

                } else if ((Timer.getSeconds() % (60*3)) == 60){

                    FinderSets.setGoldenLocked(false);

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("]", ColorType.TEXT.getColor()))
                            .append(Component.text(" The ", ColorType.MC_LIME.getColor()))
                            .append(Component.text("Golden Item", ColorType.RAINBOW.getColor()))
                            .append(Component.text(" has been unlocked", ColorType.MC_LIME.getColor())));

                    if (Timer.getSeconds() > 60){

                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] The item set in the shop will be replaced in ", ColorType.TEXT.getColor()))
                                .append(Component.text("60 seconds", ColorType.MC_GREEN.getColor())));
                    }

                } else if ((Timer.getSeconds() % (60*3)) == 30){

                    if (Timer.getSeconds() > 60){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] The item set in the shop will be replaced in ", ColorType.TEXT.getColor()))
                                .append(Component.text("30 seconds", ColorType.MC_LIME.getColor())));
                    } else {
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text(" ends in 30 seconds", ColorType.MC_RED.getColor())));
                    }

                } else if ((Timer.getSeconds() % (60*3)) == 5){

                    if (Timer.getSeconds() > 60){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] The item set in the shop will be replaced in ", ColorType.TEXT.getColor()))
                                .append(Component.text("5 seconds", ColorType.MC_YELLOW.getColor())));
                    } else {
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text(" ends in 5 seconds", ColorType.MC_RED.getColor())));
                    }

                } else if ((Timer.getSeconds() % (60*3)) == 3){

                    if (Timer.getSeconds() > 60){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] The item set in the shop will be replaced in ", ColorType.TEXT.getColor()))
                                .append(Component.text("3 seconds", ColorType.MC_ORANGE.getColor())));
                    } else {
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text(" ends in 3 seconds", ColorType.MC_RED.getColor())));
                    }

                } else if ((Timer.getSeconds() % (60*3)) == 2){

                    if (Timer.getSeconds() > 60){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] The item set in the shop will be replaced in ", ColorType.TEXT.getColor()))
                                .append(Component.text("2 seconds", ColorType.MC_RED.getColor())));
                    } else {
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text(" ends in 2 seconds", ColorType.MC_RED.getColor())));
                    }

                } else if ((Timer.getSeconds() % (60*3)) == 1){

                    if (Timer.getSeconds() > 60){
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] The item set in the shop will be replaced in ", ColorType.TEXT.getColor()))
                                .append(Component.text("1 second", ColorType.MC_DARK_RED.getColor())));
                    } else {
                        Bukkit.broadcast(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text(" ends in 1 second", ColorType.MC_RED.getColor())));
                    }


                } else if (Timer.getSeconds() > 60 && (Timer.getSeconds() % (60*3)) == 0){

                    FinderSets.newRandomItemSet();

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                            .append(Component.text("] The item set in the shop ", ColorType.TEXT.getColor()))
                            .append(Component.text("has been replaced", ColorType.MC_LIME.getColor())));

                }
            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void actionBarTask(TheEvent580_2 plugin){
        new BukkitRunnable() {
            @Override
            public void run() {

                for (PlayerManager playerManager : Online.getOnlinePlayers()){
                    if (playerManager.getOnlinePlayer() != null){

                        Player player = playerManager.getOnlinePlayer();

                        player.sendActionBar(Component.text("Items found this set : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                                .append(Component.text(Finder.getMaterialsFoundPlayerForCurrentSet(player) + "/" + Finder.getMaterialsFoundAllPlayersForCurrentSet(), ColorType.SUBTEXT.getColor(), TextDecoration.BOLD))
                                .append(Component.text(" | ", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false))
                                .append(Component.text("Items found : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD))
                                .append(Component.text(Finder.getPlayerItemsFound(player) + "/" + Finder.getTotalItemsFound(), ColorType.SUBTEXT.getColor(), TextDecoration.BOLD)));

                    }
                }

            }
        }.runTaskTimer(plugin, 0L, 2L);
    }
    
}
