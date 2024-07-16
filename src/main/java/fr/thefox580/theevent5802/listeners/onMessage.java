package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.commands.utils.ColorType;
import fr.thefox580.theevent5802.commands.utils.Colors;
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
import org.jetbrains.annotations.NotNull;

public class onMessage implements Listener, ChatRenderer {

    private final TheEvent580_2 advMain;

    public onMessage(TheEvent580_2 advMain) {
        this.advMain = advMain;
    }

    @EventHandler
    public void onPlayerMessage(AsyncChatEvent event){

        if (isBannedWordInMessage(PlainTextComponentSerializer.plainText().serialize(event.originalMessage()))){
            int time = 10000;
            while (time > 0){
            event.getPlayer().sendActionBar(Component.text("One word in your message is banned due to not being PG", Colors.getColor(ColorType.SUBTITLE), TextDecoration.UNDERLINED, TextDecoration.BOLD));
                time--;
            }
        }

        if (isMuted() || isBannedWordInMessage(PlainTextComponentSerializer.plainText().serialize(event.originalMessage()))){
            event.setCancelled(true);
        } else {
            event.renderer(this);
        }

    }

    private boolean isMuted() {
        return advMain.getConfig().getBoolean("muted_chat");
    }

    private static boolean isBannedWordInMessage(String messageContent) {
        boolean bannedWordInMessage = false;
        int index = 0;

        String[] bannedWords = {"kys", "shit", "nigge", "fuck", "nigga", "nigger", "niggas", "niggers", "fag", "faggot", "fagg0t"}; //Set an Array with death messages

        for (int i = 0; i <= bannedWords.length - 1; i++) {

            if (messageContent.contains(bannedWords[i])) {
                bannedWordInMessage = true;
                index = i;
            }
        }
        return bannedWordInMessage;
    }

    @Override
    public @NotNull Component render(@NotNull Player player, @NotNull Component component, @NotNull Component component1, @NotNull Audience audience) {

        PlainTextComponentSerializer plainTextComponentSerializer = PlainTextComponentSerializer.plainText();
        String messageFR = plainTextComponentSerializer.serialize(component1);
        FileConfiguration config = this.advMain.getConfig();

        Component componentPlayer = Component.translatable("%nox_uuid%" + player.getUniqueId() + ",false,0,-1,1", "\uD83D\uDC64"); //Setup custom player head
        TextColor colorPlayer = Colors.getColor(ColorType.TEXT); //Set color of text to white (base for if the player doesn't have a team)
        String teamPlayer = "タ"; //Set the tag of the player's team

        if (player.hasPermission("group.spectators")){
            colorPlayer = Colors.getColor(ColorType.MC_GRAY); //Set color of text to red
            teamPlayer = "露"; //Set the tag of the player's team
        } else if (player.hasPermission("group.rouge")) { //If the player is in team red
            colorPlayer = Colors.getColor(ColorType.MC_RED); //Set color of text to red
            teamPlayer = "ラ"; //Set the tag of the player's team

        } else if (player.hasPermission("group.orange")) { //If the player is in team orange
            colorPlayer = Colors.getColor(ColorType.MC_ORANGE); //Set color of text to orange
            teamPlayer = "ャ"; //Set the tag of the player's team

        } else if (player.hasPermission("group.jaune")) { //If the player is in team yellow
            colorPlayer = Colors.getColor(ColorType.MC_YELLOW); //Set color of text to yellow
            teamPlayer = "ギ"; //Set the tag of the player's team

        } else if (player.hasPermission("group.vert")) { //If the player is in team lime / green
            colorPlayer = Colors.getColor(ColorType.MC_LIME); //Set color of text to lime / green
            teamPlayer = "画"; //Set the tag of the player's team

        } else if (player.hasPermission("group.bleu_clair")) { //If the player is in team light blue
            colorPlayer = Colors.getColor(ColorType.MC_AQUA); //Set color of text to light blue
            teamPlayer = "動"; //Set the tag of the player's team

        } else if (player.hasPermission("group.bleu")) { //If the player is in team blue
            colorPlayer = Colors.getColor(ColorType.MC_BLUE); //Set color of text to blue
            teamPlayer = "像"; //Set the tag of the player's team

        } else if (player.hasPermission("group.violet")) { //If the player is in team purple
            colorPlayer = Colors.getColor(ColorType.MC_PURPLE); //Set color of text to purple
            teamPlayer = "の"; //Set the tag of the player's team

        } else if (player.hasPermission("group.rose")) { //If the player is in team pink
            colorPlayer = Colors.getColor(ColorType.MC_PINK); //Set color of text to pink
            teamPlayer = "目"; //Set the tag of the player's team
        }

        if (player.hasPermission("op")){
            teamPlayer += "リ"; //Set the tag of the player's team
        }

        Component message;

        switch (messageFR) {
            case ":skull:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('☠', Colors.getColor(ColorType.TEXT)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('☠', Colors.getColor(ColorType.TEXT)));
                }
            }
            case ":darkredlove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_DARK_RED)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_DARK_RED)));
                }
            }
            case ":redlove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_RED)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_RED)));
                }
            }
            case ":orangelove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_ORANGE)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_ORANGE)));
                }
            }
            case ":yellowlove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_YELLOW)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_YELLOW)));
                }
            }
            case ":limelove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_LIME)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_LIME)));
                }
            }
            case ":greenlove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_GREEN)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_GREEN)));
                }
            }
            case ":cyanlove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_CYAN)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_CYAN)));
                }
            }
            case ":lightbluelove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_AQUA)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_AQUA)));
                }
            }
            case ":bluelove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_BLUE)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_BLUE)));
                }
            }
            case ":darkbluelove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_DARK_BLUE)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_DARK_BLUE)));
                }
            }
            case ":purplelove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_PURPLE)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_PURPLE)));
                }
            }
            case ":pinklove:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_PINK)));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text('❤', Colors.getColor(ColorType.MC_PINK)));
                }
            }
            default -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text(messageFR));
                } else {
                    message = Component.text(teamPlayer, Colors.getColor(ColorType.TEXT))
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", Colors.getColor(ColorType.TEXT)))
                            .append(Component.text(messageFR));
                }
            }
        }
        return message;
    }
}
