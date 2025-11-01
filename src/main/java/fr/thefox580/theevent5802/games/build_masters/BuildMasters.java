package fr.thefox580.theevent5802.games.build_masters;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import me.clip.placeholderapi.libs.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

    private static TheEvent580_2 plugin;

    /**
     * Method to call to start the pre-game setup
     * @param plugin The main class of the plugin.
     */
    public static void startPreGame(TheEvent580_2 plugin){

        BossBar mallBossbar = BossbarManager.getBossbar("mall");

        if (mallBossbar != null){
            BossbarManager.setBossbarVisibility(mallBossbar, false);
        }

        BuildMasters.plugin = plugin;

        int plot = 1;

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            Player player = playerManager.getOnlinePlayer();

            if (player != null){
                playerPlot.put(player, plot);
                playerBuilds.put(player, 1);

                showBuildWall(0, plot);
                showBuildFloor(0, plot);

                changePlotText(player);

                toToPlot(player);

                playerManager.getTimer().setSeconds(10*60);

                plot++;

                PersistentDataContainer pdc = player.getPersistentDataContainer();

                if (Boolean.FALSE.equals(pdc.get(new NamespacedKey(plugin, "showBlocks"), PersistentDataType.BOOLEAN))){
                    Bukkit.dispatchCommand(player, "showBlocks");
                }

            }
        }


        BuildMastersTasks.preGameTask(plugin);

    }

    public static World getWorld(){ return world; }

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
        int completedBuild = getCurrentPlayerBuild(player);
        int priceOldBuild = buildPrice(completedBuild);
        Points.addGamePoints(player, (int) Math.ceil((priceOldBuild - (playersCompletedBuild(completedBuild)/(float) Players.getMaxPlayerCount())* priceOldBuild) * (1+(completedBuild/10f)) * Points.getMultiplier()));

        player.sendMessage(Component.text("[")
                .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                .append(Component.text("] You completed build n°" + completedBuild + "!", ColorType.TEXT.getColor())));

        clearFloorBuild(plot);
        playerBuilds.put(player, completedBuild+1);
        completedBuild = getCurrentPlayerBuild(player);
        showBuildWall(completedBuild, plot);

        PlayerManager playerManager = Online.getPlayerManager(player);
        if (completedBuild > 10){
            showBuildWall(-1, plot);
            showBuildFloor(-1, plot);

            playerManager.getTimer().setSeconds(0);
        } else {
            for (int price = buildPrice(getCurrentPlayerBuild(player)) + 2*60; price > 0; price--){
                playerManager.getTimer().add1Second();
            }
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

        player.teleport(new Location(world, 54., 131, 5000.5, player.getYaw(), 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, PotionEffect.INFINITE_DURATION, 2, true, false));

    }

    public static void toToPlot(Player player){
        player.teleport(getPlotLocations(getPlayerPlot(player)).getFirst());
        player.removePotionEffect(PotionEffectType.SPEED);

        int cost = inventoryPrice(player);

        PlayerManager playerManager = Online.getPlayerManager(player);

        if (playerManager != null){
            if (cost >= playerManager.getTimer().getSeconds()){
                playerManager.getTimer().setSeconds(0);
            } else {
                playerManager.getTimer().setSeconds(playerManager.getTimer().getSeconds()-cost);
            }
        }

    }

    private static void changePlotText(Player player){
        PlayerManager playerManager = Online.getPlayerManager(player);

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

    public static List<Material> materialsInShop(){
        return List.of(Material.BLACK_CONCRETE, Material.CYAN_CONCRETE, Material.BLUE_CONCRETE,
                Material.BROWN_CONCRETE, Material.GRAY_CONCRETE, Material.GREEN_CONCRETE,
                Material.LIME_CONCRETE, Material.MAGENTA_CONCRETE, Material.ORANGE_CONCRETE,
                Material.PINK_CONCRETE, Material.LIGHT_BLUE_CONCRETE, Material.LIGHT_GRAY_CONCRETE,
                Material.PURPLE_CONCRETE, Material.RED_CONCRETE, Material.WHITE_CONCRETE,
                Material.YELLOW_CONCRETE, Material.BLACK_TERRACOTTA, Material.CYAN_TERRACOTTA, Material.BLUE_TERRACOTTA,
                Material.BROWN_TERRACOTTA, Material.GRAY_TERRACOTTA, Material.GREEN_TERRACOTTA,
                Material.LIME_TERRACOTTA, Material.MAGENTA_TERRACOTTA, Material.ORANGE_TERRACOTTA,
                Material.PINK_TERRACOTTA, Material.LIGHT_BLUE_TERRACOTTA, Material.LIGHT_GRAY_TERRACOTTA,
                Material.PURPLE_TERRACOTTA, Material.RED_TERRACOTTA, Material.WHITE_TERRACOTTA,
                Material.YELLOW_TERRACOTTA, Material.TERRACOTTA, Material.TERRACOTTA, Material.WAXED_COPPER_BLOCK, Material.WAXED_EXPOSED_COPPER, Material.WAXED_WEATHERED_COPPER,
                Material.WAXED_OXIDIZED_COPPER, Material.WAXED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER,
                Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_CHISELED_COPPER,
                Material.WAXED_EXPOSED_CHISELED_COPPER, Material.WAXED_WEATHERED_CHISELED_COPPER, Material.WAXED_OXIDIZED_CHISELED_COPPER);
    }

    public static boolean isInShop(Material material){
        return materialsInShop().contains(material);
    }

    public static int inventoryPrice(Player player){
        int price = 0;

        for (ItemStack item : player.getInventory().getContents()){
            if (item != null){

                if (isInShop(item.getType())){

                    if (!item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "bought"))) {

                        if (List.of(Material.BLACK_CONCRETE, Material.CYAN_CONCRETE, Material.BLUE_CONCRETE,
                                Material.BROWN_CONCRETE, Material.GRAY_CONCRETE, Material.GREEN_CONCRETE,
                                Material.LIME_CONCRETE, Material.MAGENTA_CONCRETE, Material.ORANGE_CONCRETE,
                                Material.PINK_CONCRETE, Material.LIGHT_BLUE_CONCRETE, Material.LIGHT_GRAY_CONCRETE,
                                Material.PURPLE_CONCRETE, Material.RED_CONCRETE, Material.WHITE_CONCRETE,
                                Material.YELLOW_CONCRETE).contains(item.getType())) {
                            price += item.getAmount();

                        } else if (List.of(Material.BLACK_TERRACOTTA, Material.CYAN_TERRACOTTA, Material.BLUE_TERRACOTTA,
                                Material.BROWN_TERRACOTTA, Material.GRAY_TERRACOTTA, Material.GREEN_TERRACOTTA,
                                Material.LIME_TERRACOTTA, Material.MAGENTA_TERRACOTTA, Material.ORANGE_TERRACOTTA,
                                Material.PINK_TERRACOTTA, Material.LIGHT_BLUE_TERRACOTTA, Material.LIGHT_GRAY_TERRACOTTA,
                                Material.PURPLE_TERRACOTTA, Material.RED_TERRACOTTA, Material.WHITE_TERRACOTTA,
                                Material.YELLOW_TERRACOTTA, Material.TERRACOTTA, Material.TERRACOTTA).contains(item.getType())){
                            price += item.getAmount()*2;

                        } else if (List.of(Material.WAXED_COPPER_BLOCK, Material.WAXED_EXPOSED_COPPER, Material.WAXED_WEATHERED_COPPER,
                                Material.WAXED_OXIDIZED_COPPER, Material.WAXED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER,
                                Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_CHISELED_COPPER,
                                Material.WAXED_EXPOSED_CHISELED_COPPER, Material.WAXED_WEATHERED_CHISELED_COPPER, Material.WAXED_OXIDIZED_CHISELED_COPPER).contains(item.getType())){
                            price += item.getAmount()*3;


                        }
                    }
                }
            }
        }

        return price;

    }

    public static int buildPrice(int build){

        List<Location> buildLocation = getBuildFloor(build);

        Location startLocation = buildLocation.getFirst();
        Location endLocation = buildLocation.getLast();

        int price = 0;

        int y = startLocation.getBlockY();

        for (int x = startLocation.getBlockX(); x <= endLocation.getBlockX(); x++){
            for (int z = startLocation.getBlockZ(); z <= endLocation.getBlockZ(); z++){
                Location currentBlock = new Location(world, x, y, z);

                if (List.of(Material.BLACK_CONCRETE, Material.CYAN_CONCRETE, Material.BLUE_CONCRETE,
                        Material.BROWN_CONCRETE, Material.GRAY_CONCRETE, Material.GREEN_CONCRETE,
                        Material.LIME_CONCRETE, Material.MAGENTA_CONCRETE, Material.ORANGE_CONCRETE,
                        Material.PINK_CONCRETE, Material.LIGHT_BLUE_CONCRETE, Material.LIGHT_GRAY_CONCRETE,
                        Material.PURPLE_CONCRETE, Material.RED_CONCRETE, Material.WHITE_CONCRETE,
                        Material.YELLOW_CONCRETE).contains(currentBlock.getBlock().getType())) {
                    price += 1;

                } else if (List.of(Material.BLACK_TERRACOTTA, Material.CYAN_TERRACOTTA, Material.BLUE_TERRACOTTA,
                        Material.BROWN_TERRACOTTA, Material.GRAY_TERRACOTTA, Material.GREEN_TERRACOTTA,
                        Material.LIME_TERRACOTTA, Material.MAGENTA_TERRACOTTA, Material.ORANGE_TERRACOTTA,
                        Material.PINK_TERRACOTTA, Material.LIGHT_BLUE_TERRACOTTA, Material.LIGHT_GRAY_TERRACOTTA,
                        Material.PURPLE_TERRACOTTA, Material.RED_TERRACOTTA, Material.WHITE_TERRACOTTA,
                        Material.YELLOW_TERRACOTTA, Material.TERRACOTTA, Material.TERRACOTTA).contains(currentBlock.getBlock().getType())){
                    price += 2;

                } else if (List.of(Material.WAXED_COPPER_BLOCK, Material.WAXED_EXPOSED_COPPER, Material.WAXED_WEATHERED_COPPER,
                        Material.WAXED_OXIDIZED_COPPER, Material.WAXED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER,
                        Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER, Material.WAXED_CHISELED_COPPER,
                        Material.WAXED_EXPOSED_CHISELED_COPPER, Material.WAXED_WEATHERED_CHISELED_COPPER, Material.WAXED_OXIDIZED_CHISELED_COPPER).contains(currentBlock.getBlock().getType())){
                    price += 3;

                }
            }
        }

        return price;

    }

    public static List<Player> getRemainingPlayers(){
        List<Player> playerList = new ArrayList<>();

        for (Player player : playerPlot.keySet()){
            PlayerManager playerManager = Online.getPlayerManager(player);

            if (playerManager != null){
                if (playerManager.getTimer().getSeconds() == 0){

                    playerList.add(player);

                }
            }
        }

        return playerList;

    }

    public static boolean hasEveryoneFinished(){
        return getRemainingPlayers().isEmpty();
    }

    public static void refillMall(){
        Location start = new Location(world, 33, 129, 4873);
        Location end = new Location(world, 149, 129, 5112);

        int y = start.clone().getBlockY();


        for (int x = start.clone().getBlockX(); x < end.clone().getBlockX(); x++){
            for (int z = start.clone().getBlockZ(); z < end.clone().getBlockZ(); z++){

                Location currentBlock = new Location(world, x, y, z);

                if (!List.of(Material.AIR, Material.LIGHT, Material.PUMPKIN).contains(currentBlock.getBlock().getType())){
                    Location refillBlock = currentBlock.clone();
                    refillBlock.setY(131);
                    refillBlock.getBlock().setType(currentBlock.getBlock().getType());

                    refillBlock.setY(132);
                    refillBlock.getBlock().setType(currentBlock.getBlock().getType());

                    refillBlock.setY(133);
                    refillBlock.getBlock().setType(currentBlock.getBlock().getType());

                    refillBlock.setY(136);
                    refillBlock.getBlock().setType(currentBlock.getBlock().getType());

                    refillBlock.setY(137);
                    refillBlock.getBlock().setType(currentBlock.getBlock().getType());

                    refillBlock.setY(138);
                    refillBlock.getBlock().setType(currentBlock.getBlock().getType());
                }

            }
        }

        Bukkit.broadcast(Component.text("[")
                .append(Component.text(Game.BUILD_MASTERS.getName(), Game.BUILD_MASTERS.getColorType().getColor()))
                .append(Component.text("] The mall has been refilled! ", ColorType.TEXT.getColor())));

    }

    public static void openGiveTimeMenu(Player player, int seconds, int page){
        String time = seconds + " seconds";

        if (seconds == 60){
            time = "1 minute";
        } else if (seconds == 60*3){
            time = "3 minutes";
        }

        Inventory gui = Bukkit.createInventory(null, 9*5, Component.text("Give " + time));

        if (page == 1){
            Team.RED.createTeamInInv(gui, 1, false);
            Team.ORANGE.createTeamInInv(gui, 2, false);
            Team.YELLOW.createTeamInInv(gui, 3, false);
            Team.LIME.createTeamInInv(gui, 4, false);

            ItemStack next = new ItemStack(Material.LIME_CARPET);
            ItemMeta nextMeta = next.getItemMeta();
            nextMeta.displayName(Component.text("Next Page", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
            next.setItemMeta(nextMeta);

            gui.setItem(gui.getSize()-1, next);
        } else {
            Team.LIGHT_BLUE.createTeamInInv(gui, 1, false);
            Team.BLUE.createTeamInInv(gui, 2, false);
            Team.PURPLE.createTeamInInv(gui, 3, false);
            Team.PINK.createTeamInInv(gui, 4, false);

            ItemStack last = new ItemStack(Material.ORANGE_CARPET);
            ItemMeta lastMeta = last.getItemMeta();
            lastMeta.displayName(Component.text("Next Page", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
            last.setItemMeta(lastMeta);

            gui.setItem(gui.getSize()-9, last);
        }

        player.openInventory(gui);

    }

    public static void giveKit(Player player){
        ItemStack pickaxe = ItemStack.of(Material.NETHERITE_PICKAXE);
        ItemMeta pickaxeMeta = pickaxe.getItemMeta();

        pickaxeMeta.setUnbreakable(true);
        pickaxeMeta.addEnchant(Enchantment.EFFICIENCY, 2, false);
        pickaxeMeta.customName(Component.text("Unbreakable Pickaxe", ColorType.MC_BLUE.getColor()));

        pickaxe.setItemMeta(pickaxeMeta);

        player.give(pickaxe);
    }

    public static int playersCompletedBuild(int build){
        int count = 0;

        for (Player player : playerBuilds.keySet()){
            if (playerBuilds.get(player) > build){
                count++;
            }
        }

        return count;
    }

}
