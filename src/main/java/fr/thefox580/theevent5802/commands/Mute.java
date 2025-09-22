package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
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

public class Mute implements CommandExecutor, TabCompleter {

    private static boolean globalMute = false;

    public Mute(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("mute")).setExecutor(this);
    }

    public static boolean isGlobalMute() {
        return globalMute;
    }

    public static void setGlobalMute(boolean globalMute) {
        Mute.globalMute = globalMute;

        String muted = "un";
        if (isGlobalMute()){
            muted = "";
        }

        Bukkit.broadcast((Component.text("⚠ The chat has been " + muted + "muted! ⚠", ColorType.MC_ORANGE.getColor())));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (args.length == 0){
            setGlobalMute(!isGlobalMute());

            String muted = "un";
            if (isGlobalMute()){
                muted = "";
            }

            sender.sendMessage(Component.text("⚠", ColorType.MC_ORANGE.getColor())
                    .append(Component.text("You have successfully " + muted + "muted the server", ColorType.TEXT.getColor())));
        } else {
            PlayerManager playerManager = Online.getPlayerManager(Bukkit.getPlayer(args[0]));

            playerManager.setMuted(!playerManager.isMuted());

            String muted = "un";
            if (playerManager.isMuted()){
                muted = "";
            }

            sender.sendMessage(Component.text("⚠", ColorType.MC_ORANGE.getColor())
                    .append(Component.text("You have successfully " + muted + "muted ", ColorType.TEXT.getColor()))
                    .append(Component.text(playerManager.getTeam().getIcon() + " ", ColorType.NO_SHADOW.getColor()))
                    .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor())));

            Objects.requireNonNull(playerManager.getOnlinePlayer()).sendMessage(Component.text("⚠", ColorType.MC_ORANGE.getColor())
                    .append(Component.text("You have been " + muted + "muted by ", ColorType.TEXT.getColor()))
                    .append(Component.text(Team.STAFF.getIcon() + " ", ColorType.NO_SHADOW.getColor()))
                    .append(Component.text(sender.getName(), Team.STAFF.getColorType().getColor())));
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {List<String> tab = new ArrayList<>();

        if (args.length == 1) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Players.isPlayer(player)) {
                    tab.add(player.getName());
                }
            }
        } else {
            tab.add("");
        }

        return tab.stream().filter(completion -> completion.startsWith(args[args.length - 1])).toList();
    }
}
