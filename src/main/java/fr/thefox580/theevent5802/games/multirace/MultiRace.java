package fr.thefox580.theevent5802.games.multirace;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;

public class MultiRace {

    private static final World world = Bukkit.getWorld("MultiRace");
    private static Map<Player, Map<Integer, Float>> boatStats = new HashMap<>();
    private static Map<Player, Map<Integer, Float>> tridentStats = new HashMap<>();
    private static Map<Player, Map<Integer, Float>> elytraStats = new HashMap<>();
    private static Map<Player, Integer> bestCatForPlayer = new HashMap<>();

    public static void startPreGame(TheEvent580_2 plugin){
        Timer.setSeconds(120);
        Timer.setMaxSeconds(120);
        Timer.setEnum(Timer.TimerEnum.PRE_GAME);

        Bukkit.getOnlinePlayers().forEach(player -> {
            //player.teleport(new Location(Bukkit.getWorld("MultiRace"), -67, 64, -53));

            if (Players.isPlayer(player)) { // If player isn't a spectator

                Map<Integer, Float> playerBoatStats = new HashMap<>();
                for (int i = 1; i <= 10; i++){
                    playerBoatStats.put(i, -1f);
                }
                boatStats.put(player, playerBoatStats);

                Map<Integer, Float> playerTridentStats = new HashMap<>();
                for (int i = 1; i <= 10; i++){
                    playerTridentStats.put(i, -1f);
                }
                tridentStats.put(player, playerTridentStats);

                Map<Integer, Float> playerElytraStats = new HashMap<>();
                for (int i = 1; i <= 10; i++){
                    playerElytraStats.put(i, -1f);
                }
                elytraStats.put(player, playerElytraStats);

                bestCatForPlayer.put(player, 0);

                Team loopPlayerTeam = player.getScoreboard().getPlayerTeam(player);
                assert loopPlayerTeam != null;
                loopPlayerTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setGameMode(GameMode.ADVENTURE);
                }
            }.runTaskLater(plugin, 20L);
        });

        Variables.setVariable("jeu_condi", Game.MULTIRACE.getGameCondition());
        Variables.setVariable("jeu", (int) Variables.getVariable("jeu") +1);
        Variables.setVariable("jeu_nom", Game.MULTIRACE.getName());
        Variables.setVariable("jeu_logo", Game.MULTIRACE.getIcon());

        Spectators.readySpectatorsGame();

        //MultiRaceTasks.preGameTask(plugin);
    }

    public static void clearStats(){
        for (Map<Integer, Float> map : boatStats.values()){
            map.clear();
        }
        boatStats.clear();

        for (Map<Integer, Float> map : tridentStats.values()){
            map.clear();
        }
        tridentStats.clear();

        for (Map<Integer, Float> map : elytraStats.values()){
            map.clear();
        }
        elytraStats.clear();

        bestCatForPlayer.clear();
    }

}
