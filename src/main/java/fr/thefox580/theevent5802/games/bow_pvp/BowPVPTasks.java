package fr.thefox580.theevent5802.games.bow_pvp;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.arms_race.ArmsRace;
import fr.thefox580.theevent5802.tasks.timer.Mode7;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class BowPVPTasks {

    public static void preGameTask(TheEvent580_2 plugin){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (Timer.getSeconds() == 60){
                    Bukkit.getOnlinePlayers().forEach(player -> player.showTitle(Title.title(
                            Component.text(Game.BOW_PVP.getIcon(), ColorType.NO_SHADOW.getColor())
                                    .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor())),
                            Component.text("Game " + Variables.getVariable("jeu") + " /6"),
                            Title.Times.times(
                                    Duration.ofMillis(500),
                                    Duration.ofSeconds(5),
                                    Duration.ofMillis(500)
                            )
                    )));
                } else if (Timer.getSeconds() == 40){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] Welcome to ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(".", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));
                } else if (Timer.getSeconds() == 35){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] Your goal is to kill as many people as possible and be the last one alive within 10 minutes !", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 30){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] You'll spawn with both an infinity bow and a wooden sword!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 25){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] While killing someone with a bow removes one of their heart permanently, killing them with the sword steals their heart!", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 20){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] Be careful, because falling in the void will kill you...", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 15){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] After 5 minutes, the border will", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 10){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] For example, if you are on the 13th weapon and you die, you will lose (5+13)% = 18% of your coins", ColorType.TEXT.getColor())));
                } else if (Timer.getSeconds() == 3){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 3.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 2){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 2.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 1){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting in 1.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, SoundCategory.VOICE, 2, 1));
                } else if (Timer.getSeconds() == 0){
                    Bukkit.broadcast(Component.text("[")
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor()))
                            .append(Component.text("] ", ColorType.TEXT.getColor()))
                            .append(Component.text(Game.BOW_PVP.getName(), Game.BOW_PVP.getColorType().getColor(), TextDecoration.BOLD))
                            .append(Component.text(" is starting now.", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false)));

                    Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.VOICE, 0.25f, 1));


                    ItemStack sword = ItemStack.of(Material.WOODEN_SWORD);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.setUnbreakable(true);
                    swordMeta.lore(List.of(Component.text("Killing a player with this sword will steal one of their heart!", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.ITALIC, false)));
                    sword.setItemMeta(swordMeta);

                    ItemStack bow = ItemStack.of(Material.BOW);
                    ItemMeta bowMeta = bow.getItemMeta();
                    bowMeta.addEnchant(Enchantment.INFINITY, 1, true);
                    bowMeta.setUnbreakable(true);
                    bowMeta.lore(List.of(Component.text("Killing a player with this bow will ", ColorType.SUBTEXT.getColor()).decoration(TextDecoration.ITALIC, false)
                            .append(Component.text("NOT", ColorType.MC_RED.getColor(), TextDecoration.BOLD))
                            .append(Component.text(" steal one of their heart!").decoration(TextDecoration.BOLD, false))));
                    bow.setItemMeta(bowMeta);

                    Players.getOnlinePlayerList().forEach(playerManager -> {
                        Player player = playerManager.getOnlinePlayer();

                        if (player != null && playerManager.isPlayer()){
                            player.getInventory().clear();

                            player.give(bow);
                            player.give(sword);
                            player.getInventory().setItem(8, ItemStack.of(Material.ARROW));

                        }
                    });

                    mainGameTask(plugin);
                    //otherTask(plugin);
                    this.cancel();
                }

            }
        }.runTaskTimer(plugin, 0L, 20L);

    }

    private static void mainGameTask(TheEvent580_2 plugin){
        Timer.setSeconds(Game.BOW_PVP.getGameTime());
        Timer.setMaxSeconds(Game.BOW_PVP.getGameTime());
        Timer.setEnum(Timer.TimerEnum.IN_GAME);

        new BukkitRunnable(){

            @Override
            public void run() {


                if (Timer.getSeconds() == 0){

                    if (Variables.equals("jeu", 6)){
                        //new Mode10(plugin);
                    } else {
                        new Mode7(plugin);
                    }

                    for (PlayerManager playerManager : Players.getOnlinePlayerList()){
                        Player player = playerManager.getOnlinePlayer();

                        if (player != null){

                        }
                    }

                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
