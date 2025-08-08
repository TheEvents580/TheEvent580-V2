package fr.thefox580.theevent5802.games.build_masters;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildMasters {

    private static final Map<Player, Integer> playerBuilds = new HashMap<>();
    private static final List<Material> launchersBlocks = List.of(Material.LIME_CARPET,
            Material.RED_CARPET, Material.YELLOW_CARPET, Material.ORANGE_CARPET, Material.GREEN_CARPET,
            Material.CYAN_CARPET);
    private static final List<Material> unbreakableBlocks = List.of(Material.SMOOTH_QUARTZ, Material.QUARTZ_PILLAR,
            Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.EMERALD_BLOCK, Material.DIAMOND_BLOCK,
            Material.DARK_OAK_TRAPDOOR, Material.CHISELED_QUARTZ_BLOCK, Material.QUARTZ_BRICKS, Material.LIME_CARPET,
            Material.RED_CARPET, Material.YELLOW_CARPET, Material.ORANGE_CARPET, Material.GREEN_CARPET,
            Material.CYAN_CARPET);

    public static int getTotalCompletedBuild(){
        int total = 0;

        for (Integer buildAt : playerBuilds.values()){

            if (buildAt-1 > 0){
                total += buildAt-1;
            }
        }

        return total;
    }

    public static List<Material> getUnbreakableBlocks(){
        return unbreakableBlocks;
    }

    public static List<Material> getLaunchersBlocks(){
        return launchersBlocks;
    }

}
