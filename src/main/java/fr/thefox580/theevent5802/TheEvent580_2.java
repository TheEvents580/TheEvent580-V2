package fr.thefox580.theevent5802;

import fr.thefox580.theevent5802.utils.Instances;
import fr.thefox580.theevent5802.utils.PluginStart;
import me.clip.placeholderapi.libs.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class TheEvent580_2 extends JavaPlugin {

    private final PluginStart start = new PluginStart(this);
    private final Instances instances = new Instances(this);
    private BukkitAudiences audience;

    @Override
    public void onEnable() {
        // Plugin startup logic

        saveDefaultConfig(); //Saves the config in the plugin folder

        start.createCommands();
        start.createListeners();
        start.createTasks();
        this.audience = BukkitAudiences.create(this);

        this.getLogger().info("TheEvent580's plugin started"); //Send a message on plugin start

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        if (this.audience != null){
            this.audience.close();
            this.audience = null;
        }

        PluginStart.stopTasks();

        this.getLogger().info("TheEvent580's plugin stopped"); //Send a message on plugin stop
    }

    public Instances getInstances(){
        return instances;
    }

    public @NotNull BukkitAudiences audience(){
        if (this.audience == null){
            throw new IllegalStateException("Tried to access Adventure when the plugin was diasbled!");
        }
        return this.audience;
    }

}
