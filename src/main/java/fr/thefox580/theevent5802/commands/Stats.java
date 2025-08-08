package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Game;
import fr.thefox580.theevent5802.utils.PlayerStats;
import fr.thefox580.theevent5802.utils.Score;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Stats implements CommandExecutor, TabCompleter {

    private final TheEvent580_2 plugin;

    public Stats(TheEvent580_2 plugin){
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("stats")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender.isOp()){

            if (strings.length < 3){
                return false;
            } else {
                UUID playerUUID = Bukkit.getOfflinePlayer(strings[0]).getUniqueId();
                Map<String, Double> scoresMap = new HashMap<>();
                for (int size = 1; size < (strings.length+1)/2; size++) {
                    scoresMap.put(strings[size * 2 - 1], Double.valueOf(strings[size * 2]));
                }
                plugin.getInstances().getStatsDatabase().addStats(playerUUID, scoresMap);
                commandSender.sendMessage(Component.text("Stats for "+Bukkit.getOfflinePlayer(playerUUID).getName()+" have been saved!", ColorType.MC_LIME.getColor()));
                return true;
            }
        } else {
            plugin.getConfig().set("episode", "Episode "+strings[1]);
            plugin.saveConfig();
            plugin.getInstances().getStatsDatabase().updateCollection();
            OfflinePlayer player = Bukkit.getOfflinePlayer(strings[0]);
            PlayerStats stats = plugin.getInstances().getStatsDatabase().getStats(player.getUniqueId());
            if (!stats.getScores().isEmpty()){
                Component message = getMessage(player, stats);
                commandSender.sendMessage(message);
                return true;
            }
            commandSender.sendMessage(Component.text("No stats were found for " + strings[0] + " in Season 1 - " + plugin.getConfig().getString("episode") + ".", ColorType.MC_RED.getColor(), TextDecoration.BOLD));
            return false;
        }
    }

    private @NotNull Component getMessage(OfflinePlayer player, PlayerStats stats) {
        Component message = Component.text("Stats for ", ColorType.MC_LIME.getColor())
                .append(Component.text(Objects.requireNonNull(player.getName()), ColorType.MC_LIME.getColor(), TextDecoration.BOLD));
        message = message.append(Component.text(" in Season 1 - " + plugin.getConfig().getString("episode") + " :", ColorType.MC_LIME.getColor()));
        double totalPoints = 0;
        for (Score score : stats.getScores()){
            message = message.append(Component.text("\n\n" + score.getGame().getIcon() + " ", ColorType.NO_SHADOW.getColor())
                    .append(Component.text(score.getGame().getName(), score.getGame().getColorType().getColor(), TextDecoration.BOLD))
                    .append(Component.text(" : " + score.getPoints(), ColorType.SUBTEXT.getColor()))
                    .append(Component.text(" 工", ColorType.NO_SHADOW.getColor())));
            totalPoints += score.getPoints();
        }
        message = message.append(Component.text("\n\nTotal : ", ColorType.MC_LIME.getColor())
                .append(Component.text(totalPoints, ColorType.MC_LIME.getColor(), TextDecoration.BOLD)));
        message = message.append(Component.text(" 工", ColorType.NO_SHADOW.getColor()));
        return message;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String @NotNull [] args) {

        if (sender.isOp()){
            List<String> tab = new ArrayList<>();

            switch (args.length){
                case 1:
                    for (OfflinePlayer loopPlayer : Bukkit.getOfflinePlayers()){
                        tab.add(loopPlayer.getName());
                    }
                    break;
                case 2, 4, 6, 8, 10, 12:
                    for (Game game : Game.values()){
                        tab.add(game.toString().toLowerCase());
                    }
                    break;
                case 3, 5, 7, 9, 11, 13:
                    for (double i = 0.5; i < 10000; i+=0.5){
                        tab.add(String.valueOf(i));
                    }
                    break;
            }
            return tab.stream().filter(completion -> completion.startsWith(args[args.length - 1])).toList();
        } else {
            List<String> tab = new ArrayList<>();

            switch (args.length) {
                case 1:
                    for (OfflinePlayer loopPlayer : Bukkit.getOfflinePlayers()) {
                        tab.add(loopPlayer.getName());
                    }
                    break;
                case 2:
                    tab.addAll(List.of("1", "2", "3", "4", "5", "VC", "6", "Darkathon3", "7"));
                    break;
            }
            return tab.stream().filter(completion -> completion.startsWith(args[args.length - 1])).toList();
        }
    }

}
