package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Leaderboard implements CommandExecutor {

    public Leaderboard(TheEvent580_2 plugin) {
        Objects.requireNonNull(plugin.getCommand("lb")).setExecutor(this);
    }

    public static List<Map.Entry<Player, Integer>> getAllPlayers(){

        Map<Player, Integer> unsortedPlayers = new HashMap<>();

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            unsortedPlayers.put(playerManager.getOnlinePlayer(), Points.getPoints(Objects.requireNonNull(playerManager.getOnlinePlayer())));
        }

        return Points.getTop(unsortedPlayers, Players.getOnlinePlayerList().size());
    }

    private static List<Map.Entry<Player, Integer>> getTop10(){

        Map<Player, Integer> unsortedPlayers = new HashMap<>();

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            unsortedPlayers.put(playerManager.getOnlinePlayer(), Points.getPoints(Objects.requireNonNull(playerManager.getOnlinePlayer())));
        }
        return Points.getTop(unsortedPlayers, 10);
    }

    private void showLeaderboard(Player commandSender){

        List<Map.Entry<Player, Integer>> allPlayers = getAllPlayers();

        Component leaderboardText = Component.text("Here is the event ", ColorType.SUBTITLE.getColor())
                .append(Component.text("overall", ColorType.SUBTITLE.getColor(), TextDecoration.BOLD, TextDecoration.UNDERLINED))
                .append(Component.text(" leaderboard :", ColorType.SUBTITLE.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false));

        if (allPlayers.isEmpty()){
            leaderboardText = leaderboardText.append(Component.text("\n\nNo players are online yet.", ColorType.SUBTITLE.getColor()));
        } else {

            int place = 1;
            Component playerText = Component.text("placement here");
            for (Map.Entry<Player, Integer> player : allPlayers){

                boolean isPlayer = false;

                PlayerManager playerManager = Online.getPlayerManager(player.getKey());

                if (player.getKey().getUniqueId() == commandSender.getUniqueId()){
                    isPlayer = true;
                }

                if (place == 1 || place == 21){
                    if (isPlayer){
                        playerText = Component.text("\n" + place + "st", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD, TextDecoration.UNDERLINED)
                                .append(Component.text(" " + playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false))
                                .append(Component.text(player.getKey().getName() + " ", playerManager.getColorType().getColor()))
                                .append(Component.text(" : ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                .append(Component.text(player.getValue(), ColorType.SPECIAL_1.getColor(), TextDecoration.UNDERLINED))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false));
                        leaderboardText = leaderboardText.append(playerText);
                    } else {
                        leaderboardText = leaderboardText.append(Component.text("\n" + place + "st", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" " + playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()))
                                .append(Component.text(player.getKey().getName() + " ", playerManager.getColorType().getColor()))
                                .append(Component.text(" : ", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(player.getValue(), ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()));
                    }
                } else if (place == 2 || place == 22){
                    if (isPlayer){
                        playerText = Component.text("\n" + place + "nd", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD, TextDecoration.UNDERLINED)
                                .append(Component.text(" " + playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false))
                                .append(Component.text(player.getKey().getName() + " ", playerManager.getColorType().getColor()))
                                .append(Component.text(" : ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                .append(Component.text(player.getValue(), ColorType.SPECIAL_1.getColor(), TextDecoration.UNDERLINED))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false));
                        leaderboardText = leaderboardText.append(playerText);
                    } else {
                        leaderboardText = leaderboardText.append(Component.text("\n" + place + "nd", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" " + playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()))
                                .append(Component.text(player.getKey().getName() + " ", playerManager.getColorType().getColor()))
                                .append(Component.text(" : ", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(player.getValue(), ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()));
                    }
                } else if (place == 3 || place == 23){
                    if (isPlayer){
                        playerText = Component.text("\n" + place + "rd", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD, TextDecoration.UNDERLINED)
                                .append(Component.text(" " + playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false))
                                .append(Component.text(player.getKey().getName() + " ", playerManager.getColorType().getColor()))
                                .append(Component.text(" : ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                .append(Component.text(player.getValue(), ColorType.SPECIAL_1.getColor(), TextDecoration.UNDERLINED))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false));
                        leaderboardText = leaderboardText.append(playerText);
                    } else {
                        leaderboardText = leaderboardText.append(Component.text("\n" + place + "rd", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" " + playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()))
                                .append(Component.text(player.getKey().getName() + " ", playerManager.getColorType().getColor()))
                                .append(Component.text(" : ", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(player.getValue(), ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()));
                    }
                } else {
                    if (isPlayer){
                        playerText = Component.text("\n" + place + "th", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD, TextDecoration.UNDERLINED)
                                .append(Component.text(" " + playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false))
                                .append(Component.text(player.getKey().getName() + " ", playerManager.getColorType().getColor()))
                                .append(Component.text(" : ", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                .append(Component.text(player.getValue(), ColorType.SPECIAL_1.getColor(), TextDecoration.UNDERLINED))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()).decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false));
                        leaderboardText = leaderboardText.append(playerText);
                    } else {
                        leaderboardText = leaderboardText.append(Component.text("\n" + place + "th", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" " + playerManager.getTeam().getIcon() + " ", ColorType.TEXT.getColor()))
                                .append(Component.text(player.getKey().getName() + " ", playerManager.getColorType().getColor()))
                                .append(Component.text(" : ", ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(player.getValue(), ColorType.SPECIAL_2.getColor()))
                                .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()));
                    }
                }

                place++;
            }

            if (!Spectators.isSpectator(commandSender)){
                leaderboardText = leaderboardText.append(Component.text("\n\nYou are placed : \n", ColorType.SUBTITLE.getColor()))
                        .append(playerText);
            }
        }

        commandSender.sendMessage(leaderboardText);

    }

    public static void updateTop10NPC(){

        List<Map.Entry<Player, Integer>> top10Players = getTop10();

        int place = 1;
        for (Map.Entry<Player, Integer> player : top10Players){
            NPC npc = CitizensAPI.getNPCRegistry().getById(7+place);
            if (player.getValue() > 0){

                PlayerManager playerManager = Online.getPlayerManager(player.getKey());

                if (place == 1){
                    npc.setName(MiniMessage.miniMessage().serialize(Component.text("Top 1\n", ColorType.GOLD.getColor())
                            .append(Component.text(playerManager.getTeam().getIcon() +  " ", ColorType.TEXT.getColor()))
                            .append(Component.text(player.getKey().getName(), playerManager.getColorType().getColor()))
                            .append(Component.text(" " + Points.formatPoints(Points.getPoints(player.getKey())), ColorType.SUBTEXT.getColor()))
                            .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()))));
                } else if (place == 2){
                    npc.setName(MiniMessage.miniMessage().serialize(Component.text("Top 2\n", ColorType.SILVER.getColor())
                            .append(Component.text(playerManager.getTeam().getIcon() +  " ", ColorType.TEXT.getColor()))
                            .append(Component.text(player.getKey().getName(), playerManager.getColorType().getColor()))
                            .append(Component.text(" " + Points.formatPoints(Points.getPoints(player.getKey())), ColorType.SUBTEXT.getColor()))
                            .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()))));
                } else if (place == 3){
                    npc.setName(MiniMessage.miniMessage().serialize(Component.text("Top 3\n", ColorType.BRONZE.getColor())
                            .append(Component.text(playerManager.getTeam().getIcon() +  " ", ColorType.TEXT.getColor()))
                            .append(Component.text(player.getKey().getName(), playerManager.getColorType().getColor()))
                            .append(Component.text(" " + Points.formatPoints(Points.getPoints(player.getKey())), ColorType.SUBTEXT.getColor()))
                            .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()))));
                } else {
                    npc.setName(MiniMessage.miniMessage().serialize(Component.text("Top " + place + "\n", ColorType.SUBTEXT.getColor())
                            .append(Component.text(playerManager.getTeam().getIcon() +  " ", ColorType.TEXT.getColor()))
                            .append(Component.text(player.getKey().getName(), playerManager.getColorType().getColor()))
                            .append(Component.text(" " + Points.formatPoints(Points.getPoints(player.getKey())), ColorType.SUBTEXT.getColor()))
                            .append(Component.text(" 工", ColorType.NO_SHADOW.getColor()))));
                }
                npc.getOrAddTrait(SkinTrait.class).setSkinPersistent(player.getKey());

            } else {
                npc.setName(MiniMessage.miniMessage().serialize(Component.text("Top " + place + " - TBD", ColorType.SUBTEXT.getColor())));
                npc.getOrAddTrait(SkinTrait.class).setSkinPersistent("QuestionMark",
                        "RPxcif+B1vYlZgxQKcZaMdJMlDfOLuHMsG+bHLEUulkOVMZIRXcEIp/YGtgPHcEjZ8tqVDPE6E+mFqnpY03JPsz1aOrWvr3D4dx7ZEMtQE/drjD6c5tFdkPt8xJi4BP79xQsQJtSKZsCn0xRUfDKugmn0i0Waxaw5UFvGDkQbhYWC9lGwNcblIYqube/L/XAtnOiDKQ8zT0hdqsuJ5Ny5AveW3hxBv221/gmwrrPwdgYX5egApxirooaoB1vVULCfd7opBpSs7IGyp4jM5PlrU8TGUMDX7/oHh4GRG9f3NG2ccbftcg05eXxok7QVBHXMnP5o3r0V71l0tvj5VyqN/uiJDWqcmaLsCt2Qeu/3hMclTxGEm3IhWWXFWcWfkATfZ6hg+Kuk7bLBNcTExwj+veu5MIm8vTLp2MXkYCZ3zv6RzLolSyM7jGG3wAczGpY01HpaIMgkOA1SUMlmu1+pRJkTHDXPTC3XGyNaY4WzgkbBgvPOD0Z1052dtG9enbRLzhCpGNxV5N1nMiod6objHdXL4MUtiuT29ANcyHm7WHSm/f5cJ8qtrF9e3VYOnXECBGBym6DiTHY1V4nSnkCtXv8rQyrpo5XJdEzCDMyLkvxqbUj9+YTz9MkAqEngqZkpCzukXbEn3pIveXMD+oMV6ncRg27qrDPKphHuMFk5TM=",
                        "ewogICJ0aW1lc3RhbXAiIDogMTYzNjEzNTYwNzkwOCwKICAicHJvZmlsZUlkIiA6ICJkZTE0MGFmM2NmMjM0ZmM0OTJiZTE3M2Y2NjA3MzViYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJTUlRlYW0iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NkOGYwMjUwMTc4NWQxNmIwOTNmNGQ2NTk2OThhNzlmY2Q1MGEwMDk0ZjUzMzQ5ZDQ4ZDY2MzJmYzZlMTMyZCIKICAgIH0KICB9Cn0=");
            }
            place++;
        }

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player){
            if (player.isOp()){
                showLeaderboard(player);
                return true;
            } else {
                if (Variables.equals("jeu", 0)){
                    player.sendMessage(Component.text("No games have been played yet.", ColorType.SUBTITLE.getColor()));
                } else if (Timer.getEnum() == Timer.TimerEnum.POST_LAST_GAME){
                    player.sendMessage(Component.text("You cannot see the leaderboard as the winner hasn't been announced yet.", ColorType.SUBTITLE.getColor()));
                } else if (!player.getWorld().getName().equals("world")){
                    player.sendMessage(Component.text("You cannot check the leaderboard while being in a game.", ColorType.SUBTITLE.getColor()));
                } else {
                    showLeaderboard(player);
                    return true;
                }
            }
        } else {
            sender.sendMessage("Only a player can execute this command!");
        }

        return false;
    }
}
