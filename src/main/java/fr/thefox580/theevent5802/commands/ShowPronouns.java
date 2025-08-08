package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ShowPronouns implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public ShowPronouns(TheEvent580_2 plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("showpronouns")).setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player){

            plugin.getConfig().set("showPronouns."+player.getUniqueId(), !plugin.getConfig().getBoolean("showPronouns."+player.getUniqueId()));
            if (plugin.getConfig().getBoolean("showPronouns."+player.getUniqueId())) {
                player.sendMessage(Component.text("[")
                        .append(Component.text("TheEvent580", ColorType.TITLE.getColor()))
                        .append(Component.text("] You now display your pronouns", ColorType.TEXT.getColor())));
            } else {
                player.sendMessage(Component.text("[")
                        .append(Component.text("TheEvent580", ColorType.TITLE.getColor()))
                        .append(Component.text("] You no longer display your pronouns", ColorType.TEXT.getColor())));
            }
            plugin.saveConfig();

        }

        return true;
    }
}
