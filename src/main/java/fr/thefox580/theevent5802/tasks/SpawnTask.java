package fr.thefox580.theevent5802.tasks;

import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.commands.Spawn;
import fr.thefox580.theevent5802.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class SpawnTask implements Runnable{

    private final TheEvent580_2 plugin;
    private final BukkitTask task;
    private final List<Integer> modes = List.of(0, 1, 7, 8, 10, 11);

    public SpawnTask(TheEvent580_2 plugin){
        this.plugin = plugin;
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
                        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 2, 2, false, false, false));
                    }
                }

                if (player.getLocation().getBlock().getType() == Material.WATER){
                    if (player.getGameMode() == GameMode.ADVENTURE){
                        Spawn.tp(player);
                    }
                }

                Location location_under = player.getLocation().clone().add(0, -0.5, 0);

                if (location_under.getBlock().getType() == Material.VERDANT_FROGLIGHT) {
                    player.teleport(new Location(player.getWorld(), 193.5, 69, -381.5, 0, 0));
                } else if (location_under.getBlock().getType() == Material.OCHRE_FROGLIGHT){
                  Spawn.tp(player);
                } else if (location_under.getBlock().getType() == Material.DIRT_PATH || location_under.getBlock().getType() == Material.PETRIFIED_OAK_SLAB){
                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 15, 1, false, false, false));
                } else if (location_under.getBlock().getType() == Material.JIGSAW){
                    PlayerManager pManager = Online.getPlayerManager(player);
                    if (pManager.getFlag().getType() == Material.CARROT_ON_A_STICK){
                        player.sendActionBar(Component.text("Your ", ColorType.TEXT.getColor())
                                .append(pManager.getFlag().displayName())
                                .append(Component.text(" has been removed!", ColorType.TEXT.getColor())));
                        pManager.setFlag(new ItemStack(Material.AIR));
                        player.getInventory().setHelmet(pManager.getFlag());
                        if (pManager.isAdmin()){
                            pManager.setColorType(Team.ADMIN.getColorType());
                        } else if (pManager.isStaff()){
                            pManager.setColorType(Team.STAFF.getColorType());
                        } else {
                            pManager.setColorType(pManager.getTeam().getColorType());
                        }
                    }
                } else if (location_under.getBlock().getType() == Material.TARGET){
                    PlayerManager pManager = Online.getPlayerManager(player);
                    pManager.setParkourCompletion(pManager.getParkourCompletion()+1);
                    Spawn.tp(player);

                    player.sendMessage(Component.text(PlaceholderAPI.setPlaceholders(player, "%theevent580_parkour.solo%"), ColorType.SUBTEXT.getColor()));

                    SpawnParkour.updateText();

                    BaseAdvancement parkourAdv = plugin.getInstances().getAdvancementAPI().getCustomAdvancement(AdvancementsEnum.HUBPARKOUR);
                    if (!parkourAdv.isGranted(player)){
                        parkourAdv.grant(player);
                        plugin.getInstances().getAdvancementAPI().getCounterAdvancement().incrementProgression(player);
                    }

                }
            }
        } else {
            task.cancel();
        }

    }
}
