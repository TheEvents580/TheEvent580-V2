package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Announcement implements CommandExecutor {

    public Announcement(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("announcement")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        Component bars = Component.text("------------------------", ColorType.SPECIAL_2.getColor());

        Bukkit.broadcast(bars);
        Bukkit.broadcast(Component.text("⚠ Announcement ⚠", ColorType.SPECIAL_ORANGE.getColor()));

        String message = String.join(" ", strings);

        Bukkit.broadcast(Component.text(message));
        Bukkit.broadcast(bars);

        return true;
    }
}
