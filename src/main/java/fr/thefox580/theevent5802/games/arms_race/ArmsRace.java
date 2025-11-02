package fr.thefox580.theevent5802.games.arms_race;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import fr.thefox580.theevent5802.utils.Timer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class ArmsRace {

    private static final Map<Player, Integer> playerWeapon = new HashMap<>();
    private static final Map<Integer, ItemStack> weapons = new HashMap<>();
    private static final World world = Bukkit.getWorld("ArmsRace");

    public static void startPreGame(TheEvent580_2 plugin){
        Timer.setSeconds(90);
        Timer.setMaxSeconds(90);
        Timer.setEnum(Timer.TimerEnum.PRE_GAME);

        initializeWeapons();

        if (world != null){
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
        }

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            Player player = playerManager.getOnlinePlayer();

            if (player != null){
                player.getInventory().clear();
                Points.addGamePoints(player, Math.round(150*Points.getMultiplier()));
                playerWeapon.put(player, 1);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, true, false));

                player.teleport(new Location(world, 17.5, 129, 10.5));
            }
        }

        Variables.setVariable("jeu_condi", Game.ARMS_RACE.getGameCondition());
        Variables.setVariable("jeu", (int) Variables.getVariable("jeu") +1);
        Variables.setVariable("jeu_nom", Game.ARMS_RACE.getName());
        Variables.setVariable("jeu_logo", Game.ARMS_RACE.getIcon());

        ArmsRaceTasks.preGameTask(plugin);

    }

    private static void initializeWeapons(){
        ItemStack goldenKnife = ItemStack.of(Material.GOLDEN_SWORD);
        ItemMeta goldenKnifeMeta = goldenKnife.getItemMeta();
        goldenKnifeMeta.setUnbreakable(true);
        goldenKnifeMeta.displayName(Component.text("Golden Knife", ColorType.MC_ORANGE.getColor()).decoration(TextDecoration.ITALIC, false));
        goldenKnife.setItemMeta(goldenKnifeMeta);

        weapons.put(0, goldenKnife);

        ItemStack nSword = ItemStack.of(Material.NETHERITE_SWORD);
        ItemMeta nSwordMeta = nSword.getItemMeta();
        nSwordMeta.setUnbreakable(true);
        nSwordMeta.displayName(Component.text("Netherite Sword", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        nSword.setItemMeta(nSwordMeta);

        weapons.put(1, nSword);
        weapons.put(2, nSword);

        ItemStack dSword = ItemStack.of(Material.DIAMOND_SWORD);
        ItemMeta dSwordMeta = dSword.getItemMeta();
        dSwordMeta.setUnbreakable(true);
        dSwordMeta.displayName(Component.text("Diamond Sword", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        dSword.setItemMeta(dSwordMeta);

        weapons.put(3, dSword);
        weapons.put(4, dSword);

        ItemStack iSword = ItemStack.of(Material.IRON_SWORD);
        ItemMeta iSwordMeta = iSword.getItemMeta();
        iSwordMeta.setUnbreakable(true);
        iSwordMeta.displayName(Component.text("Iron Sword", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        iSword.setItemMeta(iSwordMeta);

        weapons.put(5, iSword);
        weapons.put(6, iSword);

        ItemStack sSword = ItemStack.of(Material.STONE_SWORD);
        ItemMeta sSwordMeta = sSword.getItemMeta();
        sSwordMeta.setUnbreakable(true);
        sSwordMeta.displayName(Component.text("Stone Sword", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        sSword.setItemMeta(sSwordMeta);

        weapons.put(7, iSword);
        weapons.put(8, iSword);

        ItemStack iBow = ItemStack.of(Material.BOW);
        ItemMeta iBowMeta = iBow.getItemMeta();
        iBowMeta.setUnbreakable(true);
        iBowMeta.displayName(Component.text("Bow", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        iBow.setItemMeta(iBowMeta);
        iBow.addEnchantment(Enchantment.INFINITY, 1);

        weapons.put(9, iBow);
        weapons.put(10, iBow);

        ItemStack nPick = ItemStack.of(Material.NETHERITE_PICKAXE);
        ItemMeta nPickMeta = nPick.getItemMeta();
        nPickMeta.displayName(Component.text("Netherite Pickaxe", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        nPickMeta.setUnbreakable(true);
        nPick.setItemMeta(nPickMeta);

        weapons.put(11, nPick);
        weapons.put(12, nPick);

        ItemStack dPick = ItemStack.of(Material.DIAMOND_PICKAXE);
        ItemMeta dPickMeta = dPick.getItemMeta();
        dPickMeta.setUnbreakable(true);
        dPickMeta.displayName(Component.text("Diamond Pickaxe", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        dPick.setItemMeta(dPickMeta);

        weapons.put(13, dPick);
        weapons.put(14, dPick);

        ItemStack iPick = ItemStack.of(Material.IRON_PICKAXE);
        ItemMeta iPickMeta = iPick.getItemMeta();
        iPickMeta.setUnbreakable(true);
        iPickMeta.displayName(Component.text("Iron Pickaxe", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        iPick.setItemMeta(iPickMeta);

        weapons.put(15, iPick);
        weapons.put(16, iPick);

        ItemStack sPick = ItemStack.of(Material.STONE_PICKAXE);
        ItemMeta sPickMeta = sPick.getItemMeta();
        sPickMeta.setUnbreakable(true);
        sPickMeta.displayName(Component.text("Stone Pickaxe", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        sPick.setItemMeta(sPickMeta);

        weapons.put(17, sPick);
        weapons.put(18, sPick);

        ItemStack iCross = ItemStack.of(Material.CROSSBOW);
        ItemMeta iCrossMeta = iCross.getItemMeta();
        iCrossMeta.setUnbreakable(true);
        iCrossMeta.displayName(Component.text("Crossbow", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        iCrossMeta.addEnchant(Enchantment.INFINITY, 1, true);
        iCross.setItemMeta(iCrossMeta);

        weapons.put(19, iCross);
        weapons.put(20, iCross);

        ItemStack dShovel = ItemStack.of(Material.DIAMOND_SHOVEL);
        ItemMeta dShovelMeta = dShovel.getItemMeta();
        dShovelMeta.setUnbreakable(true);
        dShovelMeta.displayName(Component.text("Diamond Shovel", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        dShovel.setItemMeta(dShovelMeta);

        weapons.put(21, dShovel);
        weapons.put(22, dShovel);

        ItemStack iShovel = ItemStack.of(Material.IRON_SHOVEL);
        ItemMeta iShovelMeta = iShovel.getItemMeta();
        iShovelMeta.setUnbreakable(true);
        iShovelMeta.displayName(Component.text("Iron Shovel", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        iShovel.setItemMeta(iShovelMeta);

        weapons.put(23, iShovel);
        weapons.put(24, iShovel);

        ItemStack sShovel = ItemStack.of(Material.STONE_SHOVEL);
        ItemMeta sShovelMeta = sShovel.getItemMeta();
        sShovelMeta.setUnbreakable(true);
        sShovelMeta.displayName(Component.text("Stone Shovel", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        sShovel.setItemMeta(sShovelMeta);

        weapons.put(25, sShovel);
        weapons.put(26, sShovel);

        ItemStack wShovel = ItemStack.of(Material.WOODEN_SHOVEL);
        ItemMeta wShovelMeta = wShovel.getItemMeta();
        wShovelMeta.setUnbreakable(true);
        wShovelMeta.displayName(Component.text("Wooden Shovel", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        wShovel.setItemMeta(wShovelMeta);

        weapons.put(27, wShovel);
        weapons.put(28, wShovel);

        ItemStack lTrident = ItemStack.of(Material.TRIDENT);
        ItemMeta lTridentMeta = lTrident.getItemMeta();
        lTridentMeta.setUnbreakable(true);
        lTridentMeta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_speed_mainhand"), -3, AttributeModifier.Operation.ADD_NUMBER));
        lTridentMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_damage_mainhand"), -100, AttributeModifier.Operation.ADD_NUMBER));
        lTridentMeta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_speed_offhand"), -3, AttributeModifier.Operation.ADD_NUMBER));
        lTridentMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_damage_offhand"), -100, AttributeModifier.Operation.ADD_NUMBER));
        lTridentMeta.displayName(Component.text("Loyalty Trident", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        lTrident.setItemMeta(lTridentMeta);
        lTrident.addEnchantment(Enchantment.LOYALTY, 2);

        weapons.put(29, lTrident);
        weapons.put(30, lTrident);

        ItemStack lastWeapon = ItemStack.of(Material.GOLDEN_SWORD);
        ItemMeta lastWeaponMeta = lastWeapon.getItemMeta();
        lastWeaponMeta.setUnbreakable(true);
        lastWeaponMeta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(new NamespacedKey(NamespacedKey.MINECRAFT, "attack_speed_mainhand"), -0.8, AttributeModifier.Operation.ADD_SCALAR));
        lastWeaponMeta.displayName(Component.text("Golden Knife", ColorType.MC_ORANGE.getColor()).decoration(TextDecoration.ITALIC, false));
        lastWeaponMeta.lore(List.of(Component.text("This is your last weapon", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.ITALIC, false)));
        lastWeapon.setItemMeta(lastWeaponMeta);

        weapons.put(31, lastWeapon);
        
        weapons.put(32, ItemStack.of(Material.AIR));
    }

    public static int getWeaponCount(){
        return weapons.size()-2;
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
        if (playerWeapon.get(player) != null){
            return playerWeapon.get(player)-1;
        }

        return 0;
    }

    public static ItemStack getWeapon(int weaponNumber){
        return weapons.get(weaponNumber);
    }

    public static ItemStack getPlayerItem(Player player){
        return getWeapon(playerWeapon.get(player));
    }

    public static ItemStack getNextPlayerItem(Player player){
        playerWeapon.put(player, playerWeapon.get(player)+1);
        return getPlayerItem(player);
    }

    public static void goBack(Player player){
        if (playerWeapon.get(player) > 1){
            playerWeapon.put(player, playerWeapon.get(player)-1);
        }
    }

    public static void randomRespawn(Player player, boolean fromKnife){

        List<Location> locationList = new ArrayList<>();

        World currentWorld = world;

        if (currentWorld != null){
            currentWorld = player.getWorld();
        }

        Location startLocation = new Location(currentWorld, -68, 129, -44);
        Location endLocation = new Location(currentWorld, 28, 129, 52);

        int y = startLocation.getBlockY();

        for (int x = startLocation.getBlockX(); x < endLocation.getBlockX(); x++){
            for (int z = startLocation.getBlockZ(); x < endLocation.getBlockZ(); z++){

                Location currentBlock = new Location(currentWorld, x, y, z);

                if (currentBlock.getBlock().getType() == Material.AIR){
                    Location ceiling = currentBlock.clone();
                    ceiling.setY(143);
                    if (ceiling.getBlock().getType() == Material.BARRIER){
                        locationList.add(currentBlock);
                    }
                }
            }
        }

        if (fromKnife){
            goBack(player);
        }

        player.spigot().respawn();
        player.teleport(locationList.get(new Random().nextInt(locationList.size())));
        player.getInventory().clear();
        if (getPlayerKills(player) < getWeaponCount()-1){
            player.give(getWeapon(0));
        }
        ItemStack item = getPlayerItem(player);
        player.give(item);
        if (List.of(Material.BOW, Material.CROSSBOW).contains(item.getType())){
            player.give(ItemStack.of(Material.ARROW));
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 255, true, false));
    }

}
