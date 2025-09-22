package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class BlockGame {

    private static TheEvent580_2 plugin;

    public BlockGame(TheEvent580_2 plugin){
        BlockGame.plugin = plugin;
    }

    public static void block(Material blockMaterial, PlayerManager playerManager){

        switch (blockMaterial){
            case Material.RED_CONCRETE -> blockRed(playerManager);
            case Material.ORANGE_CONCRETE -> blockOrange(playerManager);
            case Material.YELLOW_CONCRETE -> blockYellow(playerManager);
            case Material.LIME_CONCRETE -> blockLime(playerManager);
            case Material.LIGHT_BLUE_CONCRETE -> blockLightBlue(playerManager);
            case Material.BLUE_CONCRETE -> blockBlue(playerManager);
            case Material.PURPLE_CONCRETE -> blockPurple(playerManager);
            case Material.PINK_CONCRETE -> blockPink(playerManager);
        }
    }

    private static void blockRed(PlayerManager playerManager){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill 3 247 8 -3 247 3 redstone_block replace air");

        Bukkit.broadcast((Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] ", ColorType.TEXT.getColor()))
                .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor(), TextDecoration.BOLD))
                .append(Component.text(" has been blocked by ", ColorType.TEXT.getColor()))
                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                .append(Component.text("!", ColorType.TEXT.getColor()))));

        for (PlayerManager player : Players.getOnlinePlayerList()){
            if (Objects.requireNonNull(player.getOnlinePlayer()).getLocation().clone().add(0, -2, 0).getBlock().getType() == Material.RED_CONCRETE){
                player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill 3 251 8 -3 253 3 minecraft:polished_deepslate");
            }
        }.runTaskLater(plugin, 2L);
    }

    private static void blockOrange(PlayerManager playerManager){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill -3 247 8 -8 247 3 redstone_block replace air");

        Bukkit.broadcast((Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] ", ColorType.TEXT.getColor()))
                .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor(), TextDecoration.BOLD))
                .append(Component.text(" has been blocked by ", ColorType.TEXT.getColor()))
                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                .append(Component.text("!", ColorType.TEXT.getColor()))));

        for (PlayerManager player : Players.getOnlinePlayerList()){
            if (Objects.requireNonNull(player.getOnlinePlayer()).getLocation().clone().add(0, -2, 0).getBlock().getType() == Material.ORANGE_CONCRETE){
                player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill -3 251 8 -8 253 3 minecraft:polished_deepslate");
            }
        }.runTaskLater(plugin, 2L);
    }

    private static void blockYellow(PlayerManager playerManager){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill -8 247 3 -3 247 -3 redstone_block replace air");

        Bukkit.broadcast((Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] ", ColorType.TEXT.getColor()))
                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor(), TextDecoration.BOLD))
                .append(Component.text(" has been blocked by ", ColorType.TEXT.getColor()))
                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                .append(Component.text("!", ColorType.TEXT.getColor()))));

        for (PlayerManager player : Players.getOnlinePlayerList()){
            if (Objects.requireNonNull(player.getOnlinePlayer()).getLocation().clone().add(0, -2, 0).getBlock().getType() == Material.YELLOW_CONCRETE){
                player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill -8 251 3 -3 253 -3 minecraft:polished_deepslate");
            }
        }.runTaskLater(plugin, 2L);
    }

    private static void blockLime(PlayerManager playerManager){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill -8 247 -3 -3 247 -8 redstone_block replace air");

        Bukkit.broadcast((Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] ", ColorType.TEXT.getColor()))
                .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor(), TextDecoration.BOLD))
                .append(Component.text(" has been blocked by ", ColorType.TEXT.getColor()))
                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                .append(Component.text("!", ColorType.TEXT.getColor()))));

        for (PlayerManager player : Players.getOnlinePlayerList()){
            if (Objects.requireNonNull(player.getOnlinePlayer()).getLocation().clone().add(0, -2, 0).getBlock().getType() == Material.LIME_CONCRETE){
                player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill -8 251 -3 -3 253 -8 minecraft:polished_deepslate");
            }
        }.runTaskLater(plugin, 2L);
    }

    private static void blockLightBlue(PlayerManager playerManager){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill -3 247 -8 3 247 -3 redstone_block replace air");

        Bukkit.broadcast((Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] ", ColorType.TEXT.getColor()))
                .append(Component.text(Game.MULTILAP.getName(), Game.MULTILAP.getColorType().getColor(), TextDecoration.BOLD))
                .append(Component.text(" has been blocked by ", ColorType.TEXT.getColor()))
                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                .append(Component.text("!", ColorType.TEXT.getColor()))));

        for (PlayerManager player : Players.getOnlinePlayerList()){
            if (Objects.requireNonNull(player.getOnlinePlayer()).getLocation().clone().add(0, -2, 0).getBlock().getType() == Material.LIGHT_BLUE_CONCRETE){
                player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill -3 251 -8 3 253 -3 minecraft:polished_deepslate");
            }
        }.runTaskLater(plugin, 2L);
    }

    private static void blockBlue(PlayerManager playerManager){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill 3 247 -8 8 247 -3 redstone_block replace air");

        Bukkit.broadcast((Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] ", ColorType.TEXT.getColor()))
                .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                .append(Component.text(" has been blocked by ", ColorType.TEXT.getColor()))
                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                .append(Component.text("!", ColorType.TEXT.getColor()))));

        for (PlayerManager player : Players.getOnlinePlayerList()){
            if (Objects.requireNonNull(player.getOnlinePlayer()).getLocation().clone().add(0, -2, 0).getBlock().getType() == Material.BLUE_CONCRETE){
                player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill 3 251 -8 8 253 -3 minecraft:polished_deepslate");
            }
        }.runTaskLater(plugin, 2L);
    }

    private static void blockPurple(PlayerManager playerManager){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill 8 247 -3 3 247 3 redstone_block replace air");

        Bukkit.broadcast((Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] ", ColorType.TEXT.getColor()))
                .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor(), TextDecoration.BOLD))
                .append(Component.text(" has been blocked by ", ColorType.TEXT.getColor()))
                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                .append(Component.text("!", ColorType.TEXT.getColor()))));

        for (PlayerManager player : Players.getOnlinePlayerList()){
            if (Objects.requireNonNull(player.getOnlinePlayer()).getLocation().clone().add(0, -2, 0).getBlock().getType() == Material.PURPLE_CONCRETE){
                player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill 8 251 -3 3 253 3 minecraft:polished_deepslate");
            }
        }.runTaskLater(plugin, 2L);
    }

    private static void blockPink(PlayerManager playerManager){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill 8 247 3 3 247 8 redstone_block replace air");

        Bukkit.broadcast((Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] ", ColorType.TEXT.getColor()))
                .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor(), TextDecoration.BOLD))
                .append(Component.text(" has been blocked by ", ColorType.TEXT.getColor()))
                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                .append(Component.text("!", ColorType.TEXT.getColor()))));

        for (PlayerManager player : Players.getOnlinePlayerList()){
            if (Objects.requireNonNull(player.getOnlinePlayer()).getLocation().clone().add(0, -2, 0).getBlock().getType() == Material.PINK_CONCRETE){
                player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:overworld run fill 8 251 3 3 253 8 minecraft:polished_deepslate");
            }
        }.runTaskLater(plugin, 2L);
    }

}
