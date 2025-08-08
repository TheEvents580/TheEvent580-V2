package fr.thefox580.theevent5802.games.tag;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.text.DecimalFormat;

public class Tag {

    private static final World world = Bukkit.getWorld("Tag");

    public static double getWorldSize(){

        double size = -1;

        if (world != null){
            size = world.getWorldBorder().getSize()/91*100;
        }

        DecimalFormat df = new DecimalFormat("###.##");

        return Double.parseDouble(df.format(size));

    }

}
