package fr.thefox580.theevent5802.games.finder;

import fr.thefox580.theevent5802.utils.Game;
import fr.thefox580.theevent5802.utils.Players;
import fr.thefox580.theevent5802.utils.Spectators;
import fr.thefox580.theevent5802.utils.Variables;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class FinderInstance {

    private static FinderKit kit;

    public void initialise(){
        kit = new FinderKit();
    }

    public static void start(){
        for (Player loopPlayer : Bukkit.getOnlinePlayers()) {
            loopPlayer.teleport(new Location(Bukkit.getWorld("Economics"), 0, 67, 0, 0, 0));
            loopPlayer.getInventory().clear();
            loopPlayer.setGameMode(GameMode.ADVENTURE);

            Team loopPlayerTeam = loopPlayer.getScoreboard().getPlayerTeam(loopPlayer);
            assert loopPlayerTeam != null;
            loopPlayerTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

            if (Players.isPlayer(loopPlayer)) { // If player isn't a spectator
                kit.giveKitToPlayer(loopPlayer);
                Variables.setVariable("economics." + loopPlayer.getUniqueId() + ".nb_items", 0);
            }
        }
        Spectators.readySpectatorsGame();

        Variables.setVariable("jeu_condi", 13);
        Variables.setVariable("jeu", (int) Variables.getVariable("jeu") +1);
        Variables.setVariable("jeu_nom", Game.FINDER.getName());
        Variables.setVariable("jeu_logo", Game.FINDER.getIcon());
        Variables.setVariable("finished_players", 0);
        Variables.setVariable("damage", 2);
    }

    public FinderKit getKit(){
        return kit;
    }
}
