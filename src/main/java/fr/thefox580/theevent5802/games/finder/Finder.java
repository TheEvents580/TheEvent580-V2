package fr.thefox580.theevent5802.games.finder;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Finder {

    private static final Map<Player, Integer> playersItemFound = new HashMap<>();
    private static final List<String> itemSets = List.of("Desert Set", "Mesa Set", "Forest Set", "Swamp Set", "Mining Set", "Ancient Set", "Savana Set", "Redstone Set");
    private static int itemSetSelected = 0;

    public static int getTotalItemsFound(){
        int totalItemsFound = 0;
        for (Integer playerItemsFound : playersItemFound.values()){
            totalItemsFound += playerItemsFound;
        }
        return totalItemsFound;
    }

    public static int getPlayerItemsFound(Player player){
        return playersItemFound.get(player);
    }

    public static void newRandomItemSet(){
        itemSetSelected = new Random().nextInt(itemSets.size());
    }

    public static String getCurrentItemSet(){
        return itemSets.get(itemSetSelected);
    }

}
