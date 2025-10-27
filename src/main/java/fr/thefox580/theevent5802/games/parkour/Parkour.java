package fr.thefox580.theevent5802.games.parkour;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

    /**
     * Method to call to start the pre-game setup
     * @param plugin The main class of the plugin.
     */
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

        Variables.setVariable("jeu_condi", Game.PARKOUR.getGameCondition());

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

    /**
     * Based on the player's current main level, returns a Component to better show it.
     * @param player The player who we want the Component from.
     * @return The main level in color as a Component
     */
    public static Component getMainLevelComp(Player player){
        return switch (getMainLevel(player)) {
            case 1 -> Component.text("Easy", ColorType.MC_LIME.getColor(), TextDecoration.BOLD);
            case 2 -> Component.text("Medium", ColorType.MC_ORANGE.getColor(), TextDecoration.BOLD);
            case 3 -> Component.text("Hard", ColorType.MC_RED.getColor(), TextDecoration.BOLD);
            case 4 -> Component.text("Extreme", ColorType.MC_DARK_RED.getColor(), TextDecoration.BOLD);
            default -> Component.text("None", ColorType.TEXT.getColor(), TextDecoration.BOLD);
        };
    }

    /**
     * @param player The player who we want the main level from.
     * @return The main level the player is at
     */
    public static int getMainLevel(Player player){
        return playerMainLevel.get(player);
    }

    /**
     * Sets the player's main level to what 'mainLevel' is set.
     * @param player The player who we want to change the main level.
     * @param mainLevel The new main level.
     */
    public static void setMainLevel(Player player, int mainLevel){
        playerMainLevel.put(player, mainLevel);
    }

    /**
     * Based on the player's current sublevel, returns a Component to better show it.
     * @param player The player who we want the Component from.
     * @return The sublevel in color as a Component
     */
    public static Component getSubLevelComp(Player player){
        return Component.text(getSubLevel(player), ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false);
    }

    /**
     * @param player The player who we want the sublevel from.
     * @return The sublevel the player is at
     */
    public static int getSubLevel(Player player){
        return playerSubLevel.get(player);
    }

    /**
     * Sets the player's sublevel to what 'subLevel' is set.
     * @param player The player who we want to change the sublevel.
     * @param subLevel The new sublevel.
     */
    public static void setSubLevel(Player player, int subLevel){
        playerSubLevel.put(player, subLevel);
    }

    /**
     * Returns how much in-game points (DIFFERENT FROM game_points) the player has.
     * (It may be different based on if a player skipped levels or not or even if someone finishes the parkour after you).
     * @param player The player who we want the in-game points from.
     * @return The amount of coins a player has gotten.
     */
    public static int getPlayerPoints(Player player){
        return playerPoints.get(player);
    }

    /**
     * Adds in-game points (DIFFERENT FROM game_points) to the player.
     * (It may be different based on if a player skipped levels or not or even if someone finishes the parkour after you)
     * @param player The player who we want to update the in-game points from.
     * @param points The amount of points you want to add.
     */
    public static void addPlayerPoints(Player player, int points){
        playerPoints.put(player, playerPoints.get(player) + points);
    }

    /**
     * Removes in-game points (DIFFERENT FROM game_points) to the player.
     * (It may be different based on if a player skipped levels or not or even if someone finishes the parkour after you)
     * @param player The player who we want to update the in-game points from.
     * @param points The amount of points you want to remove.
     */
    public static void removePlayerPoints(Player player, int points){
        playerPoints.put(player, playerPoints.get(player) - points);
    }

    /**
     * @param player The player who we want the multiplier from.
     * @return The multiplier a player has.
     */
    public static float getPlayerMult(Player player){
        return playerMults.get(player);
    }

    /**
     * Adds multiplier to the player.
     * @param player The player who we want to update the multiplier.
     * @param mult The multiplier we want to add.
     */
    public static void addPlayerMult(Player player, float mult){
        playerMults.put(player, playerMults.get(player) + mult);
    }

    /**
     * @param player The player who we want the last checkpoint location from.
     * @return The location of the checkpoint of the player.
     */
    public static Location getPlayerCheckpoint(Player player){
        return playerCheckpoint.get(player);
    }

    /**
     * Upon calling this method, will update the location of the player's checkpoint.
     * @param player The player who we want to update the checkpoint location from.
     */
    public static void setPlayerCheckpoint(Player player){
        playerCheckpoint.put(player, player.getLocation());
    }

    /**
     * @param player The player who may have skipped a level.
     * @return true if the player has skipped a level in the last 10 seconds, false otherwise.
     */
    public static boolean hasPlayerSkipped(Player player){
        return playerSkipped.get(player);
    }

    /**
     * Upon calling this method, if Parkour#getPlayerSkipped(player) is called within the next 10 seconds,
     * then it will return true.
     * @param player The player who skipped a level.
     */
    public static void setPlayerSkipped(Player player, TheEvent580_2 plugin){
        playerSkipped.put(player, true);
        removePlayerPoints(player, 5);

        ItemStack skip = player.getActiveItem();

        if (skip.getType() == Material.CARROT_ON_A_STICK){
            player.setCooldown(skip, 10*20);
        }

        Location checkpoint = getPlayerCheckpoint(player);
        checkpoint.setY(129);
        player.teleport(checkpoint.add(16, 0, 0));
        player.sendMessage(Component.text("[")
                .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                .append(Component.text("] You will be able to skip again in 10 seconds!", ColorType.TEXT.getColor())));

        new BukkitRunnable() {
            @Override
            public void run() {
                playerSkipped.put(player, false);
            }
        }.runTaskLater(plugin, 10*20L);
    }

}
