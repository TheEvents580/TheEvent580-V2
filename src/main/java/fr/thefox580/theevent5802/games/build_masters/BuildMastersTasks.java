package fr.thefox580.theevent5802.games.build_masters;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.tasks.timer.Mode7;
import fr.thefox580.theevent5802.utils.*;
import me.clip.placeholderapi.libs.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;

public class BuildMastersTasks {

    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 60){
                    Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                            Component.text(Game.BUILD_MASTERS.getIcon(), ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor())),
                            Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                            Title.Times.times(
                                    Duration.ofMillis(500),
                                    Duration.ofSeconds(5),
                                    Duration.ofMillis(500)
                            )
                    )));
                } else if (Timer.getSeconds() == 40){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (Timer.getSeconds() == 35){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] Your goal is to be the fastest to build the 10 builds before you run out of time!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 30){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] Once the builds are completed, it will switch automatically.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 25){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] You can get all of the blocks you need in the mall, available with the portal behind you.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 20){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] Like in real life, everything has a cost!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 15){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] In this game, you'll have to pay blocks, with your own time!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 10){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] A Concrete block costs 1 second, a Terracotta block costs 2 seconds and a Copper block costs 3 seconds.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.VOICE, 0.25f, 1));

                    Players.getOnlinePlayerList().forEach(playerManager -> {
                        Player player = playerManager.getOnlinePlayer();

                        if (player != null){
                            player.setGameMode(GameMode.SURVIVAL);

                            BuildMasters.giveKit(player);

                        }
                    });

                    BossBar mallBossbar = BossbarManager.getBossbar("mall");

                    if (mallBossbar != null){
                        BossbarManager.setBossbarVisibility(mallBossbar, true);
                    }

                    mainGameTask(plugin);
                    otherTask(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void mainGameTask(TheEvent580_2 plugin){

        Timer.setSeconds(Game.BUILD_MASTERS.getGameTime());
        Timer.setMaxSeconds(Game.BUILD_MASTERS.getGameTime());
        Timer.setEnum(Timer.TimerEnum.IN_GAME);

        new BukkitRunnable() {
            @Override
            public void run() {

                BossBar mallBossbar = BossbarManager.getBossbar("mall");

                if (Timer.getSeconds() == 0 || BuildMasters.hasEveryoneFinished()){
                    if (mallBossbar != null){
                        BossbarManager.setBossbarVisibility(mallBossbar, false);
                    }

                    if (Variables.equals("jeu", 6)){
                        //new Mode10(plugin);
                    } else {
                        new Mode7(plugin);
                    }

                    this.cancel();
                }

                for (Player player : BuildMasters.getRemainingPlayers()){
                    PlayerManager playerManager = Online.getPlayerManager(player);

                    if (playerManager.getTimer().getSeconds() > 0){
                        playerManager.getTimer().remove1Second();
                    }

                    if (playerManager.getTimer().getSeconds() <= 0){
                        Spectators.readySpectatorGame(playerManager);

                        if (BuildMasters.getCurrentPlayerBuild(player) > 10){

                            Bukkit.broadcast(Component.text("[")
                                    .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                                    .append(Component.text("] " + playerManager.getTeam().getIcon(), ColorType.TEXT.getColor()))
                                    .append(Component.text(player.getName(), playerManager.getColorType().getColor(), TextDecoration.BOLD))
                                    .append(Component.text(" completed all 10 builds!", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                        } else {

                            Bukkit.broadcast(Component.text("[")
                                    .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                                    .append(Component.text("] " + playerManager.getTeam().getIcon(), ColorType.TEXT.getColor()))
                                    .append(Component.text(player.getName(), playerManager.getColorType().getColor(), TextDecoration.BOLD))
                                    .append(Component.text(" Ran out of time on build " + BuildMasters.getCurrentPlayerBuild(player), ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                        }

                    }
                }

                if (mallBossbar != null){
                    BossbarManager.setBossbarText(mallBossbar, me.clip.placeholderapi.libs.kyori.adventure.text.Component.text("Refill in : " + (Timer.getSeconds() % 30), ColorTypeAlt.MC_BLUE.getColor()));
                    BossbarManager.setBossbarProgression(mallBossbar, (float) (Timer.getSeconds()%30) / 30);
                }

                if (Timer.getSeconds() % 30 == 0){
                    BuildMasters.refillMall();
                }


            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void otherTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                for (Player player : Bukkit.getOnlinePlayers()){
                    if (BuildMasters.getRemainingPlayers().contains(player)){

                        if (BuildMasters.isFloorBuildCorrect(BuildMasters.getCurrentPlayerBuild(player), BuildMasters.getPlayerPlot(player))){
                            BuildMasters.setNextBuild(player);
                        }

                        Location playerLocation = player.getLocation().clone();

                        PersistentDataContainer pdc = player.getPersistentDataContainer();

                        if (!pdc.has(new NamespacedKey(plugin, "showBlocks")) || Boolean.FALSE.equals(pdc.get(new NamespacedKey(plugin, "showBlocks"), PersistentDataType.BOOLEAN))){
                            if (playerLocation.z() > 3000){
                                player.sendActionBar(Component.text("Current inventory price : " + Conversions.convertSecondsToTimeOptimized(BuildMasters.inventoryPrice(player)), ColorType.MC_BLUE.getColor(), TextDecoration.BOLD));
                            }
                        }

                        playerLocation.setX(128);

                        if (playerLocation.getBlock().getType() == Material.PUMPKIN){
                            if (playerLocation.z() > 3000){
                                BuildMasters.toToPlot(player);
                            } else {
                                BuildMasters.tpToMall(player);
                            }
                        }

                    } else {
                        if (BuildMasters.getRemainingPlayers().size() > 1){
                            player.sendActionBar(Component.text(BuildMasters.getRemainingPlayers().size() + " players left."));
                        } else {
                            player.sendActionBar(Component.text("1 player left."));
                        }
                    }

                }

            }
        }.runTaskTimer(plugin, 0L, 2L);

    }

}
