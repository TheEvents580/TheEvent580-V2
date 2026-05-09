package fr.thefox580.theevent5802;

import fr.thefox580.theevent5802.utils.Instances;
import fr.thefox580.theevent5802.utils.PluginStart;
import org.bukkit.plugin.java.JavaPlugin;

public final class TheEvent580_2 extends JavaPlugin {

    private final PluginStart start = new PluginStart(this);
    private final Instances instances = new Instances(this);

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveDefaultConfig(); //Saves the config in the plugin folder

        start.createCommands();
        start.createListeners();
        start.createTasks();

        instances.initialize();

        this.getLogger().info("TheEvent580's plugin started"); //Send a message on plugin start

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        PluginStart.stopTasks();

        instances.getStatsDatabase().shutdown();
        instances.getVariablesDatabase().shutdown();
        instances.getAdvancementsDatabase().shutdown();

        this.getLogger().info("TheEvent580's plugin stopped"); //Send a message on plugin stop
    }

    public Instances getInstances(){
        return instances;
    }

}
