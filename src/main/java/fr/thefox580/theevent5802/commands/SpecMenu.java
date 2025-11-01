package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Team;
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

import java.util.Objects;

public class SpecMenu implements CommandExecutor {

    public SpecMenu(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("specmenu")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player){

            if (player.getAllowFlight()){

                Inventory gui = Bukkit.createInventory(player, 9*5, Component.text("Spectator TP Menu"));

                if (args.length == 0){
                    Team.RED.createTeamInInv(gui, 1, true);
                    Team.ORANGE.createTeamInInv(gui, 2, true);
                    Team.YELLOW.createTeamInInv(gui, 3, true);
                    Team.LIME.createTeamInInv(gui, 4, true);
                    createNextPage(gui);

                } else {
                    Team.LIGHT_BLUE.createTeamInInv(gui, 1, true);
                    Team.BLUE.createTeamInInv(gui, 2, true);
                    Team.PURPLE.createTeamInInv(gui, 3, true);
                    Team.PINK.createTeamInInv(gui, 4, true);
                    createLastPage(gui);
                }

                player.openInventory(gui);
            }
        }

        return true;
    }

    private void createNextPage(Inventory gui){
        ItemStack next = new ItemStack(Material.LIME_CARPET);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.displayName(Component.text("Next Page", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        next.setItemMeta(nextMeta);

        gui.setItem(gui.getSize()-1, next);
    }

    private void createLastPage(Inventory gui){
        ItemStack last = new ItemStack(Material.ORANGE_CARPET);
        ItemMeta lastMeta = last.getItemMeta();
        lastMeta.displayName(Component.text("Next Page", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        last.setItemMeta(lastMeta);

        gui.setItem(gui.getSize()-9, last);
    }
}
