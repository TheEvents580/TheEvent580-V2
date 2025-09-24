package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Spectators;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Fly implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public Fly(TheEvent580_2 plugin){
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("fly")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player){
            if (Spectators.isSpectator(player)){
                player.setAllowFlight(!player.getAllowFlight());
                if (player.getAllowFlight()){
                    player.sendMessage(Component.text("You can now fly!", ColorType.MC_LIME.getColor()));
                } else {
                    player.sendMessage(Component.text("You can no longer fly!", ColorType.MC_LIME.getColor()));
                }
                return true;
            } else {
                PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                if (Boolean.FALSE.equals(playerContainer.get(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN))){
                    player.setAllowFlight(!player.getAllowFlight());
                    if (player.getAllowFlight()){
                        player.sendMessage(Component.text("You can now fly!", ColorType.MC_LIME.getColor()));
                    } else {
                        player.sendMessage(Component.text("You can no longer fly!", ColorType.MC_LIME.getColor()));
                    }
                    return true;
                }
            }
        }

        sender.sendMessage(Component.text("You cannot toggle your fly mode!", ColorType.MC_RED.getColor()));

        return false;
    }
}
