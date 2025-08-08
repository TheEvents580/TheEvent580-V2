package fr.thefox580.theevent5802.tasks;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class SpawnTask implements Runnable{

    private final BukkitTask task;
    private final List<Integer> modes = List.of(0, 1, 7, 8, 10, 11);

    public SpawnTask(TheEvent580_2 plugin){
        this.task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 1L);
        PluginStart.addTaskToList(task);
    }

    @Override
    public void run() {

        if (modes.contains(Timer.getEnum().getMode())){
            for (Player player : Bukkit.getOnlinePlayers()){

                if (player.getLocation().getY() < 4.5){
                    Block block = new Location(player.getWorld(), player.getX(), -6, player.getZ()).getBlock();
                    if (block.getType() == Material.STRUCTURE_BLOCK){
                        player.spawnParticle(Particle.END_ROD, player.getLocation(), 3, 0.1 , 0, 0.1, 0.1);
                        player.removePotionEffect(PotionEffectType.LEVITATION);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 2, 3, false, false, false));
                    }
                }

                if (player.getLocation().getBlock().getType() == Material.WATER){
                    player.performCommand("spawn");
                }

                Location location_under = player.getLocation().clone().add(0, -0.5, 0);

                if (location_under.getBlock().getType() == Material.VERDANT_FROGLIGHT) {
                    player.teleport(new Location(player.getWorld(), 193.5, 69, -381.5, 0, 0));
                } else if (location_under.getBlock().getType() == Material.OCHRE_FROGLIGHT){
                  player.performCommand("spawn");
                } else if (location_under.getBlock().getType() == Material.DIRT_PATH || location_under.getBlock().getType() == Material.PETRIFIED_OAK_SLAB){
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 2, false, false, false));
                } else if (location_under.getBlock().getType() == Material.JIGSAW){
                    if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType() == Material.CARROT_ON_A_STICK){
                        player.sendActionBar(Component.text("Your ", ColorType.TEXT.getColor())
                                .append(player.getInventory().getHelmet().displayName())
                                .append(Component.text(" has been removed!", ColorType.TEXT.getColor())));
                        player.getInventory().setHelmet(new ItemStack(Material.AIR));
                        if (Players.isPlayer(player)){
                            PlayerManager pManager = Players.getPlayerManager(player);
                            assert pManager != null;
                            pManager.setColorType(pManager.getTeam().getColorType());
                        } else {
                            PlayerManager pManager = Spectators.getPlayerManager(player);
                            assert pManager != null;
                            pManager.setColorType(pManager.getTeam().getColorType());
                        }
                    }
                }
            }
        } else {
            task.cancel();
        }

    }
}
