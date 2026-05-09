package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FixGlowing implements CommandExecutor {

    public FixGlowing(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("fixglowing")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (Bukkit.getOnlinePlayers().isEmpty()){
            return false;
        }

        for (World world : Bukkit.getWorlds()){
            for (Entity entity : world.getEntities()){
                entity.setGlowing(false);
            }
        }

        commandSender.sendMessage(Component.text("All entities got their glowing removed!"));
        return true;
    }
}
