package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import me.clip.placeholderapi.libs.kyori.adventure.bossbar.BossBar;
import me.clip.placeholderapi.libs.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class RemoveTime implements Runnable {

    public RemoveTime(TheEvent580_2 plugin){
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);
        PluginStart.addTaskToList(task);
    }

    @Override
    public void run() {
        if (!Timer.isPaused() && !List.of(Timer.TimerEnum.NONE, Timer.TimerEnum.STARTING_SOON, Timer.TimerEnum.END).contains(Timer.getEnum()) && Timer.getSeconds() > -1){
            Timer.remove1Second();

            EventTime.add1Second();
            TotalEventTime.add1Second();

            BossBar countBossbar = BossbarManager.getBossbar("count");
            if (countBossbar != null){
                BossbarManager.setBossbarProgression(countBossbar, (float) Timer.getSeconds() / Timer.getMaxSeconds());
                BossbarManager.setBossbarText(countBossbar, Component.text("氣到惡",ColorTypeAlt.NO_SHADOW.getColor())
                        .append(Component.text(Timer.getFormatedTimer(), ColorTypeAlt.BOSSBAR.getColor())));
            }
        }
    }
}
