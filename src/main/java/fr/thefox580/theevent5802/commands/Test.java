package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.build_masters.BuildMasters;
import fr.thefox580.theevent5802.utils.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Test implements CommandExecutor {

    private final TheEvent580_2 plugin;

    public Test(TheEvent580_2 plugin){
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("test")).setExecutor(this);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player) {

            float amtOfPlayers = Float.parseFloat(strings[0]);

            int bestPlayer = 0;
            int worstPlayer = 0;

            for (int build = 1; build < 11; build++){
                int price = BuildMasters.buildPrice(build);
                player.sendMessage("Cost for build " + build + ": " + price);

                player.sendMessage("1st place: " + (int) Math.ceil((price - (0/amtOfPlayers)*price) * ((1+(build/10f)*2))));
                bestPlayer += (int) Math.ceil((price - (0/amtOfPlayers)*price) * ((1+(build/10f)*2)));

                player.sendMessage("10th place: " + (int) Math.ceil((price - ((amtOfPlayers-1)/amtOfPlayers)*price) * ((1+(build/10f)*2))));
                worstPlayer += (int) Math.ceil((price - ((amtOfPlayers-1)/amtOfPlayers)*price) * ((1+(build/10f)*2)));

                player.sendMessage(" ");
            }

            player.sendMessage("With " + (int) amtOfPlayers + " players:");
            player.sendMessage("Best player would have " + bestPlayer + " points");
            player.sendMessage("Worst player would have " + worstPlayer + " points");

        }

        return true;
    }
}
