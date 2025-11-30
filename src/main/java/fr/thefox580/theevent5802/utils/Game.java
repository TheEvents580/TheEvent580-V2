package fr.thefox580.theevent5802.utils;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum Game {

    TRIALS("Trials", ColorType.MC_RED, "\uE001", true, 10, 10*60, Material.RED_CONCRETE, 1),
    PARKOUR("Parkour", ColorType.MC_ORANGE, "\uE002", true, 2, 10*60, Material.ORANGE_CONCRETE, 1),
    FINDER("Finder", ColorType.MC_YELLOW, "\uE003", true, 13, 15*60, Material.YELLOW_CONCRETE, 1),
    TAG("Tag", ColorType.MC_LIME, "\uE004", true, 11, -1, Material.LIME_CONCRETE, 1),
    MULTILAP("Multilap", ColorType.MC_AQUA, "\uE005", true, 5, 10*60, Material.LIGHT_BLUE_CONCRETE, 1),
    MULTIRACE("Multi Race", ColorType.MC_AQUA, "?", false, 14, 10*60, Material.LIGHT_BLUE_CONCRETE, 1),
    SPLEEF("Spleef", ColorType.MC_AQUA, "?", true, 15, 5*60, Material.LIGHT_BLUE_CONCRETE, 3),
    BUILD_MASTERS("Build Masters", ColorType.MC_BLUE, "\uE006", true, 9, 10*60, Material.BLUE_CONCRETE, 1),
    ARMS_RACE("Arms Race", ColorType.MC_PURPLE, "\uE007", true, 12, 15*60, Material.PURPLE_CONCRETE, 1),
    BOW_PVP("Bow PVP", ColorType.MC_PINK, "\uE008", true, 8, 10*60, Material.PINK_CONCRETE, 1),
    HUB("Hub", ColorType.TEXT, "", false, 0, -1, Material.WHITE_CONCRETE, 0),
    @Deprecated
    DROPPER("Dropper", ColorType.MC_RED, "?", false, 1, 10*60, Material.RED_CONCRETE, 1),
    @Deprecated
    BINGO("Bingo", ColorType.MC_YELLOW, "\uE003", false, 3, 20*60, Material.YELLOW_CONCRETE, 1),
    @Deprecated
    FIND_THE_BUTTON("Find The Button", ColorType.MC_LIME, "?", false, 4, 12*60+30, Material.LIME_CONCRETE, 1),
    @Deprecated
    RACES("Races", ColorType.MC_AQUA, "?", false, 5, 10*60, Material.LIGHT_BLUE_CONCRETE, 1),
    @Deprecated
    SURVIVAL_GAMES("Survival Games", ColorType.MC_PURPLE, "\uE007", false, 6, -1, Material.PURPLE_CONCRETE, 1),
    @Deprecated
    LABYRINTH("Labyrinth", ColorType.MC_PURPLE, "?", false, 7, 10*60, Material.PURPLE_CONCRETE, 1);

    private final String name;
    private final ColorType color;
    private final String icon;
    private final boolean isPlayed;
    private final int gameCondition;
    private final int gameTime;
    private final Material materialBlock;
    private final int roundCount;

    Game(String name, ColorType color, String icon, boolean isPlayed, int gameCondition, int gameTime, Material materialBlock, int roundCount){
        this.name = name;
        this.color = color;
        this.icon = icon;
        this.isPlayed = isPlayed;
        this.gameCondition = gameCondition;
        this.gameTime = gameTime;
        this.materialBlock = materialBlock;
        this.roundCount = roundCount;
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

    public boolean isPlayed(){
        return isPlayed;
    }

    public int getGameCondition(){
        return gameCondition;
    }

    public int getGameTime() {
        return gameTime;
    }

    public Material getMaterialBlock() {return materialBlock;}

    public int getRoundCount() {
        return roundCount;
    }

    public boolean isMultipleRounds(){
        return roundCount > 1;
    }

    public static List<Game> playedGames(){
        List<Game> playedGames = new ArrayList<>();

        for (Game game : values()){
            if (game.isPlayed()){
                playedGames.add(game);
            }
        }

        return playedGames;
    }

    public static Game getGameByGameCondition(int gameCondition){
        for (Game game : Game.values()){
            if (game.getGameCondition() == gameCondition){
                return game;
            }
        }
        return Game.HUB;
    }
}
