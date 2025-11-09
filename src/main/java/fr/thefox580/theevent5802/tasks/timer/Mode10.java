package fr.thefox580.theevent5802.tasks.timer;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.parkour.Parkour;
import fr.thefox580.theevent5802.utils.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
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

public class Mode10 implements Runnable{

    private final TheEvent580_2 plugin;
    private final BukkitTask task;

    public Mode10(TheEvent580_2 plugin){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);

        Timer.setSeconds(90);
        Timer.setMaxSeconds(90);
        Timer.setEnum(Timer.TimerEnum.POST_LAST_GAME);
    }

    @Override
    public void run() {



        if (Timer.getSeconds() == 89) {
            for (Player loopPlayer : Bukkit.getOnlinePlayers()) {
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

                if (Boolean.TRUE.equals(pdc.get(new NamespacedKey(plugin, "showBlocks"), PersistentDataType.BOOLEAN))) {
                    Bukkit.dispatchCommand(loopPlayer, "showBlocks");
                }

                if (Players.isPlayer(loopPlayer)) {
                    if (Variables.equals("jeu_condi", Game.PARKOUR.getGameCondition())) {
                        Points.addGamePoints(loopPlayer, Math.round(Parkour.getPlayerPoints(loopPlayer) * Parkour.getPlayerMult(loopPlayer)));
                    }
                }
                loopPlayer.sendMessage(Component.text("This game, you placed ")
                        .append(Component.text("????", ColorType.TEXT.getColor(), TextDecoration.OBFUSCATED))
                        .append(Component.text("with "))
                        .append(Component.text("????", ColorType.TEXT.getColor(), TextDecoration.OBFUSCATED))
                        .append(Component.text('工', ColorType.NO_SHADOW.getColor())));

                Map<Player, Integer> unsortedPlayers = new HashMap<>();

                for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                    unsortedPlayers.put(playerManager.getOnlinePlayer(), Integer.valueOf(Points.getGamePoints(Objects.requireNonNull(playerManager.getOnlinePlayer()))));
                }

                for (PlayerManager pM : Spectators.getSpectatorOnlineList()){
                    Objects.requireNonNull(pM.getOnlinePlayer()).sendMessage(Component.text("To " + loopPlayer.getName() + " : " + Points.formatPoints(unsortedPlayers.get(loopPlayer))));
                }

                Bukkit.getConsoleSender().sendMessage(Component.text("To " + loopPlayer.getName() + " : " + Points.formatPoints(unsortedPlayers.get(loopPlayer))));

                PlayerManager playerManager = Players.getPlayerManager(loopPlayer);
                Game game = Game.getGameByGameCondition((int) Variables.getVariable("jeu_condi"));

                if (playerManager != null && game != Game.HUB){

                    Score gameScore = new Score(game);
                    gameScore.setPoints(unsortedPlayers.get(loopPlayer));
                    List<Score> playerScores = playerManager.getStats().getScores();
                    playerScores.add(gameScore);
                    playerManager.getStats().setScores(playerScores);

                }

                Points.addGamePointsToOverallPoints(loopPlayer);
            }
        } else if (Timer.getSeconds() ==  70){
            List<Integer> npcsToHide = List.of(8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
            for (Integer npcId : npcsToHide){
                NPC npc = CitizensAPI.getNPCRegistry().getById(npcId);

                npc.setName(PlainTextComponentSerializer.plainText().serialize(Component.text("Top " + (npcId-7) + " - ", ColorType.SUBTEXT.getColor())
                        .append(Component.text("TBD", ColorType.SUBTEXT.getColor(), TextDecoration.OBFUSCATED))));

                npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("QuestionMark",
                        "RPxcif+B1vYlZgxQKcZaMdJMlDfOLuHMsG+bHLEUulkOVMZIRXcEIp/YGtgPHcEjZ8tqVDPE6E+mFqnpY03JPsz1aOrWvr3D4dx7ZEMtQE/drjD6c5tFdkPt8xJi4BP79xQsQJtSKZsCn0xRUfDKugmn0i0Waxaw5UFvGDkQbhYWC9lGwNcblIYqube/L/XAtnOiDKQ8zT0hdqsuJ5Ny5AveW3hxBv221/gmwrrPwdgYX5egApxirooaoB1vVULCfd7opBpSs7IGyp4jM5PlrU8TGUMDX7/oHh4GRG9f3NG2ccbftcg05eXxok7QVBHXMnP5o3r0V71l0tvj5VyqN/uiJDWqcmaLsCt2Qeu/3hMclTxGEm3IhWWXFWcWfkATfZ6hg+Kuk7bLBNcTExwj+veu5MIm8vTLp2MXkYCZ3zv6RzLolSyM7jGG3wAczGpY01HpaIMgkOA1SUMlmu1+pRJkTHDXPTC3XGyNaY4WzgkbBgvPOD0Z1052dtG9enbRLzhCpGNxV5N1nMiod6objHdXL4MUtiuT29ANcyHm7WHSm/f5cJ8qtrF9e3VYOnXECBGBym6DiTHY1V4nSnkCtXv8rQyrpo5XJdEzCDMyLkvxqbUj9+YTz9MkAqEngqZkpCzukXbEn3pIveXMD+oMV6ncRg27qrDPKphHuMFk5TM=",
                        "ewogICJ0aW1lc3RhbXAiIDogMTYzNjEzNTYwNzkwOCwKICAicHJvZmlsZUlkIiA6ICJkZTE0MGFmM2NmMjM0ZmM0OTJiZTE3M2Y2NjA3MzViYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTUlRlYW0iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NkOGYwMjUwMTc4NWQxNmIwOTNmNGQ2NTk2OThhNzlmY2Q1MGEwMDk0ZjUzMzQ5ZDQ4ZDY2MzJmYzZlMTMyZCIKICAgIH0KICB9Cn0=");
            }
        } else if (Timer.getSeconds() == 60){
            for (Player loopPlayer : Bukkit.getOnlinePlayers()) {
                Bukkit.dispatchCommand(loopPlayer, "spawn");
            }
        } else if (Timer.getSeconds() == 10){

            for (Player player : Bukkit.getOnlinePlayers()){
                player.teleport(new Location(player.getWorld(), 335.5, -5.00, 337.50, -104, -18));
                player.showTitle(Title.title(
                        Component.text("And your Episode 7 winner is..."),
                        Component.text("???????????", ColorType.TEXT.getColor(), TextDecoration.OBFUSCATED),
                        Title.Times.times(
                                Duration.ofMillis(500),
                                Duration.ofSeconds(6),
                                Duration.ofMillis(500)
                        )
                ));
            }

        } else if (Timer.getSeconds() == 5){

            Player winner = Points.getTopEvent().getFirst().getKey();
            PlayerManager winnerPlayerManager = Online.getPlayerManager(winner);

            for (Player player : Bukkit.getOnlinePlayers()){
                player.showTitle(Title.title(
                        Component.text("And your Episode 7 winner is"),
                        winnerPlayerManager.playerComponent(),
                        Title.Times.times(
                                Duration.ofMillis(0),
                                Duration.ofSeconds(3),
                                Duration.ofMillis(500)
                        )
                ));
            }

            NPC npc = CitizensAPI.getNPCRegistry().getById(27);
            npc.getOrAddTrait(SkinTrait.class).setSkinPersistent(winner);

            Hologram winnerHolo = DHAPI.getHologram("S1E7W");
            if (winnerHolo != null){
                winnerHolo.getPage(1).setLine(2, PlainTextComponentSerializer.plainText().serialize(winnerPlayerManager.playerComponent()));
            }

            Leaderboard.getAllPlayers();

        } else if (Timer.getSeconds() == 0){
            Timer.setEnum(Timer.TimerEnum.END);

            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] Thanks for playing, see you next time!", ColorType.TEXT.getColor())));

                player.showTitle(Title.title(Component.text("Thanks for playing", ColorType.TEXT.getColor()),
                        Component.text("or even watching! See you next time...", ColorType.TEXT.getColor()),
                        Title.Times.times(
                                Duration.ofMillis(500),
                                Duration.ofSeconds(5),
                                Duration.ofMillis(500)
                        )));
            }

            task.cancel();
        }

    }
}
