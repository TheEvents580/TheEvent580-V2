package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.commands.Mute;
import fr.thefox580.theevent5802.games.trials.Trials;
import fr.thefox580.theevent5802.games.trials.TrialsDecision;
import fr.thefox580.theevent5802.games.trials.TrialsTasks;
import fr.thefox580.theevent5802.utils.*;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OnMessage implements Listener, ChatRenderer {

    private final TheEvent580_2 plugin;

    public OnMessage(TheEvent580_2 plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMessage(AsyncChatEvent event){

        if (isBannedWordInMessage(PlainTextComponentSerializer.plainText().serialize(event.originalMessage()))){
            new BukkitRunnable() {
                private int time = 3*20;

                @Override
                public void run() {
                    if (time > 0){
                        time--;
                    } else {
                        this.cancel();
                    }
                    event.getPlayer().sendActionBar(Component.text("Your message wasn't sent due to moderation conflicts", ColorType.SUBTITLE.getColor(), TextDecoration.UNDERLINED, TextDecoration.BOLD));
                }
            }.runTaskTimer(plugin, 0L, 1L);
            event.setCancelled(true);
        }

        if (isMuted()){
            event.setCancelled(true);
            event.getPlayer().sendMessage(Component.text("⚠ ", ColorType.MC_ORANGE.getColor())
                    .append(Component.text("The chat is currently muted!", ColorType.TEXT.getColor())));
        } else if (Online.getPlayerManager(event.getPlayer()).isMuted()){
            event.setCancelled(true);
            event.getPlayer().sendMessage(Component.text("⚠ ", ColorType.MC_ORANGE.getColor())
                    .append(Component.text("You are currently muted by one of the staff members!", ColorType.TEXT.getColor())));
        } else {
            event.renderer(this);
        }

    }

    private boolean isMuted() {
        return Mute.isGlobalMute();
    }

    private static boolean isBannedWordInMessage(String messageContent) {
        String[] bannedWords = {"kys", "shit", "nigge", "fuck", "nigga", "nigger", "niggas", "niggers", "fag", "faggot", "fagg0t"}; //Set an Array with death messages

        for (String word : messageContent.split("")){
            if (Arrays.stream(bannedWords).toList().contains(word)){
                return true;
            }
        }
        return false;
    }

    @Override
    public @NotNull Component render(@NotNull Player player, @NotNull Component component, @NotNull Component component1, @NotNull Audience audience) {

        PlainTextComponentSerializer plainTextComponentSerializer = PlainTextComponentSerializer.plainText();
        String messageFR = plainTextComponentSerializer.serialize(component1);
        FileConfiguration config = this.plugin.getConfig();


        PlayerManager playerManager;

        if (Players.isPlayer(player)){
            playerManager = Players.getPlayerManager(player);
        } else {
            playerManager = Spectators.getPlayerManager(player);
        }
        assert playerManager != null;

        Component componentPlayer = Component.translatable("%nox_uuid%" + player.getUniqueId() + ",false,0,-1,1", "\uD83D\uDC64"); //Setup custom player head
        TextColor colorPlayer = playerManager.getColorType().getColor(); //Set color of text to white (base for if the player doesn't have a team)
        String teamPlayer = playerManager.getTeam().getIcon(); //Set the tag of the player's team

        if (colorPlayer == playerManager.getTeam().getColorType().getColor()){
            if (playerManager.isAdmin()){
                teamPlayer += Team.ADMIN.getIcon();
                colorPlayer = Team.ADMIN.getColorType().getColor();
            }
            else if (playerManager.isStaff()){
                teamPlayer += Team.STAFF.getIcon();
                colorPlayer = Team.STAFF.getColorType().getColor();
            }
        }

        Component message = getMessage(teamPlayer, componentPlayer, player, colorPlayer, messageFR);

        if ((int) Objects.requireNonNull(Variables.getVariable("jeu_condi")) == Game.TRIALS.getGameCondition() && List.of(7, 8, 9, 26, 27, 28, 41, 42, 43, 44, 45, 46, 47, 48).contains(TrialsTasks.getCurrentTrial())){

            switch (TrialsDecision.getLastTrial()){
                case 7 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase("minecraft")){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" typed \"Minecraft\" correctly", ColorType.MC_LIME.getColor()));
                    }
                }
                case 8 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase("theevent580")){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" typed \"TheEvent580\" correctly", ColorType.MC_LIME.getColor()));
                    }
                }
                case 9 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase("trials")){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" typed \"Trials\" correctly", ColorType.MC_LIME.getColor()));
                    }
                }

                case 26 -> {
                    if (Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase("minecraft")){
                        Trials.setRoundPoints(player, 0);

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" sadly typed \"Minecraft\" correctly", ColorType.MC_RED.getColor()));
                    }
                }
                case 27 -> {
                    if (Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase("theevent580")){
                        Trials.setRoundPoints(player, 0);

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" sadly typed \"TheEvent580\" correctly", ColorType.MC_RED.getColor()));
                    }
                }
                case 28 -> {
                    if (Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase("trials")){
                        Trials.setRoundPoints(player, 0);

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" sadly typed \"Trials\" correctly", ColorType.MC_RED.getColor()));
                    }
                }

                case 41 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase(String.valueOf(4*2))){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" answered correctly!", ColorType.MC_LIME.getColor()));
                    }
                }
                case 42 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase(String.valueOf(6*3))){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" answered correctly!", ColorType.MC_LIME.getColor()));
                    }
                }
                case 43 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase(String.valueOf(489-259+5))){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" answered correctly!", ColorType.MC_LIME.getColor()));
                    }
                }
                case 44 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase(String.valueOf(Math.pow(5, 1)))){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" answered correctly!", ColorType.MC_LIME.getColor()));
                    }
                }
                case 45 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase(String.valueOf(3*35))){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" answered correctly!", ColorType.MC_LIME.getColor()));
                    }
                }
                case 46 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase(String.valueOf(Math.pow(165, 0)))){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" answered correctly!", ColorType.MC_LIME.getColor()));
                    }
                }
                case 47 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase(String.valueOf(9+10))){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" answered correctly!", ColorType.MC_LIME.getColor()));
                    } else if (messageFR.equalsIgnoreCase(String.valueOf(21))){
                        player.sendMessage(Component.text("No, the answer isn't 21, try again...", ColorType.MC_RED.getColor()));
                    }
                }
                case 48 -> {
                    if (!Trials.hasPlayerCompleted(player) && messageFR.equalsIgnoreCase(String.valueOf(1+1))){
                        Trials.setRoundPoints(player, Math.round(TrialsTasks.getCurrentTrialPoints()));

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(" answered correctly!", ColorType.MC_LIME.getColor()));
                    }
                }
            }

        } else {

            switch (messageFR) {
                case ":skull:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('☠', ColorType.TEXT.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('☠', ColorType.TEXT.getColor()));
                    }
                }
                case ":darkredlove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_DARK_RED.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_DARK_RED.getColor()));
                    }
                }
                case ":redlove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_RED.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_RED.getColor()));
                    }
                }
                case ":orangelove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_ORANGE.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_ORANGE.getColor()));
                    }
                }
                case ":yellowlove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_YELLOW.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_YELLOW.getColor()));
                    }
                }
                case ":limelove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_LIME.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_LIME.getColor()));
                    }
                }
                case ":greenlove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_GREEN.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_GREEN.getColor()));
                    }
                }
                case ":cyanlove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_CYAN.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_CYAN.getColor()));
                    }
                }
                case ":lightbluelove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_AQUA.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_AQUA.getColor()));
                    }
                }
                case ":bluelove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_BLUE.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor())
                                        .append(Component.text('❤', ColorType.MC_BLUE.getColor())));
                    }
                }
                case ":darkbluelove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_DARK_BLUE.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_DARK_BLUE.getColor()));
                    }
                }
                case ":purplelove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_PURPLE.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_PURPLE.getColor()));
                    }
                }
                case ":pinklove:" -> {

                    if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_PINK.getColor()));
                    } else {
                        message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                                .append(Component.text(' '))
                                .append(componentPlayer)
                                .append(Component.text(' ' + player.getName(), colorPlayer))
                                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                                .append(Component.text('❤', ColorType.MC_PINK.getColor()));
                    }
                }
            }
        }
        return message;
    }

    private Component getMessage(String teamPlayer, Component componentPlayer, Player player, TextColor colorPlayer, String message){
        FileConfiguration config = this.plugin.getConfig();

        if (plugin.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

            return Component.text(teamPlayer, ColorType.TEXT.getColor())
                    .append(Component.text(' '))
                    .append(componentPlayer)
                    .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                    .append(Component.text(' ' + player.getName(), colorPlayer))
                    .append(Component.text(" > ", ColorType.TEXT.getColor()))
                    .append(Component.text(message));
        }
        return Component.text(teamPlayer, ColorType.TEXT.getColor())
                .append(Component.text(' '))
                .append(componentPlayer)
                .append(Component.text(' ' + player.getName(), colorPlayer))
                .append(Component.text(" > ", ColorType.TEXT.getColor()))
                .append(Component.text(message));
    }
}
