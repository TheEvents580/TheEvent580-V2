package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.parkour.Parkour;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Mode7 implements Runnable{

    private final TheEvent580_2 plugin;
    private final BukkitTask task;

    public Mode7(TheEvent580_2 plugin){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);

        Timer.setSeconds(30);
        Timer.setMaxSeconds(30);
        Timer.setEnum(Timer.TimerEnum.POST_GAME);
    }

    @Override
    public void run() {

        if (Timer.getSeconds() == 30){
            for (Player loopPlayer : Bukkit.getOnlinePlayers()){
                loopPlayer.getAttribute(Attribute.MAX_HEALTH).setBaseValue(loopPlayer.getAttribute(Attribute.MAX_HEALTH).getDefaultValue());
                loopPlayer.getInventory().clear();
                loopPlayer.stopAllSounds();
                loopPlayer.removePotionEffect(PotionEffectType.INVISIBILITY);
                loopPlayer.removePotionEffect(PotionEffectType.SPEED);
                loopPlayer.removePotionEffect(PotionEffectType.NIGHT_VISION);
                loopPlayer.playSound(loopPlayer.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.VOICE, 2, 1);
                loopPlayer.showTitle(Title.title(
                        Component.text("Game Over", ColorType.MC_RED.getColor()),
                        Component.text("Check the chat to see your ", ColorType.TEXT.getColor())
                                .append(Component.text("工", ColorType.NO_SHADOW.getColor())),
                        Title.Times.times(
                                Duration.ofSeconds(1),
                                Duration.ofSeconds(5),
                                Duration.ofSeconds(1)
                        )));
                loopPlayer.setGlowing(false);

                PersistentDataContainer pdc = loopPlayer.getPersistentDataContainer();

                if (Boolean.TRUE.equals(pdc.get(new NamespacedKey(plugin, "showBlocks"), PersistentDataType.BOOLEAN))){
                    Bukkit.dispatchCommand(loopPlayer, "showBlocks");
                }

                if (Players.isPlayer(loopPlayer)){
                    if (Variables.equals("jeu_condi", Game.PARKOUR.getGameCondition())){
                        Points.addGamePoints(loopPlayer, Math.round(Parkour.getPlayerPoints(loopPlayer) * Parkour.getPlayerMult(loopPlayer)));
                    }
                }
            }

            List<Map.Entry<Player, Integer>> allPlayers = Points.getTopGame();

            showTopPlayers(allPlayers);

            Map<Player, Integer> unsortedPlayers = new HashMap<>();

            for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                unsortedPlayers.put(playerManager.getOnlinePlayer(), Integer.valueOf(Points.getGamePoints(Objects.requireNonNull(playerManager.getOnlinePlayer()))));
            }

            allPlayers = Points.getTop(unsortedPlayers, Players.getMaxPlayerCount());
            int place = 1;

            for (Map.Entry<Player, Integer> player : allPlayers){
                player.getKey().sendMessage("");
                Component message = Component.text("This game, you placed ");

                if (place == 1 || place == 21){
                    message = message.append(Component.text(place + "st "));
                } else if (place == 2 || place == 22){
                    message = message.append(Component.text(place + "nd "));
                } else if (place == 3 || place == 23){
                    message = message.append(Component.text(place + "rd "));
                } else {
                    message = message.append(Component.text(place + "th "));
                }

                message = message.append(Component.text("with " + Points.formatPoints(player.getValue()) + " "))
                        .append(Component.text('工', ColorType.NO_SHADOW.getColor()));

                player.getKey().sendMessage(message);
                player.getKey().playSound(player.getKey().getLocation(), "custom:coins", 1, 1);

                for (PlayerManager pM : Spectators.getSpectatorOnlineList()){
                    Objects.requireNonNull(pM.getOnlinePlayer()).sendMessage(Component.text("To " + player.getKey().getName() + " : ")
                            .append(message));
                }

                Bukkit.getConsoleSender().sendMessage(Component.text("To " + player.getKey().getName() + " : ")
                                .append(message));

                PlayerManager playerManager = Players.getPlayerManager(player.getKey());
                Game game = Game.getGameByGameCondition((int) Variables.getVariable("jeu_condi"));

                if (playerManager != null && game != Game.HUB){

                    Score gameScore = new Score(game);
                    gameScore.setPoints(player.getValue());
                    List<Score> playerScores = playerManager.getStats().getScores();
                    playerScores.add(gameScore);
                    playerManager.getStats().setScores(playerScores);

                }

                Points.addGamePointsToOverallPoints(player.getKey());

                place++;
            }

        } else if (Timer.getSeconds() == 25){
            if (Variables.equals("jeu_condi", Game.MULTILAP.getGameCondition())) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in races_lap_3 run setblock 44 131 -20 blackstone");
            } else if (Variables.equals("jeu_condi", Game.BUILD_MASTERS.getGameCondition())){
                BossbarManager.setBossbarVisibility(BossbarManager.getBossbar("mall"), false);
            }
            Bukkit.broadcast(
                        Component.text("[")
                                .append(Component.text("Game Scores", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                                .append(Component.text("] Event Top 3 :", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

            } else if (Timer.getSeconds() == 22){
                List<Map.Entry<Player, Integer>> allPlayers = Points.getTopEvent();

                showTopPlayers(allPlayers);

                Leaderboard.updateTop10NPC();
            } else if (Timer.getSeconds() == 20){

                for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                    Objects.requireNonNull(playerManager.getOnlinePlayer()).getPersistentDataContainer().set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, true);
            }

        } else if (Timer.getSeconds() == 10){

            List<Map.Entry<Player, Integer>> allPlayers = Leaderboard.getAllPlayers();
            int place = 1;

            for (Map.Entry<Player, Integer> player : allPlayers){

                Component message = Component.text("");

                if (place == 1 || place == 21){
                    message = message.append(Component.text(place + "st", ColorType.SPECIAL_2.getColor()));
                } else if (place == 2 || place == 22){
                    message = message.append(Component.text(place + "nd", ColorType.SPECIAL_2.getColor()));
                } else if (place == 3 || place == 23){
                    message = message.append(Component.text(place + "rd", ColorType.SPECIAL_2.getColor()));
                } else {
                    message = message.append(Component.text(place + "th", ColorType.SPECIAL_2.getColor()));
                }

                player.getKey().sendMessage(
                        message.append(Component.text(" - ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                .append(Component.text(Points.formatPoints(player.getValue())).decoration(TextDecoration.BOLD, false))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()))
                );

                place++;
            }

            Bukkit.broadcast(
                    Component.text("[")
                            .append(Component.text("Game Scores", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                            .append(Component.text("] You are placed :", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

        } else if (Timer.getSeconds() == 2){

            Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                    Component.text("轉"),
                    Component.text(""),
                    Title.Times.times(
                            Duration.ofMillis(500),
                            Duration.ofSeconds(5),
                            Duration.ofMillis(500)
                    )
            )));

        } else if (Timer.getSeconds() == 1) {

            if (Variables.equals("jeu", 2)){
                Objects.requireNonNull(Bukkit.getWorld("world")).setTime(6000L);
            } else if (Variables.equals("jeu", 4)){
                Objects.requireNonNull(Bukkit.getWorld("world")).setTime(12000L);
            } else if (Variables.equals("jeu", 3)){
                Objects.requireNonNull(Bukkit.getWorld("world")).setTime(18000L);
            }

        } else if (Timer.getSeconds() == 0){

            Variables.setVariable("jeu_nom", Game.HUB.getName());
            Variables.setVariable("jeu_condi", Game.HUB.getGameCondition());

            for (PlayerManager playerManager : Online.getOnlinePlayers()){
                Objects.requireNonNull(playerManager.getOnlinePlayer()).getInventory().setHelmet(playerManager.getFlag());
                playerManager.getOnlinePlayer().setGameMode(GameMode.ADVENTURE);
                Bukkit.dispatchCommand(playerManager.getOnlinePlayer(), "spawn");
                playerManager.getOnlinePlayer().setAllowFlight(false);
                playerManager.getOnlinePlayer().setFlying(false);

                for (PlayerManager playerManager2 : Online.getOnlinePlayers()){
                    Objects.requireNonNull(playerManager2.getOnlinePlayer()).showPlayer(plugin, Objects.requireNonNull(playerManager.getOnlinePlayer()));
                }

            }

            if (Variables.equals("jeu",3)){
                new Mode8(plugin, "break");
            } else {
                new Mode8(plugin, "normal");
            }

            task.cancel();
        }

    }

    private void showTopPlayers(List<Map.Entry<Player, Integer>> allPlayers) {
        PlayerManager topPlayerManager = Players.getPlayerManager(allPlayers.getFirst().getKey());

        if (topPlayerManager != null){
            Bukkit.broadcast(
                    Component.text("[")
                            .append(Component.text("Game Scores", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                            .append(Component.text("] 1st ", ColorType.TEXT.getColor()))
                            .append(Component.text(topPlayerManager.getTeam().getIcon(), ColorType.NO_SHADOW.getColor()))
                            .append(Component.text(" " + topPlayerManager.getName(), topPlayerManager.getColorType().getColor()))
                            .append(Component.text(" - " + Points.formatPoints(allPlayers.getFirst().getValue())))
                            .append(Component.text(" 工", ColorType.NO_SHADOW.getColor())));

            Objects.requireNonNull(topPlayerManager.getOnlinePlayer()).playSound(topPlayerManager.getOnlinePlayer().getLocation(), "custom:coins", 1, 1);
        }

        topPlayerManager = Players.getPlayerManager(allPlayers.get(1).getKey());

        if (topPlayerManager != null){
            Bukkit.broadcast(
                    Component.text("[")
                            .append(Component.text("Game Scores", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                            .append(Component.text("] 2nd ", ColorType.TEXT.getColor()))
                            .append(Component.text(topPlayerManager.getTeam().getIcon(), ColorType.NO_SHADOW.getColor()))
                            .append(Component.text(" " + topPlayerManager.getName(), topPlayerManager.getColorType().getColor()))
                            .append(Component.text(" - " + Points.formatPoints(allPlayers.get(1).getValue())))
                            .append(Component.text(" 工", ColorType.NO_SHADOW.getColor())));

            Objects.requireNonNull(topPlayerManager.getOnlinePlayer()).playSound(topPlayerManager.getOnlinePlayer().getLocation(), "custom:coins", 1, 1);
        }

        topPlayerManager = Players.getPlayerManager(allPlayers.get(2).getKey());

        if (topPlayerManager != null){
            Bukkit.broadcast(
                    Component.text("[")
                            .append(Component.text("Game Scores", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                            .append(Component.text("] 3rd ", ColorType.TEXT.getColor()))
                            .append(Component.text(topPlayerManager.getTeam().getIcon(), ColorType.NO_SHADOW.getColor()))
                            .append(Component.text(" " + topPlayerManager.getName(), topPlayerManager.getColorType().getColor()))
                            .append(Component.text(" - " + Points.formatPoints(allPlayers.get(2).getValue())))
                            .append(Component.text(" 工", ColorType.NO_SHADOW.getColor())));

            Objects.requireNonNull(topPlayerManager.getOnlinePlayer()).playSound(topPlayerManager.getOnlinePlayer().getLocation(), "custom:coins", 1, 1);
        }
    }
}
