package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.tasks.AnnouncePlayers;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mode1 implements Runnable {

    private final TheEvent580_2 plugin;
    private final List<PlayerManager> newPlayers = new ArrayList<>();
    private final List<PlayerManager> players = new ArrayList<>();
    private final List<PlayerManager> spectators = new ArrayList<>();
    private final BukkitTask task;

    public Mode1(TheEvent580_2 plugin){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);

        Timer.setSeconds(2*60+59);
        Timer.setMaxSeconds(2*60+59);
        Timer.setEnum(Timer.TimerEnum.START);
        Timer.setPaused(false);
    }

    @Override
    public void run() {
        if (Timer.getSeconds() == 2*60+30){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("Announcing new players :",
                        ColorType.RAINBOW.getColor()), Component.text("")));
            }

            for (PlayerManager loopPlayer : Players.getOnlinePlayerList()){
                int totalPlayerPoints = Points.getTotalPointsPreEvent(Objects.requireNonNull(loopPlayer.getOnlinePlayer()));
                if (totalPlayerPoints <= 0){
                    newPlayers.add(loopPlayer);
                }
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (newPlayers.isEmpty()){

                        for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                            Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("Announcing new players :", ColorType.RAINBOW.getColor()),
                                    Component.text("Oh wow, no newcomers!", ColorType.RAINBOW.getColor())));
                        }
                    } else {
                        new AnnouncePlayers("new_players", newPlayers, plugin);
                    }
                }
            }.runTaskLater(plugin, 2*20L);

        } else if (Timer.getSeconds() == 60+45){
            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("Next, announcing players :",
                        ColorType.RAINBOW_WAVE.getColor()), Component.text("")));
            }

            players.addAll(Players.getOnlinePlayerList());

            new BukkitRunnable() {
                @Override
                public void run() {
                    new AnnouncePlayers("players", players, plugin);
                }
            }.runTaskLater(plugin, 2*20L);
        } else if (Timer.getSeconds() == 60+30){
            for (Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(), "custom:intro", SoundCategory.VOICE, 1, 1);
            }
        }else if (Timer.getSeconds() == 60){
            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("And here are your spectators :",
                        ColorType.RAINBOW_ITER.getColor()), Component.text("")));
            }

            spectators.addAll(Spectators.getSpectatorOnlineList());

            new BukkitRunnable() {
                @Override
                public void run() {
                    new AnnouncePlayers("spectators", spectators, plugin);
                }
            }.runTaskLater(plugin, 2*20L);
        } else if (Timer.getSeconds() == 10) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text(">     10     <", ColorType.MC_DARK_RED.getColor()),
                        Title.Times.times(Duration.ofMillis(500), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 9) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text(">     9     <", ColorType.MC_DARK_RED.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 8) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text(">    8    <", ColorType.MC_RED.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 7) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text(">    7    <", ColorType.MC_RED.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 6) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text(">   6   <", ColorType.MC_ORANGE.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 5) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text(">   5   <", ColorType.MC_ORANGE.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 4) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text(">  4  <", ColorType.MC_YELLOW.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 3) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text(">  3  <", ColorType.MC_YELLOW.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 2){
          Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "execute in overworld run fill 8 247 -8 -8 247 8 redstone_block");
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text("> 2 <", ColorType.MC_LIME.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 1) {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting in", ColorType.RAINBOW.getColor()),
                        Component.text("> 1 <", ColorType.MC_LIME.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(0))));
            }
        } else if (Timer.getSeconds() == 0){
            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(Component.text("Event starting", ColorType.RAINBOW.getColor()),
                        Component.text("NOW", ColorType.MC_GREEN.getColor()),
                        Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(2))));
            }
            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
                loopPlayer.getOnlinePlayer().setGameMode(GameMode.ADVENTURE);
                for (PlayerManager player : Players.getOnlinePlayerList()){
                    if (loopPlayer.getUniqueId() != player.getUniqueId()){
                        Objects.requireNonNull(player.getOnlinePlayer()).hidePlayer(plugin, Objects.requireNonNull(loopPlayer.getOnlinePlayer()));
                    }
                }
            }

            Spectators.readySpectatorsDecision();
            Component message = Component.text('[')
                    .append(Component.text("TheEvent580 - Admin", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                    .append(Component.text("] Teleported ", ColorType.TEXT.getColor()))
                    .append(Component.text("ALL PLAYERS", ColorType.SPECIAL_3.getColor(), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                    .append(Component.text(" to the decision crystal !", ColorType.TEXT.getColor()));
            for (PlayerManager loopPlayer : Spectators.getSpectatorOnlineList()){
                if (Objects.requireNonNull(loopPlayer.getOnlinePlayer()).hasPermission("theevent580.staff")){
                    loopPlayer.getOnlinePlayer().sendMessage(message);
                }
            }
            new Mode2(plugin);
            task.cancel();
        }
    }
}
