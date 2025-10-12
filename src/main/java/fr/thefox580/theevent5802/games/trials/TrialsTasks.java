package fr.thefox580.theevent5802.games.trials;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.tasks.timer.Mode7;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TrialsTasks {
    
    private static int currentTrial = 0;
    private static float currentTrialPoints = 100f;
    private static final Map<Player, Location> playerLocation = new HashMap<>();
    private static final Map<Player, Integer> playerStats = new HashMap<>();

    /**
     * @return The index of the current trial.
     */
    public static int getCurrentTrial(){
        return currentTrial;
    }

    /**
     * @return The amount of points a player will be awarded when this method is called.
     */
    public static float getCurrentTrialPoints(){
        return currentTrialPoints;
    }

    /**
     * @return A map containing a stats needed for a trial (i.e. : sneaking, jumping, ...).
     */
    public static Map<Player, Integer> getStats(){
        return playerStats;
    }

    /**
     * Method to call to start the pre-game sequence
     * @param plugin The main class of the plugin.
     */
    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 60){
                    Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                            Component.text(Game.TRIALS.getIcon(), ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor())),
                            Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                            Title.Times.times(
                                    Duration.ofMillis(500),
                                    Duration.ofSeconds(5),
                                    Duration.ofMillis(500)
                            )
                    )));
                } else if (Timer.getSeconds() == 30){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (Timer.getSeconds() == 25){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] Your goal is to complete as many trials as possible in 10 minutes.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 20){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] The faster you go, the more points you'll get, so try to be the fastest!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 15){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] Every 2 minutes, the time between the trials will get smaller, and so will the platform!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 10){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] The trial completion time will go from 10s to 6s.", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.TRIALS.getName(), Game.TRIALS.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.VOICE, 0.25f, 1));

                    mainGameTask(plugin);
                    checkTrialsTask(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    /**
     * Method to call to start the game mechanics (runs every second)
     * @param plugin The main class of the plugin.
     */
    private static void mainGameTask(TheEvent580_2 plugin) {

        Timer.setSeconds(Game.TRIALS.getGameTime());
        Timer.setMaxSeconds(Game.TRIALS.getGameTime());
        Timer.setEnum(Timer.TimerEnum.IN_GAME);

        new BukkitRunnable() {
            @Override
            public void run() {

                if (List.of(7 * 60 + 4, 4 * 60 + 56, 2 * 60 + 59, 57).contains(Timer.getSeconds())) {
                    Trials.nextTrialLevel();
                } else if (Timer.getSeconds() % (Trials.getTrialsSpeed() + 5) == 0) {

                    currentTrial = TrialsDecision.chooseNewTrial();
                    currentTrialPoints = 100f;

                    if (42 <= currentTrial && currentTrial <= 48) {
                        Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                                Component.text(TrialsDecision.getTrial(currentTrial)),
                                Component.text("Type the answer in chat"),
                                Title.Times.times(
                                        Duration.ofMillis(250),
                                        Duration.ofSeconds(2),
                                        Duration.ofMillis(250)
                                )
                        )));
                    } else {
                        Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                                Component.text(TrialsDecision.getTrial(currentTrial)),
                                Component.text(""),
                                Title.Times.times(
                                        Duration.ofMillis(250),
                                        Duration.ofSeconds(2),
                                        Duration.ofMillis(250)
                                )
                        )));

                        if (List.of(5, 25).contains(currentTrial)) {
                            Trials.triggerGlass(plugin);
                        } else if (List.of(10, 11, 12, 13, 19, 29, 30, 31, 32, 38).contains(currentTrial)) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 133 17 -18 127 -15 air replace light");

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:trials run fill 14 133 17 -18 127 -15 air replace light");
                                }
                            }.runTaskLater(plugin, Trials.getTrialsSpeed() * 20L);
                        }

                        if (List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
                                12, 13, 14, 15, 16, 17, 18, 19, 20,
                                21, 41, 42, 43, 44, 45, 46, 47, 48, 49).contains(currentTrial)) {

                            playerLocation.clear();
                            playerStats.clear();

                            for (PlayerManager player : Players.getOnlinePlayerList()) {
                                Trials.setRoundPoints(Objects.requireNonNull(player.getOnlinePlayer()), 0);
                                if (currentTrial == 49) {
                                    playerLocation.put(player.getOnlinePlayer(), player.getOnlinePlayer().getLocation());
                                } else if (List.of(1, 2, 3, 4, 6, 22, 23, 24).contains(currentTrial)) {
                                    playerStats.put(player.getOnlinePlayer(), 0);
                                }


                            }

                        } else {

                            for (PlayerManager player : Players.getOnlinePlayerList()) {
                                Trials.setRoundPoints(Objects.requireNonNull(player.getOnlinePlayer()), 50);
                                if (currentTrial == 50) {
                                    playerLocation.put(player.getOnlinePlayer(), player.getOnlinePlayer().getLocation());
                                } else {
                                    playerLocation.clear();
                                }
                            }

                        }
                    }

                } else if (Timer.getSeconds() == 0) {

                    if (Variables.equals("jeu", 6)) {
                        //new Mode10(plugin);
                    } else {
                        new Mode7(plugin);
                    }

                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    /**
     * Method to call to start the trials mechanics (runs every tick)
     * @param plugin The main class of the plugin.
     */
    private static void checkTrialsTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds()%(Trials.getTrialsSpeed()+5) > 5){
                    for (PlayerManager player : Online.getOnlinePlayers()){
                        if (Players.isPlayer(Objects.requireNonNull(player.getOnlinePlayer()))){
                            player.getOnlinePlayer().sendActionBar(
                                    Component.text("Trial points : +", ColorType.SUBTEXT.getColor())
                                            .append(Component.text('工', ColorType.NO_SHADOW.getColor()))
                            );
                        } else {
                            int playersCompleted = Trials.getPlayersCompletedCount();
                            player.getOnlinePlayer().sendActionBar(
                                    Component.text(playersCompleted + " players completed this trial!", ColorType.SUBTEXT.getColor())
                            );
                        }
                    }

                    if (currentTrialPoints > 0){

                        switch (currentTrial){
                            case 5: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Objects.requireNonNull(player.getOnlinePlayer()).getY() < 0){
                                        player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("trials"), 0.5, 130, 0.5, 0, 0));
                                        if (!Trials.hasPlayerCompleted(player.getOnlinePlayer())){
                                            Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                            player.getOnlinePlayer().showTitle(
                                                    Title.title(
                                                            Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                            Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                    .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                            Title.Times.times(
                                                                    Duration.ofMillis(100),
                                                                    Duration.ofMillis(1500),
                                                                    Duration.ofMillis(100))
                                                    )
                                            );
                                        }
                                    }
                                }
                                break;
                            }

                            case 10: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.RED_CONCRETE){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 11: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.LIME_CONCRETE){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 12: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.BLUE_CONCRETE){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 13: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.GRAY_CONCRETE){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 14: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getX() >= 0){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 15: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getZ() >= 0){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 16: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getX() < 0){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 17: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getZ() < 0){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 18: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getTargetEntity(10) instanceof Player){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 19: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && Objects.requireNonNull(player.getOnlinePlayer().getTargetBlockExact(5)).getType() == Material.TINTED_GLASS){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 20: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getPitch() <= -65){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 21: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getPitch() >= 65){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 25: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Objects.requireNonNull(player.getOnlinePlayer()).getY() < 0){
                                        player.getOnlinePlayer().teleport(new Location(Bukkit.getWorld("trials"), 0.5, 130, 0.5, 0, 0));
                                        if (Trials.hasPlayerCompleted(player.getOnlinePlayer())){
                                            Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                            player.getOnlinePlayer().showTitle(
                                                    Title.title(
                                                            Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                            Component.text("", ColorType.SUBTEXT.getColor()),
                                                            Title.Times.times(
                                                                    Duration.ofMillis(100),
                                                                    Duration.ofMillis(1500),
                                                                    Duration.ofMillis(100))
                                                    )
                                            );
                                        }
                                    }
                                }
                                break;
                            }

                            case 29: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.RED_CONCRETE){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 30: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.LIME_CONCRETE){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 31: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.BLUE_CONCRETE){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 32: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().clone().add(0, -1, 0).getBlock().getType() == Material.GRAY_CONCRETE){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 33: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getX() >= 0){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 34: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getZ() >= 0){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 35: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getX() < 0){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 36: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getZ() < 0){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 37: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getTargetEntity(10) instanceof Player){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 38: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && Objects.requireNonNull(player.getOnlinePlayer().getTargetBlockExact(5)).getType() == Material.TINTED_GLASS){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 39: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getPitch() <= -65){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 40: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation().getPitch() >= 65){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    }
                                }
                                break;
                            }

                            case 49: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation() == playerLocation.get(player.getOnlinePlayer())){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), Math.round(currentTrialPoints));
                                        playerLocation.remove(player.getOnlinePlayer());
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You completed this trial!", ColorType.MC_LIME.getColor()),
                                                        Component.text("+" + Math.round(currentTrialPoints), ColorType.SUBTEXT.getColor())
                                                                .append(Component.text('工', ColorType.NO_SHADOW.getColor())),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    } else if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer()))) {
                                        playerLocation.put(player.getOnlinePlayer(), player.getOnlinePlayer().getLocation());
                                    }
                                }
                                break;
                            }

                            case 50: {
                                for (PlayerManager player : Players.getOnlinePlayerList()){
                                    if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer())) && player.getOnlinePlayer().getLocation() == playerLocation.get(player.getOnlinePlayer())){
                                        Trials.setRoundPoints(player.getOnlinePlayer(), 0);
                                        playerLocation.remove(player.getOnlinePlayer());
                                        player.getOnlinePlayer().showTitle(
                                                Title.title(
                                                        Component.text("You failed this trial!", ColorType.MC_RED.getColor()),
                                                        Component.text("", ColorType.SUBTEXT.getColor()),
                                                        Title.Times.times(
                                                                Duration.ofMillis(100),
                                                                Duration.ofMillis(1500),
                                                                Duration.ofMillis(100))
                                                )
                                        );
                                    } else if (!Trials.hasPlayerCompleted(Objects.requireNonNull(player.getOnlinePlayer()))) {
                                        playerLocation.put(player.getOnlinePlayer(), player.getOnlinePlayer().getLocation());
                                    }
                                }
                            }
                            break;
                        }
                    }

                    currentTrialPoints -= 100f/Trials.getTrialsSpeed()/2/10;

                } else if (Timer.getSeconds()%Trials.getTrialsSpeed()+5 == 5){

                    currentTrial = 0;
                    for (PlayerManager player : Players.getOnlinePlayerList()){
                        World world = Objects.requireNonNull(player.getOnlinePlayer()).getWorld();
                        Firework firework = (Firework) world.spawnEntity(player.getOnlinePlayer().getLocation(), EntityType.FIREWORK_ROCKET);
                        FireworkMeta fireworkMeta = firework.getFireworkMeta();
                        FireworkEffect.Builder builder = FireworkEffect.builder();
                        if (Trials.hasPlayerCompleted(player.getOnlinePlayer())){
                            builder.withColor(Color.LIME);
                        } else {
                            builder.withColor(Color.RED);
                        }
                        fireworkMeta.addEffect(builder.with(FireworkEffect.Type.BURST).build());
                        fireworkMeta.setPower(1);
                        firework.setFireworkMeta(fireworkMeta);
                        firework.setTicksToDetonate(0);
                        firework.detonate();

                        Points.addGamePoints(player.getOnlinePlayer(), Trials.getRoundPoints(player.getOnlinePlayer()));
                    }

                    currentTrialPoints = 100f;
                    Trials.resetRoundPoints();

                } else if (Timer.getSeconds() == 0){
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 2L);

    }

}
