package fr.thefox580.theevent5802.games.finder;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Finder {

    private static final Map<Player, Map<Material, Integer>> playersItemFound = new HashMap<>();

    /**
     * Method to call to start the pre-game setup
     * @param plugin The main class of the plugin.
     */
    public static void startPreGame(TheEvent580_2 plugin){
        Timer.setSeconds(90);
        Timer.setMaxSeconds(90);
        Timer.setEnum(Timer.TimerEnum.PRE_GAME);

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(new Location(Bukkit.getWorld("Finder"), -67, 64, -53));
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setGameMode(GameMode.ADVENTURE);
                    Bukkit.getOnlinePlayers().forEach(otherPlayer -> {
                        if (otherPlayer.getUniqueId() != player.getUniqueId()){
                            player.hidePlayer(plugin, otherPlayer);
                        }
                    });
                }
            }.runTaskLater(plugin, 20L);
        });

        Variables.setVariable("jeu_condi", Game.FINDER.getGameCondition());

        for (PlayerManager player : Online.getOnlinePlayers()){
            Map<Material, Integer> mapList = new HashMap<>();
            for (String set : FinderSets.getAllItemSets()){
                for (Material material : FinderSets.getItemSet(set)){
                    mapList.put(material, 0);
                }
            }
            playersItemFound.put(player.getOnlinePlayer(), mapList);
        }

        FinderTasks.preGameTask(plugin);

    }

    /**
     * Returns the amount of items collected in total.
     * @return The amount of items collected in total
     */
    public static int getTotalItemsFound(){
        int totalItemsFound = 0;
        for (Player key : playersItemFound.keySet()){
            for (Integer itemFound : playersItemFound.get(key).values()){
                totalItemsFound += itemFound;
            }
        }
        return totalItemsFound;
    }

    /**
     * Returns the amount of items a player has collected in total.
     * @param player The player who is collecting items
     * @return The amount of items this player has collected in total
     */
    public static int getPlayerItemsFound(Player player){
        int playerItemsFound = 0;
        for (Material material : playersItemFound.get(player).keySet()){
            playerItemsFound += getMaterialsFoundPlayer(player, material);
        }
        return playerItemsFound;
    }

    /**
     * Returns how many times a player has collected a certain item.
     * @param player The player who is collecting items
     * @param material The item
     * @return How many times the player has collected this item
     */
    public static int getMaterialsFoundPlayer(Player player, Material material){
        if (playersItemFound.get(player) != null && playersItemFound.get(player).get(material) != null){
            return playersItemFound.get(player).get(material);
        }
        return 0;
    }

    /**
     * Returns the amount of items the player has collected for the current set
     * @param player The player who is collecting items
     * @return The amount of item this player has collected for the current set
     */

    public static int getMaterialsFoundPlayerForCurrentSet(Player player){
        int count = 0;

        List<Material> currentSet = FinderSets.getItemSet(FinderSets.getCurrentItemSetName());

        for (Material material : currentSet){
            count += getMaterialsFoundPlayer(player, material);
        }
        return count;
    }

    /**
     * Returns the amount of items collected by all players for the current set.
     * @return The amount of items collected by all players for the current set
     */
    public static int getMaterialsFoundAllPlayersForCurrentSet(){
        int count = 0;

        List<Material> currentSet = FinderSets.getItemSet(FinderSets.getCurrentItemSetName());

        for (PlayerManager player : Players.getOnlinePlayerList()){
            for (Material material : currentSet){
                count += getMaterialsFoundPlayer(player.getOnlinePlayer(), material);
            }
        }
        return count;
    }

    /**
     * Sends a message to the player based on the item they collected and how many there was in that slot.
     * @param player The player who collected the item
     * @param material The item
     * @param amount The amount of item in that slot
     */
    public static void playerCollectedMaterialAmount(Player player, Material material, int amount){
        int currentAmount = getMaterialsFoundPlayer(player, material);
        playersItemFound.get(player).put(material, currentAmount+amount);

        boolean isGoldenItem = FinderSets.getCurrentItemSet().getFirst().equals(material);

        if (isGoldenItem){
            player.sendMessage(Component.text("[")
                    .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                    .append(Component.text("] Banked " + amount + "x "))
                    .append(ItemStack.of(material).displayName().color(ColorType.GOLD.getColor())));
            Points.addGamePoints(player, 3*Math.round(2*amount*Points.getMultiplier()));
            return;
        }
        player.sendMessage(Component.text("[")
                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                .append(Component.text("] Banked " + amount + "x "))
                .append(ItemStack.of(material).displayName().color(ColorType.SILVER.getColor())));
        Points.addGamePoints(player, Math.round(2*amount*Points.getMultiplier()));
    }

    /**
     * Returns how many times this item has been collected in total.
     * @param material The item
     * @return How many times this item has been collected in total
     */
    public static int getMaterialsFound(Material material){
        int totalItemsFound = 0;
        for (Player key : playersItemFound.keySet()){
            totalItemsFound += getMaterialsFoundPlayer(key, material);
        }
        return totalItemsFound;
    }

    /**
     * Opens a menu to see the items to collect for the current set, as well as how much the player has collected compared to all players, and the status on if the golden item can be banked or not
     * @param player The player who wands to see the menu
     */
    public static void openShopGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 9, Component.text(FinderSets.getCurrentItemSetName() + "'s Items"));

        List<Material> materialSet = FinderSets.getCurrentItemSet();

        ItemStack goldenItem = new ItemStack(materialSet.getFirst());
        ItemMeta goldenItemMeta = goldenItem.getItemMeta();
        goldenItemMeta.customName(goldenItem.effectiveName()
                .color(ColorType.GOLD.getColor())
                .decoration(TextDecoration.ITALIC, false));
        List<Component> lore = new ArrayList<>(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.getFirst()) + "/" + Finder.getMaterialsFound(materialSet.getFirst()), ColorType.RAINBOW.getColor()).decoration(TextDecoration.ITALIC, false)));

        if (FinderSets.isGoldenLocked()){
            lore.add(Component.text("Status : Locked", ColorType.MC_RED.getColor()).decoration(TextDecoration.ITALIC, false));
        } else {
            lore.add(Component.text("Status : Unlocked", ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false));
        }

        goldenItemMeta.lore(lore);
        goldenItem.setItemMeta(goldenItemMeta);

        ItemStack normalItem1 = new ItemStack(materialSet.get(1));
        ItemMeta normalItem1Meta = normalItem1.getItemMeta();
        normalItem1Meta.customName(normalItem1.effectiveName()
                .color(ColorType.SILVER.getColor())
                .decoration(TextDecoration.ITALIC, false));
        normalItem1Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(1)) + "/" + Finder.getMaterialsFound(materialSet.get(1)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
        normalItem1.setItemMeta(normalItem1Meta);

        ItemStack normalItem2 = new ItemStack(materialSet.get(2));
        ItemMeta normalItem2Meta = normalItem2.getItemMeta();
        normalItem2Meta.customName(normalItem2.effectiveName()
                .color(ColorType.SILVER.getColor())
                .decoration(TextDecoration.ITALIC, false));
        normalItem2Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(2)) + "/" + Finder.getMaterialsFound(materialSet.get(2)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
        normalItem2.setItemMeta(normalItem2Meta);

        ItemStack normalItem3 = new ItemStack(materialSet.get(3));
        ItemMeta normalItem3Meta = normalItem3.getItemMeta();
        normalItem3Meta.customName(normalItem3.effectiveName()
                .color(ColorType.SILVER.getColor())
                .decoration(TextDecoration.ITALIC, false));
        normalItem3Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(3)) + "/" + Finder.getMaterialsFound(materialSet.get(3)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
        normalItem3.setItemMeta(normalItem1Meta);

        ItemStack normalItem4 = new ItemStack(materialSet.get(4));
        ItemMeta normalItem4Meta = normalItem4.getItemMeta();
        normalItem4Meta.customName(normalItem4.effectiveName()
                .color(ColorType.SILVER.getColor())
                .decoration(TextDecoration.ITALIC, false));
        normalItem4Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(4)) + "/" + Finder.getMaterialsFound(materialSet.get(4)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
        normalItem4.setItemMeta(normalItem4Meta);

        ItemStack normalItem5 = new ItemStack(materialSet.get(5));
        ItemMeta normalItem5Meta = normalItem5.getItemMeta();
        normalItem5Meta.customName(normalItem5.effectiveName()
                .color(ColorType.SILVER.getColor())
                .decoration(TextDecoration.ITALIC, false));
        normalItem5Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(5)) + "/" + Finder.getMaterialsFound(materialSet.get(5)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
        normalItem5.setItemMeta(normalItem5Meta);

        ItemStack normalItem6 = new ItemStack(materialSet.get(6));
        ItemMeta normalItem6Meta = normalItem6.getItemMeta();
        normalItem6Meta.customName(normalItem6.effectiveName()
                .color(ColorType.SILVER.getColor())
                .decoration(TextDecoration.ITALIC, false));
        normalItem6Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(6)) + "/" + Finder.getMaterialsFound(materialSet.get(6)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
        normalItem6.setItemMeta(normalItem6Meta);

        ItemStack normalItem7 = new ItemStack(materialSet.get(7));
        ItemMeta normalItem7Meta = normalItem7.getItemMeta();
        normalItem7Meta.customName(normalItem7.effectiveName()
                .color(ColorType.SILVER.getColor())
                .decoration(TextDecoration.ITALIC, false));
        normalItem7Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(7)) + "/" + Finder.getMaterialsFound(materialSet.get(7)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
        normalItem7.setItemMeta(normalItem1Meta);

        ItemStack normalItem8 = new ItemStack(materialSet.get(8));
        ItemMeta normalItem8Meta = normalItem8.getItemMeta();
        normalItem8Meta.customName(normalItem8.effectiveName()
                .color(ColorType.SILVER.getColor())
                .decoration(TextDecoration.ITALIC, false));
        normalItem8Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(8)) + "/" + Finder.getMaterialsFound(materialSet.get(8)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
        normalItem8.setItemMeta(normalItem8Meta);

        ItemStack[] menu_items = {
                goldenItem,
                normalItem1,
                normalItem2,
                normalItem3,
                normalItem4,
                normalItem5,
                normalItem6,
                normalItem7,
                normalItem8};

        gui.setContents(menu_items);

        player.openInventory(gui);

    }

}
