package fr.thefox580.theevent5802.tasks.timer;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.build_masters.BuildMasters;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Conversions;
import fr.thefox580.theevent5802.utils.PluginStart;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class ShowBlocksTask implements Runnable{

    private final TheEvent580_2 plugin;

    public ShowBlocksTask(TheEvent580_2 plugin){
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, this, 0L, 2L);
        PluginStart.addTaskToList(task);
        this.plugin = plugin;
    }

    @Override
    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()){
            PersistentDataContainer pdc = player.getPersistentDataContainer();

            if (pdc.has(new NamespacedKey(plugin, "showBlocks")) && Boolean.TRUE.equals(pdc.get(new NamespacedKey(plugin, "showBlocks"), PersistentDataType.BOOLEAN))){
                Location playerLocation = player.getLocation();

                Block targetBlock = player.getTargetBlockExact(10);

                if (targetBlock != null){

                    String blockName;
                    
                    Material blockType = targetBlock.getType();
                    
                    if (blockType == Material.END_GATEWAY){
                        blockName = "Teleportation Portal";
                    } else if (List.of(Material.RED_CARPET, Material.ORANGE_CARPET,
                            Material.YELLOW_CARPET, Material.LIME_CARPET).contains(blockType)){
                        blockName = "Boost Pad";
                    } else if (List.of(Material.GREEN_CARPET, Material.CYAN_CARPET).contains(blockType)){
                        blockName = "Jump Pad";
                    } else if (List.of(Material.LIGHT_BLUE_CARPET, Material.BLUE_CARPET,
                            Material.PURPLE_CARPET, Material.MAGENTA_CARPET,
                            Material.PINK_CARPET, Material.WHITE_CARPET).contains(blockType)){
                        blockName = "Informational Panel";
                    } else if (List.of(Material.AIR, Material.BARRIER).contains(blockType)){
                        blockName = "";
                    } else {
                        blockName = PlainTextComponentSerializer.plainText().serialize(ItemStack.of(blockType).effectiveName());
                    }

                    if (player.getWorld().equals(BuildMasters.getWorld()) && playerLocation.z() > 3000){
                        player.sendActionBar(Component.text(blockName + " | Current inventory price : " + Conversions.convertSecondsToTimeOptimized(BuildMasters.inventoryPrice(player)), ColorType.MC_BLUE.getColor(), TextDecoration.BOLD));
                    } else {
                        player.sendActionBar(Component.text(blockName, ColorType.MC_BLUE.getColor(), TextDecoration.BOLD));
                    }
                }
            }
        }
    }
}
