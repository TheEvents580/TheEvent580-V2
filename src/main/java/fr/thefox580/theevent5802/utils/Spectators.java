package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Spectators implements CommandExecutor, TabCompleter {

    private static final List<PlayerManager> spectatorList = new ArrayList<>();
    private static TheEvent580_2 plugin;

    public Spectators(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("spectators")).setExecutor(this);
        Spectators.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        switch (args[0]) {
            case "add" -> {
                List<String> usernameList = plugin.getConfig().getStringList("spectators");
                if (usernameList.contains(args[1])) {
                    sender.sendMessage(Component.text(args[1] + " already is a spectator!", ColorType.MC_LIME.getColor()));
                    return false;
                }
                usernameList.add(args[1]);
                plugin.getConfig().set("spectators", usernameList);
                plugin.saveConfig();
                sender.sendMessage(Component.text("Added " + args[1] + " to the spectator list!", ColorType.MC_LIME.getColor()));
                return true;
            }
            case "remove" -> {
                List<String> usernameList = plugin.getConfig().getStringList("spectators");
                if (usernameList.contains(args[1])) {
                    usernameList.remove(args[1]);
                    plugin.getConfig().set("spectators", usernameList);
                    plugin.saveConfig();
                    sender.sendMessage(Component.text("Removed " + args[1] + " from the spectator list!", ColorType.MC_LIME.getColor()));
                    return true;
                }
                sender.sendMessage(Component.text(args[1] + " isn't a spectator!", ColorType.MC_LIME.getColor()));
                return false;
            }
            case "get" -> {
                sender.sendMessage(Component.text("Here are all spectators :"));
                List<String> usernameList = plugin.getConfig().getStringList("spectators");

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
                        if (!plugin.getConfig().getStringList("spectators").contains(loopPlayer.getName()) && !plugin.getConfig().getStringList("players").contains(loopPlayer.getName())){
                            tab.add(loopPlayer.getName());
                        }
                    }
                } else if (args[0].equals("remove")){
                    tab.addAll(plugin.getConfig().getStringList("spectators"));
                }
                break;
        }
        return tab.stream().filter(completion -> completion.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).toList();
    }

    public static void addSpectator(Player player, boolean isStaff, boolean isAdmin){
        PlayerManager playerManager = new PlayerManager(player, Team.SPECTATORS, isStaff, isAdmin);
        spectatorList.add(playerManager);
        Online.addPlayer(playerManager);
    }

    public static @Nullable PlayerManager getPlayerManager(OfflinePlayer player){
        for (PlayerManager playerManager : spectatorList){
            if (Objects.equals(playerManager.getUniqueId().toString(), player.getUniqueId().toString())){
                return playerManager;
            }
        }
        return null;
    }

    public static void removeSpectator(Player player){
        PlayerManager playerManager = getPlayerManager(player);
        spectatorList.remove(playerManager);
        Online.removePlayer(playerManager);
    }

    public static boolean isSpectator(OfflinePlayer player){
        return !Players.isPlayer(player);
    }

    public static List<PlayerManager> getSpectatorOnlineList(){
        return spectatorList;
    }

    public static List<OfflinePlayer> getSpectatorList(){
        List<OfflinePlayer> spectators = new ArrayList<>();

        for (String spectatorName : plugin.getConfig().getStringList("spectators")){
            spectators.add(Bukkit.getOfflinePlayer(spectatorName));
        }

        return spectators;
    }

    public static int getMaxSpectatorCount(){
        return plugin.getConfig().getStringList("spectators").size();
    }

    public static int getSpectatorCount(){
        return spectatorList.size();
    }

    private static void giveSpectatorTool(Player spectator){

        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.displayName(Component.text("Teleport to player", ColorType.SUBTEXT.getColor())
                .decoration(TextDecoration.ITALIC, false));
        compass.setItemMeta(compassMeta);

        ItemStack feather = new ItemStack(Material.FEATHER);
        ItemMeta featherMeta = feather.getItemMeta();
        featherMeta.customName(Component.text("Change your fly speed", ColorType.SUBTEXT.getColor())
                .decoration(TextDecoration.BOLD, false)
                .decoration(TextDecoration.ITALIC, false));
        featherMeta.lore(List.of(Component.text("Right-click to reduce fly speed by 50%", ColorType.SPECIAL_2.getColor()).decoration(TextDecoration.ITALIC, false), Component.text("Left-click to increase fly speed by 50%", ColorType.SPECIAL_2.getColor()).decoration(TextDecoration.ITALIC, false)));
        feather.setItemMeta(featherMeta);

        spectator.getInventory().setItem(0, compass);
        spectator.getInventory().setItem(1, feather);

        Objects.requireNonNull(spectator.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(20);
        spectator.heal(20);

        spectator.setFoodLevel(20);
    }

    private static void giveSpectatorTools(){
        for (PlayerManager spectator : spectatorList){
            Player player = spectator.getOnlinePlayer();
            if (player != null){
                giveSpectatorTool(player);
            }
        }
    }

    private static void hideSpectators(){
        spectatorList.forEach(spectator -> Bukkit.getOnlinePlayers().forEach(loopPlayer -> loopPlayer.hidePlayer(plugin, Objects.requireNonNull(spectator.getOnlinePlayer()))));
    }

    private static void showSpectators(){
        spectatorList.forEach(spectator -> Bukkit.getOnlinePlayers().forEach(loopPlayer -> loopPlayer.showPlayer(plugin, Objects.requireNonNull(spectator.getOnlinePlayer()))));
    }

    private static void flySpectators(){
        spectatorList.forEach(spectator -> {
            Player spec = spectator.getOnlinePlayer();
            assert spec != null;
            spec.setGameMode(GameMode.ADVENTURE);
            spec.setAllowFlight(true);
            Location pLoc = spec.getLocation();
            pLoc.setY(pLoc.y()-1);
            spec.setVelocity(spec.getLocation().toVector().subtract(pLoc.toVector()).normalize());
            spec.setFlying(true);
        });
    }

    private static void groundSpectators(){
        spectatorList.forEach(spectator -> {
            Player spec = spectator.getOnlinePlayer();
            assert spec != null;
            spec.setGameMode(GameMode.ADVENTURE);
            spec.setAllowFlight(true);
            spec.setFlying(false);
        });
    }

    public static void readySpectatorsGame(){
        giveSpectatorTools();
        hideSpectators();
        flySpectators();
    }

    public static void readySpectatorGame(PlayerManager spectator){
        Player spec = spectator.getOnlinePlayer();
        assert spec != null;

        spec.getInventory().clear();

        PersistentDataContainer playerContainer = spec.getPersistentDataContainer();
        playerContainer.set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, false);

        giveSpectatorTool(spec);

        Bukkit.getOnlinePlayers().forEach(loopPlayer -> {
            loopPlayer.hidePlayer(plugin, spec);
            if (Online.getPlayerManager(loopPlayer).isAlive(plugin)){
                spec.showPlayer(plugin, loopPlayer);
            }
        });

        spec.setGameMode(GameMode.ADVENTURE);
        spec.setAllowFlight(true);
        Location pLoc = spec.getLocation();
        pLoc.setY(pLoc.y()-1);
        spec.setVelocity(spec.getLocation().toVector().subtract(pLoc.toVector()).normalize());
        spec.setFlying(true);
    }

    public static void readySpectatorsDecision(){
        hideSpectators();
        flySpectators();
    }

    public static void readySpectatorDecision(PlayerManager spectator){
        Player spec = spectator.getOnlinePlayer();
        assert spec != null;

        PersistentDataContainer playerContainer = spec.getPersistentDataContainer();
        playerContainer.set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, false);

        Bukkit.getOnlinePlayers().forEach(loopPlayer -> loopPlayer.hidePlayer(plugin, spec));

        spec.setGameMode(GameMode.ADVENTURE);
        spec.setAllowFlight(true);
        spec.setFlying(false);
    }

    public static void readySpectatorsLobby(){
        spectatorList.forEach(spectator -> Objects.requireNonNull(spectator.getOnlinePlayer()).getInventory().clear());
        showSpectators();
        groundSpectators();
    }

    public static void readySpectatorLobby(PlayerManager spectator){
        Player spec = spectator.getOnlinePlayer();
        assert spec != null;

        PersistentDataContainer playerContainer = spec.getPersistentDataContainer();
        playerContainer.set(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN, true);

        Bukkit.getOnlinePlayers().forEach(loopPlayer -> loopPlayer.showPlayer(plugin, spec));
        spec.setAllowFlight(true);
        Location pLoc = spec.getLocation();
        pLoc.setY(pLoc.y()-1);
        spec.setVelocity(spec.getLocation().toVector().subtract(pLoc.toVector()).normalize());
        spec.setFlying(true);

        if (!spec.getInventory().contains(Material.FEATHER)){
            int percentage = Math.round(spec.getFlySpeed()*1000)-Math.round((spec.getFlySpeed()-0.1f)*500);

            ItemStack feather = new ItemStack(Material.FEATHER);
            ItemMeta featherMeta = feather.getItemMeta();
            featherMeta.customName(Component.text("Change your fly speed", ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.BOLD, false)
                    .decoration(TextDecoration.ITALIC, false));
            featherMeta.lore(List.of(Component.text("Right-click to reduce fly speed by 50%", ColorType.SPECIAL_2.getColor()).decoration(TextDecoration.ITALIC, false),
                    Component.text("Left-click to increase fly speed by 50%", ColorType.SPECIAL_2.getColor()).decoration(TextDecoration.ITALIC, false),
                    Component.text("Current fly speed : " + percentage + "%", ColorType.SPECIAL_2.getColor()).decoration(TextDecoration.ITALIC, false)));
            feather.setItemMeta(featherMeta);

            spec.give(feather);
            spec.setGameMode(GameMode.ADVENTURE);
        }
    }

}
