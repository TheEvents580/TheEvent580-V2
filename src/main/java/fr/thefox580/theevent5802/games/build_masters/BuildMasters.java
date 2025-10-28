package fr.thefox580.theevent5802.games.build_masters;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.PlayerManager;
import fr.thefox580.theevent5802.utils.Players;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildMasters {

    private static final World world = Bukkit.getWorld("build_masters");
    private static final Map<Player, Integer> playerBuilds = new HashMap<>();
    private static final Map<Player, Integer> playerPlot = new HashMap<>();
    private static final List<Material> launchersBlocks = List.of(Material.LIME_CARPET,
            Material.RED_CARPET, Material.YELLOW_CARPET, Material.ORANGE_CARPET, Material.GREEN_CARPET,
            Material.CYAN_CARPET);
    private static final List<Material> unbreakableBlocks = List.of(Material.SMOOTH_QUARTZ, Material.QUARTZ_PILLAR,
            Material.IRON_BLOCK, Material.GOLD_BLOCK, Material.EMERALD_BLOCK, Material.DIAMOND_BLOCK,
            Material.DARK_OAK_TRAPDOOR, Material.CHISELED_QUARTZ_BLOCK, Material.QUARTZ_BRICKS, Material.LIME_CARPET,
            Material.RED_CARPET, Material.YELLOW_CARPET, Material.ORANGE_CARPET, Material.GREEN_CARPET,
            Material.CYAN_CARPET);

    /**
     * Method to call to start the pre-game setup
     * @param plugin The main class of the plugin.
     */
    public static void startPreGame(TheEvent580_2 plugin){

        int plot = 1;

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            Player player = playerManager.getOnlinePlayer();
            playerPlot.put(player, plot);
            playerBuilds.put(player, 1);

            showBuildWall(0, plot);
            showBuildFloor(0, plot);

            changePlotText(player);

            plot++;
        }


        BuildMastersTasks.preGameTask(plugin);

    }

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

    public static int getPlayerPlot(Player player){
        return playerPlot.get(player);
    }

    public static int getCurrentPlayerBuild(Player player){
        return playerBuilds.get(player);
    }

    public static List<Location> getPlotLocations(int plot){

        while (plot > 25){
            plot -= 25;
        }

        int x = -1 + 21*((plot-1)/5);
        int y = 129;
        int z = 987 + 21*((plot-1)%5);

        List<Location> locationList = new ArrayList<>();

        locationList.add(new Location(world, x+3.5, y, z+11, -90,  0));
        locationList.add(new Location(world, x+6, y-1, z+6));
        locationList.add(new Location(world, x+15, y-1, z+15));
        locationList.add(new Location(world, x+20, y+2, z+6));
        locationList.add(new Location(world, x+20, y+11, z+15));
        locationList.add(new Location(world, x+11, y+21, z+11));

        return locationList;
    }

    public static List<Location> getBuildWall(int buildNumber){

        int x = 1 - (6*buildNumber);
        int y = 129;
        int z = 10001;

        List<Location> locationList = new ArrayList<>();

        locationList.add(new Location(world, x, y, z));
        locationList.add(new Location(world, x, y+9, z+9));

        return locationList;

    }

    public static void showBuildWall(int buildNumber, int plot){

        if (buildNumber > 10) {
            buildNumber = -1;
        }

        List<Location> playerLocationList = getPlotLocations(plot);
        List<Location> buildLocation = getBuildWall(buildNumber);

        Location playerFloor1 = playerLocationList.get(3).clone();
        Location playerFloor2 = playerLocationList.get(4).clone();

        Location buildFloor1 = buildLocation.getFirst().clone();

        int xPlayer = playerFloor1.getBlockX();
        int xBuild = buildFloor1.getBlockX();

        int yBuild = buildFloor1.getBlockY();
        for (int yPlayer = playerFloor1.getBlockY(); yPlayer <= playerFloor2.getBlockY(); yPlayer++){
            int zBuild = buildFloor1.getBlockZ();
            for (int zPlayer = playerFloor1.getBlockZ(); zPlayer <= playerFloor2.getBlockZ(); zPlayer++){

                Location currentBlockPlayer = new Location(world, xPlayer, yPlayer, zPlayer);
                Location currentBlockBuild = new Location(world, xBuild, yBuild, zBuild);

                currentBlockPlayer.getBlock().setType(currentBlockBuild.getBlock().getType());

                zBuild++;
            }
            yBuild++;
        }

    }

    public static boolean isFloorBuildCorrect(int buildNumber, int plot){

        List<Location> playerLocationList = getPlotLocations(plot);
        List<Location> buildLocation = getBuildFloor(buildNumber);

        Location playerFloor1 = playerLocationList.get(1).clone();
        Location playerFloor2 = playerLocationList.get(2).clone();

        Location buildFloor1 = buildLocation.getFirst().clone();

        int yPlayer = playerFloor1.getBlockY();
        int yBuild = buildFloor1.getBlockY();

        int xBuild = buildFloor1.getBlockX();
        for (int xPlayer = playerFloor1.getBlockX(); xPlayer <= playerFloor2.getBlockX(); xPlayer++){
            int zBuild = buildFloor1.getBlockZ();
            for (int zPlayer = playerFloor1.getBlockZ(); zPlayer <= playerFloor2.getBlockZ(); zPlayer++){

                Location currentBlockPlayer = new Location(world, xPlayer, yPlayer, zPlayer);
                Location currentBlockBuild = new Location(world, xBuild, yBuild, zBuild);

                if (currentBlockBuild.getBlock().getType() != currentBlockPlayer.getBlock().getType()){
                    return false;
                }

                zBuild++;
            }
            xBuild++;
        }

        return true;

    }

    public static void showBuildFloor(int buildNumber, int plot){

        if (buildNumber > 10) {
            buildNumber = -1;
        }

        List<Location> playerLocationList = getPlotLocations(plot);
        List<Location> buildLocation = getBuildFloor(buildNumber);

        Location playerFloor1 = playerLocationList.get(1).clone();
        Location playerFloor2 = playerLocationList.get(2).clone();

        Location buildFloor1 = buildLocation.getFirst().clone();

        int yPlayer = playerFloor1.getBlockY();
        int yBuild = buildFloor1.getBlockY();

        int xBuild = buildFloor1.getBlockX();
        for (int xPlayer = playerFloor1.getBlockX(); xPlayer <= playerFloor2.getBlockX(); xPlayer++){
            int zBuild = buildFloor1.getBlockZ();
            for (int zPlayer = playerFloor1.getBlockZ(); zPlayer <= playerFloor2.getBlockZ(); zPlayer++){

                Location currentBlockPlayer = new Location(world, xPlayer, yPlayer, zPlayer);
                Location currentBlockBuild = new Location(world, xBuild, yBuild, zBuild);

                currentBlockPlayer.getBlock().setType(currentBlockBuild.getBlock().getType());

                zBuild++;
            }
            xBuild++;
        }

    }

    public static void clearFloorBuild(int plot){

        List<Location> locationList = getPlotLocations(plot);

        Location floor1 = locationList.get(1).clone();
        Location floor2 = locationList.get(2).clone();

        int y = floor1.getBlockY();

        for (int x = floor1.getBlockX(); x <= floor2.getBlockX(); x++){
            for (int z = floor1.getBlockZ(); z <= floor2.getBlockZ(); z++){
                Location currentBlock = new Location(world, x, y, z);
                currentBlock.getBlock().setType(Material.AIR);
            }
        }

    }

    public static void setNextBuild(Player player){
        int plot = getPlayerPlot(player);
        clearFloorBuild(plot);
        playerBuilds.put(player, getCurrentPlayerBuild(player)+1);
        showBuildWall(getCurrentPlayerBuild(player), plot);

        if (getCurrentPlayerBuild(player) > 10){
            showBuildWall(-1, plot);
            showBuildFloor(-1, plot);
            // TODO player has completed the game
        }
    }

    public static List<Location> getBuildFloor(int buildNumber){

        int x = 3;
        int y = 136 + (buildNumber*7);
        int z = 10019;

        List<Location> locationList = new ArrayList<>();

        locationList.add(new Location(world, x, y, z));
        locationList.add(new Location(world, x+9, y, z+9));

        return locationList;

    }

    public static void tpToMall(Player player){

        player.teleport(new Location(world, 4.5, 131, 5000.5, -90, 0));

    }

    public static void toToPlot(Player player){
        player.teleport(getPlotLocations(getPlayerPlot(player)).getFirst());
    }

    private static void changePlotText(Player player){
        PlayerManager playerManager = Players.getPlayerManager(player);

        if (playerManager != null){

            int plot = getPlayerPlot(player);

            Location billboardLocation = getPlotLocations(plot).get(5);

            TextDisplay plotBillboard = null;

            if (world != null){
                for (TextDisplay textDisplay : world.getNearbyEntitiesByType(TextDisplay.class, billboardLocation, 5)){
                    plotBillboard = textDisplay;
                    break;
                }
            }

            if (plotBillboard != null){
                plotBillboard.text(Component.text("Plot n°" + plot + " : " + playerManager.getTeam().getIcon())
                        .append(Component.text(" " + playerManager.getName(), playerManager.getColorType().getColor())));
            }
        }
    }


}
