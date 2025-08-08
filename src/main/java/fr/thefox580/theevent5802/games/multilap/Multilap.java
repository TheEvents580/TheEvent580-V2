package fr.thefox580.theevent5802.games.multilap;

import fr.thefox580.theevent5802.utils.ColorType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class Multilap {

    public static int getOpenPath(){
        return 1;
    }

    public static Component getPlayerCheckpoint(Player player){
        return Component.text("3", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false);
    }

}
