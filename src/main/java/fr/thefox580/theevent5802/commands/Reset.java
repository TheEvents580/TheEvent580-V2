package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Reset implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public Reset(TheEvent580_2 plugin){
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("reset")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender.isOp()) {

            Variables.setVariable("jeu", 0);
            Variables.setVariable("jeu_condi", Game.HUB.getGameCondition());
            Variables.setVariable("jeu_nom", Game.HUB.getName());
            Variables.setVariable("jeu_logo", Game.HUB.getIcon());

            plugin.getInstances().getVariablesDatabase().addVariables();

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -2 300 4 2 86 9 minecraft:red_concrete replace minecraft:light_gray_concrete");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -2 300 4 2 86 9 minecraft:red_stained_glass replace minecraft:light_gray_stained_glass");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -4 300 4 -9 86 9 minecraft:orange_concrete replace minecraft:light_gray_concrete");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -3 300 4 -9 86 9 minecraft:orange_stained_glass replace minecraft:light_gray_stained_glass");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -2 300 3 -9 86 -2 minecraft:yellow_concrete replace minecraft:light_gray_concrete");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -2 300 3 -9 86 -2 minecraft:yellow_stained_glass replace minecraft:light_gray_stained_glass");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -3 300 -3 -9 86 -9 minecraft:lime_concrete replace minecraft:light_gray_concrete");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -3 300 -3 -9 86 -9 minecraft:lime_stained_glass replace minecraft:light_gray_stained_glass");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -2 300 -2 3 86 -9 minecraft:light_blue_concrete replace minecraft:light_gray_concrete");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -2 300 -2 3 86 -9 minecraft:light_blue_stained_glass replace minecraft:light_gray_stained_glass");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill 4 300 -3 9 86 -9 minecraft:blue_concrete replace minecraft:light_gray_concrete");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill 4 300 -3 9 86 -9 minecraft:blue_stained_glass replace minecraft:light_gray_stained_glass");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill 3 300 -2 9 86 3 minecraft:purple_concrete replace minecraft:light_gray_concrete");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill 3 300 -2 9 86 3 minecraft:purple_stained_glass replace minecraft:light_gray_stained_glass");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill 3 300 3 9 86 9 minecraft:pink_concrete replace minecraft:light_gray_concrete");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill 3 300 3 9 86 9 minecraft:pink_stained_glass replace minecraft:light_gray_stained_glass");

            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -8 247 -8 8 247 8 minecraft:air");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fill -8 251 -8 8 251 8 minecraft:air");

            new BukkitRunnable() {
                @Override
                public void run() {
                    for (int x = -8; x < 9; x++){
                        for (int z = -8; z < 9; z++){
                            Location blockLooping = new Location(Bukkit.getWorld("world"), x, 250, z);
                            Material blockUnder = blockLooping.clone().add(0, -1, 0).getBlock().getType();

                            if (blockUnder == Material.GLASS){
                                blockLooping.getBlock().setType(Material.POLISHED_DEEPSLATE_SLAB);
                            }
                        }
                    }
                }
            }.runTaskLater(plugin, 20L);

            for (PlayerManager playerManager : Online.getOnlinePlayers()){

                Player player = playerManager.getOnlinePlayer();
                assert player != null;

                if (playerManager.isPlayer()){
                    Points.resetPoints(player);
                }

                Spawn.tp(player);
            }

            return true;
        }

        return false;
    }
}
