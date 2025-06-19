package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class craft implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player){
            player.openWorkbench(null, true);

            player.sendMessage(Component.text("[", ColorType.TEXT.getColor())
                            .append(Component.text("TheEvent580", ColorType.TITLE.getColor()))
                            .append(Component.text("] You just opened a virtual crafting table", ColorType.TEXT.getColor()))
            );
        }

        return true;
    }
}
