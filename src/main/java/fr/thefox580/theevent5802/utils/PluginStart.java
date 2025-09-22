package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.commands.*;
import fr.thefox580.theevent5802.commands.TimerCommand;
import fr.thefox580.theevent5802.games.finder.FinderCommand;
import fr.thefox580.theevent5802.games.finder.FinderListeners;
import fr.thefox580.theevent5802.listeners.*;
import fr.thefox580.theevent5802.tasks.*;
import fr.thefox580.theevent5802.tasks.timer.RemoveTime;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public record PluginStart(TheEvent580_2 plugin) {

    private static final List<BukkitTask> tasks = new ArrayList<>();

    public void createCommands() {
        new Start(plugin);
        new GameTp(plugin);
        new Damage(plugin);
        new Pronouns(plugin);
        new ShowPronouns(plugin);
        new Announcement(plugin);
        new Minecraftle(plugin);
        new Craft(plugin);
        new CustomColor(plugin);
        new Stats(plugin);
        new DB(plugin);
        new Test(plugin);
        new FinderCommand(plugin);
        new Version(plugin);
        new VariablesCommand(plugin);
        new ReloadCfg(plugin);
        new TimerCommand(plugin);
        new Spawn(plugin);
        new Mute(plugin);
        new Leaderboard(plugin);
        new SpecMenu(plugin);
    }

    public void createListeners() {
        new OnJoinEvent(plugin);
        new OnLeaveEvent(plugin);
        new OnMessage(plugin);
        new OnGUIClick(plugin);
        new OnDamage(plugin);
        new OnInventoryClose(plugin);
        new OnItemInteract(plugin);
        new OnWorldInteract(plugin);
        new OnServerListPing(plugin);
        new OnPlayerMove(plugin);
        new FinderListeners(plugin);
    }

    public void createTasks() {
        new BeaconBeamTask(plugin);
        new RetrieveVariablesAPITask(plugin);
        new UpdateBoardAndTab(plugin);
        new RemoveTime(plugin);
        new SpawnTask(plugin);
    }

    public static void stopTasks() {
        tasks.forEach(BukkitTask::cancel);
    }

    public static void addTaskToList(BukkitTask task) {
        tasks.add(task);
    }

}
