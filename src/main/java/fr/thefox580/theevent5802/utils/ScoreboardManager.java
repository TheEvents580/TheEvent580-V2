package fr.thefox580.theevent5802.utils;

import fr.mrmicky.fastboard.adventure.FastBoard;
import fr.thefox580.theevent5802.games.arms_race.ArmsRace;
import fr.thefox580.theevent5802.games.bow_pvp.BowPVP;
import fr.thefox580.theevent5802.games.build_masters.BuildMasters;
import fr.thefox580.theevent5802.games.finder.Finder;
import fr.thefox580.theevent5802.games.multilap.Multilap;
import fr.thefox580.theevent5802.games.parkour.Parkour;
import fr.thefox580.theevent5802.games.tag.Tag;
import fr.thefox580.theevent5802.games.trials.Trials;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ScoreboardManager {

    private static final Map<UUID, FastBoard> fastboards = new HashMap<>();

    public static Map<UUID, FastBoard> getBoards(){
        return fastboards;
    }

    public static void createBoard(Player player){
        FastBoard board = new FastBoard(player);

        board.updateTitle(Component.text("    TheEvent580 • Season 1    ", ColorType.TITLE.getColor(), TextDecoration.BOLD));

        fastboards.put(player.getUniqueId(), board);
    }

    public static void deleteBoard(Player player){
        FastBoard board = fastboards.remove(player.getUniqueId());

        if (board != null){
            board.delete();
        }
    }

    public static void updateBoard(FastBoard board){

        Player player = board.getPlayer();

        board.updateLine(0, Component.text(""));
        board.updateLine(1, Component.text("Minigame :", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));

        String jeuNom = Objects.requireNonNull(Variables.getVariable("jeu_nom")).toString();
        Component jeuNomComponent = getJeuNomComponent(jeuNom);

        board.updateLine(2, Component.text(Variables.getVariable("jeu") + "/6", ColorType.SUBTEXT.getColor())
                .append(Component.text(" - ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD))
                .append(jeuNomComponent));

        board.updateLine(3, Component.text(""));

        board.updateLine(4, Component.text("Players Online: ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                .append(Component.text(Players.getPlayerCount() + "/" + Players.getMaxPlayerCount(), ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));

        board.updateLine(5, Component.text("Everybody is here!", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));

        int jeuCondi = (int) Variables.getVariable("jeu_condi");

        switch (jeuCondi) {
            case 2 -> {
                if (player.getAllowFlight()) { // Can fly = is done
                    board.updateLine(5, Component.text("Waiting for other players", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));
                } else {
                    Component mainLevel = Parkour.getMainLevel(player);
                    Component subLevel = Parkour.getSubLevel(player);
                    board.updateLine(5, Component.text("Level ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(mainLevel)
                            .append(Component.text(" | ", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false))
                            .append(subLevel));
                }
            }
            case 5 -> {
                if (player.hasPermission("group.spectators")) {
                    board.updateLine(5, Component.text("Current opened path : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(Component.text(Multilap.getOpenPath(), ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (player.getAllowFlight()) { // Can fly = is done
                    board.updateLine(5, Component.text("Waiting for other players", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));
                } else {
                    Component checkpoint = Multilap.getPlayerCheckpoint(player);
                    board.updateLine(5, Component.text("Current checkpoint : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(checkpoint));
                }
            }
            case 8 -> {
                if (player.hasPermission("group.spectators")) {
                    board.updateLine(5, Component.text("Total Completed Builds : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(Component.text(BuildMasters.getTotalCompletedBuild(), ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else {
                    Component timeLeft = Objects.requireNonNull(Players.getPlayerManager(player)).getTimer().timeLeft();
                    board.updateLine(5, Component.text("Time Left : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(timeLeft));
                }
            }
            case 9 -> {
                if (player.hasPermission("group.spectators")) {
                    board.updateLine(5, Component.text("Waiting for other players", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));
                } else {
                    if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType() == Material.NETHERITE_HELMET) {
                        board.updateLine(5, Component.text("You are not invincible", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));
                    } else {
                        Component invincibilityTime = BowPVP.getPlayerInvincibilityTime(player);
                        board.updateLine(5, Component.text("You are invincible for : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                                .append(invincibilityTime));
                    }
                }
            }
            case 10 ->
                    board.updateLine(5, Component.text("Current speed of trials : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(Component.text(Trials.getTrialsSpeed() + "s", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
            case 11 ->
                    board.updateLine(5, Component.text("Current map size : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(Component.text(Tag.getWorldSize() + "%", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
            case 12 -> {
                if (player.hasPermission("group.spectators")) {
                    ArrayList<Player> topPlayers = ArmsRace.getFurthestPlayers();
                    if (topPlayers.size() == 1) {
                        board.updateLine(5, Component.text("Player in lead : ", ColorType.SUBTEXT.getColor(), TextDecoration.BOLD)
                                .append(Component.text(topPlayers.getFirst().getName(), Objects.requireNonNull(Players.getPlayerManager(topPlayers.getFirst())).getTeam().getColorType().getColor()))
                                .append(Component.text(" with ", ColorType.SUBTEXT.getColor()))
                                .append(Component.text(ArmsRace.getPlayerKills(topPlayers.getFirst()), ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD))
                                .append(Component.text(" kills", ColorType.SUBTEXT.getColor())));
                    } else {
                        board.updateLine(5, Component.text("Player in lead : ", ColorType.SUBTEXT.getColor(), TextDecoration.BOLD)
                                .append(Component.text(topPlayers.size() + " players", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" with ", ColorType.SUBTEXT.getColor()))
                                .append(Component.text(ArmsRace.getPlayerKills(topPlayers.getFirst()), ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD))
                                .append(Component.text(" kills", ColorType.SUBTEXT.getColor())));
                    }
                } else {
                    board.updateLine(5, Component.text("Next weapon : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(ArmsRace.getNextPlayerItem(player).displayName().color(ColorType.SUBTEXT.getColor())));
                }
            }
            case 13 -> {
                if (player.hasPermission("group.spectators")) {
                    board.updateLine(5, Component.text("Current Item Set : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(Component.text(Finder.getCurrentItemSet(), ColorType.SUBTEXT.getColor())));
                } else {
                    board.updateLine(5, Component.text("Items found : ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                            .append(Component.text(Finder.getPlayerItemsFound(player) + "/" + Finder.getTotalItemsFound())));
                }
            }
            case null, default -> {
                if (Players.getPlayerCount() < Players.getMaxPlayerCount()) {
                    int playersMissing = Players.getMaxPlayerCount() - Players.getPlayerCount();

                    if (playersMissing == 1) {
                        board.updateLine(5, Component.text("Waiting for ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                                .append(Component.text(playersMissing, ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false))
                                .append(Component.text(" player", ColorType.SPECIAL_2.getColor())));
                    } else {
                        board.updateLine(5, Component.text("Waiting for ", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)
                                .append(Component.text(playersMissing, ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false))
                                .append(Component.text(" players", ColorType.SPECIAL_2.getColor())));
                    }
                }
            }
        }

        board.updateLine(6, Component.text(""));

        List<Map.Entry<Player, Integer>> playersSorted = Points.getTopEvent();

        if (Objects.equals(jeuCondi, 0)){
            board.updateLine(7, Component.text("Top 3 Event :", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));
        } else {
            playersSorted = Points.getTopGame();
            board.updateLine(7, Component.text("Top 3 Game :", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));
        }
        if (playersSorted.get(0).getValue() < 1){
            board.updateLine(8, Component.text("1. ", ColorType.GOLD.getColor(), TextDecoration.BOLD)
                    .append(Component.text("None", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
        } else {
            board.updateLine(8, Component.text("1. ", ColorType.GOLD.getColor(), TextDecoration.BOLD)
                    .append(Component.text(playersSorted.getFirst().getKey().getName(), Objects.requireNonNull(Players.getPlayerManager(playersSorted.getFirst().getKey())).getColorType().getColor()).decoration(TextDecoration.BOLD, false))
                    .append(Component.text(" with ", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false))
                    .append(Component.text(Points.formatPoints(playersSorted.getFirst().getValue())))
                    .append(Component.text(" 工",ColorType.TEXT.getColor())));
        }

        if (playersSorted.get(1).getValue() < 1){
            board.updateLine(9, Component.text("2. ", ColorType.SILVER.getColor(), TextDecoration.BOLD)
                    .append(Component.text("None", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
        } else {
            board.updateLine(9, Component.text("2. ", ColorType.SILVER.getColor(), TextDecoration.BOLD)
                    .append(Component.text(playersSorted.get(1).getKey().getName(), Objects.requireNonNull(Players.getPlayerManager(playersSorted.get(1).getKey())).getColorType().getColor()).decoration(TextDecoration.BOLD, false))
                    .append(Component.text(" with ", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false))
                    .append(Component.text(Points.formatPoints(playersSorted.get(1).getValue())))
                    .append(Component.text(" 工",ColorType.TEXT.getColor())));
        }

        if (playersSorted.get(2).getValue() < 1){
            board.updateLine(10, Component.text("3. ", ColorType.BRONZE.getColor(), TextDecoration.BOLD)
                    .append(Component.text("None", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
        } else {
            board.updateLine(10, Component.text("3. ", ColorType.BRONZE.getColor(), TextDecoration.BOLD)
                    .append(Component.text(playersSorted.get(2).getKey().getName(), Objects.requireNonNull(Players.getPlayerManager(playersSorted.get(2).getKey())).getColorType().getColor()).decoration(TextDecoration.BOLD, false))
                    .append(Component.text(" with ", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false))
                    .append(Component.text(Points.formatPoints(playersSorted.get(2).getValue())))
                    .append(Component.text(" 工",ColorType.TEXT.getColor())));
        }

        board.updateLine(11, Component.text(""));

        if (player.hasPermission("group.spectators")){
            board.updateLine(12, Component.text("Event time : ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD)
                            .append(Component.text(EventTime.getFormatedTimer(), ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
            board.updateLine(13, Component.text("Total event time : ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD)
                            .append(Component.text(TotalEventTime.getFormatedTimer(), ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
        } else {
            board.updateLine(12, Component.text("Your ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD)
                            .append(Component.text("工 ", ColorType.TEXT.getColor()))
                            .append(Component.text(": ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                            .append(Component.text(Points.formatPoints(Points.getPoints(player)), ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
            if (Objects.equals(jeuCondi, 0)){
                board.updateLine(13, Component.text("Your all-time ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD)
                        .append(Component.text("工 ", ColorType.TEXT.getColor()))
                        .append(Component.text(": ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                        .append(Component.text(Points.formatPoints(Points.getTotalPoints(player)), ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
            }
            else {
                board.updateLine(13, Component.text("Your in-game ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD)
                        .append(Component.text("工 ", ColorType.TEXT.getColor()))
                        .append(Component.text(": ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                        .append(Component.text(Points.formatPoints(Points.getGamePoints(player)), ColorType.SUBTEXT.getColor()).decoration(TextDecoration.BOLD, false)));
            }
        }
        board.updateLine(14, Component.text(""));

    }

    private static @NotNull Component getJeuNomComponent(String jeuNom) {
        return getComponent(jeuNom);
    }

    static @NotNull Component getComponent(String jeuNom) {
        Component jeuNomComponent = Component.text(jeuNom, ColorType.SUBTEXT.getColor(), TextDecoration.BOLD);

        if (jeuNom.equals(Game.TRIALS.getName())){
            jeuNomComponent = jeuNomComponent.color(Game.TRIALS.getColorType().getColor());
        } else if (jeuNom.equals(Game.PARKOUR.getName())){
            jeuNomComponent = jeuNomComponent.color(Game.PARKOUR.getColorType().getColor());
        } else if (jeuNom.equals(Game.FINDER.getName())){
            jeuNomComponent = jeuNomComponent.color(Game.FINDER.getColorType().getColor());
        } else if (jeuNom.equals(Game.TAG.getName())){
            jeuNomComponent = jeuNomComponent.color(Game.TAG.getColorType().getColor());
        } else if (jeuNom.equals(Game.MULTILAP.getName())){
            jeuNomComponent = jeuNomComponent.color(Game.MULTILAP.getColorType().getColor());
        } else if (jeuNom.equals(Game.BUILD_MASTERS.getName())){
            jeuNomComponent = jeuNomComponent.color(Game.BUILD_MASTERS.getColorType().getColor());
        } else if (jeuNom.equals(Game.ARMS_RACE.getName())){
            jeuNomComponent = jeuNomComponent.color(Game.ARMS_RACE.getColorType().getColor());
        } else if (jeuNom.equals(Game.BOW_PVP.getName())){
            jeuNomComponent = jeuNomComponent.color(Game.BOW_PVP.getColorType().getColor());
        } else {
            jeuNomComponent = jeuNomComponent.color(ColorType.TEXT.getColor());
        }
        return jeuNomComponent;
    }


}
