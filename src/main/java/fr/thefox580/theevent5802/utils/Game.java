package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.format.TextColor;

public enum Game {

    TRIALS("Trials", ColorType.MC_RED.getColor(), "\uE001"),
    PARKOUR("Parkour", ColorType.MC_ORANGE.getColor(), "\uE002"),
    BINGO("Bingo", ColorType.MC_YELLOW.getColor(), "\uE003"),
    TAG("Tag", ColorType.MC_LIME.getColor(), "\uE004"),
    MULTILAP("Multilap", ColorType.MC_AQUA.getColor(), "\uE005"),
    BUILD_MASTERS("Build Masters", ColorType.MC_BLUE.getColor(), "\uE006"),
    ARMS_RACE("Arms Race", ColorType.MC_PURPLE.getColor(), "\uE007"),
    BOW_PVP("Bow PVP", ColorType.MC_PINK.getColor(), "\uE008");

    private final String name;
    private final TextColor color;
    private final String icon;

    Game(String name, TextColor color, String icon){
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public TextColor getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }
}
