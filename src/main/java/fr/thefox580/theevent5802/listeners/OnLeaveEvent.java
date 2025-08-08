package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OnLeaveEvent implements Listener {

    public OnLeaveEvent(TheEvent580_2 plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerLeavesEvent(PlayerQuitEvent event){ //When a player leaves the server
        Player player = event.getPlayer(); //Get the player

        ScoreboardManager.deleteBoard(player);

        PlayerManager playerManager;

        if (Players.isPlayer(player)){
            playerManager = Players.getPlayerManager(player);
            if (Timer.getSeconds() <= 30 && Objects.equals(Variables.getVariable("jeu_condi"), 0)){
                Timer.setPaused(true);
            }
        } else {
            playerManager = Spectators.getPlayerManager(player);
        }
        assert playerManager != null;

        TextColor color = playerManager.getColorType().getColor(); //Set color of text to white (base for if the player doesn't have a team)
        Component component = Component.translatable("%nox_uuid%"+player.getUniqueId()+",true,0,-1,1","\uD83D\uDC64"); //Setup custom player head
        Component message = getPlayerLeaveComponent(component, player, color);//Setup leaving message

        event.quitMessage(message);
    }

    @NotNull
    private static Component getPlayerLeaveComponent(Component component, Player player, TextColor color) { //Setup leaving message
        return Component.text('[') //Setup part 1
                .append(Component.text('-', ColorType.MC_RED.getColor())) //Setup part 2
                .append(Component.text("] ", ColorType.TEXT.getColor())) //Setup part 3
                .append(component) //Add custom player head to the message
                .append(Component.text(" "+ player.getName(), color)); //Add player's name to the message
    }
}
