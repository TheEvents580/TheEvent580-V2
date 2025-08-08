package fr.thefox580.theevent5802.tasks;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Online;
import fr.thefox580.theevent5802.utils.PlayerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class AnnouncePlayers implements Runnable{

    private final String type;
    private final List<PlayerManager> players;
    private final BukkitTask task;

    public AnnouncePlayers(String type, List<PlayerManager> players, TheEvent580_2 plugin){
        this.type = type;
        this.players = players;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 2*20L);
    }

    @Override
    public void run() {

        if (players.isEmpty()){
            task.cancel();
        } else {

            Component title = getTitle();

            PlayerManager player = players.removeFirst();

            Objects.requireNonNull(player.getOnlinePlayer()).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 2*20, 0, false, false, false));

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(title,
                        Component.text(player.getTeam().getIcon() + " ", ColorType.TEXT.getColor())
                                .append(Component.text(player.getName(), player.getColorType().getColor())), Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(3), Duration.ofSeconds(2))));
            }
        }
    }

    private @NotNull Component getTitle() {
        Component title = Component.text("TITLE TYPE WAS WRONG!!!");

        title = switch (type) {
            case "new_players" -> Component.text("Announcing new players :", ColorType.RAINBOW.getColor());
            case "players" -> Component.text("Next, announcing players :", ColorType.RAINBOW_WAVE.getColor());
            case "spectators" -> Component.text("And here are your spectators :", ColorType.RAINBOW_ITER.getColor());
            default -> title;
        };
        return title;
    }
}
