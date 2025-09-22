package fr.thefox580.theevent5802.games.finder;

import fr.thefox580.theevent5802.utils.Online;
import fr.thefox580.theevent5802.utils.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Finder {

    private static final Map<Player, Map<Material, Integer>> playersItemFound = new HashMap<>();

    public static void startGame(){
        for (PlayerManager player : Online.getOnlinePlayers()){
            Map<Material, Integer> mapList = new HashMap<>();
            for (String set : FinderSets.getAllItemSets()){
                for (Material material : FinderSets.getItemSet(set)){
                    mapList.put(material, 0);
                }
            }
            playersItemFound.put(player.getOnlinePlayer(), mapList);
        }

    }

    public static int getTotalItemsFound(){
        int totalItemsFound = 0;
        for (Player key : playersItemFound.keySet()){
            for (Integer itemFound : playersItemFound.get(key).values()){
                totalItemsFound += itemFound;
            }
        }
        return totalItemsFound;
    }

    public static int getPlayerItemsFound(Player player){
        int playerItemsFound = 0;
        for (Integer itemFound : playersItemFound.get(player).values()){
            playerItemsFound += itemFound;
        }
        return playerItemsFound;
    }

    public static int getMaterialsFoundPlayer(Player player, Material material){
        return playersItemFound.get(player).get(material);
    }

    public static int getMaterialsFound(Material material){
        int totalItemsFound = 0;
        for (Player key : playersItemFound.keySet()){
            totalItemsFound += playersItemFound.get(key).get(material);
        }
        return totalItemsFound;
    }

    public static String getCurrentItemSetName(){
        return FinderSets.getCurrentItemSetName();
    }

}
