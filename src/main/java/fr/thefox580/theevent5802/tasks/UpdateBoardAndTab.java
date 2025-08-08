package fr.thefox580.theevent5802.tasks;

import fr.mrmicky.fastboard.adventure.FastBoard;
import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.PluginStart;
import fr.thefox580.theevent5802.utils.ScoreboardManager;
import fr.thefox580.theevent5802.utils.TabListManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class UpdateBoardAndTab implements Runnable {

    public UpdateBoardAndTab(TheEvent580_2 plugin){
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 2L);
        PluginStart.addTaskToList(task);
    }

    @Override
    public void run() {
        for (FastBoard board : ScoreboardManager.getBoards().values()){
            ScoreboardManager.updateBoard(board);
        }
        TabListManager.sendCustomTab();
    }
}
