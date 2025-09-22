package fr.thefox580.theevent5802.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Team {

    ADMIN("Admin", ColorType.MC_DARK_RED, "リ", Material.BROWN_CONCRETE),
    STAFF("Staff", ColorType.RAINBOW, "リ", Material.BLACK_CONCRETE),
    SPECTATORS("Spectators", ColorType.MC_GRAY, "露", Material.GRAY_CONCRETE),
    RED("Red", ColorType.MC_RED, "ラ", Material.RED_CONCRETE),
    ORANGE("Orange", ColorType.MC_ORANGE, "ャ", Material.ORANGE_CONCRETE),
    YELLOW("Yellow", ColorType.MC_YELLOW, "ギ", Material.YELLOW_CONCRETE),
    LIME("Lime", ColorType.MC_LIME, "画", Material.LIME_CONCRETE),
    LIGHT_BLUE("Light Blue", ColorType.MC_AQUA, "動", Material.LIGHT_BLUE_CONCRETE),
    BLUE("Blue", ColorType.MC_BLUE, "像", Material.BLUE_CONCRETE),
    PURPLE("Purple", ColorType.MC_PURPLE, "の", Material.PURPLE_CONCRETE),
    PINK("Pink", ColorType.MC_PINK, "目", Material.PINK_CONCRETE),
    OFFLINE("Offline", ColorType.MC_LIGHT_GRAY, "タ", Material.LIGHT_GRAY_CONCRETE),
    NONE("None", ColorType.TEXT, "", Material.WHITE_CONCRETE);

    private final String name;
    private final ColorType color;
    private final String icon;
    private final Material block;

    Team(String name, ColorType color, String icon, Material block){
        this.name = name;
        this.color = color;
        this.icon = icon;
        this.block = block;
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

    public Material getMaterial(){
        return block;
    }

    public ItemStack getItemStack(){
        return new ItemStack(block);
    }
}
