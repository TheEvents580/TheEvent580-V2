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
        if (!Timer.isPaused() && !List.of(TimerEnum.NONE, TimerEnum.STARTING_SOON, TimerEnum.END).contains(Timer.getEnum())){
            Timer.remove1Second();

            BossBar countBossbar = BossbarManager.getBossbar("count");
            assert countBossbar != null;

            BossbarManager.setBossbarProgression(countBossbar, (float) Timer.getSeconds() / Timer.getMaxSeconds());
            BossbarManager.setBossbarText(countBossbar, Component.text("氣到惡",ColorTypeAlt.NO_SHADOW.getColor())
                    .append(Component.text(Timer.getFormatedTimer(), ColorTypeAlt.BOSSBAR.getColor())));
        }
    }
}
