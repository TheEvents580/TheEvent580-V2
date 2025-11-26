package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import javax.swing.*;
import java.util.*;

public class Voting {


    private static final List<Game> gameList = new ArrayList<>();
    private static Map<Game, Integer> votes = new HashMap<>();
    private static int noVote;
    private static int alreadyPlayedVote;

    public static void addAllGames(){
        gameList.addAll(Game.playedGames());
    }

    public static void addGame(Game game){
        gameList.add(game);
    }

    public static void removeGame(Game game){
        gameList.remove(game);

        Material glass = Material.LIGHT_GRAY_STAINED_GLASS;
        Material concrete = Material.LIGHT_GRAY_CONCRETE;

        switch (game){
            case TRIALS -> {
                glass = Material.RED_STAINED_GLASS;
                concrete = Material.RED_CONCRETE;
            }
            case PARKOUR -> {
                glass = Material.ORANGE_STAINED_GLASS;
                concrete = Material.ORANGE_CONCRETE;
            }
            case FINDER -> {
                glass = Material.YELLOW_STAINED_GLASS;
                concrete = Material.YELLOW_CONCRETE;
            }
            case TAG -> {
                glass = Material.LIME_STAINED_GLASS;
                concrete = Material.LIME_CONCRETE;
            }
            case SPLEEF -> {
                glass = Material.LIGHT_BLUE_STAINED_GLASS;
                concrete = Material.LIGHT_BLUE_CONCRETE;
            }
            case BUILD_MASTERS -> {
                glass = Material.BLUE_STAINED_GLASS;
                concrete = Material.BLUE_CONCRETE;
            }
            case ARMS_RACE -> {
                glass = Material.PURPLE_STAINED_GLASS;
                concrete = Material.PURPLE_CONCRETE;
            }
            case BOW_PVP -> {
                glass = Material.PINK_STAINED_GLASS;
                concrete = Material.PINK_CONCRETE;
            }
        }

        for (int x = -9; x<10; x++){
            for (int y = 236; y<273; y++){
                for (int z = -9; z<10; z++){
                    Location blockToReplace = new Location(Bukkit.getWorld("world"), x, y, z);
                    if (blockToReplace.getBlock().getType() == concrete){
                        blockToReplace.getBlock().setType(Material.LIGHT_GRAY_CONCRETE);
                    } else if (blockToReplace.getBlock().getType() == glass){
                        blockToReplace.getBlock().setType(Material.LIGHT_GRAY_STAINED_GLASS);
                    }
                }
            }
        }
    }

    public static void resetVotes(){
        noVote = 0;
        alreadyPlayedVote = 0;

        votes = new HashMap<>();

        for (Game game : Game.values()){
            if (gameList.contains(game)){
                votes.put(game, 0);
            }
        }
    }

    public static void castGameVote(Game game, Material chestplateMaterial){

        int vote = 1;

        if (chestplateMaterial == Material.DIAMOND_CHESTPLATE){
            vote = 3;
        } else if (chestplateMaterial == Material.GOLDEN_CHESTPLATE){
            vote = 2;
        }

        if (votes.containsKey(game)){
            votes.put(game, votes.get(game)+vote);
        } else {
            alreadyPlayedVote += vote;
        }
    }

    public static void castBlankVote(Material chestplateMaterial){
        int vote = 1;

        if (chestplateMaterial == Material.DIAMOND_CHESTPLATE){
            vote = 3;
        } else if (chestplateMaterial == Material.GOLDEN_CHESTPLATE){
            vote = 2;
        }
        noVote += vote;
    }

    public static List<Game> getChosenGame(){
        List<Game> chosenGames = new ArrayList<>();
        int max = 0;

        for (Game game : votes.keySet()){
            if (game != Game.HUB){
                if (votes.get(game) > max){
                    max = votes.get(game);
                    chosenGames = new ArrayList<>();
                    chosenGames.add(game);
                } else if (votes.get(game) == max){
                    chosenGames.add(game);
                }
            }
        }

        return chosenGames;
    }

    public static List<Game> getRandomChosenGame(List<Game> chosenGames){
        int random = new Random().nextInt(chosenGames.size());

        return List.of(chosenGames.get(random));
    }

    public static Component getAfterVotingMessage(){
        Component message = Component.text('[')
                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] Also, ", ColorType.TEXT.getColor()));

        message = switch (noVote){
            case 0 -> message.append(Component.text(""));
            case 1 -> message.append(Component.text(1, ColorType.TEXT.getColor(), TextDecoration.BOLD))
                    .append(Component.text(" player hasn't voted").decoration(TextDecoration.BOLD, false));
            default -> message.append(Component.text(noVote, ColorType.TEXT.getColor(), TextDecoration.BOLD))
                    .append(Component.text(" players haven't voted").decoration(TextDecoration.BOLD, false));
        };

        if (alreadyPlayedVote > 0){
            message = message.append(Component.text(" and ", ColorType.TEXT.getColor()));

            if (alreadyPlayedVote == 1){
                message = message.append(Component.text(1, ColorType.MC_LIGHT_GRAY.getColor(), TextDecoration.BOLD))
                        .append(Component.text(" player voted an already played game", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false));
            } else {
                message = message.append(Component.text(alreadyPlayedVote, ColorType.MC_LIGHT_GRAY.getColor(), TextDecoration.BOLD))
                        .append(Component.text(" players voted an already played game", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false));
            }
        }

        message = message.append(Component.text('!', ColorType.TEXT.getColor()));

        return message;
    }

    public static void updateMultiplier(){
        float multiplier = switch ((int) Variables.getVariable("jeu")){
            case 4, 5 -> 1.5f;
            case 6 -> 2f;
            default -> 1f;
        };

        Points.setMultiplier(multiplier);

    }

}
