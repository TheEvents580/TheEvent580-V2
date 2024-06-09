package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;
import java.util.UUID;

public class onGUIClick implements Listener {

    private final TheEvent580_2 plugin;

    public onGUIClick(TheEvent580_2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void checkGUIClickEvent(InventoryClickEvent event) {

        FileConfiguration config = plugin.getConfig();

        Player player = (Player) event.getWhoClicked();
        UUID playerUUID = player.getUniqueId();

        //if (event.getView().getTitle().equals("Are you sure to start ?")) {
        //    event.setCancelled(true);
        //    if (event.getCurrentItem().getType() == Material.LIME_CONCRETE || event.getCurrentItem().getType() == Material.RED_CONCRETE) {
        //
        //        if (event.getCurrentItem().getType() == Material.LIME_CONCRETE) {
        //            player.performCommand("timer mode 1");
        //            player.performCommand("timer set 1 30");
        //            config.set("timer_mode", !config.getBoolean("timer_mode"));
        //            player.performCommand("startskript");
        //
        //            plugin.adventure().players().sendMessage(Component.text("[")
        //                    .append(Component.text("TheEvent580", TextColor.color(255, 85, 85), TextDecoration.BOLD))
        //                    .append(Component.text("] Event starting in 1 minute and 30 seconds", TextColor.color(255, 255, 255))));
        //
        //            for (Player loopPlayer : Bukkit.getOnlinePlayers()) {
        //                loopPlayer.playSound(loopPlayer, "custom:intro", SoundCategory.VOICE, 1, 1);
        //            }
        //        }
        //
        //        player.closeInventory();
        //    }
        //}


        if (event.getInventory().getType() == InventoryType.WORKBENCH) {
            if (player.getWorld().getName().equals("world")) {
                if (event.getSlot() == 0){
                    config.set("minecraftle_game_tries." + player.getUniqueId(), config.getInt("minecraftle_game_tries." + player.getUniqueId())+1);
                    if (Objects.equals(event.getCurrentItem().getType().getItemTranslationKey(), config.getString("minecraftle_game." + player.getUniqueId()))){
                        player.closeInventory();
                        player.getInventory().clear();
                        if(config.getInt("minecraftle_game_tries." + player.getUniqueId()) == 1){
                            if(player.getWorld().getName().equals("world")){
                                Bukkit.broadcast(Component.text(player.getName()+" found ")
                                        .append(Component.translatable(config.getString("minecraftle_game." + player.getUniqueId()), TextColor.color(188, 215, 29), TextDecoration.BOLD))
                                        .append(Component.text(" in ", TextColor.color(255, 255, 255)))
                                        .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), TextColor.color(188, 215, 29), TextDecoration.BOLD))
                                        .append(Component.text(" try.", TextColor.color(255, 255, 255))));
                            } else {
                                player.sendMessage(Component.text(player.getName()+" found ")
                                        .append(Component.translatable(config.getString("minecraftle_game." + player.getUniqueId()), TextColor.color(188, 215, 29), TextDecoration.BOLD))
                                        .append(Component.text(" in ", TextColor.color(255, 255, 255)))
                                        .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), TextColor.color(188, 215, 29), TextDecoration.BOLD))
                                        .append(Component.text(" try.", TextColor.color(255, 255, 255)))
                                        .append(Component.text("Sadly, your message hasn't been broadcasted to the whole server as some player are still playing.")));
                            }
                        } else {
                            if(player.getWorld().getName().equals("world")){
                                Bukkit.broadcast(Component.text(player.getName()+" found ")
                                        .append(Component.translatable(config.getString("minecraftle_game." + player.getUniqueId()), TextColor.color(188, 215, 29), TextDecoration.BOLD))
                                        .append(Component.text(" in ", TextColor.color(255, 255, 255)))
                                        .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), TextColor.color(188, 215, 29), TextDecoration.BOLD))
                                        .append(Component.text(" tries.", TextColor.color(255, 255, 255))));
                            } else {
                                player.sendMessage(Component.text(player.getName()+" found ")
                                        .append(Component.translatable(config.getString("minecraftle_game." + player.getUniqueId()), TextColor.color(188, 215, 29), TextDecoration.BOLD))
                                        .append(Component.text(" in ", TextColor.color(255, 255, 255)))
                                        .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), TextColor.color(188, 215, 29), TextDecoration.BOLD))
                                        .append(Component.text(" tries.", TextColor.color(255, 255, 255)))
                                        .append(Component.text("Sadly, your message hasn't been broadcasted to the whole server as some player are still playing.")));
                            }
                        }
                    } else {
                        player.sendMessage(Component.text("Nope, wrong item. Try again", TextColor.color(255, 85, 85)));
                    }
                }
            }
        }
        if (event.getView().title().equals(Component.text("Set your pronouns"))) {
            event.setCancelled(true);
            String pronoun1 = config.getString("pronoun_1." + playerUUID);
            String pronoun2 = config.getString("pronoun_2." + playerUUID);
            if (event.getCurrentItem().getType() == Material.RED_CONCRETE){
                pronoun1 = "He";
                config.set("pronoun_1." + playerUUID, '[' + pronoun1);
            }
            if (event.getCurrentItem().getType() == Material.ORANGE_CONCRETE){
                pronoun1 = "She";
                config.set("pronoun_1." + playerUUID, '[' + pronoun1);
            }
            if (event.getCurrentItem().getType() == Material.YELLOW_CONCRETE){
                pronoun1 = "They";
                config.set("pronoun_1." + playerUUID, '[' + pronoun1);
            }
            if (event.getCurrentItem().getType() == Material.LIME_CONCRETE){
                pronoun1 = "Any";
                config.set("pronoun_1." + playerUUID, '[' + pronoun1);
            }
            if (event.getCurrentItem().getType() == Material.GREEN_CONCRETE){
                pronoun1 = "It";
                config.set("pronoun_1." + playerUUID, '[' + pronoun1);
            }
            if (event.getCurrentItem().getType() == Material.CYAN_CONCRETE){
                pronoun1 = "Ask";
                config.set("pronoun_1." + playerUUID, '[' + pronoun1);
            }
            if (event.getCurrentItem().getType() == Material.LIGHT_BLUE_CONCRETE){
                pronoun2 = "Him";
                config.set("pronoun_2." + playerUUID, pronoun2 + ']');
            }
            if (event.getCurrentItem().getType() == Material.BLUE_CONCRETE){
                pronoun2 = "Her";
                config.set("pronoun_2." + playerUUID, pronoun2 + ']');
            }
            if (event.getCurrentItem().getType() == Material.PURPLE_CONCRETE){
                pronoun2 = "Them";
                config.set("pronoun_2." + playerUUID, pronoun2 + ']');
            }
            if (event.getCurrentItem().getType() == Material.MAGENTA_CONCRETE){
                pronoun2 = "All";
                config.set("pronoun_2." + playerUUID, pronoun2 + ']');
            }
            if (event.getCurrentItem().getType() == Material.PINK_CONCRETE){
                pronoun2 = "Its";
                config.set("pronoun_2." + playerUUID, pronoun2 + ']');
            }
            if (event.getCurrentItem().getType() == Material.BROWN_CONCRETE){
                pronoun2 = "Me";
                config.set("pronoun_2." + playerUUID, pronoun2 + ']');
            }
            if (event.getCurrentItem().getType() == Material.BARRIER){
                player.closeInventory();
                player.sendMessage("You 1st pronoun is now set to : " + pronoun1);
                player.sendMessage("Your 2nd pronoun is now set to : " + pronoun2);
            }
        }
    }
}
