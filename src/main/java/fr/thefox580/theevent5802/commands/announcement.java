package fr.thefox580.theevent5802.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class announcement implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Component bars = Component.text("------------------------", TextColor.color(226, 240, 147));

        Bukkit.broadcast(bars);
        Bukkit.broadcast(Component.text("⚠ Announcement ⚠", TextColor.color(140, 215, 29)));

        String message = String.join(" ", strings);

        if (commandSender instanceof Player sender){
            Bukkit.broadcast(Component.text(message).append(Component.text(" - Sent by " +  sender.getName())));
        } else {
            Bukkit.broadcast(Component.text(message).append(Component.text(" - Sent by Console")));
        }
        Bukkit.broadcast(bars);

        return true;
    }
}
