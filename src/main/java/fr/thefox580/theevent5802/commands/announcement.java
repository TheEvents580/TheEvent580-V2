package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class announcement implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        Component bars = Component.text("------------------------", ColorType.SPECIAL_2.getColor());

        Bukkit.broadcast(bars);
        Bukkit.broadcast(Component.text("⚠ Announcement ⚠", ColorType.SPECIAL_ORANGE.getColor()));

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
