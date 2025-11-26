package fr.thefox580.theevent5802.games.spleef;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import fr.thefox580.theevent5802.utils.Timer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Spleef {

    private static TheEvent580_2 plugin;

    public enum LayerType{

        NONE("No powerup", "You're spared, for now.", 0d, 0d),
        BIGGER("Bigger size", "You are now bigger", 2.5d, 6d),
        SMALLER("Smaller size", "You are now smaller", 0.5d, 3d),
        FASTER("Gotta go fast", "You are now faster", 0.15d, 0.6d),
        SLOWER("Gotta go slow", "You are now slower", 0.05d, 0.15d),
        BOING("Boing Boing", "You jump higher", 0.75d, 0d),
        GNIOB("gnioB gnioB", "You jump lower", 0.3d, 0d);

        private final String name;
        private final String description;
        private final double attributeValue;
        private final double extraValue;

        LayerType(String name, String description, double attributeValue, double extraValue){
            this.name = name;
            this.description = description;
            this.attributeValue = attributeValue;
            this.extraValue = extraValue;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public double getAttributeValue() {
            return attributeValue;
        }

        public double getExtraValue() {
            return extraValue;
        }
    }

    private static List<LayerType> availableLayers = new ArrayList<>();
    private static Map<Integer, LayerType> layerTypeMap = new HashMap<>();
    private static final World world = Bukkit.getWorld("Spleef");
    private static int currentRound = 0;

    public static int getCurrentRound(){
        return currentRound;
    }

    public static void startPreGame(TheEvent580_2 plugin){

        Spleef.plugin = plugin;
        availableLayers = new ArrayList<>();
        availableLayers.addAll(List.of(LayerType.BIGGER, LayerType.SMALLER, LayerType.FASTER, LayerType.SLOWER, LayerType.BOING, LayerType.GNIOB));
        layerTypeMap = new HashMap<>();

        currentRound++;

        if (currentRound >= 4){
            currentRound = 1;
        }
        
        if (currentRound == 1){
            Timer.setSeconds(90);
            Timer.setMaxSeconds(90);

            Variables.setVariable("jeu_condi", Game.SPLEEF.getGameCondition());
            Variables.setVariable("jeu", (int) Variables.getVariable("jeu") +1);
            Variables.setVariable("jeu_nom", Game.SPLEEF.getName());
            Variables.setVariable("jeu_logo", Game.SPLEEF.getIcon());
        } else {
            Timer.setSeconds(20);
            Timer.setMaxSeconds(20);
        }

        Timer.setEnum(Timer.TimerEnum.PRE_GAME);

        for (int x = 10; x>-11; x--){
            for (int z = 10; z>-11; z--){
                new Location(world, x, 255, z).getBlock().setType(Material.BARRIER);
            }
        }

        layerTypeMap.put(0, LayerType.NONE);
        layerTypeMap.put(1, LayerType.NONE);
        for (int layers = 3; layers > 0; layers--){
            layerTypeMap.put(layers, availableLayers.remove(new Random().nextInt(availableLayers.size())));
        }

        circle(0, 230, 0, 20, Material.LIGHT_BLUE_CONCRETE);
        circle(0, 220, 0, 30, Material.CYAN_CONCRETE);
        circle(0, 210, 0, 40, Material.LIGHT_BLUE_CONCRETE);
        circle(0, 200, 0, 50, Material.CYAN_CONCRETE);

        ItemStack pickaxe = ItemStack.of(Material.GOLDEN_PICKAXE);
        ItemMeta pickMeta = pickaxe.getItemMeta();
        pickMeta.setUnbreakable(true);
        pickMeta.addEnchant(Enchantment.EFFICIENCY, 10, true);
        pickMeta.customName(Component.text("Golden Pickaxe", ColorType.MC_AQUA.getColor(), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
        pickaxe.setItemMeta(pickMeta);

        for (PlayerManager playerManager : Online.getOnlinePlayers()){
            Player player = playerManager.getOnlinePlayer();

            if (player != null){
                PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                playerContainer.set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, true);
                player.getInventory().clear();
                player.teleport(new Location(world, 0, 256, 0));

                player.setFlying(false);
                player.setAllowFlight(false);

                Bukkit.getOnlinePlayers().forEach(loopPlayer -> {
                    player.showPlayer(plugin, loopPlayer);
                    loopPlayer.showPlayer(plugin, player);
                });

                player.give(pickaxe);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, true, false, false));
                player.setGameMode(GameMode.SURVIVAL);
            }
        }

        Spectators.readySpectatorsGame();

        SpleefTasks.preGameTask(plugin);
    }

    public static void circle(int xCenter, int yCenter, int zCenter, int radius, Material block){
        if (world != null){
            List<Location> blockRadius = new ArrayList<>();
            Location center = new Location(world, xCenter, yCenter, zCenter);
            for (int x = xCenter-(radius*2); x<xCenter+radius; x++){
                for (int z = zCenter-(radius*2); z<zCenter+radius; z++){
                    Location blockLoc = new Location(world, x, yCenter, z);
                    if (blockLoc.distanceSquared(center) <= Math.pow(radius, 2)-1){
                        blockRadius.add(blockLoc);
                    }
                }
            }

            for (Location blockLoc : blockRadius){
                blockLoc.getBlock().setType(block);
            }
        }
    }

    public static int getPlayerLayer(Player player){
        if (player.getY() < 198){
            return 0;
        } else if (player.getY() < 208){
            return 4;
        } else if (player.getY() < 218){
            return 3;
        } else if (player.getY() < 228){
            return 2;
        }
        return 1;
    }

    public static LayerType getLayerType(int layer){
        return layerTypeMap.get(layer);
    }

    public static LayerType getPlayerLayerType(Player player){
        return getLayerType(getPlayerLayer(player));
    }

    public static void decay(int xCenter, int yCenter, int zCenter, int radius){

        final int[] size = {radius};

        Material block = new Location(Bukkit.getWorld("Spleef"), xCenter, yCenter, zCenter).getBlock().getType();

        new BukkitRunnable() {
            @Override
            public void run() {

                Particle.DustOptions dustOptions2 = new Particle.DustOptions(Color.RED, 5);
                ParticlesShaped.createCircle(new Location(Bukkit.getWorld("Spleef"), xCenter, yCenter + size[0] +1, zCenter), radius, Particle.DUST, dustOptions2, 10, 3, 0.05f);

                Spleef.circle(xCenter, yCenter, zCenter, radius, Material.AIR);
                Spleef.circle(xCenter, yCenter, zCenter, size[0], Material.RED_STAINED_GLASS);
                Spleef.circle(xCenter, yCenter, zCenter, size[0] -1, block);

                size[0]--;
                if (size[0] < 0){
                    this.cancel();
                } else if (Timer.getSeconds() == 0){
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 5*20L);

        final int[] particleSize = {0};

        new BukkitRunnable() {
            @Override
            public void run() {

                if (particleSize[0] == 5){
                    particleSize[0] = 0;
                }

                particleSize[0]++;

                Particle.DustOptions dustOptions1 = new Particle.DustOptions(Color.ORANGE, 2  + particleSize[0]/2f);
                ParticlesShaped.createCircleOutside(new Location(Bukkit.getWorld("Spleef"), xCenter, yCenter, zCenter), size[0] +1, Particle.DUST, dustOptions1, 5, 1, 2f);

                if (size[0] < 0){
                    this.cancel();
                } else if (Timer.getSeconds() == 0){
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

}
