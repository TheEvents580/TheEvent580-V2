package fr.thefox580.theevent5802;

import fr.thefox580.theevent5802.commands.*;
import fr.thefox580.theevent5802.listeners.*;
import fr.thefox580.theevent5802.tasks.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class TheEvent580_2 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getLogger().info("TheEvent580's plugin started"); //Send a message on plugin start

        saveDefaultConfig(); //Saves the config in the plugin folder

        getCommand("starttp").setExecutor(new startTp()); //Add the /starttp command to the plugin
        getCommand("gametp").setExecutor(new gameTp()); //Add the /gametp command to the plugin
        getCommand("damage").setExecutor(new damage(this)); //Add the /damage command to the plugin
        getCommand("pronouns").setExecutor(new pronouns()); //Add the /pronouns command to the plugin
        getCommand("showpronouns").setExecutor(new showPronouns(this)); //Add the /pronouns command to the plugin
        getCommand("announcement").setExecutor(new announcement()); //Add the /announcement command to the plugin
        getCommand("minecraftle").setExecutor(new minecraftle(this)); //Add the /minecraftle command to the plugin
        getCommand("craft").setExecutor(new craft()); //Add the /craft command to the plugin
        getCommand("toast").setExecutor(new toast()); //Add the /toast command to the plugin
        getCommand("customcolor").setExecutor(new customColor(this)); //Add the /customcolor command to the plugin

        getServer().getPluginManager().registerEvents(new onJoinEvent(this), this); //Registers the join message on player join to the plugin
        getServer().getPluginManager().registerEvents(new onLeaveEvent(this), this); //Registers the leave message on player leave to the plugin
        getServer().getPluginManager().registerEvents(new onDeathEvent(), this); //Registers the death message on player death to the plugin
        getServer().getPluginManager().registerEvents(new onMessage(this), this); //Registers the custom message on player message to the plugin
        getServer().getPluginManager().registerEvents(new onGUIClick(this), this); //Registers when a players click in an inventory to the plugin
        getServer().getPluginManager().registerEvents(new onDamage(this), this); //Registers when damage is dealt
        getServer().getPluginManager().registerEvents(new onInventoryClose(this), this); //Registers when an inventory is closed
        getServer().getPluginManager().registerEvents(new onItemDrop(), this); //Registers when an item is dropped
        getServer().getPluginManager().registerEvents(new onBlockPlace(), this); //Registers when a block is placed in the world
        getServer().getPluginManager().registerEvents(new onEntityDamageByEntity(), this); //Registers when an entity gets damages by another entity

        BukkitTask beaconBeamTask = new beaconBeamTask().runTaskTimer(this , 0L, 100L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("TheEvent580's plugin stopped"); //Send a message on plugin stop
    }
}
