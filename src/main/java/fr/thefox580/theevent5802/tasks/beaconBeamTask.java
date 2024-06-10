package fr.thefox580.theevent5802.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.Random;

public class beaconBeamTask extends BukkitRunnable {
    @Override
    public void run() {
        Material currentColor = Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(310, 64, 323).getType();

        Material[] possibleColors = {Material.RED_STAINED_GLASS, Material.ORANGE_STAINED_GLASS,
                Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS,  Material.LIGHT_BLUE_STAINED_GLASS, Material.BLUE_STAINED_GLASS,
                Material.PURPLE_STAINED_GLASS, Material.PINK_STAINED_GLASS};

        Random randomNumber = new Random();

        Material randomBlock = possibleColors[randomNumber.nextInt(possibleColors.length)];

        while (randomBlock == currentColor){
            randomBlock = possibleColors[randomNumber.nextInt(possibleColors.length)];
        }

        Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(310, 64, 323).setType(randomBlock);

    }
}
