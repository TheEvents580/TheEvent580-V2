package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class customColor implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public customColor(TheEvent580_2 plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        String playerName = strings[0];
        OfflinePlayer player = Bukkit.getPlayer(playerName);

        if (player != null){
            FileConfiguration config = this.plugin.getConfig();
            config.set("color."+ player.getUniqueId(), strings[1]);
        } else {
            Bukkit.getLogger().warning("Player " + playerName + " doesn't exist");
        }

        return true;
    }
}
