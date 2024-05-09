package fr.thefox580.theevent5802;

import fr.thefox580.theevent5802.commands.*;
import fr.thefox580.theevent5802.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheEvent580_2 extends JavaPlugin {

    //private Audience adventure;

    //public @NonNull Audience adventure() {
    //    if(this.adventure == null) {
    //        throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
    //    }
    //    return this.adventure;
    //}

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

        getServer().getPluginManager().registerEvents(new onJoinEvent(this), this); //Registers the join message on player join to the plugin
        getServer().getPluginManager().registerEvents(new onLeaveEvent(this), this); //Registers the leave message on player leave to the plugin
        getServer().getPluginManager().registerEvents(new onDeathEvent(), this); //Registers the death message on player death to the plugin
        getServer().getPluginManager().registerEvents(new onMessage(this), this); //Registers the custom message on player message to the plugin
        getServer().getPluginManager().registerEvents(new onGUIClick(this), this); //Registers when a players click in an inventory to the plugin
        getServer().getPluginManager().registerEvents(new onNoxesiumJoinEvent(), this); //Registers when a player joins the server to remove trident / boat collisions
        getServer().getPluginManager().registerEvents(new onDamage(this), this); //Registers when damage is dealt

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("TheEvent580's plugin stopped"); //Send a message on plugin stop
    }
}
