package fr.thefox580.theevent5802.games.parkour;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.tasks.timer.Mode7;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.List;

public class ParkourTasks {
    
    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 60){
                    Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                            Component.text(Game.PARKOUR.getIcon(), ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor())),
                            Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                            Title.Times.times(
                                    Duration.ofMillis(500),
                                    Duration.ofSeconds(5),
                                    Duration.ofMillis(500)
                            )
                    )));
                } else if (Timer.getSeconds() == 35){
                    Spectators.getSpectatorOnlineList().forEach(Spectators::readySpectatorGame);
                } else if (Timer.getSeconds() == 30){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                            .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (Timer.getSeconds() == 25){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                            .append(Component.text("] Your goal is to complete all 25 levels in less than 10 minutes.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 20){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                            .append(Component.text("] Finishing a section will up your multiplier, so try to get the most you can!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 15){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                            .append(Component.text("] You will get invisibility AND NO COLLISIONS.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.VOICE, 0.25f, 1);

                        if (Players.isPlayer(player)){
                            ItemStack skip = ItemStack.of(Material.CARROT_ON_A_STICK);
                            ItemMeta skipMeta = skip.getItemMeta();

                            skipMeta.customName(Component.text("Skip Level", ColorType.MC_ORANGE.getColor()).decoration(TextDecoration.ITALIC, false));

                            CustomModelDataComponent cmdComp = skipMeta.getCustomModelDataComponent();
                            cmdComp.setStrings(List.of("skip_level"));
                            skipMeta.setCustomModelDataComponent(cmdComp);

                            skip.setItemMeta(skipMeta);

                            player.getInventory().setItem(7, skip);
                            player.setCooldown(skip, 10*20);
                        }
                    });

                    mainGameTask(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private static boolean withinBounds(Location playerLocation){
        return 198 < playerLocation.z() && playerLocation.z() < 204 && 128 < playerLocation.y();
    }

    private static void mainGameTask(TheEvent580_2 plugin){

        Timer.setSeconds(Game.PARKOUR.getGameTime());
        Timer.setMaxSeconds(Game.PARKOUR.getGameTime());
        Timer.setEnum(Timer.TimerEnum.IN_GAME);

        new BukkitRunnable() {
            @Override
            public void run() {

                if (!Timer.isPaused()){
                    if (Timer.getSeconds() == 0){
                        if (Variables.equals("jeu", 6)){
                            //new Mode10(plugin);
                        } else {
                            new Mode7(plugin);
                        }

                        this.cancel();
                    } else {

                        Players.getOnlinePlayerList().forEach(playerManager -> {
                            Player player = playerManager.getOnlinePlayer();

                            if (player != null){
                                if (player.getLocation().y() < 100){
                                    player.teleport(Parkour.getPlayerCheckpoint(player));
                                } else if (!player.getAllowFlight()){
                                    if (Parkour.getMainLevel(player) == 4 && Parkour.getSubLevel(player) == 8){
                                        if (player.getInventory().contains(Material.CARROT_ON_A_STICK)){
                                            player.getInventory().clear();
                                        }
                                        if (Parkour.getPlayerCheckpoint(player).x()+32 <= player.getX() && withinBounds(player.getLocation())){
                                            Parkour.addPlayerMult(player, 1.3f);
                                            Spectators.readySpectatorGame(playerManager);
                                            Players.getOnlinePlayerList().forEach(otherPlayerManager -> {
                                                Player otherPlayer = otherPlayerManager.getOnlinePlayer();

                                                if (otherPlayer != null){
                                                    if (player.getUniqueId() == otherPlayer.getUniqueId()){
                                                        player.sendMessage(Component.text("[")
                                                                .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                                                .append(Component.text("] You fully completed ", ColorType.TEXT.getColor()))
                                                                .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                                                .append(Component.text("! You get rewarded with " + (Parkour.getPlayerPoints(player)*Parkour.getPlayerMult(player)) + "工!", ColorType.TEXT.getColor())));
                                                    } else if (otherPlayer.getAllowFlight()){
                                                        if (Spectators.isSpectator(player)){
                                                            player.sendMessage(Component.text("[")
                                                                    .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                                                    .append(Component.text("] " + player.getName() + "  finished all 25 levels of ", ColorType.TEXT.getColor()))
                                                                    .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor())));
                                                        } else {
                                                            Parkour.addPlayerPoints(player, 10);
                                                            player.sendMessage(Component.text("[")
                                                                    .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                                                    .append(Component.text("] " + player.getName() + "  finished ", ColorType.TEXT.getColor()))
                                                                    .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                                                    .append(Component.text(" after you! You get rewarded with " + (10*Points.getMultiplier()) + "工 (unmultiplied)!", ColorType.TEXT.getColor())));
                                                        }
                                                    }
                                                }
                                            });
                                        }
                                    } else {
                                        if (Parkour.getPlayerCheckpoint(player).x()+16 <= player.getX() && withinBounds(player.getLocation())){
                                            if (Parkour.hasPlayerSkipped(player)){
                                                switch (Parkour.getMainLevel(player)){
                                                    case 1 -> Parkour.addPlayerMult(player, 0.1f);
                                                    case 2 -> Parkour.addPlayerMult(player, 0.2f);
                                                    case 3 -> Parkour.addPlayerMult(player, 0.4f);
                                                    case 4 -> Parkour.addPlayerMult(player, 0.75f);
                                                }
                                            }

                                            Parkour.setPlayerCheckpoint(player);

                                            player.sendMessage(Component.text("[")
                                                    .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                                    .append(Component.text("] ", ColorType.TEXT.getColor()))
                                                    .append(Parkour.getMainLevelComp(player))
                                                    .append(Component.text(" sub-level completed! New multiplier : 必"+ Parkour.getPlayerMult(player) +"!", ColorType.TEXT.getColor())));

                                            if (Parkour.getSubLevel(player) == 7){
                                                Parkour.setMainLevel(player,Parkour.getMainLevel(player) + 1);
                                                Parkour.setSubLevel(player,0);
                                                Bukkit.broadcast(Component.text("[")
                                                        .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                                        .append(Component.text("] " + player.getName() + " finished all ", ColorType.TEXT.getColor()))
                                                        .append(Parkour.getMainLevelComp(player))
                                                        .append(Component.text(" levels!", ColorType.TEXT.getColor())));
                                            }
                                            Parkour.setSubLevel(player,Parkour.getSubLevel(player) + 1);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}
