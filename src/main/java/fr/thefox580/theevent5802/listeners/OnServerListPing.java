package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Players;
import fr.thefox580.theevent5802.utils.Spectators;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class OnServerListPing implements Listener {

    public OnServerListPing(TheEvent580_2 plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent event) throws ParseException {

        event.setMaxPlayers(Players.getMaxPlayerCount()+ Spectators.getMaxSpectatorCount());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        Date d1 = Date.from(Instant.now());
        Date d2 = sdf.parse("31-12-2029 23:59:59");

        long differenceInTime = d2.getTime() - d1.getTime();

        long differenceInSeconds = (differenceInTime / 1000) % 60;

        long differenceInMinutes = (differenceInTime / (1000 * 60)) % 60;

        long differenceInHours = (differenceInTime / (1000 * 60 * 60)) % 24;

        long differenceInYears = (differenceInTime / (1000L * 60 * 60 * 24 * 365));

        long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;

        event.motd(Component.text("Working on Episode 7... We'll be back in ", ColorType.SPECIAL_1.getColor())
                .append(Component.text(differenceInYears + " years, " + differenceInDays + " days, " + differenceInHours + " hours, " + differenceInMinutes + " minutes and " + differenceInSeconds + " seconds", ColorType.SUBTEXT.getColor())));

    }

}
