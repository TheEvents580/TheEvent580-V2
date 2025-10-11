package fr.thefox580.theevent5802.games.parkour;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Parkour {
    private static final Map<Player, Integer> playerPoints = new HashMap<>();
    private static final Map<Player, Float> playerMults = new HashMap<>();
    private static final Map<Player, Integer> playerMainLevel = new HashMap<>();
    private static final Map<Player, Integer> playerSubLevel = new HashMap<>();
    private static final Map<Player, Location> playerCheckpoint = new HashMap<>();
    private static final Map<Player, Boolean> playerSkipped = new HashMap<>();

    public static void startPreGame(TheEvent580_2 plugin){
        Timer.setSeconds(90);
        Timer.setMaxSeconds(90);
        Timer.setEnum(Timer.TimerEnum.PRE_GAME);

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(new Location(Bukkit.getWorld("parkour"), 131.5, 129, 201.5, -90, 0));
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setGameMode(GameMode.ADVENTURE);
                    Bukkit.getOnlinePlayers().forEach(otherPlayer -> {
                        if (otherPlayer.getUniqueId() != player.getUniqueId()){
                            player.hidePlayer(plugin, otherPlayer);
                        }
                    });
                }
            }.runTaskLater(plugin, 20L);
        });

        playerPoints.clear();
        playerMults.clear();
        playerMainLevel.clear();
        playerSubLevel.clear();

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            playerPoints.put(playerManager.getOnlinePlayer(), Math.round(120 * Points.getMultiplier()));
            playerMults.put(playerManager.getOnlinePlayer(), 1f);
            playerMainLevel.put(playerManager.getOnlinePlayer(), 1);
            playerSubLevel.put(playerManager.getOnlinePlayer(), 1);
            playerCheckpoint.put(playerManager.getOnlinePlayer(), new Location(Bukkit.getWorld("parkour"), 139.5, 129, 201.5, -90, 0));
        }

        ParkourTasks.preGameTask(plugin);
    }

    public static Component getMainLevelComp(Player player){
        return switch (playerMainLevel.get(player)) {
            case 1 -> Component.text("Easy", ColorType.MC_LIME.getColor(), TextDecoration.BOLD);
            case 2 -> Component.text("Medium", ColorType.MC_ORANGE.getColor(), TextDecoration.BOLD);
            case 3 -> Component.text("Hard", ColorType.MC_RED.getColor(), TextDecoration.BOLD);
            case 4 -> Component.text("Extreme", ColorType.MC_DARK_RED.getColor(), TextDecoration.BOLD);
            default -> Component.text("None", ColorType.TEXT.getColor(), TextDecoration.BOLD);
        };
    }

    public static int getMainLevel(Player player){
        return playerMainLevel.get(player);
    }

    public static void setMainLevel(Player player, int mainLevel){
        playerMainLevel.put(player, mainLevel);
    }

    public static Component getSubLevelComp(Player player){
        return Component.text(playerSubLevel.get(player), ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false);
    }

    public static int getSubLevel(Player player){
        return playerSubLevel.get(player);
    }

    public static void setSubLevel(Player player, int subLevel){
        playerSubLevel.put(player, subLevel);
    }

    public static int getPlayerPoints(Player player){
        return playerPoints.get(player);
    }

    public static void addPlayerPoints(Player player, int points){
        playerPoints.put(player, playerPoints.get(player) + points);
    }

    public static void removePlayerPoints(Player player, int points){
        playerPoints.put(player, playerPoints.get(player) - points);
    }

    public static float getPlayerMult(Player player){
        return playerMults.get(player);
    }

    public static void addPlayerMult(Player player, float mult){
        playerMults.put(player, playerMults.get(player) + mult);
    }

    public static Location getPlayerCheckpoint(Player player){
        return playerCheckpoint.get(player);
    }

    public static void setPlayerCheckpoint(Player player){
        playerCheckpoint.put(player, player.getLocation());
    }

    public static boolean getPlayerSkipped(Player player){
        return playerSkipped.get(player);
    }

    public static void setPlayerSkipped(Player player, TheEvent580_2 plugin){
        playerSkipped.put(player, true);

        new BukkitRunnable() {
            @Override
            public void run() {
                playerSkipped.put(player, false);
            }
        }.runTaskLater(plugin, 10*20L);
    }

}
