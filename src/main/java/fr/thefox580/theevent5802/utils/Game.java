package fr.thefox580.theevent5802.utils;

public enum Game {

    TRIALS("Trials", ColorType.MC_RED, "\uE001"),
    PARKOUR("Parkour", ColorType.MC_ORANGE, "\uE002"),
    BINGO("Bingo", ColorType.MC_YELLOW, "\uE003"),
    TAG("Tag", ColorType.MC_LIME, "\uE004"),
    MULTILAP("Multilap", ColorType.MC_AQUA, "\uE005"),
    BUILD_MASTERS("Build Masters", ColorType.MC_BLUE, "\uE006"),
    ARMS_RACE("Arms Race", ColorType.MC_PURPLE, "\uE007"),
    BOW_PVP("Bow PVP", ColorType.MC_PINK, "\uE008"),
    @Deprecated
    DROPPER("Dropper", ColorType.MC_RED, "?"),
    @Deprecated
    FIND_THE_BUTTON("Find The Button", ColorType.MC_LIME, "?"),
    @Deprecated
    RACES("Races", ColorType.MC_AQUA, "?"),
    @Deprecated
    SURVIVAL_GAMES("Survival Games", ColorType.MC_PURPLE, "\uE007"),
    @Deprecated
    LABYRINTH("Labyrinth", ColorType.MC_PURPLE, "?");

    private final String name;
    private final ColorType color;
    private final String icon;

    Game(String name, ColorType color, String icon){
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public ColorType getColorType() {
        return color;
    }

    public String getIcon() {
        return icon;
    }
}
