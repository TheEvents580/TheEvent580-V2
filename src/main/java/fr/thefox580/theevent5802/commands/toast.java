package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Colors;
import fr.thefox580.theevent5802.utils.Toast;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class toast implements CommandExecutor, TabCompleter{
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length < 4){
            commandSender.sendMessage("Please enter at least 4 arguments");
            return false;
        }

        if (Bukkit.getPlayer(strings[0]) == null){
            commandSender.sendMessage(Component.text("Player not found", Colors.getColor(ColorType.MC_RED)));

            return true;
        }

        final Toast.Style style;

        try {
            style = Toast.Style.valueOf(strings[1].toUpperCase());
        } catch (final Throwable t){
            commandSender.sendMessage(Component.text("Invalid style :" + strings[1], Colors.getColor(ColorType.MC_RED)));

            return true;
        }

        final String materialName = strings[2];

        try{
            Material.valueOf(materialName.toUpperCase());
        } catch (final Throwable t){
          commandSender.sendMessage(Component.text("Invalid material :" + materialName, Colors.getColor(ColorType.MC_RED)));

            return true;
        }

        StringBuilder message = new StringBuilder();

        for (int i= 3; i < strings.length; i++){
            message.append(strings[i]).append(" ");
        }

        String messageString = message.toString();

        messageString = ChatColor.translateAlternateColorCodes('&', messageString.trim());

        Toast.displayTo(Bukkit.getPlayer(strings[0]), materialName, messageString, style);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        final List<String> tab = new ArrayList<>();

        switch (strings.length){
            case 1:
                for (final Player loopPlayer : Bukkit.getOnlinePlayers()){
                    tab.add(loopPlayer.getName());
                }
                break;
            case 2:
                for (final Toast.Style style : Toast.Style.values()){
                    tab.add(style.toString());
                }
                break;

            case 3:
                for (final Material material : Material.values()){
                    tab.add(material.toString().toLowerCase());
                }
                break;
        }

        return tab.stream().filter(completion -> completion.startsWith(strings[strings.length - 1])).toList();
    }
}
