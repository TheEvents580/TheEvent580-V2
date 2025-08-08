package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class Start implements CommandExecutor {

    public Start(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("start")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {

        if (commandSender instanceof Player player){

            Inventory gui = Bukkit.createInventory(player, 27, Component.text("Do you want to start the event ?", ColorType.MC_ORANGE.getColor()));

            ItemStack yes = new ItemStack(Material.LIME_CONCRETE);
            ItemStack no = new ItemStack(Material.RED_CONCRETE);

            ItemStack blank = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);

            ItemMeta yes_meta = yes.getItemMeta();
            yes_meta.displayName(Component.text("Yes,", ColorType.MC_LIME.getColor(), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
            ArrayList<Component> yes_lore = new ArrayList<>();
            yes_lore.add(Component.text("The event is ready to be started.", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
            yes_meta.lore(yes_lore);
            yes.setItemMeta(yes_meta);

            ItemMeta no_meta = no.getItemMeta();
            no_meta.displayName(Component.text("No,", ColorType.MC_RED.getColor(), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
            ArrayList<Component> no_lore = new ArrayList<>();
            no_lore.add(Component.text("The event is not ready to be started.", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
            no_meta.lore(no_lore);
            no.setItemMeta(no_meta);

            ItemMeta blank_meta = blank.getItemMeta();
            blank_meta.displayName(Component.text(""));
            blank.setItemMeta(blank_meta);

            ItemStack[] menu_items = {
                    blank, blank, blank, blank, blank, blank, blank, blank, blank,
                    blank, blank, yes  , blank, blank, blank, no   , blank, blank,
                    blank, blank, blank, blank, blank, blank, blank, blank, blank
            };
            gui.setContents(menu_items);

            player.openInventory(gui);
        }

        return true;
    }
}
