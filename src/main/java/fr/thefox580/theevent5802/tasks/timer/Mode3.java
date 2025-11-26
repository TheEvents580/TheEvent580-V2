package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Mode3 implements Runnable {

    private final TheEvent580_2 plugin;
    private final BukkitTask task;
    List<Game> chosenGames;

    public Mode3(TheEvent580_2 plugin){
        this.plugin = plugin;
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 20L);

        Timer.setSeconds(40);
        Timer.setMaxSeconds(40);
        Timer.setEnum(Timer.TimerEnum.VOTING);

        Voting.resetVotes();
    }

    @Override
    public void run() {
        if (Timer.getSeconds() == 35){

            for (PlayerManager loopPlayer : Players.getOnlinePlayerList()){
                Powerups powerup = Powerups.getRandomPowerup();

                if (powerup != null){
                    Player player = Objects.requireNonNull(loopPlayer.getOnlinePlayer());

                    ItemStack powerupItem = powerup.getItem();
                    player.give(powerupItem);

                    player.sendMessage(Component.text('[')
                            .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                            .append(Component.text("] You received ", ColorType.TEXT.getColor()))
                            .append(Component.text(powerup.getRarity().getName() + ' ', powerup.getRarity().getColor(), TextDecoration.BOLD))
                            .append(powerupItem.displayName()));

                    for (PlayerManager spectator : Spectators.getSpectatorOnlineList()){
                        Objects.requireNonNull(spectator.getOnlinePlayer()).sendMessage(Component.text('[')
                                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                                .append(Component.text("] Player", ColorType.TEXT.getColor()))
                                .append(Component.text(loopPlayer.getName(), loopPlayer.getColorType().getColor()))
                                .append(Component.text(" received ", ColorType.TEXT.getColor()))
                                .append(Component.text(powerup.getRarity().getName() + ' ', powerup.getRarity().getColor(), TextDecoration.BOLD))
                                .append(powerupItem.displayName()));
                    }

                } else {
                    for (PlayerManager spectator : Spectators.getSpectatorOnlineList()){
                        Objects.requireNonNull(spectator.getOnlinePlayer()).sendMessage(Component.text('[')
                                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                                .append(Component.text("] Player", ColorType.TEXT.getColor()))
                                .append(Component.text(loopPlayer.getName(), loopPlayer.getColorType().getColor()))
                                .append(Component.text(" didn't recieve anything...", ColorType.TEXT.getColor())));
                    }
                }
            }

        } else if (Timer.getSeconds() == 13){

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 247 -8 -8 247 8 redstone_block");

            for (PlayerManager playerManager : Players.getOnlinePlayerList()){

                Player player =  playerManager.getOnlinePlayer();

                if (player != null){

                    if (Boolean.TRUE.equals(player.getPersistentDataContainer().get(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN))){
                        Material chestplate = Objects.requireNonNull(player.getInventory().getChestplate()).getType();

                        getBlockUnderEntity(chestplate, player);
                    }

                    player.getInventory().remove(Material.CARROT_ON_A_STICK);
                    player.getInventory().remove(Material.MINECART);
                    player.getInventory().remove(Material.NETHERITE_SWORD);
                    player.getInventory().remove(Material.CROSSBOW);
                    player.getInventory().remove(Material.FOX_SPAWN_EGG);
                    player.getInventory().remove(Material.DIAMOND_CHESTPLATE);
                    player.getInventory().remove(Material.GOLDEN_CHESTPLATE);
                }
            }


            for (Entity entity : Objects.requireNonNull(Bukkit.getWorld("world")).getNearbyEntities(new Location(Bukkit.getWorld("world"), 0, 250, 0), 30, 10, 30)) {
                if (entity instanceof Fox) {

                    getBlockUnderEntity(Material.AIR, entity);
                    entity.remove();

                }
            }

        } else if (Timer.getSeconds() == 10) {

            chosenGames = Voting.getChosenGame();

            Bukkit.broadcast(Component.text("chosenGames has " + chosenGames.size() + " games"));

            if (chosenGames.size() > 1){
                Bukkit.broadcast(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] Vote tied between " + chosenGames.size() + " games!", ColorType.TEXT.getColor())));

                for (PlayerManager playerManager : Online.getOnlinePlayers()){
                    Objects.requireNonNull(playerManager.getOnlinePlayer()).showTitle(Title.title(Component.text("Vote is tied", ColorType.MC_RED.getColor(), TextDecoration.BOLD),
                            Component.text("Vote tied between " + chosenGames.size() + " games!", ColorType.TEXT.getColor()),
                            Title.Times.times(Duration.ofMillis(500), Duration.ofSeconds(3), Duration.ofMillis(500))));
                    if (Spectators.isSpectator(playerManager.getOnlinePlayer())){
                        playerManager.getOnlinePlayer().sendMessage(Component.text('[')
                                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                                .append(Component.text("] The tied games are : ", ColorType.TEXT.getColor())));
                        for (Game game : chosenGames){
                            playerManager.getOnlinePlayer().sendMessage(Component.text('[')
                                    .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                                    .append(Component.text("] ", ColorType.TEXT.getColor()))
                                    .append(Component.text(game.getIcon() + " ", ColorType.NO_SHADOW.getColor()))
                                    .append(Component.text(game.getName(), game.getColorType().getColor(), TextDecoration.BOLD)));
                        }
                    }

                    chosenGames = Voting.getRandomChosenGame(chosenGames);
                }
            }

        } else if (Timer.getSeconds() == 3) {

            for (PlayerManager player : Players.getOnlinePlayerList()){
                Bukkit.getOnlinePlayers().forEach(loopPlayer -> loopPlayer.showPlayer(plugin, Objects.requireNonNull(player.getOnlinePlayer())));
                Objects.requireNonNull(player.getOnlinePlayer()).setAllowFlight(false);
                player.getOnlinePlayer().setFlying(false);

                PersistentDataContainer playerContainer = player.getOnlinePlayer().getPersistentDataContainer();
                playerContainer.set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, true);
            }

        } else if (Timer.getSeconds() == 0){

            Game chosenGame = chosenGames.getFirst();
            Bukkit.broadcast(Component.text('[')
                    .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                    .append(Component.text("] The 1st minigame is ", ColorType.TEXT.getColor()))
                    .append(Component.text(chosenGame.getIcon() + " ", ColorType.NO_SHADOW.getColor()))
                    .append(Component.text(chosenGame.getName(), chosenGame.getColorType().getColor(), TextDecoration.BOLD)));

            for (PlayerManager playerManager : Online.getOnlinePlayers()){
                Objects.requireNonNull(playerManager.getOnlinePlayer()).showTitle(
                        Title.title(Component.text(chosenGame.getIcon() + " ", ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(chosenGame.getName(), chosenGame.getColorType().getColor(), TextDecoration.BOLD)),
                            Component.text("Teleportation in 10 secs")));
            }

            Variables.setVariable("jeu_nom", chosenGame.getName());

            Voting.removeGame(chosenGame);

            new BukkitRunnable() {
                @Override
                public void run() {

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 247 -8 -8 247 8 air");

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 250 3 -8 250 3 polished_deepslate_slab");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 250 -3 -8 250 -3 polished_deepslate_slab");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill -3 250 -8 -3 250 8 polished_deepslate_slab");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 3 250 -8 3 250 8 polished_deepslate_slab");

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 251 3 -8 251 3 air");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 8 251 -3 -8 251 -3 air");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill -3 251 -8 -3 251 8 air");
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fill 3 251 -8 3 251 8 air");

                    new Mode4(plugin, chosenGame);
                    task.cancel();

                }
            }.runTaskLater(plugin, 5L);
        }
    }

    private void getBlockUnderEntity(Material chestplate, Entity entity) {
        switch (entity.getLocation().clone().add(0, -2, 0).getBlock().getType()){
            case LIGHT_GRAY_CONCRETE -> Voting.castGameVote(Game.HUB, chestplate);
            case RED_CONCRETE -> Voting.castGameVote(Game.TRIALS, chestplate);
            case ORANGE_CONCRETE -> Voting.castGameVote(Game.PARKOUR, chestplate);
            case YELLOW_CONCRETE -> Voting.castGameVote(Game.FINDER, chestplate);
            case LIME_CONCRETE -> Voting.castGameVote(Game.TAG, chestplate);
            case LIGHT_BLUE_CONCRETE -> Voting.castGameVote(Game.SPLEEF, chestplate);
            case BLUE_CONCRETE -> Voting.castGameVote(Game.BUILD_MASTERS, chestplate);
            case PURPLE_CONCRETE -> Voting.castGameVote(Game.ARMS_RACE, chestplate);
            case PINK_CONCRETE -> Voting.castGameVote(Game.BOW_PVP, chestplate);
            default -> Voting.castBlankVote(chestplate);
        }
    }
}
