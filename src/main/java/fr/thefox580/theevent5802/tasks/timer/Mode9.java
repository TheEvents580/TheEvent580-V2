package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.Points;
import fr.thefox580.theevent5802.utils.Timer;
import fr.thefox580.theevent5802.utils.Variables;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Mode9 implements Runnable{

    private final TheEvent580_2 plugin;
    private final BukkitTask task;

    public Mode9(TheEvent580_2 plugin){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);

        Timer.setSeconds(10);
        Timer.setMaxSeconds(10);
        Timer.setEnum(Timer.TimerEnum.PRE_VOTING);
    }

    @Override
    public void run() {

        if (Timer.getSeconds() == 5){
            if (Variables.equals("jeu", 4)){
                Points.setMultiplier(1.5f);
            } else if (Variables.equals("jeu", 6)){
                Points.setMultiplier(2f);
            }
        } else if (Timer.getSeconds() == 0) {
            Variables.setVariable("jeu", (int) Variables.getVariable("jeu") + 1);

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 247 -8 -8 247 8 air");

            new BukkitRunnable() {
                @Override
                public void run() {

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 250 3 -8 250 3 polished_deepslate_slab");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 250 -3 -8 250 -3 polished_deepslate_slab");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill -3 250 -8 -3 250 8 polished_deepslate_slab");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 3 250 -8 3 250 8 polished_deepslate_slab");

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 251 3 -8 251 3 air");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 251 -3 -8 251 -3 air");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill -3 251 -8 -3 251 8 air");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 3 251 -8 3 251 8 air");

                    new Mode3(plugin);

                    task.cancel();

                }
            }.runTaskLater(plugin, 5L);
        }
    }
}
