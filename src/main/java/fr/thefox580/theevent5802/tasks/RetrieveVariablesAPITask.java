package fr.thefox580.theevent5802.tasks;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.PluginStart;
import fr.thefox580.theevent5802.utils.VariablesDatabase;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class RetrieveVariablesAPITask implements Runnable {

    private final TheEvent580_2 plugin;
    private final VariablesDatabase variablesDatabase;


    public RetrieveVariablesAPITask(TheEvent580_2 plugin){
        this.plugin = plugin;
        this.variablesDatabase = plugin.getInstances().getVariablesDatabase();
        BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, 0, 5*60*20L);
        PluginStart.addTaskToList(task);
    }

    @Override
    public void run() {
        variablesDatabase.getVariables(false); // Retrieves the variables
        variablesDatabase.addVariables(); // Saves the variables

        plugin.getLogger().info("Variables have been correctly saved!");
    }
}
