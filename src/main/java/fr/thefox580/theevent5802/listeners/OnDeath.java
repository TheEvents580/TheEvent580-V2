package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.arms_race.ArmsRace;
import fr.thefox580.theevent5802.games.bow_pvp.BowPVP;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class OnDeath implements Listener {

    private final TheEvent580_2 plugin;

    public OnDeath(TheEvent580_2 plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    static String[] killMessage = {"'s game stopped working...", " should uninstall the game.", " didn't know the game.",
            " fell off.", " should go back to studying.", " would be better at Fortnite.", " did not buy enough shares.",
            " did not eat an apple a day.", " blue screened."}; //Set an Array with death messages

    public static String killMessageFinal(){ //Function to choose a random message
        Random random = new Random(); //Set a random number
        int randomNumberInArray = random.nextInt(killMessage.length); //Choose a random number between 0-8
        return killMessage[randomNumberInArray]; //Returns the String for the message
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){ //On player death
        Player victim = event.getEntity();

        PlayerManager victimPlayerManager = Online.getPlayerManager(victim);

        Player killer = victim.getKiller();

        event.deathMessage(Component.text("")); //Clear death message

        if (killer == null) { //If player dies to a NPC
            Component message = Component.text("[") //Set custom death message
                    .append(Component.text("☠", ColorType.MC_RED.getColor())) //Set custom death message
                    .append(Component.text("] ", ColorType.TEXT.getColor())) //Set custom death message
                    .append(victimPlayerManager.playerComponent())
                    .append(Component.text(killMessageFinal(), ColorType.MC_LIGHT_GRAY.getColor())); //Set custom message

            event.deathMessage(message);

        } else {
            PlayerManager killerPlayerManager = Online.getPlayerManager(killer);
            Component message = Component.text('[') //Setup custom death message
                    .append(Component.text('☠', ColorType.MC_RED.getColor())) /* ☠ */
                    .append(Component.text("] ") //I mean
                    .append(victimPlayerManager.playerComponent()) //Add custom victim head to message
                    .append(Component.text(" was killed by ", ColorType.TEXT.getColor())) //Do I need to explain what this does ?
                    .append(killerPlayerManager.playerComponent())); //Add custom killer head to message

            event.deathMessage(message);
        }

        if (Variables.equals("jeu_condi", Game.ARMS_RACE.getGameCondition())){

            boolean fromKnife;

            int pointsLost = Math.round(Points.getGamePoints(victim)*(0.05f + ArmsRace.getPlayerKills(victim)/100f));
            Points.removeGamePoints(victim, pointsLost);
            if (killer != null){
                Points.addGamePoints(killer, pointsLost);
                fromKnife = killer.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD;

                killer.getInventory().clear();
                ItemStack item = ArmsRace.getNextPlayerItem(killer);
                if (ArmsRace.getPlayerKills(killer) < ArmsRace.getWeaponCount()-1){
                    killer.give(ArmsRace.getWeapon(0));
                }
                killer.give(item);
                if (List.of(Material.BOW, Material.CROSSBOW).contains(item.getType())){
                    killer.give(ItemStack.of(Material.ARROW));
                }

                if (ArmsRace.getPlayerKills(killer) % 2 == 0){
                    Points.addGamePoints(killer, Math.round(20*Points.getMultiplier()));
                }

                if (ArmsRace.getPlayerKills(killer) == ArmsRace.getWeaponCount()-1){
                    PlayerManager killerPlayerManager = Online.getPlayerManager(killer);

                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.ARMS_RACE.getName(), Game.ARMS_RACE.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(killerPlayerManager.playerComponent())
                            .append(Component.text(" is on their last weapon!", ColorType.TEXT.getColor())));
                }

            } else {
                fromKnife = false;
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    ArmsRace.randomRespawn(victim, fromKnife);
                }
            }.runTaskLater(plugin, 2L);
        } else if (Variables.equals("jeu_condi", Game.BOW_PVP.getGameCondition())){
            if (killer != null){
                Material weapon = killer.getInventory().getItemInMainHand().getType();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        BowPVP.respawnPlayer(victim, killer, weapon);
                    }
                }.runTaskLater(plugin, 2L);
            } else {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        BowPVP.respawnPlayer(victim);
                    }
                }.runTaskLater(plugin, 2L);
            }
        }

    }

    @EventHandler
    public void onRespawnEvent(PlayerRespawnEvent event){
        Player player = event.getPlayer();

        if (Variables.equals("jeu_condi", Game.BOW_PVP.getGameCondition())){
            BowPVP.respawnPlayer(player);
        } else if (Variables.equals("jeu_condi", Game.FINDER.getGameCondition())){
            player.teleport(new Location(Bukkit.getWorld("Finder"), -67, 64, -53));
        }
    }

}

