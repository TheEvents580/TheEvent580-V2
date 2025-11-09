package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.format.TextColor;

import java.util.ArrayList;
import java.util.List;

public enum ColorType {
    TITLE(TextColor.color(21, 89, 102), false),
    SUBTITLE(TextColor.color(29, 120, 116), false),
    TEXT(TextColor.color(255, 255, 255), false),
    SUBTEXT(TextColor.color(217, 216, 241), false),
    SPECIAL_1(TextColor.color(188, 215, 29), false),
    SPECIAL_2(TextColor.color(226, 240, 147), false),
    SPECIAL_3(TextColor.color(103, 146, 137), false),
    SPECIAL_ORANGE(TextColor.color(140, 215, 29), false),
    MC_DARK_RED(TextColor.color(170, 0, 0), false),
    MC_RED(TextColor.color(255, 85, 85), false),
    MC_ORANGE(TextColor.color(255, 170, 0), false),
    MC_YELLOW(TextColor.color(255, 255, 85), false),
    MC_LIME(TextColor.color(85, 255, 85), false),
    MC_GREEN(TextColor.color(0, 170, 0), false),
    MC_CYAN(TextColor.color(0, 170, 170), false),
    MC_AQUA(TextColor.color(85, 255, 255), false),
    MC_BLUE(TextColor.color(85, 85, 255), false),
    MC_DARK_BLUE(TextColor.color(0, 0, 170), false),
    MC_PURPLE(TextColor.color(170, 0, 170), false),
    MC_PINK(TextColor.color(255, 85, 255), false),
    MC_LIGHT_GRAY(TextColor.color(170, 170, 170), false),
    MC_GRAY(TextColor.color(85, 85, 85), false),
    RAINBOW(TextColor.color(240, 240, 28), true),
    RAINBOW_WAVE(TextColor.color(240, 240, 132), true),
    RAINBOW_ITER(TextColor.color(240, 240, 136), true),
    LESBIAN(TextColor.color(240, 240, 64), true),
    GAY(TextColor.color(240, 240, 68), true),
    BISEXUAL(TextColor.color(240, 240, 72), true),
    TRANSGENDER(TextColor.color(240, 240, 76), true),
    PRIDE(TextColor.color(240, 240, 80), true),
    PANSEXUAL(TextColor.color(240, 240, 84), true),
    ASEXUAL(TextColor.color(240, 240, 88), true),
    AROMANTIC(TextColor.color(240, 240, 92), true),
    NON_BINARY(TextColor.color(240, 240, 96), true),
    NO_SHADOW(TextColor.color(240, 240, 100), false),
    BOSSBAR(TextColor.color(240, 240, 104), false),
    GOLD(TextColor.color(255, 215, 0), false),
    SILVER(TextColor.color(192, 192, 192), false),
    BRONZE(TextColor.color(205, 127, 50), false),
    ARMS_RACE_BLANK_EVEN(TextColor.color(240, 240, 108), false),
    ARMS_RACE_BLANK_ODD(TextColor.color(240, 240, 112), false),
    ARMS_RACE_BLANK_LAST(TextColor.color(240, 240, 116), false),
    ARMS_RACE_DONE_EVEN(TextColor.color(240, 240, 120), false),
    ARMS_RACE_DONE_ODD(TextColor.color(240, 240, 124), false),
    ARMS_RACE_DONE_LAST(TextColor.color(240, 240, 128), false);

    private final TextColor color;
    private final boolean isCustomColorCompatible;

    ColorType(TextColor color, boolean isCustomColorCompatible){
        this.color = color;
        this.isCustomColorCompatible = isCustomColorCompatible;
    }

    public TextColor getColor(){
        return color;
    }

    public boolean isCustomColorCompatible() {
        return isCustomColorCompatible;
    }

    public static boolean isCustomColor(String color){
        List<String> isACustomColor = new ArrayList<>();

        for (ColorType colorType : ColorType.values()){
            if (colorType.isCustomColorCompatible()){
                isACustomColor.add(colorType.toString());
            }
        }
        
        return isACustomColor.contains(color);
    }
}
