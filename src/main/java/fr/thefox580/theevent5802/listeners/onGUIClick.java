package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.commands.minecraftle;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Colors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class onGUIClick implements Listener {

    private final TheEvent580_2 plugin;

    public onGUIClick(TheEvent580_2 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void checkGUIClickEvent(InventoryClickEvent event) {

        FileConfiguration config = plugin.getConfig();

        Player player = (Player) event.getWhoClicked();
        UUID playerUUID = player.getUniqueId();

        if (event.getClickedInventory() == null){
            Bukkit.getLogger().warning("Player " + player.getName() + "clicked outside of an inventory");
        }   else {

            if (event.getClickedInventory().getType() != null) {
                if (event.getClickedInventory().getType() == InventoryType.WORKBENCH) {
                    if (player.getAllowFlight()){
                        if (event.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE || event.getCurrentItem().getType() == Material.YELLOW_STAINED_GLASS_PANE) {
                            event.setCancelled(true);
                            event.getClickedInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                        }
                        if (event.getSlot() == 0) {
                            ItemStack item = event.getClickedInventory().getItem(event.getSlot());
                            ItemStack solution = new ItemStack(Material.ACTIVATOR_RAIL);
                            for (Material material : minecraftle.getPossibleItems()){
                                if (Objects.equals(material.getItemTranslationKey(), config.getString("minecraftle_game." + player.getUniqueId()))){
                                    solution = new ItemStack(material);
                                    break;
                                }
                            }
                            if (item.getType() != Material.AIR) {
                                config.set("minecraftle_game_tries." + player.getUniqueId(), config.getInt("minecraftle_game_tries." + player.getUniqueId()) + 1);
                                if (Objects.equals(event.getCurrentItem().getType().getItemTranslationKey(), config.getString("minecraftle_game." + player.getUniqueId()))) {
                                    player.closeInventory();
                                    if (config.getInt("minecraftle_game_tries." + player.getUniqueId()) == 1) {
                                        if (player.getWorld().getName().equals("world")) {
                                            Bukkit.broadcast(Component.text("[")
                                                    .append(Component.text("Minecraftle", Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text("] " + player.getName() + " found "))
                                                    .append(Component.translatable(config.getString("minecraftle_game." + player.getUniqueId()), Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text(" in ", Colors.getColor(ColorType.TEXT)))
                                                    .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text(" try.", Colors.getColor(ColorType.TEXT))));
                                        } else {
                                            player.sendMessage(Component.text("[")
                                                    .append(Component.text("Minecraftle", Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text("] " + player.getName() + " found "))
                                                    .append(Component.translatable(config.getString("minecraftle_game." + player.getUniqueId()), Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text(" in ", Colors.getColor(ColorType.TEXT)))
                                                    .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text(" try.", Colors.getColor(ColorType.TEXT)))
                                                    .append(Component.text("Sadly, your message hasn't been broadcasted to the whole server as some player are still playing.")));
                                        }
                                    } else {
                                        if (player.getWorld().getName().equals("world")) {
                                            Bukkit.broadcast(Component.text("[")
                                                    .append(Component.text("Minecraftle", Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text("] " + player.getName() + " found "))
                                                    .append(Component.translatable(config.getString("minecraftle_game." + player.getUniqueId()), Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text(" in ", Colors.getColor(ColorType.TEXT)))
                                                    .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text(" try.", Colors.getColor(ColorType.TEXT))));
                                        } else {
                                            player.sendMessage(Component.text("[")
                                                    .append(Component.text("Minecraftle", Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text("] " + player.getName() + " found "))
                                                    .append(Component.translatable(config.getString("minecraftle_game." + player.getUniqueId()), Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text(" in ", Colors.getColor(ColorType.TEXT)))
                                                    .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), Colors.getColor(ColorType.SPECIAL_1), TextDecoration.BOLD))
                                                    .append(Component.text(" try.", Colors.getColor(ColorType.TEXT)))
                                                    .append(Component.text("Sadly, your message hasn't been broadcasted to the whole server as some player are still playing.")));
                                        }
                                    }
                                } else {
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, SoundCategory.VOICE, 1f, 1f);
                                    player.sendMessage(Component.text("Nope, wrong item. Try again", TextColor.color(255, 85, 85)));

                                    for (Recipe recipe1 : Bukkit.getServer().getRecipesFor(item)){
                                        if (recipe1 instanceof ShapelessRecipe){
                                            break;
                                        }
                                    }
                                    Map<Material, ArrayList<ArrayList<Material>>> shapedRecipesList = minecraftle.getShapedRecipes();
                                    ArrayList<Material> recipe = shapedRecipesList.get(solution.getType()).get(0);
                                    for (int i = 1; i < 10; i++) { // If item is at the right place
                                        Material itemMaterial = Material.AIR;
                                        if (event.getClickedInventory().getItem(i) != null){
                                            itemMaterial = event.getClickedInventory().getItem(i).getType();
                                        }
                                        if (itemMaterial == recipe.get(i-1)){
                                            if (itemMaterial != Material.AIR){
                                                player.give(new ItemStack(itemMaterial));
                                            }
                                            event.getClickedInventory().setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
                                        }
                                    }
                                    for (int i = 1; i < 10; i++) { // If item is in the crafting recipe
                                        Material itemMaterial = Material.AIR;
                                        if (event.getClickedInventory().getItem(i) != null){
                                            itemMaterial = event.getClickedInventory().getItem(i).getType();
                                        }
                                        if (recipe.contains(itemMaterial)){
                                            if (itemMaterial != Material.AIR){
                                                player.give(new ItemStack(itemMaterial));
                                            }
                                            event.getClickedInventory().setItem(i, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                        }
                                    }
                                    for (int i = 1; i < 10; i++) { // Else
                                        Material itemMaterial = Material.AIR;
                                        if (event.getClickedInventory().getItem(i) != null){
                                            itemMaterial = event.getClickedInventory().getItem(i).getType();
                                        }
                                        if (itemMaterial != Material.AIR && itemMaterial != Material.LIME_STAINED_GLASS_PANE && itemMaterial != Material.YELLOW_STAINED_GLASS_PANE){
                                            player.give(new ItemStack(itemMaterial));
                                            event.getClickedInventory().setItem(i, new ItemStack(Material.AIR));
                                        }
                                    }
                                }
                            }
                            event.setCancelled(true);
                        }
                    }
                }
            } if (event.getView().title().equals(Component.text("Set your pronouns"))) {
                event.setCancelled(true);
                String pronoun1 = config.getString("pronoun_1." + playerUUID);
                String pronoun2 = config.getString("pronoun_2." + playerUUID);
                if (event.getCurrentItem().getType() == Material.RED_CONCRETE) {
                    pronoun1 = "He";
                    config.set("pronoun_1." + playerUUID, '[' + pronoun1);
                }
                if (event.getCurrentItem().getType() == Material.ORANGE_CONCRETE) {
                    pronoun1 = "She";
                    config.set("pronoun_1." + playerUUID, '[' + pronoun1);
                }
                if (event.getCurrentItem().getType() == Material.YELLOW_CONCRETE) {
                    pronoun1 = "They";
                    config.set("pronoun_1." + playerUUID, '[' + pronoun1);
                }
                if (event.getCurrentItem().getType() == Material.LIME_CONCRETE) {
                    pronoun1 = "Any";
                    config.set("pronoun_1." + playerUUID, '[' + pronoun1);
                }
                if (event.getCurrentItem().getType() == Material.GREEN_CONCRETE) {
                    pronoun1 = "It";
                    config.set("pronoun_1." + playerUUID, '[' + pronoun1);
                }
                if (event.getCurrentItem().getType() == Material.CYAN_CONCRETE) {
                    pronoun1 = "Ask";
                    config.set("pronoun_1." + playerUUID, '[' + pronoun1);
                }
                if (event.getCurrentItem().getType() == Material.LIGHT_BLUE_CONCRETE) {
                    pronoun2 = "Him";
                    config.set("pronoun_2." + playerUUID, pronoun2 + ']');
                }
                if (event.getCurrentItem().getType() == Material.BLUE_CONCRETE) {
                    pronoun2 = "Her";
                    config.set("pronoun_2." + playerUUID, pronoun2 + ']');
                }
                if (event.getCurrentItem().getType() == Material.PURPLE_CONCRETE) {
                    pronoun2 = "Them";
                    config.set("pronoun_2." + playerUUID, pronoun2 + ']');
                }
                if (event.getCurrentItem().getType() == Material.MAGENTA_CONCRETE) {
                    pronoun2 = "All";
                    config.set("pronoun_2." + playerUUID, pronoun2 + ']');
                }
                if (event.getCurrentItem().getType() == Material.PINK_CONCRETE) {
                    pronoun2 = "Its";
                    config.set("pronoun_2." + playerUUID, pronoun2 + ']');
                }
                if (event.getCurrentItem().getType() == Material.BROWN_CONCRETE) {
                    pronoun2 = "Me";
                    config.set("pronoun_2." + playerUUID, pronoun2 + ']');
                }
                if (event.getCurrentItem().getType() == Material.BARRIER) {
                    player.closeInventory();
                    player.sendMessage("You 1st pronoun is now set to : " + pronoun1);
                    player.sendMessage("Your 2nd pronoun is now set to : " + pronoun2);
                }
            }
        }
    }
}

