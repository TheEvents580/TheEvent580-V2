package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.Timer;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class Mode7 implements Runnable{

    private final TheEvent580_2 plugin;
    private final BukkitTask task;

    public Mode7(TheEvent580_2 plugin){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);

        Timer.setSeconds(10);
        Timer.setMaxSeconds(10);
        Timer.setEnum(Timer.TimerEnum.TP_TO_GAME);
    }

    @Override
    public void run() {

        if (Timer.getSeconds() == 5){
        }

        else if (Timer.getSeconds() == 0){

            //new Mode8(plugin);

            task.cancel();
        }

    }
}
