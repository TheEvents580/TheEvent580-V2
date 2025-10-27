package fr.thefox580.theevent5802.games.finder;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinderSets {

    private static int itemSetSelected = 0;
    private static boolean goldenLocked = true;
    private static final List<String> itemSets = List.of("Desert Set", "Mesa Set", "Forest Set", "Swamp Set", "Mining Set", "Ancient Set", "Village Set", "Redstone Set");
    private static final List<String> itemSetsAlreadyUsed = new ArrayList<>();

    private static final List<Material> desertSet = List.of(Material.SUSPICIOUS_SAND, Material.SANDSTONE, Material.SANDSTONE_SLAB, Material.SANDSTONE_STAIRS, Material.SANDSTONE_WALL, Material.CHISELED_SANDSTONE, Material.SMOOTH_SANDSTONE, Material.SMOOTH_SANDSTONE_SLAB, Material.SMOOTH_SANDSTONE_STAIRS);
    private static final List<Material> mesaSet = List.of(Material.LIME_GLAZED_TERRACOTTA, Material.CUT_RED_SANDSTONE, Material.RED_SANDSTONE_STAIRS, Material.RED_SANDSTONE_WALL, Material.SMOOTH_RED_SANDSTONE, Material.TERRACOTTA, Material.YELLOW_TERRACOTTA, Material.BLACK_TERRACOTTA, Material.LIGHT_GRAY_TERRACOTTA);
    private static final List<Material> forestSet = List.of(Material.BEEHIVE, Material.OAK_LEAVES, Material.OAK_TRAPDOOR, Material.OAK_HANGING_SIGN, Material.BIRCH_LOG, Material.BIRCH_BOAT, Material.BIRCH_DOOR, Material.ROSE_BUSH, Material.LILAC, Material.LILY_OF_THE_VALLEY);
    private static final List<Material> swampSet = List.of(Material.BONE_BLOCK, Material.LILY_PAD, Material.CLAY, Material.VINE, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.BLUE_ORCHID, Material.DEAD_BUSH, Material.SEAGRASS);
    private static final List<Material> miningSet = List.of(Material.DEEPSLATE_EMERALD_ORE, Material.COPPER_BLOCK, Material.IRON_INGOT, Material.RAW_GOLD, Material.LAPIS_ORE, Material.REDSTONE, Material.DIAMOND, Material.LAVA_BUCKET, Material.OBSIDIAN);
    private static final List<Material> ancientSet = List.of(Material.SKELETON_SKULL, Material.CHISELED_DEEPSLATE, Material.GRAY_WOOL, Material.CANDLE, Material.DARK_OAK_FENCE, Material.POLISHED_DEEPSLATE_WALL, Material.SOUL_LANTERN, Material.SCULK_SENSOR, Material.PACKED_ICE);
    private static final List<Material> villageSet = List.of(Material.BELL, Material.WHITE_BED, Material.HAY_BLOCK, Material.COMPOSTER, Material.BOOKSHELF, Material.BEETROOT_SEEDS, Material.GRINDSTONE, Material.OAK_FENCE, Material.MOSSY_COBBLESTONE);
    private static final List<Material> redstoneSet = List.of(Material.CALIBRATED_SCULK_SENSOR, Material.REDSTONE_TORCH, Material.REDSTONE_BLOCK, Material.REDSTONE_LAMP, Material.PISTON, Material.REPEATER, Material.TARGET, Material.TRIPWIRE_HOOK, Material.NOTE_BLOCK);

    public static void newRandomItemSet(){
        if (itemSetsAlreadyUsed.size() == itemSets.size()){
            itemSetsAlreadyUsed.clear();
        }
        int random = new Random().nextInt(itemSets.size());
        if (itemSetsAlreadyUsed.contains(itemSets.get(random))){
            newRandomItemSet();
            return;
        }
        setGoldenLocked(true);
        itemSetSelected = random;
        itemSetsAlreadyUsed.add(itemSets.get(itemSetSelected));
    }

    public static boolean isGoldenLocked(){return goldenLocked;}

    public static void setGoldenLocked(boolean locked){goldenLocked = locked;}

    public static String getCurrentItemSetName(){
        return itemSets.get(itemSetSelected);
    }

    public static List<String> getAllItemSets(){
        return itemSets;
    }

    public static List<Material> getItemSet(String itemSet){
        return switch (itemSet){
            case "Desert Set" -> desertSet;
            case "Mesa Set" -> mesaSet;
            case "Forest Set" -> forestSet;
            case "Swamp Set" -> swampSet;
            case "Mining Set" -> miningSet;
            case "Ancient Set" -> ancientSet;
            case "Village Set" -> villageSet;
            case "Redstone Set" -> redstoneSet;
            default -> throw new IllegalStateException("Unexpected value: " + getCurrentItemSetName());
        };
    }

    public static List<Material> getCurrentItemSet(){
        return getItemSet(getCurrentItemSetName());
    }

}
