package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.*;

public class Points implements CommandExecutor, TabCompleter {

    private static TheEvent580_2 plugin;
    private static float multiplier = 1f;

    public Points(TheEvent580_2 plugin){
        Points.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("points")).setExecutor(this);
    }

    public static void initialisePoints(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        playerContainer.set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, 0);
        playerContainer.set(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER, 0);

        Object totalPoints = Variables.getVariable("total_points."+player.getUniqueId());

        if (totalPoints != null){
            playerContainer.set(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER, (int) totalPoints);
            return;
        }

        playerContainer.set(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER, 0);
    }

    public static void resetPoints(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        int totalPoints = getTotalPoints(player);
        playerContainer.set(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER, totalPoints-Math.max(0, getPoints(player)));

        playerContainer.set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, 0);
        playerContainer.set(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER, 0);
    }

    public static List<Map.Entry<Player, Integer>> getTopEvent(){

        Map<Player, Integer> unsortedPlayers = new HashMap<>();

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            unsortedPlayers.put(playerManager.getOnlinePlayer(), getPoints(Objects.requireNonNull(playerManager.getOnlinePlayer())));
        }

        return getTop(unsortedPlayers, 3);

    }

    public static List<Map.Entry<Player, Integer>> getTopGame(){

        Map<Player, Integer> unsortedPlayers = new HashMap<>();

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            unsortedPlayers.put(playerManager.getOnlinePlayer(), getGamePoints(Objects.requireNonNull(playerManager.getOnlinePlayer())));
        }

        return getTop(unsortedPlayers, 3);

    }

    @NotNull
    public static List<Map.Entry<Player, Integer>> getTop(Map<Player, Integer> unsortedPlayers, int limit) {

        if (limit > 0) {

            List<Map.Entry<Player, Integer>> sorted = new ArrayList<>(unsortedPlayers.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(limit).toList());

            while (sorted.size() < limit) {
                Map<Player, Integer> nonePlayer = new HashMap<>();
                nonePlayer.put(Bukkit.getPlayer("TheFox580"), -1);
                sorted.add(nonePlayer.entrySet().stream().toList().getFirst());
            }

            return sorted;
        }

        return new ArrayList<>(unsortedPlayers.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).toList());

    }

    public static int getPoints(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        if (playerContainer.get(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER) != null){
            return Objects.requireNonNull(playerContainer.get(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER));
        }

        return 0;
    }

    public static int getGamePoints(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        if (playerContainer.get(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER) != null){
            return Objects.requireNonNull(playerContainer.get(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER));
        }

        return 0;
    }

    public static int getTotalPoints(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        if (playerContainer.get(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER) != null){
            return Objects.requireNonNull(playerContainer.get(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER));
        }

        return 0;
    }

    public static int getTotalPointsPreEvent(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        Integer total_points = playerContainer.get(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER);
        if (total_points == null){
           total_points = 0;
        }

        Integer total_points_event = playerContainer.get(new NamespacedKey(plugin, "total_points_event"), PersistentDataType.INTEGER);
        if (total_points_event == null){
            total_points_event = 0;
        }

        return total_points-total_points_event;
    }

    public static void addPoints(Player player, int points){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        Integer current_points = playerContainer.get(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER);
        if (current_points == null){
            current_points = 0;
        }
        playerContainer.set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, current_points+points);


        Integer current_total_points_event = playerContainer.get(new NamespacedKey(plugin, "total_points_event"), PersistentDataType.INTEGER);
        if (current_total_points_event == null){
            current_total_points_event = 0;
        }
        playerContainer.set(new NamespacedKey(plugin, "total_points_event"), PersistentDataType.INTEGER, current_total_points_event+points);


        Integer current_total_points = playerContainer.get(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER);
        if (current_total_points == null){
            current_total_points = 0;
        }
        playerContainer.set(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER, current_total_points+points);
    }

    public static void removePoints(Player player, int points){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        Integer current_points = playerContainer.get(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER);
        if (current_points == null){
            current_points = 0;
        }
        playerContainer.set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, current_points-points);


        Integer current_total_points_event = playerContainer.get(new NamespacedKey(plugin, "total_points_event"), PersistentDataType.INTEGER);
        if (current_total_points_event == null){
            current_total_points_event = 0;
        }
        playerContainer.set(new NamespacedKey(plugin, "total_points_event"), PersistentDataType.INTEGER, current_total_points_event-points);


        Integer current_total_points = playerContainer.get(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER);
        if (current_total_points == null){
            current_total_points = 0;
        }
        playerContainer.set(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER, current_total_points-points);
    }

    public static void addGamePoints(Player player, int points){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        Integer current_game_points = playerContainer.get(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER);
        if (current_game_points == null){
            current_game_points = 0;
        }
        playerContainer.set(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER, current_game_points+points);

        player.playSound(player, "custom:coins", SoundCategory.VOICE, 1f, 1f);
    }

    public static void removeGamePoints(Player player, int points){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        Integer current_game_points = playerContainer.get(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER);
        if (current_game_points == null){
            current_game_points = 0;
        }
        playerContainer.set(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER, current_game_points-points);
    }

    public static void addGamePointsToOverallPoints(Player player){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        Integer current_points = playerContainer.get(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER);
        if (current_points == null){
            current_points = 0;
        }

        Integer game_points = playerContainer.get(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER);
        if (game_points == null){
            game_points = 0;
        }

        playerContainer.set(new NamespacedKey(plugin, "points"), PersistentDataType.INTEGER, current_points+game_points);
        playerContainer.set(new NamespacedKey(plugin, "game_points"), PersistentDataType.INTEGER, 0);


    }

    /**
     * WARNING!!!
     * PLEASE ONLY USE THIS TO HARD SET THE PLAYER'S TOTAL POINTS
     * <p>
     * IT IS ADVISED TO ONLY USE THIS FUNCTION TO FIX TOTAL POINTS FROM A PLAYER!!!
     */
    public static int setTotalPoints(Player player, int points){
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        playerContainer.set(new NamespacedKey(plugin, "total_points"), PersistentDataType.INTEGER, points);

        return points;
    }

    public static void setMultiplier(float newMultiplier){
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.showTitle(Title.title(
                    Component.text("New Multiplier !", ColorType.MC_RED.getColor(), TextDecoration.BOLD),
                    Component.text("Multiplier went from " + multiplier + "必 to " + newMultiplier + "必", ColorType.TEXT.getColor()),
                    Title.Times.times(
                            Duration.ofMillis(500),
                            Duration.ofSeconds(3),
                            Duration.ofMillis(500)
                    )));

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, SoundCategory.VOICE, 2, 0.5f);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, SoundCategory.VOICE, 2, 1f);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, SoundCategory.VOICE, 2, 1.5f);

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, SoundCategory.VOICE, 2, 2f);
                                        }
                                    }.runTaskLater(plugin, 1L);
                                }
                            }.runTaskLater(plugin, 1L);
                        }
                    }.runTaskLater(plugin, 1L);

                }
            }.runTaskLater(plugin, 1L);
        });
        multiplier = newMultiplier;
    }

    public static float getMultiplier(){
        return multiplier;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(strings[1]);

        if (Players.isPlayer(player)){
            PlayerManager playerManager = Players.getPlayerManager(player);
            assert playerManager != null;

            switch (strings[0]) {
                case "get" -> commandSender.sendMessage(Component.text(playerManager.getTeam().getIcon() + " ")
                            .append(Component.text(Objects.requireNonNull(player.getPlayer()).getName(), playerManager.getTeam().getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text( " has " + formatPoints(Points.getPoints(player.getPlayer())) + " 工", ColorType.TEXT.getColor())));
                case "add" -> {
                    Points.addPoints(Objects.requireNonNull(player.getPlayer()), Integer.parseInt(strings[2]));
                    commandSender.sendMessage(Component.text(playerManager.getTeam().getIcon() + " ")
                            .append(Component.text(player.getPlayer().getName(), playerManager.getTeam().getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text( " has " + formatPoints(Points.getPoints(player.getPlayer())) + " 工", ColorType.TEXT.getColor())));
                }
                case "remove" -> {
                    Points.removePoints(Objects.requireNonNull(player.getPlayer()), Integer.parseInt(strings[2]));
                    commandSender.sendMessage(Component.text(playerManager.getTeam().getIcon() + " ")
                        .append(Component.text(player.getPlayer().getName(), playerManager.getTeam().getColorType().getColor(), TextDecoration.BOLD))
                        .append(Component.text( " has " + formatPoints(Points.getPoints(player.getPlayer())) + " 工", ColorType.TEXT.getColor())));
                }
                case "set_total" -> commandSender.sendMessage(Component.text( "Set" + player.getName() + "'s total points to : " + formatPoints(Points.setTotalPoints(Objects.requireNonNull(player.getPlayer()), Integer.parseInt(strings[2])))));
            }
            return true;
        }

        return false;
    }

    public static String formatPoints(int points){
        String finalPoints;

        if (points <= 999){
            finalPoints = String.valueOf(points);
        } else {
            String last3 = String.valueOf(points).substring(String.valueOf(points).length() - 3);
            if (points > 999999){
                int setPoints1 = String.valueOf(points).length() - 6;
                int setPoints2 = String.valueOf(points).length() - 3;
                finalPoints = String.valueOf(points).substring(0, setPoints1);
                if (points > 99999999){
                    finalPoints = finalPoints + "," + String.valueOf(points).substring(3, setPoints2);
                } else if (points > 9999999){
                    finalPoints = finalPoints + "," + String.valueOf(points).substring(2, setPoints2);
                } else {
                    finalPoints = finalPoints + "," + String.valueOf(points).substring(1, setPoints2);
                }
                finalPoints = finalPoints + "," + last3;
            } else {
                int setPoints = String.valueOf(points).length() - 3;
                finalPoints = String.valueOf(points).substring(0, setPoints);
                finalPoints = finalPoints + "," + last3;
            }
        }

        return finalPoints;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        List<String> tab = new ArrayList<>();

        switch (args.length) {
            case 1:
                tab.add("add");
                tab.add("remove");
                tab.add("get");
                tab.add("set_total");

                break;
            case 2:
                for (OfflinePlayer loopPlayer : Bukkit.getOnlinePlayers()) {
                    tab.add(loopPlayer.getName());
                }
                break;
            case 3:
                if (!args[0].equals("get")){
                    for (int points = 1; points < 100000; points++){
                        tab.add(String.valueOf(points));
                    }
                }
        }
        return tab.stream().filter(completion -> completion.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).toList();
    }

}
