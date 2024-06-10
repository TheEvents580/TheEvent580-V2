package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class minecraftle implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public minecraftle(TheEvent580_2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player){

            if(player.getWorld().getName().equals("world") || player.getAllowFlight()){

                FileConfiguration config = this.plugin.getConfig();

                player.openWorkbench(null, plugin.isEnabled());

                Material[] materialsInInv = {Material.OAK_PLANKS, Material.COBBLESTONE, Material.STONE, Material.GLASS,
                        Material.WHITE_WOOL, Material.STICK, Material.DIAMOND, Material.GOLD_INGOT, Material.IRON_INGOT,
                        Material.REDSTONE, Material.QUARTZ, Material.OAK_SLAB, Material.OAK_LOG,
                        Material.IRON_NUGGET, Material.REDSTONE_TORCH, Material.STRING, Material.LEATHER, Material.COAL};

                Random randomNumber = new Random();

                Material randomItem = getPossibleItems()[randomNumber.nextInt(getPossibleItems().length)];

                for (Player loopedPlayer : Bukkit.getOnlinePlayers()){
                    if (loopedPlayer.isOp()){
                        loopedPlayer.sendMessage(Component.text(player.getName()+" started a Minecraftle and needs to find : ")
                                .append(Component.translatable(randomItem.getItemTranslationKey(), TextColor.color(188, 215, 29), TextDecoration.BOLD)));
                    }
                }

                config.set("minecraftle_game." + player.getUniqueId(), randomItem.getItemTranslationKey());
                config.set("minecraftle_game_tries." + player.getUniqueId(), 0);

                for (int i = 0; i < materialsInInv.length; i++){
                    player.getInventory().setItem(i+9,new ItemStack(materialsInInv[i], 64));
                }

                plugin.saveConfig();

            }
        }

        return true;
    }

    public static Material[] getPossibleItems(){

        Material[] possibleItems = {Material.STICK, Material.TORCH, Material.CRAFTING_TABLE, Material.FURNACE,
                Material.CHEST, Material.LADDER, Material.OAK_FENCE, Material.OAK_BOAT, Material.OAK_SLAB,
                Material.COBBLESTONE_SLAB, Material.STONE_SLAB, Material.OAK_SIGN, Material.OAK_DOOR,
                Material.IRON_DOOR, Material.OAK_WOOD, Material.NOTE_BLOCK, Material.DIAMOND_BLOCK,
                Material.GOLD_BLOCK, Material.IRON_BLOCK, Material.COAL_BLOCK, Material.STONE_BRICKS,
                Material.OAK_STAIRS, Material.COBBLESTONE_STAIRS, Material.STONE_STAIRS, Material.COBBLESTONE_WALL,
                Material.REDSTONE_BLOCK, Material.QUARTZ_BLOCK, Material.DIORITE, Material.CHISELED_BOOKSHELF,
                Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE,
                Material.DIAMOND_PICKAXE, Material. WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE,
                Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.WOODEN_SHOVEL, Material.STONE_SHOVEL,
                Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.WOODEN_HOE,
                Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE,
                Material.FISHING_ROD, Material.COMPASS, Material.CLOCK, Material.BUCKET, Material.SHEARS,
                Material.LEATHER_HELMET, Material.IRON_HELMET, Material.GOLDEN_HELMET, Material.DIAMOND_HELMET,
                Material.LEATHER_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE,
                Material.DIAMOND_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.IRON_LEGGINGS,
                Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.LEATHER_BOOTS, Material.IRON_BOOTS,
                Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS, Material.WOODEN_SWORD, Material.STONE_SWORD,
                Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.BOW,
                Material.LEATHER_HORSE_ARMOR, Material.SHIELD, Material.OAK_PRESSURE_PLATE,
                Material.STONE_PRESSURE_PLATE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE,
                Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.OAK_TRAPDOOR, Material.OAK_FENCE_GATE,
                Material.OAK_BUTTON, Material.STONE_BUTTON, Material.LEVER, Material.REPEATER,
                Material.REDSTONE_TORCH, Material.JUKEBOX, Material.PISTON, Material.MINECART, Material.RAIL,
                Material.POWERED_RAIL, Material.TRIPWIRE_HOOK, Material.ACTIVATOR_RAIL, Material.DAYLIGHT_DETECTOR,
                Material.DROPPER, Material.HOPPER, Material.IRON_TRAPDOOR, Material.OBSERVER, Material.BOWL,
                Material.WHITE_BED, Material.PAINTING, Material.GLASS_PANE, Material.IRON_BARS, Material.GOLD_NUGGET,
                Material.IRON_NUGGET, Material.IRON_INGOT, Material.ITEM_FRAME, Material.WHITE_CARPET,
                Material.WHITE_BANNER, Material.CAMPFIRE, Material.BARREL, Material.COMPOSTER,
                Material.SMITHING_TABLE, Material.STONECUTTER, Material.GRINDSTONE, Material.LOOM, Material.CHAIN,
                Material.WHITE_WOOL, Material.GLASS_BOTTLE, Material.CAULDRON};

        return possibleItems;
    }
}
