package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.commands.utils.ColorType;
import fr.thefox580.theevent5802.commands.utils.Colors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class onDeathEvent implements Listener {

    static String[] killMessage = {"'s game stopped working...", " should uninstall the game.", " didn't know the game.",
            " fell off.", " should go back to studying.", " would be better at Fortnite.", " did not buy enough shares.",
            " ate an apple.", " blue screened."}; //Set an Array with death messages

    public static String killMessageFinal(){ //Function to choose a random message
        Random random = new Random(); //Set a random number
        int randomNumberInArray = random.nextInt(killMessage.length+1); //Choose a random number between 0-9
        return killMessage[randomNumberInArray]; //Returns the String for the message
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event){ //On player death
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        Component componentVictim = Component.translatable("%nox_uuid%" + victim.getUniqueId() + ",true,0,-1,1", "\uD83D\uDC64"); //Setup custom victim head
        TextColor colorVictim = Colors.getColor(ColorType.TEXT); //Set color of text to white (base for if the player doesn't have a team)
        char teamVictim = 'タ'; //Set the tag of the player's team

        if (victim.hasPermission("group.rouge")) { //If the player is in team red
            colorVictim = Colors.getColor(ColorType.MC_RED); //Set color of text to red
            teamVictim = 'ラ'; //Set the tag of the player's team

        } else if (victim.hasPermission("group.orange")) { //If the player is in team orange
            colorVictim = Colors.getColor(ColorType.MC_ORANGE); //Set color of text to orange
            teamVictim = 'ャ'; //Set the tag of the player's team

        } else if (victim.hasPermission("group.jaune")) { //If the player is in team yellow
            colorVictim = Colors.getColor(ColorType.MC_YELLOW); //Set color of text to yellow
            teamVictim = 'ギ'; //Set the tag of the player's team

        } else if (victim.hasPermission("group.vert")) { //If the player is in team lime / green
            colorVictim = Colors.getColor(ColorType.MC_LIME); //Set color of text to lime / green
            teamVictim = '画'; //Set the tag of the player's team

        } else if (victim.hasPermission("group.bleu_clair")) { //If the player is in team light blue
            colorVictim = Colors.getColor(ColorType.MC_AQUA); //Set color of text to light blue
            teamVictim = '動'; //Set the tag of the player's team

        } else if (victim.hasPermission("group.bleu")) { //If the player is in team blue
            colorVictim = Colors.getColor(ColorType.MC_BLUE); //Set color of text to blue
            teamVictim = '像'; //Set the tag of the player's team

        } else if (victim.hasPermission("group.violet")) { //If the player is in team purple
            colorVictim = Colors.getColor(ColorType.MC_PURPLE); //Set color of text to purple
            teamVictim = 'の'; //Set the tag of the player's team

        } else if (victim.hasPermission("group.rose")) { //If the player is in team pink
            colorVictim = Colors.getColor(ColorType.MC_PINK); //Set color of text to pink
            teamVictim = '目'; //Set the tag of the player's team
        }
        event.setDeathMessage(""); //Clear death message

        if (killer == null){ //If player dies to a NPC
            Component message = Component.text("[") //Set custom death message
                    .append(Component.text("☠", Colors.getColor(ColorType.MC_RED))) //Set custom death message
                    .append(Component.text("] " + teamVictim, Colors.getColor(ColorType.TEXT))) //Set custom death message
                    .append(componentVictim)
                    .append(Component.text(' '+victim.getName(), colorVictim)) //Set custom death message
                    .append(Component.text(killMessageFinal(), Colors.getColor(ColorType.MC_LIGHT_GRAY))); //Set custom message

            for (Player loopPlayer : Bukkit.getOnlinePlayers()){
                loopPlayer.sendMessage(message);
            }
        }
        else {

            Component componentKiller = Component.translatable("%nox_uuid%" + killer.getUniqueId() + ",false,0,-1,1", "\uD83D\uDC64"); //Setup custom killer head
            TextColor colorKiller = TextColor.color(255, 255, 255); //Set color of text to white (base for if the player doesn't have a team)
            char teamKiller = 'タ'; //Set the tag of the player's team

            if (killer.hasPermission("group.rouge")) { //If the player is in team red
                colorKiller = Colors.getColor(ColorType.MC_RED); //Set color of text to red
                teamKiller = 'ラ'; //Set the tag of the player's team

            } else if (killer.hasPermission("group.orange")) { //If the player is in team orange
                colorKiller = Colors.getColor(ColorType.MC_ORANGE); //Set color of text to orange
                teamKiller = 'ャ'; //Set the tag of the player's team

            } else if (killer.hasPermission("group.jaune")) { //If the player is in team yellow
                colorKiller = Colors.getColor(ColorType.MC_YELLOW); //Set color of text to yellow
                teamKiller = 'ギ'; //Set the tag of the player's team

            } else if (killer.hasPermission("group.vert")) { //If the player is in team lime / green
                colorKiller = Colors.getColor(ColorType.MC_LIME); //Set color of text to lime / green
                teamKiller = '画'; //Set the tag of the player's team

            } else if (killer.hasPermission("group.bleu_clair")) { //If the player is in team light blue
                colorKiller = Colors.getColor(ColorType.MC_AQUA); //Set color of text to light blue
                teamKiller = '動'; //Set the tag of the player's team

            } else if (killer.hasPermission("group.bleu")) { //If the player is in team blue
                colorKiller = Colors.getColor(ColorType.MC_BLUE); //Set color of text to blue
                teamKiller = '像'; //Set the tag of the player's team

            } else if (killer.hasPermission("group.violet")) { //If the player is in team purple
                colorKiller = Colors.getColor(ColorType.MC_PURPLE); //Set color of text to purple
                teamKiller = 'の'; //Set the tag of the player's team

            } else if (killer.hasPermission("group.rose")) { //If the player is in team pink
                colorKiller = Colors.getColor(ColorType.MC_PINK); //Set color of text to pink
                teamKiller = '目'; //Set the tag of the player's team

            }

            Component message = Component.text('[') //Setup custom death message
                    .append(Component.text('☠', Colors.getColor(ColorType.MC_RED))) /* ☠ */
                    .append(Component.text("] " + teamVictim, Colors.getColor(ColorType.TEXT))) //I mean
                    .append(componentVictim) //Add custom victim head to message
                    .append(Component.text(' ' + victim.getName(), colorVictim)) //Add victim's name
                    .append(Component.text(" was killed by " + teamKiller, Colors.getColor(ColorType.TEXT))) //Do I need to explain what this does ?
                    .append(componentKiller) //Add custom killer head to message
                    .append(Component.text(' ' + killer.getName(), colorKiller)); //Set custom message

            for (Player loopPlayer : Bukkit.getOnlinePlayers()){
                loopPlayer.sendMessage(message);
            }
        }
    }
}

