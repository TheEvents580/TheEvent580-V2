package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import java.util.Objects;

public class Mode8 implements Runnable{

    private final TheEvent580_2 plugin;
    private final BukkitTask task;

    public Mode8(TheEvent580_2 plugin, String mode){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);

        if (Objects.equals(mode, "break")){
            Timer.setSeconds(6*60);
            Timer.setMaxSeconds(6*60);
        } else {
            Timer.setSeconds(2*60);
            Timer.setMaxSeconds(2*60);
        }

        Timer.setEnum(Timer.TimerEnum.INTERMISSION);
    }

    @Override
    public void run() {

        if (Timer.getSeconds() % 60 == 0){
            for (Player loopPlayer : Bukkit.getOnlinePlayers()){
                loopPlayer.playSound(loopPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, SoundCategory.VOICE, 2, 1);
            }
        }

        if (Timer.getSeconds() == 60){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"fill 8 253 9 -8 250 -9 air replace polished_deepslate");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"fill 8 247 3 -8 247 3 air replace redstone_block");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"fill 8 247 -3 -8 247 -3 air replace redstone_block");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"fill -3 247 -8 -3 247 8 air replace redstone_block");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"fill 3 247 -8 3 247 8 air replace redstone_block");
        } else if (Timer.getSeconds() == 0) {

            Spectators.readySpectatorsDecision();


            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("group.spectators")) {
                    player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 0.5));
                } else {
                    if (player.hasPermission("group.rouge")) {
                        player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, 6.5));
                    } else if (player.hasPermission("group.orange")) {
                        player.teleport(new Location(Bukkit.getWorld("world"), -5.5, 251, 6.5));
                    } else if (player.hasPermission("group.jaune")) {
                        player.teleport(new Location(Bukkit.getWorld("world"), -5.5, 251, 0.5));
                    } else if (player.hasPermission("group.vert")) {
                        player.teleport(new Location(Bukkit.getWorld("world"), -5.5, 251, -5.5));
                    } else if (player.hasPermission("group.bleu_clair")) {
                        player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 251, -5.5));
                    } else if (player.hasPermission("group.bleu")) {
                        player.teleport(new Location(Bukkit.getWorld("world"), 6.5, 251, -5.5));
                    } else if (player.hasPermission("group.violet")) {
                        player.teleport(new Location(Bukkit.getWorld("world"), 6.5, 251, 0.5));
                    } else if (player.hasPermission("group.rose")) {
                        player.teleport(new Location(Bukkit.getWorld("world"), 6.5, 251, 6.5));
                    }
                    player.setGameMode(GameMode.ADVENTURE);
                }
            }

            new Mode9(plugin);

            task.cancel();
        }
    }
}
