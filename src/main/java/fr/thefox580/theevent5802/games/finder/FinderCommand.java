package fr.thefox580.theevent5802.games.finder;

import fr.thefox580.theevent5802.TheEvent580_2;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FinderCommand implements CommandExecutor {

    public FinderCommand(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("finder")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        FinderInstance.start();

        return true;
    }
}
