package fr.thefox580.theevent5802.games.arms_race;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.tasks.timer.Mode10;
import fr.thefox580.theevent5802.tasks.timer.Mode7;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;

public class ArmsRaceTasks {

    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 60){
                    Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                            Component.text(Game.ARMS_RACE.getIcon(), ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor())),
                            Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                            Title.Times.times(
                                    Duration.ofMillis(500),
                                    Duration.ofSeconds(5),
                                    Duration.ofMillis(500)
                            )
                    )));
                } else if (Timer.getSeconds() == 40){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (Timer.getSeconds() == 35){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] Your goal is to be the 1st one to kill a player with the golden knife as your ONLY weapon.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 30){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] Every 2 kills, you upgrade to a new weapon!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 25){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] You will also have a Golden Knife at all time, killing someone with it will send them back 1 weapon.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 20){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] If nobody gets the final kill after 15 minutes, the game will end.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 15){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] Be careful, dying will remove (5+your weapon progression)% of your in-game points, so make sure to not die!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 10){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] For example, if you are on the 13th weapon and you die, you will lose (5+13)% = 18% of your coins", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.VOICE, 0.25f, 1));

                    Players.getOnlinePlayerList().forEach(playerManager -> {
                        Player player = playerManager.getOnlinePlayer();

                        if (player != null){
                            player.getInventory().clear();
                            player.give(ArmsRace.getWeapon(0));
                            ItemStack item = ArmsRace.getPlayerItem(player);
                            player.give(item);
                            if (List.of(Material.BOW, Material.CROSSBOW).contains(item.getType())){
                                player.give(ItemStack.of(Material.ARROW));
                            }

                        }
                    });

                    mainGameTask(plugin);
                    otherTask(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void mainGameTask(TheEvent580_2 plugin){

        Timer.setSeconds(Game.ARMS_RACE.getGameTime());
        Timer.setMaxSeconds(Game.ARMS_RACE.getGameTime());
        Timer.setEnum(Timer.TimerEnum.IN_GAME);

        new BukkitRunnable() {
            @Override
            public void run() {


                if (Timer.getSeconds() == 0){

                    if (Variables.equals("jeu", 6)){
                        new Mode10(plugin);
                    } else {
                        new Mode7(plugin);
                    }

                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void otherTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                    Player player = playerManager.getOnlinePlayer();

                    if (player != null){
                        if (ArmsRace.getPlayerKills(player) > ArmsRace.getWeaponCount()){
                            Timer.setSeconds(0);
                        }
                    }
                }

                if (Timer.getSeconds() == 0){
                    this.cancel();
                } else {
                    for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                        Player player = playerManager.getOnlinePlayer();

                        if (player != null){
                            int playerProgress = ArmsRace.getPlayerKills(player);

                            Component actionBar = getActionBar(playerProgress);

                            player.sendActionBar(actionBar);

                            if (ArmsRace.getPlayerKills(ArmsRace.getFurthestPlayers().getFirst()) > 0){
                                if (ArmsRace.getFurthestPlayers().contains(player)){
                                    if (!player.isGlowing()){
                                        player.setGlowing(true);
                                    }
                                } else if (player.isGlowing()){
                                    player.setGlowing(false);
                                }
                            }

                        }
                    }

                }
            }

            private static @NotNull Component getActionBar(int playerProgress) {
                int totalWeapons = ArmsRace.getWeaponCount();

                if (playerProgress > totalWeapons){
                    playerProgress = totalWeapons;
                }

                Component actionBar = Component.text("Progression : ", ColorType.SPECIAL_2.getColor());

                for (int i = 0; i < playerProgress; i++) {
                    if (i == totalWeapons-1){
                        actionBar = actionBar.append(Component.text("| ", ColorType.ARMS_RACE_DONE_LAST.getColor()));
                        return actionBar;
                    } else {
                        if (i%2 == 0){
                            actionBar = actionBar.append(Component.text("| ", ColorType.ARMS_RACE_DONE_EVEN.getColor()));
                        } else {
                            actionBar = actionBar.append(Component.text("| ", ColorType.ARMS_RACE_DONE_ODD.getColor()));
                        }
                    }
                }

                for (int i = playerProgress; i < totalWeapons; i++) {
                    if (i == totalWeapons - 1) {
                        actionBar = actionBar.append(Component.text("| ", ColorType.ARMS_RACE_BLANK_LAST.getColor()));
                    } else {
                        if (i % 2 == 0) {
                            actionBar = actionBar.append(Component.text("| ", ColorType.ARMS_RACE_BLANK_EVEN.getColor()));
                        } else {
                            actionBar = actionBar.append(Component.text("| ", ColorType.ARMS_RACE_BLANK_ODD.getColor()));
                        }
                    }
                }

                return actionBar;
            }
        }.runTaskTimer(plugin, 0L, 2L);

    }

}
