package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;

import javax.swing.*;
import java.util.*;

public class Voting {


    private static final List<Game> gameList = new ArrayList<>();
    private static Map<Game, Integer> votes = new HashMap<>();
    private static int noVote;
    private static int alreadyPlayedVote;
    private static float multiplier = 1;

    public static void addAllGames(){
        gameList.addAll(Game.playedGames());
    }

    public static void addGame(Game game){
        gameList.add(game);
    }

    public static void removeGame(Game game){
        gameList.remove(game);
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
            if (votes.get(game) > max){
                max = votes.get(game);
                chosenGames = new ArrayList<>();
                chosenGames.add(game);
            } else if (votes.get(game) == max){
                chosenGames.add(game);
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

    public static float getMultiplier(){
        return multiplier;
    }

    public static void updateMultiplier(){
        multiplier = switch ((int) Variables.getVariable("jeu")){
            case 4, 5 -> 1.5f;
            case 6 -> 2f;
            default -> 1f;
        };

    }

}
