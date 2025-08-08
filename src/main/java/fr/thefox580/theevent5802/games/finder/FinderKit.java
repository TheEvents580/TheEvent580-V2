package fr.thefox580.theevent5802.games.finder;

import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FinderKit {

    public void giveKitToPlayer(Player player){
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setUnbreakable(true);
        swordMeta.addEnchant(Enchantment.SHARPNESS, 3, false);
        swordMeta.displayName(Component.text("Diamond Sword", ColorType.MC_YELLOW.getColor()));
        sword.setItemMeta(swordMeta);

        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta axeMeta = axe.getItemMeta();
        axeMeta.setUnbreakable(true);
        axeMeta.addEnchant(Enchantment.EFFICIENCY, 2, false);
        axeMeta.displayName(Component.text("Diamond Axe", ColorType.MC_YELLOW.getColor()));
        axe.setItemMeta(axeMeta);

        ItemStack pickaxeFortune = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickaxeFortuneMeta = pickaxeFortune.getItemMeta();
        pickaxeFortuneMeta.setUnbreakable(true);
        pickaxeFortuneMeta.addEnchant(Enchantment.EFFICIENCY, 2, false);
        pickaxeFortuneMeta.addEnchant(Enchantment.FORTUNE, 2, false);
        pickaxeFortuneMeta.displayName(Component.text("Fortune Diamond Pickaxe", ColorType.MC_YELLOW.getColor()));
        pickaxeFortune.setItemMeta(pickaxeFortuneMeta);

        ItemStack pickaxeSilk = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta pickaxeSilkMeta = pickaxeSilk.getItemMeta();
        pickaxeSilkMeta.setUnbreakable(true);
        pickaxeSilkMeta.addEnchant(Enchantment.EFFICIENCY, 2, false);
        pickaxeSilkMeta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
        pickaxeSilkMeta.displayName(Component.text("Silk Touch Diamond Pickaxe", ColorType.MC_YELLOW.getColor()));
        pickaxeSilk.setItemMeta(pickaxeSilkMeta);

        ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta shovelMeta = shovel.getItemMeta();
        shovelMeta.setUnbreakable(true);
        shovelMeta.addEnchant(Enchantment.EFFICIENCY, 2, false);
        shovelMeta.displayName(Component.text("Diamond Shovel", ColorType.MC_YELLOW.getColor()));
        shovel.setItemMeta(shovelMeta);

        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemMeta elytraMeta = elytra.getItemMeta();
        elytraMeta.setUnbreakable(true);
        elytraMeta.displayName(Component.text("Elytra", ColorType.MC_YELLOW.getColor()));
        elytra.setItemMeta(elytraMeta);

        ItemStack rocket = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta rocketMeta = rocket.getItemMeta();
        rocketMeta.displayName(Component.text("Elytra Rockets", ColorType.MC_YELLOW.getColor()));
        rocketMeta.setMaxStackSize(99);
        rocket.setItemMeta(rocketMeta);
        rocket.setAmount(99);
        player.setCooldown(rocket, 20);

        ItemStack steak = new ItemStack(Material.COOKED_BEEF);
        ItemMeta steakMeta = steak.getItemMeta();
        steakMeta.displayName(Component.text("Steak", ColorType.MC_YELLOW.getColor()));
        steakMeta.setMaxStackSize(99);
        steak.setItemMeta(steakMeta);
        steak.setAmount(99);

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setUnbreakable(true);
        helmetMeta.addEnchant(Enchantment.PROTECTION, 4, false);
        helmetMeta.displayName(Component.text("Diamond Helmet", ColorType.MC_YELLOW.getColor()));
        helmet.setItemMeta(helmetMeta);

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta chestplateMeta = chestplate.getItemMeta();
        chestplateMeta.setUnbreakable(true);
        chestplateMeta.addEnchant(Enchantment.PROTECTION, 4, false);
        chestplateMeta.displayName(Component.text("Diamond Chestplate", ColorType.MC_YELLOW.getColor()));


        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta leggingsMeta = leggings.getItemMeta();
        leggingsMeta.setUnbreakable(true);
        leggingsMeta.addEnchant(Enchantment.PROTECTION, 4, false);
        leggingsMeta.displayName(Component.text("Diamond Leggings", ColorType.MC_YELLOW.getColor()));
        leggings.setItemMeta(leggingsMeta);


        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setUnbreakable(true);
        bootsMeta.addEnchant(Enchantment.PROTECTION, 4, false);
        bootsMeta.displayName(Component.text("Diamond Boots", ColorType.MC_YELLOW.getColor()));
        boots.setItemMeta(bootsMeta);

        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, axe);
        player.getInventory().setItem(2, pickaxeFortune);
        player.getInventory().setItem(3, pickaxeSilk);
        player.getInventory().setItem(4, rocket);
        player.getInventory().setItem(8, steak);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
    }

}
