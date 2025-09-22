package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.format.TextColor;

public enum Rarity {

    LEGENDARY("LEGENDARY", ColorType.MC_ORANGE, 0.01f),
    EPIC("EPIC", ColorType.MC_PURPLE, 0.05f),
    RARE("RARE", ColorType.MC_AQUA, 0.1f),
    COMMON("COMMON", ColorType.MC_LIME, 0.15f);

    private final String name;
    private final ColorType color;
    private final float probability;

    Rarity(String name, ColorType color, float probability){
        this.name = name;
        this.color = color;
        this.probability = probability;
    }

    public String getName(){
        return name;
    }

    public TextColor getColor(){
        return color.getColor();
    }

    public float getProbability(){
        return probability;
    }

}
