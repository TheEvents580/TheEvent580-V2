package fr.thefox580.theevent5802.games.finder;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FinderCommand implements CommandExecutor {

    public FinderCommand(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("sets")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String @NotNull [] strings) {

        if (commandSender instanceof Player player){
            if (Timer.getEnum() == Timer.TimerEnum.IN_GAME
                && Variables.equals("jeu_condi", Game.FINDER.getGameCondition()) || Spectators.isSpectator(player)){
                Finder.openShopGUI(player);
                return true;
            }
        }

        commandSender.sendMessage(Component.text("You cannot open this menu right now!", ColorType.MC_RED.getColor()));
        return false;
    }
}
