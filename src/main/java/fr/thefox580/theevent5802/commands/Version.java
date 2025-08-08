package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Version implements CommandExecutor {

    public Version(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("version")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        commandSender.sendMessage(Component.text("TheEvent580 (version 1.7.7) is running on Paper API 1.21.4", ColorType.SUBTEXT.getColor()));

        return true;
    }
}
