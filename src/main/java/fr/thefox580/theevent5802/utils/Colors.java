package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.format.TextColor;

public class Colors {

    public static TextColor getColor(ColorType type){
        if (type == ColorType.TITLE){
            return TextColor.color(21, 89, 102);
        } else if (type == ColorType.SUBTITLE){
            return TextColor.color(29, 120, 116);
        } else if (type == ColorType.TEXT){
            return TextColor.color(255, 255, 255);
        } else if (type == ColorType.SUBTEXT){
            return TextColor.color(217, 219, 241);
        } else if (type == ColorType.SPECIAL_1){
            return TextColor.color(188, 215, 29);
        } else if (type == ColorType.SPECIAL_2){
            return TextColor.color(226, 240, 147);
        } else if (type == ColorType.SPECIAL_3){
            return TextColor.color(103, 146, 137);
        } else if (type == ColorType.SPECIAL_ORANGE){
            return TextColor.color(140, 215, 29);
        } else if (type == ColorType.MC_DARK_RED){
            return TextColor.color(170, 0, 0);
        } else if (type == ColorType.MC_RED){
            return TextColor.color(255, 85, 85);
        } else if (type == ColorType.MC_ORANGE){
            return TextColor.color(255, 170, 0);
        } else if (type == ColorType.MC_YELLOW){
            return TextColor.color(255, 255, 85);
        } else if (type == ColorType.MC_LIME){
            return TextColor.color(85, 255, 85);
        } else if (type == ColorType.MC_GREEN){
            return TextColor.color(0, 170, 0);
        } else if (type == ColorType.MC_CYAN){
            return TextColor.color(0, 170, 170);
        } else if (type == ColorType.MC_AQUA){
            return TextColor.color(85, 255, 255);
        } else if (type == ColorType.MC_BLUE){
            return TextColor.color(85, 85, 170);
        } else if (type == ColorType.MC_DARK_BLUE){
            return TextColor.color(0, 0, 170);
        } else if (type == ColorType.MC_PURPLE){
            return TextColor.color(170, 0, 170);
        } else if (type == ColorType.MC_PINK){
            return TextColor.color(255, 85, 255);
        } else if (type == ColorType.MC_LIGHT_GRAY){
            return TextColor.color(170, 170, 170);
        } else if (type == ColorType.MC_GRAY){
            return TextColor.color(85, 85, 85);
        } else if (type == ColorType.RAINBOW){
            return TextColor.color(240, 240, 28);
        } else if (type == ColorType.RAINBOW_WAVE){
            return TextColor.color(240, 240, 132);
        } else if (type == ColorType.RAINBOW_ITER){
            return TextColor.color(240, 240, 136);
        } else if (type == ColorType.LESBIAN){
            return TextColor.color(240, 240, 64);
        } else if (type == ColorType.GAY){
            return TextColor.color(240, 240, 68);
        } else if (type == ColorType.BISEXUAL){
            return TextColor.color(240, 240, 72);
        } else if (type == ColorType.TRANSGENDER){
            return TextColor.color(240, 240, 76);
        } else if (type == ColorType.PRIDE){
            return TextColor.color(240, 240, 80);
        } else if (type == ColorType.PANSEXUAL){
            return TextColor.color(240, 240, 84);
        } else if (type == ColorType.ASEXUAL){
            return TextColor.color(240, 240, 88);
        } else if (type == ColorType.AROMANTIC){
            return TextColor.color(240, 240, 92);
        } else if (type == ColorType.NON_BINARY){
            return TextColor.color(240, 240, 96);
        }
        return TextColor.color(0, 0, 0);
    }

}
