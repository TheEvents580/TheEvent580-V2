package fr.thefox580.theevent5802.utils;

public enum Team {

    STAFF("Staff", ColorType.MC_DARK_RED, "リ"),
    SPECTATORS("Spectators", ColorType.MC_GRAY, "露"),
    RED("Red", ColorType.MC_RED, "ラ"),
    ORANGE("Orange", ColorType.MC_ORANGE, "ャ"),
    YELLOW("Yellow", ColorType.MC_YELLOW, "ギ"),
    LIME("Lime", ColorType.MC_LIME, "画"),
    LIGHT_BLUE("Light Blue", ColorType.MC_AQUA, "動"),
    BLUE("Blue", ColorType.MC_BLUE, "像"),
    PURPLE("Purple", ColorType.MC_PURPLE, "の"),
    PINK("Pink", ColorType.MC_PINK, "目"),
    OFFLINE("Offline", ColorType.MC_LIGHT_GRAY, "タ"),
    NONE("None", ColorType.TEXT, "");

    private final String name;
    private final ColorType color;
    private final String icon;

    Team(String name, ColorType color, String icon){
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
