package fr.thefox580.theevent5802.games.bow_pvp;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import fr.thefox580.theevent5802.utils.Timer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class BowPVP {

    private static final Map<Player, Double> invincibleTime = new HashMap<>();
    private static final World world = Bukkit.getWorld("Bow_PVP");
    private static final List<Location> respawnLocations = List.of(
            new Location(world, 1.5, 128, 0.5), new Location(world, 7.5, 128, 0.5), new Location(world, -4.5, 128, 0.5),
            new Location(world, 1.5, 128, -5.5), new Location(world, 7.5, 128, 6.5), new Location(world, -3.5, 122.5, -25.5),
            new Location(world, -24.5, 122.5, 4.5), new Location(world, 5.5, 122.5, 26.5), new Location(world, 57.5, 122.5, -3.5),

            new Location(world, 35.5, 111, 4.5), new Location(world, 5.5, 111, -33.5), new Location(world, -32.5, 111, -3.5),
            new Location(world, -2.5, 111, -34.5), new Location(world, 35.5, 141, 4.5), new Location(world, 5.5, 141, -33.5),
            new Location(world, -32.5, 141, -3.5), new Location(world, -2.5, 141, -34.5));
    private static TheEvent580_2 plugin;

    public static void startPreGame(TheEvent580_2 plugin) {
        Timer.setSeconds(90);
        Timer.setMaxSeconds(90);
        Timer.setEnum(Timer.TimerEnum.PRE_GAME);

        BowPVP.plugin = plugin;

        if (world != null){
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
            world.getWorldBorder().setSize(83);
        }

        for (PlayerManager playerManager : Online.getOnlinePlayers()){
            Player player = playerManager.getOnlinePlayer();

            if (player != null){
                player.getInventory().clear();
                if (playerManager.isPlayer()){
                    invincibleTime.put(player, 0d);
                    Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(8);
                    player.heal(16d, EntityRegainHealthEvent.RegainReason.CUSTOM);
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, true, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 255, true, false));

                player.teleport(new Location(world, 1.5, 169, 0.5));
            }
        }

        Variables.setVariable("jeu_condi", Game.BOW_PVP.getGameCondition());
        Variables.setVariable("jeu", (int) Variables.getVariable("jeu") +1);
        Variables.setVariable("jeu_nom", Game.BOW_PVP.getName());
        Variables.setVariable("jeu_logo", Game.BOW_PVP.getIcon());

        Spectators.readySpectatorsGame();

        BowPVPTasks.preGameTask(plugin);
    }

    public static int nbPlayersAlive(){
        int count = 0;

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            if (playerManager.isAlive(plugin)){
                count++;
            }
        }

        return count;
    }

    public static List<Player> topPlayers(){
        List<Player> topPlayers = new ArrayList<>();

        double max = 0d;

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            if (playerManager.isAlive(plugin)){
                Player player = playerManager.getOnlinePlayer();
                if (player != null){
                    double playerMaxHealth = Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue();
                    if (playerMaxHealth >= max){
                        if (playerMaxHealth > max){
                            max = playerMaxHealth;
                            topPlayers.clear();
                        }
                        topPlayers.add(player);
                    }
                }
            }
        }

        return topPlayers;
    }

    public static Component getPlayerInvincibilityTimeComp(Player player){
        double time = getPlayerInvincibilityTime(player);

        if (time > 0){
            return Component.text(Math.round(Math.ceil(time)) + "s", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false);
        }
        return Component.text("Alive", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false);

    }

    public static double getPlayerInvincibilityTime(Player player){
        double time = 0;

        if (invincibleTime.get(player) != null){
            time = invincibleTime.get(player);
        }

        return time;
    }

    public static boolean isPlayerInvincible(Player player){
        return getPlayerInvincibilityTime(player) != 0;
    }

    public static void respawnPlayer(Player victim, Player attacker, Material weapon){
        boolean dead = Objects.requireNonNull(victim.getAttribute(Attribute.MAX_HEALTH)).getValue() <= 2d;
        if (weapon == Material.WOODEN_SWORD){
            Objects.requireNonNull(attacker.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(Objects.requireNonNull(attacker.getAttribute(Attribute.MAX_HEALTH)).getValue()+2);
            attacker.heal(20, EntityRegainHealthEvent.RegainReason.CUSTOM);

            if (dead){
                Points.addGamePoints(attacker, Math.round(45*Points.getMultiplier()));
            } else {
                Points.addGamePoints(attacker, Math.round(30*Points.getMultiplier()));
            }

        } else if (dead){
            Points.addGamePoints(attacker, Math.round(60*Points.getMultiplier()));
        } else {
            Points.addGamePoints(attacker, Math.round(40*Points.getMultiplier()));
        }

        respawnPlayer(victim);
    }

    public static void respawnPlayer(Player player){
        invincibleTime.put(player, 5d);
        player.setGlowing(false);

        player.spigot().respawn();
        tpPlayerToRespawnLocation(player, Objects.requireNonNull(world).getWorldBorder().getSize() < 68);
        boolean dead = Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue() <= 2d;

        if (!dead){
            Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue()-2);
        }
        player.heal(20, EntityRegainHealthEvent.RegainReason.CUSTOM);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 255, false, false, false));

        if (dead){
            Spectators.readySpectatorGame(Online.getPlayerManager(player));

            for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                Player loopPlayer = playerManager.getOnlinePlayer();

                if (loopPlayer != null){
                    Points.addGamePoints(loopPlayer, Math.round(10*Points.getMultiplier()));
                }

            }

            return;
        }

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            if (playerManager.isAlive(plugin)){
                Player loopPlayer = playerManager.getOnlinePlayer();

                if (loopPlayer != null){
                    if (loopPlayer.getUniqueId() != player.getUniqueId()){
                        Points.addGamePoints(loopPlayer, Math.round(5*Points.getMultiplier()));
                    }
                }

            }
        }

        player.getInventory().setHelmet(ItemStack.of(Material.NETHERITE_HELMET));
        player.getInventory().setChestplate(ItemStack.of(Material.NETHERITE_CHESTPLATE));
        player.getInventory().setLeggings(ItemStack.of(Material.NETHERITE_LEGGINGS));
        player.getInventory().setBoots(ItemStack.of(Material.NETHERITE_BOOTS));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.getInventory().setHelmet(ItemStack.of(Material.AIR));
                player.getInventory().setChestplate(ItemStack.of(Material.AIR));
                player.getInventory().setLeggings(ItemStack.of(Material.AIR));
                player.getInventory().setBoots(ItemStack.of(Material.AIR));
            }
        }.runTaskLater(plugin, 5*20L);
    }

    private static void tpPlayerToRespawnLocation(Player player, boolean postBorderReduction){
        if (postBorderReduction){
            player.teleport(respawnLocations.get(new Random().nextInt(8)));
        } else {
            player.teleport(respawnLocations.get(new Random().nextInt(respawnLocations.size())));
        }
    }

    public static void reduceWorldBorder(){
        if (world != null){
            world.getWorldBorder().setSize(68, 60);
        }
    }

}
