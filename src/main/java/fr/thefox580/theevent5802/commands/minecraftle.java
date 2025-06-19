package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class minecraftle implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public minecraftle(TheEvent580_2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player){

            if(player.getWorld().getName().equals("world") || player.getAllowFlight()){

                FileConfiguration config = this.plugin.getConfig();

                player.openWorkbench(null, true);

                Material[] materialsInInv = {Material.OAK_PLANKS, Material.COBBLESTONE, Material.STONE, Material.GLASS,
                        Material.WHITE_WOOL, Material.STICK, Material.DIAMOND, Material.GOLD_INGOT, Material.IRON_INGOT,
                        Material.REDSTONE, Material.QUARTZ, Material.OAK_SLAB, Material.OAK_LOG,
                        Material.IRON_NUGGET, Material.REDSTONE_TORCH, Material.STRING, Material.LEATHER, Material.COAL};

                Random randomNumber = new Random();

                Material randomItem = getPossibleItems()[randomNumber.nextInt(getPossibleItems().length)];

                for (Player loopedPlayer : Bukkit.getOnlinePlayers()){
                    if (loopedPlayer.isOp()){
                        loopedPlayer.sendMessage(Component.text(player.getName()+" started a Minecraftle and needs to find : ")
                                .append(Component.translatable(Objects.requireNonNull(randomItem.getItemTranslationKey()),
                                        ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD)));
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

        return new Material[]{
                Material.ACTIVATOR_RAIL, Material.BARREL, Material.BOW, Material.BOWL, Material.BUCKET,
                Material.BUNDLE, Material.CAMPFIRE, Material.CAULDRON, Material.CHAIN, Material.CHEST,
                Material.CHISELED_BOOKSHELF, Material.CLOCK, Material.COAL_BLOCK, Material.COBBLESTONE_SLAB,
                Material.COBBLESTONE_STAIRS, Material.COBBLESTONE_WALL, Material.COMPASS, Material.COMPOSTER,
                Material.CRAFTING_TABLE, Material.DAYLIGHT_DETECTOR, Material.DIAMOND_AXE, Material.DIAMOND_BLOCK,
                Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_HOE,
                Material.DIAMOND_LEGGINGS, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_SWORD,
                Material.DIORITE, Material.DROPPER, Material.FISHING_ROD, Material.FURNACE, Material.GLASS_BOTTLE,
                Material.GLASS_PANE, Material.GOLD_BLOCK, Material.GOLD_NUGGET, Material.GOLDEN_AXE,
                Material.GOLDEN_BOOTS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET, Material.GOLDEN_HOE,
                Material.GOLDEN_LEGGINGS, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL, Material.GOLDEN_SWORD,
                Material.GRINDSTONE, Material.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.HOPPER, Material.IRON_AXE,
                Material.IRON_BARS, Material.IRON_BLOCK, Material.IRON_BOOTS, Material.IRON_CHESTPLATE,
                Material.IRON_DOOR, Material.IRON_HELMET, Material.IRON_HOE, Material.IRON_INGOT,
                Material.IRON_LEGGINGS, Material.IRON_NUGGET, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
                Material.IRON_SWORD, Material.IRON_TRAPDOOR, Material.ITEM_FRAME, Material.JUKEBOX, Material.LADDER,
                Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET,
                Material.LEATHER_HORSE_ARMOR, Material.LEATHER_LEGGINGS, Material.LEVER,
                Material.LIGHT_WEIGHTED_PRESSURE_PLATE, Material.LOOM, Material.MINECART, Material.NOTE_BLOCK,
                Material.OAK_BOAT, Material.OAK_BUTTON, Material.OAK_DOOR, Material.OAK_FENCE, Material.OAK_FENCE_GATE,
                Material.OAK_PRESSURE_PLATE, Material.OAK_SIGN, Material.OAK_SLAB, Material.OAK_STAIRS,
                Material.OAK_TRAPDOOR, Material.OAK_WOOD, Material.OBSERVER, Material.PAINTING, Material.PISTON,
                Material.POWERED_RAIL, Material.QUARTZ_BLOCK, Material.RAIL, Material.REDSTONE_BLOCK,
                Material.REDSTONE_TORCH, Material.REPEATER, Material.SHEARS, Material.SHIELD, Material.SMITHING_TABLE,
                Material.STICK, Material.STONECUTTER, Material.STONE_AXE, Material.STONE_BRICKS, Material.STONE_BUTTON,
                Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_PRESSURE_PLATE, Material.STONE_SHOVEL,
                Material.STONE_SLAB, Material.STONE_STAIRS, Material.STONE_SWORD, Material.TORCH,
                Material.TRIPWIRE_HOOK, Material.WHITE_BANNER, Material.WHITE_BED, Material.WHITE_CARPET,
                Material.WHITE_WOOL, Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE,
                Material.WOODEN_SHOVEL, Material.WOODEN_SWORD
        };
    }

    public static Map<Material, ArrayList<ArrayList<Material>>> getShapedRecipes(){
        Map<Material, ArrayList<ArrayList<Material>>> recipes = new HashMap<>();

        // Initialize every crafting recipe to an ArrayList of nothing, because there could be multiple crafting recipes
        // of a single item.

        for (Material material : getPossibleItems()){
            recipes.put(material, new ArrayList<>());
        }

        // The recipe of each item in getPossibleItems() is saved as (i.e. Diamond Sword):
        //[Material.AIR, Material.DIAMOND, Material.AIR, Material.AIR, Material.DIAMOND, Material.AIR, Material.AIR, Material.STICK, Material.AIR]

        Character[] chrList = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

        for (Material material : getPossibleItems()){
            ArrayList<Material> ingredients = new ArrayList<>();
            ItemStack item = new ItemStack(material);
            for (Recipe recipe : Bukkit.getServer().getRecipesFor(item)){
                if (recipe instanceof ShapedRecipe shapedRecipe){
                    for (Character chr : chrList){
                        if (shapedRecipe.getIngredientMap().get(chr) == null){
                            ingredients.add(Material.AIR);
                        } else {
                            ingredients.add(shapedRecipe.getIngredientMap().get(chr).getType());
                        }
                    }
                }
            }
            recipes.get(material).add(ingredients);
        }

        return recipes;
    }
}
