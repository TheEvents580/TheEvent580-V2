package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Colors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class startTp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Component message = Component.text('[')
                .append(Component.text("TheEvent580 - Admin", Colors.getColor(ColorType.TITLE), TextDecoration.BOLD))
                .append(Component.text("] This command is only used to teleport ", Colors.getColor(ColorType.TEXT)))
                .append(Component.text("ALL OF THE PLAYERS", Colors.getColor(ColorType.SPECIAL_3), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                .append(Component.text(" to the decision crystal, please retry without any arguments !", Colors.getColor(ColorType.TEXT)));

        if (strings.length == 0){
            for (Player player : Bukkit.getOnlinePlayers()){
                player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
                if (player.hasPermission("group.spectators")){
                    player.setGameMode(GameMode.SPECTATOR);
                } else {
                    player.setGameMode(GameMode.ADVENTURE);
                }
            }
            message = Component.text('[')
                    .append(Component.text("TheEvent580 - Admin", Colors.getColor(ColorType.TITLE), TextDecoration.BOLD))
                    .append(Component.text("] Teleported ", Colors.getColor(ColorType.TEXT)))
                    .append(Component.text("ALL PLAYERS", Colors.getColor(ColorType.SPECIAL_3), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                    .append(Component.text(" to the decision crystal !", Colors.getColor(ColorType.TEXT)));
        }
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(message);
        }
        for (Player loopedPlayer : Bukkit.getOnlinePlayers()){
            if (loopedPlayer.hasPermission("theevent580.staff")){
                loopedPlayer.sendMessage(message);
            }
        }
        return true;
    }
}
