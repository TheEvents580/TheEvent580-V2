package fr.thefox580.theevent5802.commands;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.commands.utils.ColorType;
import fr.thefox580.theevent5802.commands.utils.Colors;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class showPronouns implements CommandExecutor {

    private final TheEvent580_2 advMain;

    public showPronouns(TheEvent580_2 advMain) {
        this.advMain = advMain;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player player){

            advMain.getConfig().set("showPronouns."+player.getUniqueId(), !advMain.getConfig().getBoolean("showPronouns."+player.getUniqueId()));
            if (advMain.getConfig().getBoolean("showPronouns."+player.getUniqueId())) {
                player.sendMessage(Component.text("[")
                        .append(Component.text("TheEvent580", Colors.getColor(ColorType.TITLE)))
                        .append(Component.text("] You now display your pronouns", Colors.getColor(ColorType.TEXT))));
            } else {
                player.sendMessage(Component.text("[")
                        .append(Component.text("TheEvent580", Colors.getColor(ColorType.TITLE)))
                        .append(Component.text("] You no longer display your pronouns", Colors.getColor(ColorType.TEXT))));
            }
            advMain.saveConfig();

        }

        return true;
    }
}
