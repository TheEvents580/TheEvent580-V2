package fr.thefox580.theevent5802.games.bow_pvp;

import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class BowPVP {

    private static final Map<Player, Double> invincibleTime = new HashMap<>();
    private static final List<Location> respawnLocations = List.of();

    public static Component getPlayerInvincibilityTime(Player player){
        //int time = Math.toIntExact(Math.round(invincibleTime.get(player)));
        int time = Math.toIntExact(Math.round(3.85));

        return Component.text(time + "s", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false);
    }

    public static void respawnPlayer(Player player){
        invincibleTime.put(player, 5d);
        tpPlayerToRespawnLocation(player, Objects.requireNonNull(Bukkit.getWorld("Bow_PVP")).getWorldBorder().getSize() < 100);
        Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue()-2);
        player.heal(20, EntityRegainHealthEvent.RegainReason.CUSTOM);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, false, false, false));
        player.getInventory().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
        player.getInventory().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
        player.getInventory().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
        player.getInventory().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
    }

    private static void tpPlayerToRespawnLocation(Player player, boolean postBorderReduction){
        if (postBorderReduction){
            player.teleport(respawnLocations.get(new Random().nextInt(8)));
        } else {
            player.teleport(respawnLocations.get(new Random().nextInt(respawnLocations.size()+1)));
        }
    }

}
