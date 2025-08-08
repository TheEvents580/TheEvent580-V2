package fr.thefox580.theevent5802.games.parkour;

import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class Parkour {

    public static Component getMainLevel(Player player){
        return Component.text("Hard", ColorType.MC_RED.getColor(), TextDecoration.BOLD);
    }

    public static Component getSubLevel(Player player){
        return Component.text("3", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false);
    }

}
