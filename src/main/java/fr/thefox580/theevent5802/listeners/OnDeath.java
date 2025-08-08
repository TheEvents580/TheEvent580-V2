package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.bow_pvp.BowPVP;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.PlayerManager;
import fr.thefox580.theevent5802.utils.Players;
import fr.thefox580.theevent5802.utils.Variables;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Random;

public class OnDeath implements Listener {

    public OnDeath(TheEvent580_2 plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    static String[] killMessage = {"'s game stopped working...", " should uninstall the game.", " didn't know the game.",
            " fell off.", " should go back to studying.", " would be better at Fortnite.", " did not buy enough shares.",
            " did not eat an apple a day.", " blue screened."}; //Set an Array with death messages

    public static String killMessageFinal(){ //Function to choose a random message
        Random random = new Random(); //Set a random number
        int randomNumberInArray = random.nextInt(killMessage.length); //Choose a random number between 0-8
        return killMessage[randomNumberInArray]; //Returns the String for the message
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){ //On player death
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        PlayerManager victimPlayerManager = Players.getPlayerManager(victim);
        PlayerManager killerPlayerManager = Players.getPlayerManager(killer);
        assert victimPlayerManager != null;
        assert killerPlayerManager != null;

        Component componentVictim = Component.translatable("%nox_uuid%" + victim.getUniqueId() + ",true,0,-1,1", "\uD83D\uDC64"); //Setup custom victim head
        TextColor colorVictim = victimPlayerManager.getColorType().getColor(); //Set color of text to white (base for if the player doesn't have a team)
        String teamVictim = victimPlayerManager.getTeam().getIcon(); //Set the tag of the player's team

        event.deathMessage(Component.text("")); //Clear death message

        if (killer == null){ //If player dies to a NPC
            Component message = Component.text("[") //Set custom death message
                    .append(Component.text("☠", ColorType.MC_RED.getColor())) //Set custom death message
                    .append(Component.text("] " + teamVictim, ColorType.TEXT.getColor())) //Set custom death message
                    .append(componentVictim)
                    .append(Component.text(' '+victim.getName(), colorVictim)) //Set custom death message
                    .append(Component.text(killMessageFinal(), ColorType.MC_LIGHT_GRAY.getColor())); //Set custom message

            event.deathMessage(message);
        }
        else {

            Component componentKiller = Component.translatable("%nox_uuid%" + killer.getUniqueId() + ",false,0,-1,1", "\uD83D\uDC64"); //Setup custom killer head
            TextColor colorKiller = killerPlayerManager.getColorType().getColor(); //Set color of text to white (base for if the player doesn't have a team)
            String teamKiller = killerPlayerManager.getTeam().getIcon(); //Set the tag of the player's team

            Component message = Component.text('[') //Setup custom death message
                    .append(Component.text('☠', ColorType.MC_RED.getColor())) /* ☠ */
                    .append(Component.text("] " + teamVictim, ColorType.TEXT.getColor())) //I mean
                    .append(componentVictim) //Add custom victim head to message
                    .append(Component.text(' ' + victim.getName(), colorVictim)) //Add victim's name
                    .append(Component.text(" was killed by " + teamKiller, ColorType.TEXT.getColor())) //Do I need to explain what this does ?
                    .append(componentKiller) //Add custom killer head to message
                    .append(Component.text(' ' + killer.getName(), colorKiller)); //Set custom message

            event.deathMessage(message);
        }
    }

    @EventHandler
    public void onRespawnEvent(PlayerRespawnEvent event){
        Player player = event.getPlayer();

        if (Variables.equals("jeu_condi", 8)){
            BowPVP.respawnPlayer(player);
        }
    }

}

