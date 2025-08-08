package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class OnJoinEvent implements Listener {

    private final TheEvent580_2 plugin;

    public OnJoinEvent(TheEvent580_2 plugin) {
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

        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, false, false, false));

        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        if (!playerContainer.has(new NamespacedKey(plugin, "points"))){
            Points.initialisePoints(player);
        }

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

        Component component = Component.translatable("%nox_uuid%"+player.getUniqueId()+",false,0,-1,1","\uD83D\uDC64").color(ColorType.TEXT.getColor()); //Setup custom player head

        if (playerManager == null){

            if (player.hasPermission("group.spectators")){ //If the player is a spectator
                Spectators.addSpectator(player);
                playerManager = Spectators.getPlayerManager(player);
            }

            else if (player.hasPermission("group.rouge")) { //If the player is in red team
                Players.addPlayer(player, Team.RED);
                playerManager = Players.getPlayerManager(player);

            }
            else if (player.hasPermission("group.orange")) { //If the player is in orange team
                Players.addPlayer(player, Team.ORANGE);
                playerManager = Players.getPlayerManager(player);

            }
            else if (player.hasPermission("group.jaune")) { //If the player is in yellow team
                Players.addPlayer(player, Team.YELLOW);
                playerManager = Players.getPlayerManager(player);

            }
            else if (player.hasPermission("group.vert")) { //If the player is in lime / green team
                Players.addPlayer(player, Team.LIME);
                playerManager = Players.getPlayerManager(player);

            }
            else if (player.hasPermission("group.bleu_clair")) { //If the player is in light blue team
                Players.addPlayer(player, Team.LIGHT_BLUE);
                playerManager = Players.getPlayerManager(player);

            }
            else if (player.hasPermission("group.bleu")) { //If the player is in blue team
                Players.addPlayer(player, Team.BLUE);
                playerManager = Players.getPlayerManager(player);

            }
            else if (player.hasPermission("group.violet")) { //If the player is in purple team
                Players.addPlayer(player, Team.PURPLE);
                playerManager = Players.getPlayerManager(player);

            }
            else if (player.hasPermission("group.rose")) { //If the player is in pink team
                Players.addPlayer(player, Team.PINK);
                playerManager = Players.getPlayerManager(player);

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

        if (Spectators.isSpectator(player)){
            assert playerManager != null;

            if (Objects.equals(Variables.getVariable("jeu_condi"), 0)){
                Spectators.readySpectatorLobby(playerManager);
            } else {
                Spectators.readySpectatorGame(playerManager);
            }
        }

        TextColor color = Objects.requireNonNull(playerManager).getColorType().getColor();
        Component message = getPlayerJoinComponent(component, player, color);
        event.joinMessage(message); //Setup join message

        new BukkitRunnable() {
            @Override
            public void run() {
                player.performCommand("spawn");
            }
        }.runTaskLater(plugin, 2*20L);
    }

    @NotNull
    private static Component getPlayerJoinComponent(Component component, Player player, TextColor color) { //Setup join message

        return Component.text('[')
                .append(Component.text('+', ColorType.MC_LIME.getColor())) //Setup part 2
                .append(Component.text("] ", ColorType.TEXT.getColor())) //Setup part 3
                .append(component) //Add custom player head to the message
                .append(Component.text(" "+ player.getName(), color)); //Add player's name to the message
    }
}