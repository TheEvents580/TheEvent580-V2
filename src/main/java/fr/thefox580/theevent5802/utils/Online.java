package fr.thefox580.theevent5802.utils;

import java.util.ArrayList;
import java.util.List;

public class Online {

    private static final List<PlayerManager> onlinePlayers = new ArrayList<>();

    public static boolean addPlayer(PlayerManager playerManager){
        if (!onlinePlayers.contains(playerManager)){
            onlinePlayers.add(playerManager);
            return true;
        }
        return false;
    }

    public static boolean removePlayer(PlayerManager playerManager){
        return onlinePlayers.remove(playerManager);
    }

    public static List<PlayerManager> getOnlinePlayers(){
        return onlinePlayers;
    }

}
