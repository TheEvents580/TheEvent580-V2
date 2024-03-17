package fr.thefox580.theevent5802.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
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
                .append(Component.text("TheEvent580 - Admin", TextColor.color(21, 89, 102), TextDecoration.BOLD))
                .append(Component.text("] This command is only used to teleport ", TextColor.color(255, 255, 255)))
                .append(Component.text("ALL OF THE PLAYERS", TextColor.color(103, 146, 137), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                .append(Component.text(" to the decision crystal, please retry without any arguments !", TextColor.color(255, 255, 255)));

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
                    .append(Component.text("TheEvent580 - Admin", TextColor.color(21, 89, 102), TextDecoration.BOLD))
                    .append(Component.text("] Teleported ", TextColor.color(255, 255, 255)))
                    .append(Component.text("ALL PLAYERS", TextColor.color(103, 146, 137), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                    .append(Component.text(" to the decision crystal !", TextColor.color(255, 255, 255)));
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
