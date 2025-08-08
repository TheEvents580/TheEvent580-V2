package fr.thefox580.theevent5802.tasks;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.PluginStart;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;
import java.util.Random;

public class BeaconBeamTask implements Runnable {


    public BeaconBeamTask(TheEvent580_2 plugin){
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 5*20L);
        PluginStart.addTaskToList(task);
    }

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
