package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
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

import java.util.Objects;

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
            event.getPlayer().sendActionBar(Component.text("One word in your message is banned due to not being PG", ColorType.SUBTITLE.getColor(), TextDecoration.UNDERLINED, TextDecoration.BOLD));
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
        String[] bannedWords = {"kys", "shit", "nigge", "fuck", "nigga", "nigger", "niggas", "niggers", "fag", "faggot", "fagg0t"}; //Set an Array with death messages

        for (int i = 0; i <= bannedWords.length - 1; i++) {

            if (messageContent.contains(bannedWords[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public @NotNull Component render(@NotNull Player player, @NotNull Component component, @NotNull Component component1, @NotNull Audience audience) {

        PlainTextComponentSerializer plainTextComponentSerializer = PlainTextComponentSerializer.plainText();
        String messageFR = plainTextComponentSerializer.serialize(component1);
        FileConfiguration config = this.advMain.getConfig();

        Component componentPlayer = Component.translatable("%nox_uuid%" + player.getUniqueId() + ",false,0,-1,1", "\uD83D\uDC64"); //Setup custom player head
        TextColor colorPlayer = ColorType.TEXT.getColor(); //Set color of text to white (base for if the player doesn't have a team)
        String teamPlayer = "タ"; //Set the tag of the player's team

        if (player.hasPermission("group.spectators")){
            colorPlayer = ColorType.MC_GRAY.getColor(); //Set color of text to red
            teamPlayer = "露"; //Set the tag of the player's team
        } else if (player.hasPermission("group.rouge")) { //If the player is in team red
            colorPlayer = ColorType.MC_RED.getColor(); //Set color of text to red
            teamPlayer = "ラ"; //Set the tag of the player's team

        } else if (player.hasPermission("group.orange")) { //If the player is in team orange
            colorPlayer = ColorType.MC_ORANGE.getColor(); //Set color of text to orange
            teamPlayer = "ャ"; //Set the tag of the player's team

        } else if (player.hasPermission("group.jaune")) { //If the player is in team yellow
            colorPlayer = ColorType.MC_YELLOW.getColor(); //Set color of text to yellow
            teamPlayer = "ギ"; //Set the tag of the player's team

        } else if (player.hasPermission("group.vert")) { //If the player is in team lime / green
            colorPlayer = ColorType.MC_LIME.getColor(); //Set color of text to lime / green
            teamPlayer = "画"; //Set the tag of the player's team

        } else if (player.hasPermission("group.bleu_clair")) { //If the player is in team light blue
            colorPlayer = ColorType.MC_AQUA.getColor(); //Set color of text to light blue
            teamPlayer = "動"; //Set the tag of the player's team

        } else if (player.hasPermission("group.bleu")) { //If the player is in team blue
            colorPlayer = ColorType.MC_BLUE.getColor(); //Set color of text to blue
            teamPlayer = "像"; //Set the tag of the player's team

        } else if (player.hasPermission("group.violet")) { //If the player is in team purple
            colorPlayer = ColorType.MC_PURPLE.getColor(); //Set color of text to purple
            teamPlayer = "の"; //Set the tag of the player's team

        } else if (player.hasPermission("group.rose")) { //If the player is in team pink
            colorPlayer = ColorType.MC_PINK.getColor(); //Set color of text to pink
            teamPlayer = "目"; //Set the tag of the player's team
        }

        if (player.hasPermission("op")){
            teamPlayer += "リ"; //Set the tag of the player's team
        }

        if (!Objects.equals(config.getString("color." + player.getUniqueId()), "TEXT")){
            colorPlayer = ColorType.valueOf(config.getString("color."+player.getUniqueId())).getColor();
        }

        Component message;

        switch (messageFR) {
            case ":skull:" -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

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
            default -> {

                if (advMain.getConfig().getBoolean("showPronouns." + player.getUniqueId())) {

                    message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + config.getString("pronoun_1." + player.getUniqueId()) + '/' + config.getString("pronoun_2." + player.getUniqueId())))
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", ColorType.TEXT.getColor()))
                            .append(Component.text(messageFR));
                } else {
                    message = Component.text(teamPlayer, ColorType.TEXT.getColor())
                            .append(Component.text(' '))
                            .append(componentPlayer)
                            .append(Component.text(' ' + player.getName(), colorPlayer))
                            .append(Component.text(" > ", ColorType.TEXT.getColor()))
                            .append(Component.text(messageFR));
                }
            }
        }
        return message;
    }
}
