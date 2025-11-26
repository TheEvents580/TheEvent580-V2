package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Players implements CommandExecutor, TabCompleter {

    private static final List<PlayerManager> playerList = new ArrayList<>();
    private static TheEvent580_2 plugin;

    public Players(TheEvent580_2 plugin){
        Players.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("players")).setExecutor(this);
    }

    public static void addPlayer(OfflinePlayer player, Team team, boolean isStaff, boolean isAdmin){
        PlayerManager playerManager = new PlayerManager(player, team, isStaff, isAdmin);
        playerList.add(playerManager);
        Online.addPlayer(playerManager);
    }

    public static void removePlayer(OfflinePlayer player){
        PlayerManager playerManager = getPlayerManager(player);
        playerList.remove(playerManager);
        Online.removePlayer(playerManager);
    }

    public static boolean isPlayer(OfflinePlayer player){
        return plugin.getConfig().getStringList("players").contains(player.getName());
    }

    public static @Nullable PlayerManager getPlayerManager(OfflinePlayer player){
        for (PlayerManager playerManager : playerList){
            if (Objects.equals(playerManager.getUniqueId().toString(), player.getUniqueId().toString())){
                return playerManager;
            }
        }
        return null;
    }

    public static List<PlayerManager> getOnlinePlayerList(){
        return playerList;
    }

    public static List<OfflinePlayer> getPlayerList(){
        List<OfflinePlayer> players = new ArrayList<>();

        for (String playerName : plugin.getConfig().getStringList("players")){
            players.add(Bukkit.getOfflinePlayer(playerName));
        }

        return players;
    }

    public static int getMaxPlayerCount(){
        return plugin.getConfig().getStringList("players").size();
    }

    public static int getPlayerCount(){
        return playerList.size();
    }

    public static int nbPlayersAlive(){
        int count = 0;

        for (PlayerManager playerManager : getOnlinePlayerList()){
            if (playerManager.isAlive(plugin)){
                count++;
            }
        }

        return count;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        switch (args[0]) {
            case "add" -> {
                List<String> usernameList = plugin.getConfig().getStringList("players");
                if (usernameList.contains(args[1])) {
                    sender.sendMessage(Component.text(args[1] + " already is a player!", ColorType.MC_LIME.getColor()));
                    return false;
                }
                usernameList.add(args[1]);
                plugin.getConfig().set("players", usernameList);
                plugin.saveConfig();
                sender.sendMessage(Component.text("Added " + args[1] + " to the player list!", ColorType.MC_LIME.getColor()));
                return true;
            }
            case "remove" -> {
                List<String> usernameList = plugin.getConfig().getStringList("players");
                if (usernameList.contains(args[1])) {
                    usernameList.remove(args[1]);
                    plugin.getConfig().set("players", usernameList);
                    plugin.saveConfig();
                    sender.sendMessage(Component.text("Removed " + args[1] + " from the player list!", ColorType.MC_LIME.getColor()));
                    return true;
                }
                sender.sendMessage(Component.text(args[1] + " isn't a player!", ColorType.MC_LIME.getColor()));
                return false;
            }
            case "get" -> {
                sender.sendMessage(Component.text("Here are all players :"));
                List<String> usernameList = plugin.getConfig().getStringList("players");

                for (String username : usernameList) {
                    if (Bukkit.getOfflinePlayer(username).isOnline()) {
                        sender.sendMessage(Component.text(" - " + username)
                                .append(Component.text(" (Online)", ColorType.MC_LIME.getColor())));
                    } else {
                        sender.sendMessage(Component.text(" - " + username)
                                .append(Component.text(" (Offline)", ColorType.MC_RED.getColor())));
                    }
                }
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        List<String> tab = new ArrayList<>();

        switch (args.length) {
            case 1:
                tab.add("add");
                tab.add("remove");
                tab.add("get");

                break;
            case 2:
                if (args[0].equals("add")){
                    for (OfflinePlayer loopPlayer : Bukkit.getOfflinePlayers()) {
                        if (!plugin.getConfig().getStringList("players").contains(loopPlayer.getName()) && !plugin.getConfig().getStringList("spectators").contains(loopPlayer.getName())){
                            tab.add(loopPlayer.getName());
                        }
                    }
                } else if (args[0].equals("remove")){
                    tab.addAll(plugin.getConfig().getStringList("players"));
                }
                break;
        }
        return tab.stream().filter(completion -> completion.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).toList();
    }
}
