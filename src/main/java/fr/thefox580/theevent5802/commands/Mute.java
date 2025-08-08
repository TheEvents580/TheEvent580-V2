package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Mute implements CommandExecutor, TabCompleter {

    private static boolean globalMute = false;

    public Mute(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("mute")).setExecutor(this);
    }

    public static boolean isGlobalMute() {
        return globalMute;
    }

    public static void setGlobalMute(boolean globalMute) {
        Mute.globalMute = globalMute;
        if (globalMute){
            Bukkit.broadcast(Component.text("muted"));
        } else {
            Bukkit.broadcast(Component.text("unmuted"));
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
