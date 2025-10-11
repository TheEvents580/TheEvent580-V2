package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.parkour.Parkour;
import fr.thefox580.theevent5802.games.trials.Trials;
import fr.thefox580.theevent5802.utils.Game;
import fr.thefox580.theevent5802.utils.Players;
import fr.thefox580.theevent5802.utils.Timer;
import fr.thefox580.theevent5802.utils.Voting;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;

public class Mode4 implements Runnable{

    private final TheEvent580_2 plugin;
    private final BukkitTask task;
    private final Game game;

    public Mode4(TheEvent580_2 plugin, Game game){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);
        this.game = game;

        Timer.setSeconds(10);
        Timer.setMaxSeconds(10);
        Timer.setEnum(Timer.TimerEnum.TP_TO_GAME);
    }

    @Override
    public void run() {

        if (Timer.getSeconds() == 5){
            Bukkit.broadcast(Voting.getAfterVotingMessage());

            Bukkit.getOnlinePlayers().forEach(player -> player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20*5+6, 2, false, false, false)));
        }

        else if (Timer.getSeconds() == 1){

            Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                    Component.text('轉'),
                    Component.text(""),
                    Title.Times.times(Duration.ofMillis(500), Duration.ofSeconds(4), Duration.ofMillis(500)))));
        }

        else if (Timer.getSeconds() == 0){

            Bukkit.getOnlinePlayers().forEach(player -> {
                player.removePotionEffect(PotionEffectType.LEVITATION);
                PersistentDataContainer playerContainer = player.getPersistentDataContainer();

                if (Players.isPlayer(player)){
                    playerContainer.set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, true);
                } else {
                    playerContainer.set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, false);
                }
            });

            switch (game){
                case TRIALS -> Trials.startPreGame(plugin);
                case PARKOUR -> Parkour.startPreGame(plugin);
                /*case FINDER ->
                case TAG ->
                case MULTILAP ->
                case BUILD_MASTERS ->
                case ARMS_RACE ->
                case BOW_PVP ->*/
            }

            task.cancel();
        }

    }
}
