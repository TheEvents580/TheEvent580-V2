package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class Test implements CommandExecutor {

    public Test(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("test")).setExecutor(this);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player){
            ItemStack feather = new ItemStack(Material.FEATHER);
            ItemMeta featherMeta = feather.getItemMeta();
            featherMeta.customName(Component.text("", ColorType.TEXT.getColor())
                    .decoration(TextDecoration.BOLD, false)
                    .decoration(TextDecoration.ITALIC, false));
            feather.setItemMeta(featherMeta);

            player.give(feather);
        }

        return true;
    }
}
