package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TabListManager {

    public static void sendCustomTab(){

        for (Player player : Bukkit.getOnlinePlayers()){

            Component header = Component.text("");

            Component title = Component.text("\n\n\n\n      TheEvent580 • Season 1 - Episode 7      ", ColorType.TITLE.getColor(), TextDecoration.BOLD);
            title = title.append(Component.text("").decoration(TextDecoration.BOLD, false));

            header = header.append(title);

            Component game = Component.text("Currently in : ", ColorType.SPECIAL_2.getColor());
            game = game.append(getJeuNomComponent(Objects.requireNonNull(Variables.getVariable("jeu_nom")).toString()))
                    .append(Component.text("\n\n\n"));

            header = header.append(game);

            Component spectators = Component.text("Spectators : \n", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD);

            for (OfflinePlayer spectator : Spectators.getSpectatorList()){
                Component spectatorComponent = Component.text("");
                if (spectator.isOnline()){
                    PlayerManager playerManager = Spectators.getPlayerManager(spectator);
                    assert playerManager != null;
                    spectatorComponent = spectatorComponent.append(Component.text("\n" + Team.SPECTATORS.getIcon() + " ", ColorType.TEXT.getColor()))
                            .append(Component.text(Objects.requireNonNull(playerManager.getName()), playerManager.getColorType().getColor()));
                } else {
                    spectatorComponent = spectatorComponent.append(Component.text("\n" + Team.OFFLINE.getIcon() + " ", ColorType.TEXT.getColor()))
                            .append(Component.text(Objects.requireNonNull(spectator.getName()), Team.OFFLINE.getColorType().getColor()));
                }

                spectators = spectators.append(spectatorComponent.decoration(TextDecoration.BOLD, false));
            }

            spectators = spectators.append(Component.text("\n\n"));

            header = header.append(spectators);

            Component players = Component.text("Players : \n", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD);

            for (OfflinePlayer loopPlayer : Players.getPlayerList()){
                Component playerComponent = Component.text("\n");
                if (loopPlayer.isOnline()){
                    PlayerManager playerManager = Players.getPlayerManager(loopPlayer);
                    assert playerManager != null;
                    playerComponent = playerComponent.append(Component.text(playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()))
                            .append(Component.text(Objects.requireNonNull(loopPlayer.getName()) + " ", playerManager.getColorType().getColor()));

                    if (Points.getTotalPointsPreEvent(Objects.requireNonNull(playerManager.getOfflinePlayer().getPlayer())) <= 0){
                        playerComponent = playerComponent.append(Component.text("[NEW!] ", ColorType.RAINBOW.getColor()));
                    }

                    if (Objects.equals(Variables.getVariable("jeu"), 6)){
                        playerComponent = playerComponent.append(Component.text(": ", ColorType.SUBTEXT.getColor()))
                                .append(Component.text("points", ColorType.SUBTEXT.getColor(), TextDecoration.OBFUSCATED))
                                .append(Component.text(" 工").decoration(TextDecoration.OBFUSCATED, false));
                    } else {
                        playerComponent = playerComponent.append(Component.text(": " + Points.formatPoints(Points.getPoints(Objects.requireNonNull(playerManager.getOfflinePlayer().getPlayer())) )+ " 工", ColorType.SUBTEXT.getColor()));
                    }

                } else {
                    playerComponent = playerComponent.append(Component.text(Team.OFFLINE.getIcon() + " ", ColorType.TEXT.getColor()))
                            .append(Component.text(Objects.requireNonNull(loopPlayer.getName()), Team.OFFLINE.getColorType().getColor()));
                }

                players = players.append(playerComponent.decoration(TextDecoration.BOLD, false));
            }

            players = players.append(Component.text("\n\n\n"));

            header = header.append(players);

            Component blocker = Component.text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            player.sendPlayerListHeader(header.append(blocker));
        }
    }

    private static @NotNull Component getJeuNomComponent(String jeuNom) {
        return ScoreboardManager.getComponent(jeuNom);
    }

}
