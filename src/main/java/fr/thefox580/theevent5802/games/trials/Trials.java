package fr.thefox580.theevent5802.games.trials;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Trials {

    private static final Map<Player, Integer> roundPoints = new HashMap<>();
    private static final Map<Player, Boolean> roundCompleted = new HashMap<>();
    private static int trialLevel = 1;

    public static void startPreGame(TheEvent580_2 plugin){
        Timer.setSeconds(90);
        Timer.setMaxSeconds(90);
        Timer.setEnum(Timer.TimerEnum.PRE_GAME);

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(new Location(Bukkit.getWorld("trials"), 0.5, 130, 0.5, 0, 0));
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setGameMode(GameMode.ADVENTURE);
                }
            }.runTaskLater(plugin, 20L);
        });

        Variables.setVariable("jeu_condi", Game.TRIALS.getGameCondition());

        Spectators.readySpectatorsGame();

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            Player player = playerManager.getOnlinePlayer();
            if (player != null){
                setRoundPoints(player, 0);
            }
        }

        resetRoundPoints();

        TrialsTasks.preGameTask(plugin);
        TrialsDecision.resetAllTrials();
    }

    /**
     * Adds the points to each player's `game_point` variable.
     * Then resets the map.
     */
    public static void resetRoundPoints(){

        for (Player player : roundPoints.keySet()){
            Points.addGamePoints(player, roundPoints.get(player));
            roundPoints.put(player, 0);
            roundCompleted.put(player, false);
        }
    }

    public static boolean hasPlayerCompleted(Player player){
        return roundCompleted.get(player);
    }

    public static int getPlayersCompletedCount(){
        int playersCompleted = 0;
        for (Player player : roundCompleted.keySet()){
            if (roundCompleted.get(player)) playersCompleted++;
        }

        return playersCompleted;
    }

    /**
     * Gets the points obtained by a player.
     * @param player The player who we want to get the points
     */
    public static int getRoundPoints(Player player){
        return roundPoints.get(player);
    }

    /**
     * Sets the points obtained by a player to `roundPoints` and if points are 0, then sets their completed round to false.
     * @param player The player who got those points
     * @param points The amount of points this player got
     */
    public static void setRoundPoints(Player player, int points){
        roundPoints.put(player, points);
        if (points == 0){
            roundCompleted.put(player, false);
            return;
        }
        roundCompleted.put(player, true);
    }

    public static int getTrialsSpeed(){
        return 11-trialLevel;
    }

    public static int getTrialLevel(){
        return trialLevel;
    }

    public static void nextTrialLevel(){
        trialLevel++;

        Bukkit.broadcast(Component.text("[")
                .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                .append(Component.text("] It's getting faster!", ColorType.TEXT.getColor())));

        Bukkit.broadcast(Component.text("[")
                .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                .append(Component.text("] " + getTrialsSpeed()+1 + "s --> " + getTrialsSpeed() + "s", ColorType.TEXT.getColor())));
    }

}
