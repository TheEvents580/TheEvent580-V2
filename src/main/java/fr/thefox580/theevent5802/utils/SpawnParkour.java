package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SpawnParkour {

    private static List<Map.Entry<Player, Integer>> getTop10Parkour(){

        Map<Player, Integer> unsortedPlayers = new HashMap<>();

        for (PlayerManager playerManager : Online.getOnlinePlayers()){

            if (playerManager.getParkourCompletion() > 0){
                unsortedPlayers.put(playerManager.getOnlinePlayer(), playerManager.getParkourCompletion());
            }
        }

        return getEntries(unsortedPlayers);
    }

    @NotNull
    private static List<Map.Entry<Player, Integer>> getEntries(Map<Player, Integer> unsortedPlayers) {
        List<Map.Entry<Player, Integer>> sorted = new ArrayList<>(unsortedPlayers.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(10).toList());

        while (sorted.size() < 10){
            Map<Player, Integer> nonePlayer = new HashMap<>();
            nonePlayer.put(Bukkit.getPlayer("TheFox580"), -1);
            sorted.add(nonePlayer.entrySet().stream().toList().getFirst());
        }

        return sorted;
    }

    public static void updateText(){

        List<Map.Entry<Player, Integer>> top10 = getTop10Parkour();

        TextDisplay parkourLeaderboard = null;

        for (Entity entity : Objects.requireNonNull(Bukkit.getWorld("world")).getNearbyEntities(new Location(Bukkit.getWorld("world"), 307.5, 67, 306.5), 3, 10, 3)){
            if (entity instanceof TextDisplay textDisplay){
                parkourLeaderboard = textDisplay;
                break;
            }
        }

        if (parkourLeaderboard != null){
            Component text = Component.text("Parkour completions :\n", ColorType.SUBTEXT.getColor());

            int place = 1;
            for (Map.Entry<Player, Integer> playerStats : top10){

                String placement;

                switch (place){
                    case 1 -> placement = "1st";
                    case 2 -> placement = "2nd";
                    case 3 -> placement = "3rd";
                    default -> placement = place+"th";
                }

                if (playerStats.getValue() > 0){

                    PlayerManager playerManager = Online.getPlayerManager(playerStats.getKey());

                    text = text.append(Component.text("\n" + placement + " : ", ColorType.SUBTEXT.getColor()))
                            .append(Component.text(playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()))
                            .append(Component.text(playerStats.getKey().getName(), playerManager.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" : " + playerStats.getValue() + " completions", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false));

                } else {
                    text = text.append(Component.text("\n" + placement + " : N/A", ColorType.SUBTEXT.getColor()));
                }
                place++;
            }

            parkourLeaderboard.text(text);

        }


    }


}
