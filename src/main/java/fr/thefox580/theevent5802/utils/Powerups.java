package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

import javax.annotation.Nullable;
import java.util.*;

public enum Powerups {

    SWAP_PLAYERS_AND_SPECTATORS(0, "Swap players and spectators", Material.CARROT_ON_A_STICK, "swap_players_and_spec", Rarity.LEGENDARY, List.of("Right click to swap the players and the spectators!")),
    YOU_CHOOSE_THE_GAME(1, "You choose the game", Material.CARROT_ON_A_STICK, "you_choose_the_game", Rarity.LEGENDARY, List.of("Right click in a game plot to choose it!")),
    BLOCK_A_GAME(2, "Block a game", Material.CARROT_ON_A_STICK, "block_a_game", Rarity.EPIC, List.of("Right click in a game category to block this game!")),
    VOTE_3(3, "Vote x3", Material.DIAMOND_CHESTPLATE, "", Rarity.RARE, List.of("Equip the chestplate to multiply your vote !")),
    KILL_A_PLAYER_SWORD(4, "Kill a player", Material.NETHERITE_SWORD, "", Rarity.RARE, List.of("⚠ ONE TIME USE ⚠", "Use your sword to kill a player!")),
    KILL_A_PLAYER_CROSSBOW(5, "Kill a player", Material.CROSSBOW, "", Rarity.RARE, List.of("⚠ ONE TIME USE ⚠", "Use your crossbow to kill a player!")),
    GRAB_A_PLAYER(6, "Grab a player", Material.MINECART, "", Rarity.RARE, List.of("Click on a player to grab them on you!")),
    VOTE_2(7, "Vote x2", Material.GOLDEN_CHESTPLATE, "", Rarity.COMMON, List.of("Equip the chestplate to multiply your vote !")),
    SPAWN_A_FOX(8, "Spawn a Fox", Material.FOX_SPAWN_EGG, "", Rarity.COMMON, List.of("Right click to spawn a fox that will count as a +1 vote !"));

    private final int id;
    private final String name;
    private final List<String> lore;
    private final Material material;
    private final String customModelString;
    private final Rarity rarity;

    Powerups(int id, String name, Material material, String customModelString, Rarity rarity, List<String> lore){
        this.id = id;
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.customModelString = customModelString;
        this.rarity = rarity;
    }

    public static @Nullable Powerups getRandomPowerup(){
        int random = new Random().nextInt(100);
        random++;

        if (random == 54){
            return SWAP_PLAYERS_AND_SPECTATORS;
        } else if (random == 66) {
            return YOU_CHOOSE_THE_GAME;
        } else if (random <= 14) {
            return VOTE_2;
        } else if (20 <= random && random <= 29) {
            return VOTE_3;
        } else if (30 <= random && random <= 39) {
            return KILL_A_PLAYER_SWORD;
        } else if (40 <= random && random <= 49) {
            return KILL_A_PLAYER_CROSSBOW;
        } else if (70 <= random && random <= 79) {
            return GRAB_A_PLAYER;
        } else if (81 <= random && random <= 85) {
            return BLOCK_A_GAME;
        } else if (86 <= random) {
            return SPAWN_A_FOX;
        } else {
            return null;
        }
    }

    public ItemStack getItem(){
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();

        if (!Objects.equals(customModelString, "")){
            CustomModelDataComponent itemCustomModel = itemMeta.getCustomModelDataComponent();
            itemCustomModel.setStrings(List.of(customModelString));
            itemMeta.setCustomModelDataComponent(itemCustomModel);
        }

        itemMeta.displayName(Component.text(name, rarity.getColor()).decoration(TextDecoration.ITALIC, false));
        if (!lore.isEmpty()){
            List<Component> finishedLoreList = new ArrayList<>(List.of(Component.text("[" + rarity.getName() + "] ", rarity.getColor(), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false), Component.text("")));

            for (String loreLine : lore){
                if (loreLine.startsWith("⚠")){
                    finishedLoreList.add(Component.text(loreLine, ColorType.MC_RED.getColor(), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
                } else {
                    finishedLoreList.add(Component.text(loreLine, ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
                }
            }

            finishedLoreList.add(Component.text(""));

            finishedLoreList.add(Component.text("Probability : " + Math.round(rarity.getProbability()*100) + "%", rarity.getColor()).decoration(TextDecoration.ITALIC, false));

            itemMeta.lore(finishedLoreList);
        }

        item.setItemMeta(itemMeta);

        if (material == Material.CROSSBOW){
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW, 1));

            item.setItemMeta(crossbowMeta);
        }

        return item;
    }

    public int getId() {
        return id;
    }

    public Rarity getRarity(){
        return rarity;
    }

    @Nullable
    public static Powerups getPowerupByID(int id){
        for (Powerups powerup : Powerups.values()){
            if (powerup.getId() == id ){
                return powerup;
            }
        }
        return null;
    }

}
