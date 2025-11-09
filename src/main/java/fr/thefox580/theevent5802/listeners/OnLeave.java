package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class OnLeave implements Listener {

    private final TheEvent580_2 plugin;

    public OnLeave(TheEvent580_2 plugin){
        this.plugin = plugin;
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

        plugin.getInstances().getAdvancementAPI().getProgressionTab().hideTab(player);
        plugin.getInstances().getAdvancementAPI().getCustomTab().hideTab(player);

        event.quitMessage(getPlayerLeaveComponent(playerManager));
    }

    @NotNull
    private static Component getPlayerLeaveComponent(PlayerManager playerManager) { //Setup join message

        return Component.text('[') //Setup part 1
                .append(Component.text('-', ColorType.MC_RED.getColor())) //Setup part 2
                .append(Component.text("] ", ColorType.TEXT.getColor())) //Setup part 3
                .append(playerManager.playerComponent()); //Add custom player head to the message
    }
}
