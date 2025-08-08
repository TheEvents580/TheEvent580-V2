package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Damage implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public Damage(TheEvent580_2 plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("damage")).setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        FileConfiguration config = plugin.getConfig();

        config.set("damage_enabled", !config.getBoolean("damage_enabled"));

        if (config.getBoolean("damage_enabled")){
            commandSender.sendMessage("Damage has been enabled");
        } else {
            commandSender.sendMessage("Damage has been disabled");
        }

        return false;
    }
}
