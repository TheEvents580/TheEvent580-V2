package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.finder.Finder;
import fr.thefox580.theevent5802.games.finder.FinderSets;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Test implements CommandExecutor {

    public Test(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("test")).setExecutor(this);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player){
            Finder.startGame();
            FinderSets.newRandomItemSet();

            Inventory gui = Bukkit.createInventory(player, 9, Component.text(Finder.getCurrentItemSetName() + "'s Items"));

            List<Material> materialSet = FinderSets.getCurrentItemSet();

            ItemStack goldenItem = new ItemStack(materialSet.getFirst());
            ItemMeta goldenItemMeta = goldenItem.getItemMeta();
            goldenItemMeta.customName(goldenItem.effectiveName()
                    .color(ColorType.GOLD.getColor())
                    .decoration(TextDecoration.ITALIC, false));
            goldenItemMeta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.getFirst()) + "/" + Finder.getMaterialsFound(materialSet.getFirst()), ColorType.RAINBOW.getColor()).decoration(TextDecoration.ITALIC, false),
                    Component.text("Status : Locked", ColorType.MC_RED.getColor()).decoration(TextDecoration.ITALIC, false)));
            goldenItem.setItemMeta(goldenItemMeta);

            ItemStack normalItem1 = new ItemStack(materialSet.get(1));
            ItemMeta normalItem1Meta = normalItem1.getItemMeta();
            normalItem1Meta.customName(normalItem1.effectiveName()
                    .color(ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.ITALIC, false));
            normalItem1Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(1)) + "/" + Finder.getMaterialsFound(materialSet.get(1)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
            normalItem1.setItemMeta(normalItem1Meta);

            ItemStack normalItem2 = new ItemStack(materialSet.get(2));
            ItemMeta normalItem2Meta = normalItem2.getItemMeta();
            normalItem2Meta.customName(normalItem2.effectiveName()
                    .color(ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.ITALIC, false));
            normalItem2Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(2)) + "/" + Finder.getMaterialsFound(materialSet.get(2)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
            normalItem2.setItemMeta(normalItem2Meta);

            ItemStack normalItem3 = new ItemStack(materialSet.get(3));
            ItemMeta normalItem3Meta = normalItem3.getItemMeta();
            normalItem3Meta.customName(normalItem3.effectiveName()
                    .color(ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.ITALIC, false));
            normalItem3Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(3)) + "/" + Finder.getMaterialsFound(materialSet.get(3)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
            normalItem3.setItemMeta(normalItem1Meta);

            ItemStack normalItem4 = new ItemStack(materialSet.get(4));
            ItemMeta normalItem4Meta = normalItem4.getItemMeta();
            normalItem4Meta.customName(normalItem4.effectiveName()
                    .color(ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.ITALIC, false));
            normalItem4Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(4)) + "/" + Finder.getMaterialsFound(materialSet.get(4)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
            normalItem4.setItemMeta(normalItem4Meta);

            ItemStack normalItem5 = new ItemStack(materialSet.get(5));
            ItemMeta normalItem5Meta = normalItem5.getItemMeta();
            normalItem5Meta.customName(normalItem5.effectiveName()
                    .color(ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.ITALIC, false));
            normalItem5Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(5)) + "/" + Finder.getMaterialsFound(materialSet.get(5)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
            normalItem5.setItemMeta(normalItem5Meta);

            ItemStack normalItem6 = new ItemStack(materialSet.get(6));
            ItemMeta normalItem6Meta = normalItem6.getItemMeta();
            normalItem6Meta.customName(normalItem6.effectiveName()
                    .color(ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.ITALIC, false));
            normalItem6Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(6)) + "/" + Finder.getMaterialsFound(materialSet.get(6)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
            normalItem6.setItemMeta(normalItem6Meta);

            ItemStack normalItem7 = new ItemStack(materialSet.get(7));
            ItemMeta normalItem7Meta = normalItem7.getItemMeta();
            normalItem7Meta.customName(normalItem7.effectiveName()
                    .color(ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.ITALIC, false));
            normalItem7Meta.lore(List.of(Component.text("Collected : " + Finder.getMaterialsFoundPlayer(player, materialSet.get(7)) + "/" + Finder.getMaterialsFound(materialSet.get(7)), ColorType.MC_LIME.getColor()).decoration(TextDecoration.ITALIC, false)));
            normalItem7.setItemMeta(normalItem1Meta);

            ItemStack normalItem8 = new ItemStack(materialSet.get(8));
            ItemMeta normalItem8Meta = normalItem8.getItemMeta();
            normalItem8Meta.customName(normalItem8.effectiveName()
                    .color(ColorType.SUBTEXT.getColor())
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

            for (Powerups powerup : Powerups.values()){
                ItemStack powerupItem = powerup.getItem();
                player.give(powerupItem);

                player.sendMessage(Component.text('[')
                        .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] You received ", ColorType.TEXT.getColor()))
                        .append(Component.text(powerup.getRarity().getName() + ' ', powerup.getRarity().getColor(), TextDecoration.BOLD))
                        .append(powerupItem.displayName()));
            }

        }

        return true;
    }
}
