package fr.thefox580.theevent5802.commands;

import eu.endercentral.crazy_advancements.JSONMessage;
import eu.endercentral.crazy_advancements.advancement.*;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class toast implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (strings.length != 4){

            Player player = Bukkit.getPlayer("TheFox580");

            if (!strings[0].equals("all")) {
                player = Bukkit.getPlayer(strings[0]);
            }

            ItemStack icon = new ItemStack(Objects.requireNonNull(Material.getMaterial(strings[1])));

            AdvancementDisplay.AdvancementFrame frame = AdvancementDisplay.AdvancementFrame.valueOf(strings[2]);

            StringBuilder message = new StringBuilder();

            for (int i= 3; i < strings.length; i++){
                message.append(strings[i]).append(" ");
            }

            String messageString = message.toString();

            JSONMessage description = new JSONMessage((BaseComponent) Component.text(messageString, ColorType.MC_AQUA.getColor()).asComponent());

            ToastNotification notification = new ToastNotification(icon, description, frame);

            if (strings[0].equals("all")){
                for (Player loopPlayer : Bukkit.getOnlinePlayers()){
                    notification.send(loopPlayer);
                }
            } else {
                notification.send(player);
            }

            return true;
        } else {
            commandSender.sendMessage(Component.text("[Error] An incorrect amount of arguments were entered, please try with 4 arguments"));
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        final List<String> tab = new ArrayList<>();

        switch (strings.length){
            case 1:
                for (Player loopPlayer : Bukkit.getOnlinePlayers()){
                    tab.add(loopPlayer.getName());
                }
                tab.add("all");
                break;
            case 2:
                for (Material material : Material.values()){
                    tab.add(material.toString());
                }
                break;
            case 3:
                for (AdvancementDisplay.AdvancementFrame frame : AdvancementDisplay.AdvancementFrame.values()){
                    tab.add(frame.toString());
                }
                break;
        }

        return tab.stream().filter(completion -> completion.startsWith(strings[strings.length - 1])).toList();
    }
}
