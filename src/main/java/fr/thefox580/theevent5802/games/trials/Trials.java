package fr.thefox580.theevent5802.games.trials;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Trials {

    private static final Map<Player, Integer> roundPoints = new HashMap<>();
    private static final Map<Player, Boolean> roundCompleted = new HashMap<>();
    private static int trialLevel = 1;
    private static int trialPlatform = 1;

    /**
     * Method to call to start the pre-game setup
     * @param plugin The main class of the plugin.
     */
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

    /**
     * @param player The player who may have completed the current trial.
     * @return true if player has completed the trial, false otherise.
     */
    public static boolean hasPlayerCompleted(Player player){
        return roundCompleted.get(player);
    }


    /**
     * @return The number of players who completed the current trial.
     */
    public static int getPlayersCompletedCount(){
        int playersCompleted = 0;
        for (Player player : roundCompleted.keySet()){
            if (roundCompleted.get(player)) playersCompleted++;
        }

        return playersCompleted;
    }

    /**
     * Gets the points obtained by a player.
     * @param player The player who we want to get the points.
     * @return The amount of points the player gained this trial.
     */
    public static int getRoundPoints(Player player){
        return roundPoints.get(player);
    }

    /**
     * Sets the points obtained by a player to `roundPoints` and if points are 0, then sets their completed round to false..
     * @param player The player who got those points.
     * @param points The amount of points this player got.
     */
    public static void setRoundPoints(Player player, int points){
        roundPoints.put(player, points);
        if (points == 0){
            roundCompleted.put(player, false);
            return;
        }
        roundCompleted.put(player, true);
    }

    /**
     * @return The speed of a trial based on the level.
     */
    public static int getTrialsSpeed(){
        return 11-trialLevel;
    }

    /**
     * @return The level of a trial.
     */
    public static int getTrialLevel(){
        return trialLevel;
    }

    /**
     * Automatically sets the platform and announces the next level in chat.
     */
    public static void nextTrialLevel(){
        trialLevel++;

        setPlatform(trialLevel, TheEvent580_2.getPlugin(TheEvent580_2.class));

        Bukkit.broadcast(Component.text("[")
                .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                .append(Component.text("] It's getting faster!", ColorType.TEXT.getColor())));

        Bukkit.broadcast(Component.text("[")
                .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                .append(Component.text("] " + getTrialsSpeed()+1 + "s --> " + getTrialsSpeed() + "s", ColorType.TEXT.getColor())));
    }

    /**
     * Automatically sets the platform and announces the next level in chat.
     * @param platform The level you want the platform to be.
     * @param plugin The main class of the plugin.
     */
    public static void setPlatform(int platform, TheEvent580_2 plugin){
        if (trialPlatform < platform){

            Bukkit.broadcast(Component.text('[', ColorType.TEXT.getColor())
                    .append(Component.text('⚠', ColorType.MC_ORANGE.getColor()))
                    .append(Component.text("] The platform ", ColorType.TEXT.getColor()))
                    .append(Component.text("will be shrinking", ColorType.TEXT.getColor(), TextDecoration.UNDERLINED))
                    .append(Component.text(" in 5 seconds", ColorType.TEXT.getColor())));

            //there is a better way of doing this. but who cares, it probably works

            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:red_stained_glass replace minecraft:gray_concrete");

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:gray_concrete replace minecraft:red_stained_glass");

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:red_stained_glass replace minecraft:gray_concrete");

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:gray_concrete replace minecraft:red_stained_glass");

                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:red_stained_glass replace minecraft:gray_concrete");

                                                    switch (platform){
                                                        case 1 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 134 1001 17 142 966 -19 125 -17");
                                                        case 2 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 124 1001 17 132 966 -19 125 -17");
                                                        case 3 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 114 1001 17 122 966 -19 125 -17");
                                                        case 4 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 104 1001 17 112 966 -19 125 -17");
                                                        default -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 94 1001 17 102 966 -19 125 -17");
                                                    }

                                                    for (int i = Math.abs(trialPlatform - platform); i > 0; i--){
                                                        Bukkit.getOnlinePlayers().forEach(player -> {
                                                            player.teleport(new Location(Bukkit.getWorld("trials"), 0.5, 130, 0.5, 0, 0));
                                                            new BukkitRunnable() {
                                                                @Override
                                                                public void run() {
                                                                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, SoundCategory.VOICE, 2, 1);
                                                                }
                                                            }.runTaskLater(plugin, 2L);
                                                        });
                                                    }

                                                }
                                            }.runTaskLater(plugin, 20L);
                                        }
                                    }.runTaskLater(plugin, 20L);
                                }
                            }.runTaskLater(plugin, 20L);
                        }
                    }.runTaskLater(plugin, 20L);
                }
            }.runTaskLater(plugin, 20L);

        } else if (trialPlatform > platform) {
            Bukkit.broadcast(Component.text('[', ColorType.TEXT.getColor())
                    .append(Component.text('⚠', ColorType.MC_ORANGE.getColor()))
                    .append(Component.text("] The platform ", ColorType.TEXT.getColor()))
                    .append(Component.text("will be growing", ColorType.TEXT.getColor(), TextDecoration.UNDERLINED))
                    .append(Component.text(" in 5 seconds", ColorType.TEXT.getColor())));

            //there is a better way of doing this. but who cares, it probably works

            new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:lime_stained_glass replace minecraft:gray_concrete");

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:gray_concrete replace minecraft:lime_stained_glass");

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:lime_stained_glass replace minecraft:gray_concrete");

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:gray_concrete replace minecraft:lime_stained_glass");

                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 126 -15 -18 126 17 minecraft:lime_stained_glass replace minecraft:gray_concrete");

                                                    switch (platform){
                                                        case 1 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 134 1001 17 142 966 -19 125 -17");
                                                        case 2 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 124 1001 17 132 966 -19 125 -17");
                                                        case 3 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 114 1001 17 122 966 -19 125 -17");
                                                        case 4 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 104 1001 17 112 966 -19 125 -17");
                                                        default -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 94 1001 17 102 966 -19 125 -17");
                                                    }

                                                    for (int i = Math.abs(trialPlatform - platform); i > 0; i--){
                                                        Bukkit.getOnlinePlayers().forEach(player -> {
                                                            player.teleport(new Location(Bukkit.getWorld("trials"), 0.5, 130, 0.5, 0, 0));
                                                            new BukkitRunnable() {
                                                                @Override
                                                                public void run() {
                                                                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_PLACE, SoundCategory.VOICE, 2, 1);
                                                                }
                                                            }.runTaskLater(plugin, 2L);
                                                        });
                                                    }
                                                }
                                            }.runTaskLater(plugin, 20L);
                                        }
                                    }.runTaskLater(plugin, 20L);
                                }
                            }.runTaskLater(plugin, 20L);
                        }
                    }.runTaskLater(plugin, 20L);
                }
            }.runTaskLater(plugin, 20L);
        } else {

            switch (platform){
                case 1 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 134 1001 17 142 966 -19 125 -17");
                case 2 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 124 1001 17 132 966 -19 125 -17");
                case 3 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 114 1001 17 122 966 -19 125 -17");
                case 4 -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 104 1001 17 112 966 -19 125 -17");
                default -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run clone -18 94 1001 17 102 966 -19 125 -17");
            }
        }

        trialPlatform = platform;
    }

    /**
     * Automatically removes and replaces the glass for any trials who need it to be removed.
     * @param plugin The main class of the plugin.
     */

    public static void triggerGlass(TheEvent580_2 plugin){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill -19 133 -16 15 126 18 air replace tinted_glass");

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill -19 133 -16 15 126 18 tinted_glass replace air\"");
            }
        }.runTaskLater(plugin, 10*20L);
    }

}
