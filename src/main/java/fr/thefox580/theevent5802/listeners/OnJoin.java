package fr.thefox580.theevent5802.listeners;

import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class OnJoin implements Listener {

    private final TheEvent580_2 plugin;

    public OnJoin(TheEvent580_2 plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerJoinsEvent(PlayerJoinEvent event){ //When a player joins the server

        Player player = event.getPlayer(); //Get the player

        if (System.currentTimeMillis() < 1798757999000L) { //If the time is before December 31st 2029, at 11:59:59PM CET
            if (player.isWhitelisted()) { //If the player is whitelisted
                if (!player.hasPermission("theevent580.tester")) { //If the player is not a tester
                    player.kick(Component.text("Sorry, but you're not allowed to join the server yet !", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)); //Kick the player for the following reason
                    return;
                }
            }
        }

        SpawnParkour.updateText();

        player.setFlySpeed(0.1f);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, false, false, false));

        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        if (!playerContainer.has(new NamespacedKey(plugin, "points"))){
            Points.initialisePoints(player);
        }

        if (!playerContainer.has(new NamespacedKey(plugin, "showBlocks"))){
            playerContainer.set(new NamespacedKey(plugin, "showBlocks"), PersistentDataType.BOOLEAN, false);
        }

        playerContainer.set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, true);

        if (Objects.equals(Variables.getVariable("jeu"), 0)){
            Points.resetPoints(player);
        }

        ScoreboardManager.createBoard(player);

        PlayerManager playerManager;

        if (player.hasPermission("group.spectators")){
            playerManager = Spectators.getPlayerManager(player);
        } else {
            playerManager = Players.getPlayerManager(player);
        }

        if (playerManager == null){

            plugin.getLogger().warning("Player Manager for " + player.getName() + " wasn't found, creating one");

            boolean isStaff = false;
            boolean isAdmin = false;

            if (player.hasPermission("group.admin")){
                isAdmin = true;
                isStaff = true;
            } else if (player.hasPermission("group.staff")){
                isStaff = true;
            }

            if (player.hasPermission("group.spectators")){ //If the player is a spectator
                Spectators.addSpectator(player, isStaff, isAdmin);
            }

            else if (player.hasPermission("group.rouge")) { //If the player is in red team
                Players.addPlayer(player, Team.RED, isStaff, isAdmin);

            }
            else if (player.hasPermission("group.orange")) { //If the player is in orange team
                Players.addPlayer(player, Team.ORANGE, isStaff, isAdmin);

            }
            else if (player.hasPermission("group.jaune")) { //If the player is in yellow team
                Players.addPlayer(player, Team.YELLOW, isStaff, isAdmin);

            }
            else if (player.hasPermission("group.vert")) { //If the player is in lime / green team
                Players.addPlayer(player, Team.LIME, isStaff, isAdmin);

            }
            else if (player.hasPermission("group.bleu_clair")) { //If the player is in light blue team
                Players.addPlayer(player, Team.LIGHT_BLUE, isStaff, isAdmin);
            }
            else if (player.hasPermission("group.bleu")) { //If the player is in blue team
                Players.addPlayer(player, Team.BLUE, isStaff, isAdmin);
            }
            else if (player.hasPermission("group.violet")) { //If the player is in purple team
                Players.addPlayer(player, Team.PURPLE, isStaff, isAdmin);
            }
            else if (player.hasPermission("group.rose")) { //If the player is in pink team
                Players.addPlayer(player, Team.PINK, isStaff, isAdmin);
            }
            else { //Else if the player isn't in a team
                for (Player p : Bukkit.getOnlinePlayers()) { //Loop all players
                    if (p.hasPermission("theevent580.staff")) { //If the looped player is in the staff
                        Component newPlayer = Component.text('[')
                                .append(Component.text("TheEvent580 - Staff", ColorType.TITLE.getColor(),TextDecoration.BOLD)) //Setup
                                .append(Component.text("] Player \" //Send a message to staff" + player.getName() +
                                        " has joined the server but isn't assigned to a color (1st time playing / New color" +
                                        " not assigned ? / Server not whitelisted ?)", ColorType.TEXT.getColor())); //Setup part 2
                        p.sendMessage(newPlayer);
                    }
                }
            }
        }

        player.getInventory().clear();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Variables.equals("jeu_condi", Game.FINDER.getGameCondition()) || Variables.equals("jeu_condi", Game.BUILD_MASTERS.getGameCondition())){
                    player.setGameMode(GameMode.SURVIVAL);
                } else {
                    PlayerManager playerManager = Online.getPlayerManager(player);
                    if (Spectators.isSpectator(player)){
                        plugin.getLogger().info("Player " + player.getName() + " is a spectator");
                        if (playerManager != null){
                            if (Variables.equals("jeu_condi", Game.HUB.getGameCondition())){
                                Spectators.readySpectatorLobby(playerManager);
                            } else {
                                Spectators.readySpectatorGame(playerManager);
                            }
                        }
                    } else {
                        plugin.getLogger().info("Player " + player.getName() + " is a player");
                        if (Variables.equals("jeu_condi", Game.HUB.getGameCondition())){
                            player.performCommand("spawn");
                        }
                    }
                    player.setGameMode(GameMode.ADVENTURE);
                }
            }
        }.runTaskLater(plugin, 20L);

        playerManager = Online.getPlayerManager(player);

        if (playerManager != null){
            event.joinMessage(getPlayerJoinComponent(playerManager)); //Setup join message
        } else {
            event.joinMessage(Component.text("")); //Setup join message
        }

    }



    @EventHandler
    public void playerCompleteLoadingEvent(PlayerLoadingCompletedEvent event){
        Player player = event.getPlayer();

        plugin.getInstances().getAdvancementAPI().getProgressionTab().showTab(player);
        plugin.getInstances().getAdvancementAPI().getCustomTab().showTab(player);

        int game = 0;
        if (game < (int) Variables.getVariable("jeu")){
            int offset = 1;
            if ((int) Variables.getVariable("jeu") > 3){
                offset = 2;
            }
            while (game < (int) Variables.getVariable("jeu") + offset){
                BaseAdvancement adv = plugin.getInstances().getAdvancementAPI().getProgressionAdvancement(game);
                if (!adv.isGranted(player)){
                    adv.grant(player);
                }
                game++;
            }
        }
    }

    @NotNull
    private static Component getPlayerJoinComponent(PlayerManager playerManager) { //Setup join message

        return Component.text('[')
                .append(Component.text('+', ColorType.MC_LIME.getColor())) //Setup part 2
                .append(Component.text("] ", ColorType.TEXT.getColor())) //Setup part 3
                .append(playerManager.playerComponent()); //Add custom player head to the message
    }
}