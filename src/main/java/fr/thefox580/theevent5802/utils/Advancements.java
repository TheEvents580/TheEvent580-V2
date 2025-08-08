package fr.thefox580.theevent5802.utils;

import javax.annotation.Nullable;

public enum Advancements {
    LASTSECONDSBM(0, "Last seconds in Build Masters", "You managed to finish the game with less than 30 seconds on your timer!"),
    FINALBATTLESG(1, "Final Battle : Survival Games", "You survived until the final battle in Survival Games!"),
    SECRETBASE(2, "Hub Secret Base", "You found the secret base in the hub!"),
    HEROBRINE(3, "Herobrine", "You discovered Herobrine!"),
    HUBPARKOUR(4, "Hub Parkour Completed", "You sucessfully completed the Hub parkour!");

    private final Integer id;
    private final String name;
    private final String description;

    Advancements(Integer id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static @Nullable Advancements getAdvancementById(Integer id){
        return switch (id) {
            case 0 -> LASTSECONDSBM;
            case 1 -> FINALBATTLESG;
            case 2 -> SECRETBASE;
            case 3 -> HEROBRINE;
            case 4 -> HUBPARKOUR;
            case null, default -> null;
        };
    }
}
