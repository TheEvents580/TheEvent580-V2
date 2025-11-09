package fr.thefox580.theevent5802.utils;

import org.bukkit.Material;

import javax.annotation.Nullable;

public enum AdvancementsEnum {
    LASTSECONDSBM(0, "Last seconds in Build Masters", "You managed to finish the game with less than 30 seconds on your timer!", Material.SMOOTH_QUARTZ),
    @Deprecated
    FINALBATTLESG(1, "Final Battle : Survival Games", "You survived until the final battle in Survival Games!", Material.DIAMOND_SWORD),
    SECRETBASE(2, "Hub Secret Base", "You found the secret base in the hub!", Material.LEVER),
    HEROBRINE(3, "Herobrine", "You discovered Herobrine!", Material.PLAYER_HEAD),
    HUBPARKOUR(4, "Hub Parkour Completed", "You sucessfully completed the Hub parkour!", Material.TARGET);

    private final int id;
    private final String name;
    private final String description;
    private final Material block;

    AdvancementsEnum(int id, String name, String description, Material block){
        this.id = id;
        this.name = name;
        this.description = description;
        this.block = block;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Material getBlock(){return block;}

    public static @Nullable AdvancementsEnum getAdvancementById(int id){
        return switch (id) {
            case 0 -> LASTSECONDSBM;
            case 1 -> FINALBATTLESG;
            case 2 -> SECRETBASE;
            case 3 -> HEROBRINE;
            case 4 -> HUBPARKOUR;
            default -> null;
        };
    }
}
