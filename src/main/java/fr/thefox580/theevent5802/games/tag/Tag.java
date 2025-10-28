package fr.thefox580.theevent5802.games.tag;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;

public class Tag {

    private static final World world = Bukkit.getWorld("Tag");
    private static Player tagger;
    private static int switchUntilExplosion = 50;
    private static int timeUntilExplosion = 40;
    private static int timeUntilExplosionBase = 40;
    private static int nbPlayersAlive;
    private static TheEvent580_2 plugin;

    /**
     * Method to call to start the pre-game setup
     * @param plugin The main class of the plugin.
     */
    public static void startPreGame(TheEvent580_2 plugin){
        Timer.setSeconds(90);
        Timer.setMaxSeconds(90);
        Timer.setEnum(Timer.TimerEnum.PRE_GAME);

        Tag.plugin = plugin;

        if (world != null){
            world.getWorldBorder().setSize(91);
        }

        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(new Location(Bukkit.getWorld("Tag"), 7.5, 142, -6.5, 180, 0));
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setGameMode(GameMode.ADVENTURE);
                }
            }.runTaskLater(plugin, 20L);
        });

        nbPlayersAlive = Players.getPlayerCount();

        Variables.setVariable("jeu_condi", Game.TAG.getGameCondition());
        Variables.setVariable("jeu", (int) Variables.getVariable("jeu") +1);
        Variables.setVariable("jeu_nom", Game.TAG.getName());
        Variables.setVariable("jeu_logo", Game.TAG.getIcon());

        Spectators.readySpectatorsGame();

        TagTasks.preGameTask(plugin);

    }

    public static void chooseRandomPlayer(){
        int random = new Random().nextInt(Players.getOnlinePlayerList().size());
        PlayerManager player = Players.getOnlinePlayerList().get(random);
        if (player.isAlive(plugin)){
            setTagger(player.getOnlinePlayer());
            Bukkit.getOnlinePlayers().forEach(loopPlayer -> {
                loopPlayer.showTitle(Title.title(
                        Component.text(player.getTeam().getIcon())
                                .append(Component.text(player.getName(), player.getColorType().getColor())),
                        Component.text("is the hunter!", ColorType.MC_RED.getColor())));
            });
            return;
        }
        chooseRandomPlayer();
    }

    public static double getWorldSize(){

        double size = -1;

        if (world != null){
            size = world.getWorldBorder().getSize()/91*100;
        }

        DecimalFormat df = new DecimalFormat("###.##");

        return Double.parseDouble(df.format(size));

    }

    public static boolean isInBorder(Player player){

        if (world != null) {
            return world.getWorldBorder().isInside(player.getLocation());
        }

        return false;

    }

    public static void setWorldSize(double size){

        if (world != null) {
            world.getWorldBorder().setSize(size, 30L);

            Bukkit.broadcast(Component.text("Warning! Over the next 30 seconds, the world border will shrink to" + new DecimalFormat("###.##").format(size/ 91 * 100) + "%", ColorType.MC_RED.getColor()));
        }
    }

    public static Player getTagger(){
        return tagger;
    }

    public static int getSwitchUntilExplosion() {
        return switchUntilExplosion;
    }

    public static int getTimeUntilExplosion() {
        return timeUntilExplosion;
    }

    public static void setTagger(Player player){
        if (tagger != null){
            tagger.setGlowing(false);
            tagger.removePotionEffect(PotionEffectType.BLINDNESS);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tag nametag show " + tagger.getName() + " -s");
            tagger.sendMessage(Component.text("[")
                    .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                    .append(Component.text("] ", ColorType.TEXT.getColor()))
                    .append(Component.text("You tagged " + player.getName(), ColorType.MC_LIME.getColor())));

            Points.addGamePoints(tagger, Math.round(20*Points.getMultiplier()));
            Points.removeGamePoints(player, Math.round(20*Points.getMultiplier()));

            player.sendMessage(Component.text("[")
                    .append(Component.text(Game.TAG.getName(), Game.TAG.getColorType().getColor()))
                    .append(Component.text("] ", ColorType.TEXT.getColor()))
                    .append(Component.text("You were tagged by " + tagger.getName(), ColorType.MC_RED.getColor())));
            player.showTitle(Title.title(
                    Component.text(""),
                    Component.text("\uD83D\uDCA3 You got tagged \uD83D\uDCA3", ColorType.MC_RED.getColor())));
        }
        player.setGlowing(true);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tag nametag hide " + player.getName() + " -s");
        tagger = player;
        switchUntilExplosion--;
        if (timeUntilExplosion > 10){
            timeUntilExplosion--;
        }

        if (switchUntilExplosion <= 0){
            bombExploded();
        }
    }

    public static void bombExploded(){
        tagger.setGlowing(false);
        tagger.removePotionEffect(PotionEffectType.BLINDNESS);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tag nametag show " + tagger.getName() + " -s");

        for (PlayerManager playerManager : Players.getOnlinePlayerList()){
            if (playerManager.getUniqueId() != tagger.getUniqueId()){
                Points.addGamePoints(Objects.requireNonNull(playerManager.getOnlinePlayer()), Math.round(15*Points.getMultiplier()));
                break;
            }
        }

        if (Objects.requireNonNull(tagger.getAttribute(Attribute.MAX_HEALTH)).getValue() <= 2){
            Spectators.readySpectatorGame(Objects.requireNonNull(Players.getPlayerManager(tagger)));

            nbPlayersAlive--;

            if (nbPlayersAlive == 1){
                Timer.setSeconds(1);
                for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                    if (playerManager.isAlive(plugin)){
                        Points.addGamePoints(Objects.requireNonNull(playerManager.getOnlinePlayer()), Math.round(50*Points.getMultiplier()));
                        break;
                    }
                }
            }
        } else {
            Objects.requireNonNull(tagger.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(Objects.requireNonNull(tagger.getAttribute(Attribute.MAX_HEALTH)).getValue()-2);
        }

        switchUntilExplosion = 50;
        if (timeUntilExplosionBase > 10){
            timeUntilExplosionBase--;
        }
        timeUntilExplosion = timeUntilExplosionBase;

        chooseRandomPlayer();

    }

}
