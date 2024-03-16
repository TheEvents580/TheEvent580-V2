package fr.thefox580.theevent5802.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gameTp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Component message = Component.text('[')
                .append(Component.text("TheEvent580 - Admin", TextColor.color(7, 30, 34), TextDecoration.BOLD))
                .append(Component.text("] This command is only used to teleport ", TextColor.color(255, 255, 255)))
                .append(Component.text("ALL OF THE PLAYERS", TextColor.color(103, 146, 137), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                .append(Component.text(" to the decision crystal, please retry without any arguments !", TextColor.color(255, 255, 255)));

        if (strings.length == 0){
            for (Player player : Bukkit.getOnlinePlayers()){
                if (player.hasPermission("group.spectators")){
                    player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
                    player.setGameMode(GameMode.SPECTATOR);
                } else if (player.hasPermission("group.rouge")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 6.5));
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (player.hasPermission("group.orange")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), -5.5, 251, 6.5));
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (player.hasPermission("group.jaune")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), -5.5, 251, 0.5));
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (player.hasPermission("group.vert")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), -5.5, 251, -5.5));
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (player.hasPermission("group.bleu_clair")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, -5.5));
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (player.hasPermission("group.bleu")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), 6.5, 251, -5.5));
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (player.hasPermission("group.violet")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), 6.5, 251, 0.5));
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (player.hasPermission("group.rose")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), 6.5, 251, 6.5));
                    player.setGameMode(GameMode.ADVENTURE);
                }
            }
            message = Component.text('[')
                    .append(Component.text("TheEvent580 - Admin", TextColor.color(7, 30, 34), TextDecoration.BOLD))
                    .append(Component.text("] This command is only used to teleport ", TextColor.color(255, 255, 255)))
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
