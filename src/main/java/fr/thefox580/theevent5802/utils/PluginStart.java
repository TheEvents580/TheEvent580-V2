package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.commands.*;
import fr.thefox580.theevent5802.listeners.*;
import fr.thefox580.theevent5802.tasks.beaconBeamTask;

import java.util.Objects;

public class PluginStart {

    private final TheEvent580_2 plugin;

    public PluginStart(TheEvent580_2 plugin){
        this.plugin = plugin;
    }

    public void createCommands(){
        Objects.requireNonNull(plugin.getCommand("starttp")).setExecutor(new startTp()); //Add the /starttp command to the plugin
        Objects.requireNonNull(plugin.getCommand("gametp")).setExecutor(new gameTp()); //Add the /gametp command to the plugin
        Objects.requireNonNull(plugin.getCommand("damage")).setExecutor(new damage(plugin)); //Add the /damage command to the plugin
        Objects.requireNonNull(plugin.getCommand("pronouns")).setExecutor(new pronouns()); //Add the /pronouns command to the plugin
        Objects.requireNonNull(plugin.getCommand("showpronouns")).setExecutor(new showPronouns(plugin)); //Add the /pronouns command to the plugin
        Objects.requireNonNull(plugin.getCommand("announcement")).setExecutor(new announcement()); //Add the /announcement command to the plugin
        Objects.requireNonNull(plugin.getCommand("minecraftle")).setExecutor(new minecraftle(plugin)); //Add the /minecraftle command to the plugin
        Objects.requireNonNull(plugin.getCommand("craft")).setExecutor(new craft()); //Add the /craft command to the plugin
        Objects.requireNonNull(plugin.getCommand("customcolor")).setExecutor(new customColor(plugin)); //Add the /customcolor command to the plugin
        Objects.requireNonNull(plugin.getCommand("stats")).setExecutor(new stats(plugin)); //Add the /stats command to the plugin
        Objects.requireNonNull(plugin.getCommand("db")).setExecutor(new db(plugin)); //Add the /stats command to the plugin
        Objects.requireNonNull(plugin.getCommand("toast")).setExecutor(new toast()); //Add the /toast command to the plugin
    }

    public void createListeners(){
        plugin.getServer().getPluginManager().registerEvents(new onJoinEvent(plugin), plugin); //Registers the join message on player join to the plugin
        plugin.getServer().getPluginManager().registerEvents(new onLeaveEvent(plugin), plugin); //Registers the leave message on player leave to the plugin
        plugin.getServer().getPluginManager().registerEvents(new onDeathEvent(), plugin); //Registers the death message on player death to the plugin
        plugin.getServer().getPluginManager().registerEvents(new onMessage(plugin), plugin); //Registers the custom message on player message to the plugin
        plugin.getServer().getPluginManager().registerEvents(new onGUIClick(plugin), plugin); //Registers when a players click in an inventory to the plugin
        plugin.getServer().getPluginManager().registerEvents(new onDamage(plugin), plugin); //Registers when damage is dealt
        plugin.getServer().getPluginManager().registerEvents(new onInventoryClose(plugin), plugin); //Registers when an inventory is closed
        plugin.getServer().getPluginManager().registerEvents(new onItemDrop(), plugin); //Registers when an item is dropped
        plugin.getServer().getPluginManager().registerEvents(new onBlockPlace(), plugin); //Registers when a block is placed in the world
        plugin.getServer().getPluginManager().registerEvents(new onEntityDamageByEntity(), plugin); //Registers when an entity gets damages by another entity
    }

    public void createTasks(){
        new beaconBeamTask().runTaskTimer(plugin , 0L, 100L);
    }

}
