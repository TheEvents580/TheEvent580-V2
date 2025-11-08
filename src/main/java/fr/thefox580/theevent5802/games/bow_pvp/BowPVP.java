package fr.thefox580.theevent5802.games.bow_pvp;

import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
    private static final World world = Bukkit.getWorld("Bow_PVP");

    public static Component getPlayerInvincibilityTimeComp(Player player){
        double time = 0;

        if (invincibleTime.get(player) != null){
            time = invincibleTime.get(player);
        }

        return Component.text(Math.round(Math.ceil(time)) + "s", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false);
    }

    public static void respawnPlayer(Player player){
        invincibleTime.put(player, 5d);
        tpPlayerToRespawnLocation(player, Objects.requireNonNull(world).getWorldBorder().getSize() < 100);
        Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue()-2);
        player.heal(20, EntityRegainHealthEvent.RegainReason.CUSTOM);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 255, false, false, false));
        player.getInventory().setHelmet(ItemStack.of(Material.NETHERITE_HELMET));
        player.getInventory().setChestplate(ItemStack.of(Material.NETHERITE_CHESTPLATE));
        player.getInventory().setLeggings(ItemStack.of(Material.NETHERITE_LEGGINGS));
        player.getInventory().setBoots(ItemStack.of(Material.NETHERITE_BOOTS));
    }

    private static void tpPlayerToRespawnLocation(Player player, boolean postBorderReduction){
        if (postBorderReduction){
            player.teleport(respawnLocations.get(new Random().nextInt(7)));
        } else {
            player.teleport(respawnLocations.get(new Random().nextInt(respawnLocations.size())));
        }
    }

}
