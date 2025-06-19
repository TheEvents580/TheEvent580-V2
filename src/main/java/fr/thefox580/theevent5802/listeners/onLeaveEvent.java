package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class onLeaveEvent implements Listener {
    // Next 4 lines of code : Setting up Adventure

    private final TheEvent580_2 plugin;

    public onLeaveEvent(TheEvent580_2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerLeavesEvent(PlayerQuitEvent event){ //When a player leaves the server
        Player player = event.getPlayer(); //Get the player
        TextColor color = ColorType.TEXT.getColor(); //Set color of text to white (base for if the player doesn't have a team)
        Component component = Component.translatable("%nox_uuid%"+player.getUniqueId()+",true,0,-1,1","\uD83D\uDC64"); //Setup custom player head

        plugin.getConfig().set("online_players", plugin.getConfig().getInt("online_players") - 1);

        if (player.hasPermission("group.spectators")){ //If the player is a spectator
            color = ColorType.MC_GRAY.getColor(); //Set the color to dark gray

        }
        else if (player.hasPermission("group.rouge")) { //If the player is in red team
            color = ColorType.MC_RED.getColor(); //Set the color to red

        }
        else if (player.hasPermission("group.orange")) { //If the player is in orange team
            color = ColorType.MC_ORANGE.getColor(); //Set the color to orange
        }
        else if (player.hasPermission("group.jaune")) { //If the player is in yellow team
            color = ColorType.MC_YELLOW.getColor(); //Set the color to yellow

        }
        else if (player.hasPermission("group.vert")) { //If the player is in lime / green team
            color = ColorType.MC_LIME.getColor(); //Set the color to lime / green

        }
        else if (player.hasPermission("group.bleu_clair")) { //If the player is in light blue team
            color = ColorType.MC_AQUA.getColor(); //Set the color to light blue

        }
        else if (player.hasPermission("group.bleu")) { //If the player is in blue team
            color = ColorType.MC_BLUE.getColor(); //Set the color to blue

        }
        else if (player.hasPermission("group.violet")) { //If the player is in purple team
            color = ColorType.MC_PURPLE.getColor(); //Set the color to purple

        }
        else if (player.hasPermission("group.rose")) { //If the player is in pink team
            color = ColorType.MC_PINK.getColor(); //Set the color to pink

        }
        else { //Else if the player isn't in a team
            for (Player p : Bukkit.getOnlinePlayers()) { //Loop all players
                if (p.hasPermission("theevent580.staff")) { //If the looped player is in the staff
                    Component newPlayer = Component.text('[')
                            .append(Component.text("TheEvent580 - Staff", ColorType.TITLE.getColor(), TextDecoration.BOLD)) //Setup
                            .append(Component.text("] Player \" //Send a message to staff" + player.getName() +
                                    " has left the server but isn't assigned to a color (1st time playing / New color" +
                                    " not assigned ? / Server not whitelisted ?)", ColorType.TEXT.getColor())); //Setup part 2
                    p.sendMessage(newPlayer);
                }
            }
        }
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
