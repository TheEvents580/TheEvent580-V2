package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GameTp implements CommandExecutor {

    public GameTp(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("gametp")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {

        Component message = Component.text('[')
                .append(Component.text("TheEvent580 - Admin", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] This command is only used to teleport ", ColorType.TEXT.getColor()))
                .append(Component.text("ALL OF THE PLAYERS", ColorType.SPECIAL_3.getColor(), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                .append(Component.text(" to the decision crystal, please retry without any arguments !", ColorType.TEXT.getColor()));

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
                    .append(Component.text("TheEvent580 - Admin", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                    .append(Component.text("] This command is only used to teleport ", ColorType.TEXT.getColor()))
                    .append(Component.text("ALL PLAYERS", ColorType.SPECIAL_3.getColor(), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                    .append(Component.text(" to the decision crystal !", ColorType.TEXT.getColor()));
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
