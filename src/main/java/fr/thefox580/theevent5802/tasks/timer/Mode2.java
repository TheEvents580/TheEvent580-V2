package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.Objects;

public class Mode2 implements Runnable {

    private final TheEvent580_2 plugin;
    private final BukkitTask task;

    public Mode2(TheEvent580_2 plugin){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);

        Timer.setSeconds(35);
        Timer.setMaxSeconds(35);
        Timer.setEnum(Timer.TimerEnum.SHOW_GAMES);
    }

    @Override
    public void run() {
        if (Timer.getSeconds() == 30){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                        ColorType.RAINBOW.getColor()), Component.text(""),
                        Title.Times.times(Duration.ofMillis(500), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] This Episode's minigames are : ", ColorType.TEXT.getColor())));
            }

        } else if (Timer.getSeconds() == 28){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                                ColorType.RAINBOW.getColor()),
                        Component.text(Game.TRIALS.getIcon(),ColorType.NO_SHADOW.getColor())
                                .append(Component.text(" Trials", Game.TRIALS.getColorType().getColor())),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                        .append(Component.text("Trials", Game.TRIALS.getColorType().getColor())));
            }

        } else if (Timer.getSeconds() == 25){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                                ColorType.RAINBOW.getColor()),
                        Component.text(Game.PARKOUR.getIcon(),ColorType.NO_SHADOW.getColor())
                                .append(Component.text(" Parkour", Game.PARKOUR.getColorType().getColor())),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                        .append(Component.text("Parkour", Game.PARKOUR.getColorType().getColor())));
            }

        } else if (Timer.getSeconds() == 22){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                                ColorType.RAINBOW.getColor()),
                        Component.text(Game.FINDER.getIcon(),ColorType.NO_SHADOW.getColor())
                                .append(Component.text(" Finder", Game.FINDER.getColorType().getColor()))
                                .append(Component.text(" [NEW]", ColorType.RAINBOW.getColor())),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                        .append(Component.text("Finder", Game.FINDER.getColorType().getColor()))
                        .append(Component.text(" [NEW]", ColorType.RAINBOW.getColor())));
            }

        } else if (Timer.getSeconds() == 19){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                                ColorType.RAINBOW.getColor()),
                        Component.text(Game.TAG.getIcon(),ColorType.NO_SHADOW.getColor())
                                .append(Component.text(" Tag", Game.TAG.getColorType().getColor())),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                        .append(Component.text("Tag", Game.TAG.getColorType().getColor())));
            }

        } else if (Timer.getSeconds() == 16){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                                ColorType.RAINBOW.getColor()),
                        Component.text(Game.SPLEEF.getIcon(),ColorType.NO_SHADOW.getColor())
                                .append(Component.text(" Multilap", Game.SPLEEF.getColorType().getColor()))
                                .append(Component.text(" [NEW]", ColorType.RAINBOW.getColor())),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                        .append(Component.text("Spleef", Game.SPLEEF.getColorType().getColor()))
                        .append(Component.text(" [NEW]", ColorType.RAINBOW.getColor())));
            }

        } else if (Timer.getSeconds() == 13){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                                ColorType.RAINBOW.getColor()),
                        Component.text(Game.BUILD_MASTERS.getIcon(),ColorType.NO_SHADOW.getColor())
                                .append(Component.text(" Build Masters", Game.BUILD_MASTERS.getColorType().getColor())),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                        .append(Component.text("Build Masters", Game.BUILD_MASTERS.getColorType().getColor())));
            }

        } else if (Timer.getSeconds() == 10){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                                ColorType.RAINBOW.getColor()),
                        Component.text(Game.ARMS_RACE.getIcon(),ColorType.NO_SHADOW.getColor())
                                .append(Component.text(" Arms Race", Game.ARMS_RACE.getColorType().getColor())),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                        .append(Component.text("Arms Race", Game.ARMS_RACE.getColorType().getColor())));
            }

        } else if (Timer.getSeconds() == 7){

            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showTitle(Title.title(Component.text("This Episode's minigames are :",
                                ColorType.RAINBOW.getColor()),
                        Component.text(Game.BOW_PVP.getIcon(),ColorType.NO_SHADOW.getColor())
                                .append(Component.text(" Bow PVP", Game.BOW_PVP.getColorType().getColor())),
                        Title.Times.times(Duration.ofMillis(0), Duration.ofSeconds(5), Duration.ofSeconds(0))));
                loopPlayer.getOnlinePlayer().sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] ", ColorType.TEXT.getColor()))
                        .append(Component.text("Bow PVP", Game.BOW_PVP.getColorType().getColor())));
            }

        } else if (Timer.getSeconds() == 4){
            for (PlayerManager loopPlayer : Online.getOnlinePlayers()){
                for (PlayerManager player : Players.getOnlinePlayerList()){
                    if (loopPlayer.getUniqueId() != player.getUniqueId()){
                        Objects.requireNonNull(loopPlayer.getOnlinePlayer()).showPlayer(plugin, Objects.requireNonNull(player.getOnlinePlayer()));
                    }
                }
            }
        } else if (Timer.getSeconds() == 0){
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
