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

public class OnLeave implements Listener {

    public OnLeave(TheEvent580_2 plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerLeavesEvent(PlayerQuitEvent event){ //When a player leaves the server
        Player player = event.getPlayer(); //Get the player

        ScoreboardManager.deleteBoard(player);

        PlayerManager playerManager = Online.getPlayerManager(player);

        if (Players.isPlayer(player)){
            if (Timer.getSeconds() <= 30 && Variables.equals("jeu_condi", 0)){
                Timer.setPaused(true);
            }
        }

        TextColor color = playerManager.getColorType().getColor();

        if (color == playerManager.getTeam().getColorType().getColor()){
            if (playerManager.isAdmin()){
                color = Team.ADMIN.getColorType().getColor();
            } else if (playerManager.isStaff()){
                color = Team.STAFF.getColorType().getColor();
            }
        }
        Component component = Component.translatable("%nox_uuid%"+player.getUniqueId()+",true,0,-1,1","\uD83D\uDC64"); //Setup custom player head
        Component message = getPlayerLeaveComponent(component, player, color); //Setup leaving message

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
