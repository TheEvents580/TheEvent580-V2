package fr.thefox580.theevent5802.games.arms_race;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArmsRace {

    private static final Map<Player, Integer> playerWeapon = new HashMap<>();
    private static final Map<Integer, ItemStack> weapons = new HashMap<>();

    public static void startPreGame(TheEvent580_2 plugin){

    }

    private void initializeWeapons(){
        ItemStack nSword = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta nSwordMeta = nSword.getItemMeta();
        nSwordMeta.setUnbreakable(true);
        nSwordMeta.displayName(Component.text("Netherite Sword"));
        nSword.setItemMeta(nSwordMeta);

        weapons.put(1, nSword);
        weapons.put(2, nSword);

        ItemStack dSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta dSwordMeta = dSword.getItemMeta();
        dSwordMeta.setUnbreakable(true);
        dSwordMeta.displayName(Component.text("Diamond Sword"));
        dSword.setItemMeta(dSwordMeta);

        weapons.put(3, dSword);
        weapons.put(4, dSword);

        ItemStack iSword = new ItemStack(Material.IRON_SWORD);
        ItemMeta iSwordMeta = iSword.getItemMeta();
        iSwordMeta.setUnbreakable(true);
        iSwordMeta.displayName(Component.text("Iron Sword"));
        iSword.setItemMeta(iSwordMeta);

        weapons.put(5, iSword);
        weapons.put(6, iSword);

        ItemStack sSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta sSwordMeta = sSword.getItemMeta();
        sSwordMeta.setUnbreakable(true);
        sSwordMeta.displayName(Component.text("Stone Sword"));
        sSword.setItemMeta(sSwordMeta);

        weapons.put(7, iSword);
        weapons.put(8, iSword);

        ItemStack iBow = new ItemStack(Material.BOW);
        ItemMeta iBowMeta = iBow.getItemMeta();
        iBowMeta.setUnbreakable(true);
        iBowMeta.displayName(Component.text("Bow"));
        iBow.setItemMeta(iBowMeta);
        iBow.addEnchantment(Enchantment.INFINITY, 1);

        weapons.put(9, iBow);
        weapons.put(10, iBow);

        ItemStack nPick = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta nPickMeta = nPick.getItemMeta();
        nPickMeta.displayName(Component.text("Netherite Pickaxe"));
        nPickMeta.setUnbreakable(true);
        nPick.setItemMeta(nPickMeta);

        weapons.put(11, nPick);
        weapons.put(12, nPick);

        ItemStack dPick = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta dPickMeta = dPick.getItemMeta();
        dPickMeta.setUnbreakable(true);
        dPick.setItemMeta(dPickMeta);

        weapons.put(13, dPick);
        weapons.put(14, dPick);

        ItemStack iPick = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta iPickMeta = iPick.getItemMeta();
        iPickMeta.setUnbreakable(true);
        iPickMeta.displayName(Component.text("Iron Pickaxe"));
        iPick.setItemMeta(iPickMeta);

        weapons.put(15, iPick);
        weapons.put(16, iPick);

        ItemStack sPick = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta sPickMeta = sPick.getItemMeta();
        sPickMeta.setUnbreakable(true);
        sPickMeta.displayName(Component.text("Stone Pickaxe"));
        sPick.setItemMeta(sPickMeta);

        weapons.put(17, sPick);
        weapons.put(18, sPick);

        ItemStack iCross = new ItemStack(Material.CROSSBOW);
        ItemMeta iCrossMeta = iCross.getItemMeta();
        iCrossMeta.setUnbreakable(true);
        iCrossMeta.displayName(Component.text("Crossbow"));
        iCross.setItemMeta(iCrossMeta);
        iCross.addEnchantment(Enchantment.INFINITY, 1);

        weapons.put(19, iCross);
        weapons.put(20, iCross);

        ItemStack dShovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta dShovelMeta = dShovel.getItemMeta();
        dShovelMeta.setUnbreakable(true);
        dShovelMeta.displayName(Component.text("Diamond Shovel"));
        dShovel.setItemMeta(dShovelMeta);

        weapons.put(21, dShovel);
        weapons.put(22, dShovel);

        ItemStack iShovel = new ItemStack(Material.IRON_SHOVEL);
        ItemMeta iShovelMeta = iShovel.getItemMeta();
        iShovelMeta.setUnbreakable(true);
        iShovelMeta.displayName(Component.text("Iron Shovel"));
        iShovel.setItemMeta(iShovelMeta);

        weapons.put(23, iShovel);
        weapons.put(24, iShovel);

        ItemStack sShovel = new ItemStack(Material.STONE_SHOVEL);
        ItemMeta sShovelMeta = sShovel.getItemMeta();
        sShovelMeta.setUnbreakable(true);
        sShovelMeta.displayName(Component.text("Stone Shovel"));
        sShovel.setItemMeta(sShovelMeta);

        weapons.put(25, sShovel);
        weapons.put(26, sShovel);

        ItemStack wShovel = new ItemStack(Material.WOODEN_SHOVEL);
        ItemMeta wShovelMeta = wShovel.getItemMeta();
        wShovelMeta.setUnbreakable(true);
        wShovelMeta.displayName(Component.text("Wooden Shovel"));
        wShovel.setItemMeta(wShovelMeta);

        weapons.put(27, wShovel);
        weapons.put(28, wShovel);

        ItemStack lTrident = new ItemStack(Material.TRIDENT);
        ItemMeta lTridentMeta = lTrident.getItemMeta();
        lTridentMeta.setUnbreakable(true);
        lTridentMeta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_speed_mainhand"), -3.9, AttributeModifier.Operation.ADD_NUMBER));
        lTridentMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_damage_mainhand"), -100, AttributeModifier.Operation.ADD_NUMBER));
        lTridentMeta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_speed_offhand"), -3.9, AttributeModifier.Operation.ADD_NUMBER));
        lTridentMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_damage_offhand"), -100, AttributeModifier.Operation.ADD_NUMBER));
        lTridentMeta.displayName(Component.text("Loyalty Trident"));
        lTrident.setItemMeta(lTridentMeta);
        lTrident.addEnchantment(Enchantment.LOYALTY, 2);

        weapons.put(29, lTrident);
        weapons.put(30, lTrident);

        ItemStack goldenKnife = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta goldenKnifeMeta = goldenKnife.getItemMeta();
        goldenKnifeMeta.setUnbreakable(true);
        goldenKnifeMeta.displayName(Component.text("Golden Knife", ColorType.MC_ORANGE.getColor()));
        goldenKnife.setItemMeta(goldenKnifeMeta);

        weapons.put(31, goldenKnife);

        ItemStack lastItem = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta lastItemMeta = lastItem.getItemMeta();
        lastItemMeta.setUnbreakable(true);
        lastItemMeta.displayName(Component.text("This is your last weapon", ColorType.SUBTEXT.getColor()));
        lastItem.setItemMeta(lastItemMeta);

        weapons.put(31, lastItem);
    }

    public static ArrayList<Player> getFurthestPlayers(){
        ArrayList<Player> playerList = new ArrayList<>();
        int max = -1;
        for (Player player : playerWeapon.keySet()){
            if (playerWeapon.get(player) > max){
                max = playerWeapon.get(player);
                playerList = new ArrayList<>();
                playerList.add(player);
            } else if (playerWeapon.get(player) == max) {
                playerList.add(player);
            }
        }
        return playerList;
    }

    public static int getPlayerKills(Player player){
        return playerWeapon.get(player)-1;
    }

    public static ItemStack getPlayerItem(Player player){
        return weapons.get(playerWeapon.get(player));
    }

    public static ItemStack getNextPlayerItem(Player player){
        return weapons.get(playerWeapon.get(player)+1);
    }

}
