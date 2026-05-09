package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

            for (int npcID = 8; npcID < 18; npcID++){
                NPC npc = CitizensAPI.getNPCRegistry().getById(npcID);
                npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("QM", "RPxcif+B1vYlZgxQKcZaMdJMlDfOLuHMsG+bHLEUulkOVMZIRXcEIp/YGtgPHcEjZ8tqVDPE6E+mFqnpY03JPsz1aOrWvr3D4dx7ZEMtQE/drjD6c5tFdkPt8xJi4BP79xQsQJtSKZsCn0xRUfDKugmn0i0Waxaw5UFvGDkQbhYWC9lGwNcblIYqube/L/XAtnOiDKQ8zT0hdqsuJ5Ny5AveW3hxBv221/gmwrrPwdgYX5egApxirooaoB1vVULCfd7opBpSs7IGyp4jM5PlrU8TGUMDX7/oHh4GRG9f3NG2ccbftcg05eXxok7QVBHXMnP5o3r0V71l0tvj5VyqN/uiJDWqcmaLsCt2Qeu/3hMclTxGEm3IhWWXFWcWfkATfZ6hg+Kuk7bLBNcTExwj+veu5MIm8vTLp2MXkYCZ3zv6RzLolSyM7jGG3wAczGpY01HpaIMgkOA1SUMlmu1+pRJkTHDXPTC3XGyNaY4WzgkbBgvPOD0Z1052dtG9enbRLzhCpGNxV5N1nMiod6objHdXL4MUtiuT29ANcyHm7WHSm/f5cJ8qtrF9e3VYOnXECBGBym6DiTHY1V4nSnkCtXv8rQyrpo5XJdEzCDMyLkvxqbUj9+YTz9MkAqEngqZkpCzukXbEn3pIveXMD+oMV6ncRg27qrDPKphHuMFk5TM=", "ewogICJ0aW1lc3RhbXAiIDogMTYzNjEzNTYwNzkwOCwKICAicHJvZmlsZUlkIiA6ICJkZTE0MGFmM2NmMjM0ZmM0OTJiZTE3M2Y2NjA3MzViYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTUlRlYW0iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NkOGYwMjUwMTc4NWQxNmIwOTNmNGQ2NTk2OThhNzlmY2Q1MGEwMDk0ZjUzMzQ5ZDQ4ZDY2MzJmYzZlMTMyZCIKICAgIH0KICB9Cn0=");
                npc.setName(MiniMessage.miniMessage().serialize(Component.text("Top " + (npcID-7) + " - TBD", ColorType.SUBTEXT.getColor())));
            }

            return true;
        }

        return false;
    }
}
