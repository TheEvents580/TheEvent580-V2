package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class db implements CommandExecutor, TabCompleter {

    private final TheEvent580_2 plugin;

    public db(TheEvent580_2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        switch (strings[0]){
            case "username":
                plugin.getConfig().set("mongodb_username", strings[1]);
                plugin.saveConfig();
                commandSender.sendMessage(Component.text("Username has been set to " + strings[1], ColorType.MC_LIME.getColor(), TextDecoration.BOLD));
                return true;
            case "password":
                plugin.getConfig().set("mongodb_password", strings[1]);
                plugin.saveConfig();
                commandSender.sendMessage(Component.text("Password has been set to " + strings[1], ColorType.MC_LIME.getColor(), TextDecoration.BOLD));
                return true;
            case "episode":
                plugin.getConfig().set("episode", strings[1]+" "+strings[2]);
                plugin.saveConfig();
                plugin.getDatabase().updateCollection();
                commandSender.sendMessage(Component.text("Episode has been set to " + strings[1] + " " + strings[2], ColorType.MC_LIME.getColor(), TextDecoration.BOLD));
                return true;
            case "get":
                OfflinePlayer player = Bukkit.getOfflinePlayer(strings[1]);
                PlayerStats stats = plugin.getDatabase().getStats(player.getUniqueId());
                if (stats.getScores().size() == 6){
                    Component message = getMessage(player, stats);
                    commandSender.sendMessage(message);
                    return true;
                }
                commandSender.sendMessage(Component.text("No stats were found for " + strings[1] + " in Season 1 - " + plugin.getConfig().getString("episode") + ".", ColorType.MC_RED.getColor(), TextDecoration.BOLD));
                return false;
            default:
                commandSender.sendMessage(Component.text(strings[0] + " is not a correct argument.", ColorType.MC_RED.getColor(), TextDecoration.BOLD));
                return false;
        }

    }

    private @NotNull Component getMessage(OfflinePlayer player, PlayerStats stats) {
        Component message = Component.text("Stats for ", ColorType.MC_LIME.getColor())
                        .append(Component.text(Objects.requireNonNull(player.getName()), ColorType.MC_LIME.getColor(), TextDecoration.BOLD));
        message = message.append(Component.text(" in Season 1 - " + plugin.getConfig().getString("episode") + " :", ColorType.MC_LIME.getColor()));
        for (Score score : stats.getScores()){
            message = message.append(Component.text("\n\n" + score.getGame().getIcon() + " ", ColorType.NO_SHADOW.getColor())
                            .append(Component.text(score.getGame().getName(), score.getGame().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" : " + score.getPoints(), ColorType.SUBTEXT.getColor()))
                            .append(Component.text(" 工", ColorType.NO_SHADOW.getColor())));
        }
        return message;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        List<String> tab = new ArrayList<>();

        switch (args.length){
            case 1:
                tab.add("username");
                tab.add("password");
                tab.add("episode");
                tab.add("get");

                break;
            case 2:
                if (args[0].equals("get"))
                    for (OfflinePlayer loopPlayer : Bukkit.getOfflinePlayers()){
                        tab.add(loopPlayer.getName());
                    }

                break;
        }
        return tab.stream().filter(completion -> completion.startsWith(args[args.length - 1])).toList();
    }
}
