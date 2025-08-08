package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.PlayerManager;
import fr.thefox580.theevent5802.utils.Players;
import fr.thefox580.theevent5802.utils.Spectators;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomColor implements CommandExecutor, TabCompleter {

    public CustomColor(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("customcolor")).setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player){

            PlayerManager playerManager;

            if (Players.isPlayer(player)){
                playerManager = Players.getPlayerManager(player);
            } else {
                playerManager = Spectators.getPlayerManager(player);
            }

            assert playerManager != null;

            if (strings[0].equals("TEAM") || strings[0].equals("RESET")){
                playerManager.setColorType(playerManager.getTeam().getColorType());
                player.sendMessage(Component.text("Your color has correctly been reset to your original team color !", ColorType.MC_LIME.getColor()));
            } else if (ColorType.isCustomColor(strings[0])){
                ColorType newColor = ColorType.valueOf(strings[0]);
                playerManager.setColorType(newColor);
                player.sendMessage(Component.text("Your color has correctly been set to ", ColorType.MC_LIME.getColor())
                        .append(Component.text(strings[0], newColor.getColor(), TextDecoration.BOLD))
                        .append(Component.text(" !", ColorType.MC_LIME.getColor())));
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        List<String> tab = new ArrayList<>();

        if (args.length == 1) {
            tab.add("TEAM");
            tab.add("RESET");
            for (ColorType color : ColorType.values()){
                if (color.isCustomColorCompatible()){
                    tab.add(color.toString());
                }
            }
        }
        return tab.stream().filter(completion -> completion.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).toList();
    }
}
