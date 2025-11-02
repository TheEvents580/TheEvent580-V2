package fr.thefox580.theevent5802.utils;

import me.clip.placeholderapi.libs.kyori.adventure.text.format.TextColor;

public enum ColorTypeAlt {
    TITLE(TextColor.color(21, 89, 102)),
    SUBTITLE(TextColor.color(29, 120, 116)),
    TEXT(TextColor.color(255, 255, 255)),
    SUBTEXT(TextColor.color(217, 219, 241)),
    SPECIAL_1(TextColor.color(188, 215, 29)),
    SPECIAL_2(TextColor.color(226, 240, 147)),
    SPECIAL_3(TextColor.color(103, 146, 137)),
    SPECIAL_ORANGE(TextColor.color(140, 215, 29)),
    MC_DARK_RED(TextColor.color(170, 0, 0)),
    MC_RED(TextColor.color(255, 85, 85)),
    MC_ORANGE(TextColor.color(255, 170, 0)),
    MC_YELLOW(TextColor.color(255, 255, 85)),
    MC_LIME(TextColor.color(85, 255, 85)),
    MC_GREEN(TextColor.color(0, 170, 0)),
    MC_CYAN(TextColor.color(0, 170, 170)),
    MC_AQUA(TextColor.color(85, 255, 255)),
    MC_BLUE(TextColor.color(85, 85, 255)),
    MC_DARK_BLUE(TextColor.color(0, 0, 170)),
    MC_PURPLE(TextColor.color(170, 0, 170)),
    MC_PINK(TextColor.color(255, 85, 255)),
    MC_LIGHT_GRAY(TextColor.color(170, 170, 170)),
    MC_GRAY(TextColor.color(85, 85, 85)),
    RAINBOW(TextColor.color(240, 240, 28)),
    RAINBOW_WAVE(TextColor.color(240, 240, 132)),
    RAINBOW_ITER(TextColor.color(240, 240, 136)),
    LESBIAN(TextColor.color(240, 240, 64)),
    GAY(TextColor.color(240, 240, 68)),
    BISEXUAL(TextColor.color(240, 240, 72)),
    TRANSGENDER(TextColor.color(240, 240, 76)),
    PRIDE(TextColor.color(240, 240, 80)),
    PANSEXUAL(TextColor.color(240, 240, 84)),
    ASEXUAL(TextColor.color(240, 240, 88)),
    AROMANTIC(TextColor.color(240, 240, 92)),
    NON_BINARY(TextColor.color(240, 240, 96)),
    NO_SHADOW(TextColor.color(240, 240, 100)),
    BOSSBAR(TextColor.color(240, 240, 104)),
    GOLD(TextColor.color(255, 215, 0)),
    SILVER(TextColor.color(192, 192, 192)),
    BRONZE(TextColor.color(205, 127, 50)),
    ARMS_RACE_BLANK_EVEN(TextColor.color(240, 240, 108)),
    ARMS_RACE_BLANK_ODD(TextColor.color(240, 240, 112)),
    ARMS_RACE_BLANK_LAST(TextColor.color(240, 240, 116)),
    ARMS_RACE_DONE_EVEN(TextColor.color(240, 240, 120)),
    ARMS_RACE_DONE_ODD(TextColor.color(240, 240, 124)),
    ARMS_RACE_DONE_LAST(TextColor.color(240, 240, 128));

    private final TextColor color;

    ColorTypeAlt(TextColor color){
        this.color = color;
    }

    public TextColor getColor(){
        return color;
    }
}
