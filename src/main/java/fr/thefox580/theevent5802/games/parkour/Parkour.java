package fr.thefox580.theevent5802.games.parkour;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.PlayerManager;
import fr.thefox580.theevent5802.utils.Players;
import fr.thefox580.theevent5802.utils.Points;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Parkour {
    private static final Map<Player, Integer> playerPoints = new HashMap<>();
    private static final Map<Player, Float> playerMults = new HashMap<>();
    private static final Map<Player, Integer> playerMainLevel = new HashMap<>();
    private static final Map<Player, Integer> playerSubLevel = new HashMap<>();

    public static void startPreGame(TheEvent580_2 plugin){
        playerPoints.clear();
        playerMults.clear();
        playerMainLevel.clear();
        playerSubLevel.clear();

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            playerPoints.put(playerManager.getOnlinePlayer(), Math.round(120 * Points.getMultiplier()));
            playerMults.put(playerManager.getOnlinePlayer(), 1f);
            playerMainLevel.put(playerManager.getOnlinePlayer(), 1);
            playerSubLevel.put(playerManager.getOnlinePlayer(), 1);
        }
    }

    public static Component getMainLevel(Player player){
        return switch (playerMainLevel.get(player)) {
            case 1 -> Component.text("Easy", ColorType.MC_LIME.getColor(), TextDecoration.BOLD);
            case 2 -> Component.text("Medium", ColorType.MC_ORANGE.getColor(), TextDecoration.BOLD);
            case 3 -> Component.text("Hard", ColorType.MC_RED.getColor(), TextDecoration.BOLD);
            case 4 -> Component.text("Extreme", ColorType.MC_DARK_RED.getColor(), TextDecoration.BOLD);
            default -> Component.text("None", ColorType.TEXT.getColor(), TextDecoration.BOLD);
        };
    }

    public static Component getSubLevel(Player player){
        return Component.text(playerSubLevel.get(player), ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false);
    }

    public static int getPlayerPoints(Player player){
        return playerPoints.get(player);
    }

    public static float getPlayerMult(Player player){
        return playerMults.get(player);
    }

}
