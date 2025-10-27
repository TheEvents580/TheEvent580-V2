package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.finder.FinderSets;
import fr.thefox580.theevent5802.utils.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Test implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public Test(TheEvent580_2 plugin){
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("test")).setExecutor(this);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player) {

            FinderSets.setGoldenLocked(false);

            new BukkitRunnable() {
                @Override
                public void run() {

                    FinderSets.setGoldenLocked(true);

                }
            }.runTaskLater(plugin, 10*20L);

        }

        return true;
    }
}
