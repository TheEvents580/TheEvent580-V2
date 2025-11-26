package fr.thefox580.theevent5802.listeners;

import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.commands.Minecraftle;
import fr.thefox580.theevent5802.games.build_masters.BuildMasters;
import fr.thefox580.theevent5802.games.finder.FinderSets;
import fr.thefox580.theevent5802.tasks.timer.Mode1;
import fr.thefox580.theevent5802.utils.*;
import me.clip.placeholderapi.libs.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class OnInventoryInteract implements Listener {

    private final TheEvent580_2 plugin;

    public OnInventoryInteract(TheEvent580_2 plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void checkGUIClickEvent(InventoryClickEvent event) {

        FileConfiguration config = plugin.getConfig();

        Player player = (Player) event.getWhoClicked();
        UUID playerUUID = player.getUniqueId();

        if (event.getClickedInventory() == null){
            plugin.getLogger().warning("Player " + player.getName() + " clicked outside of an inventory");
        } else {

            if (event.getClickedInventory().getType() == InventoryType.WORKBENCH) {
                if (event.getView().title().equals(Component.text("Minecraftle"))){
                    if (player.getAllowFlight()) {
                        if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.LIME_STAINED_GLASS_PANE || event.getCurrentItem().getType() == Material.YELLOW_STAINED_GLASS_PANE) {
                            event.setCancelled(true);
                            event.getClickedInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                        }
                        if (event.getSlot() == 0) {
                            ItemStack item = event.getClickedInventory().getItem(event.getSlot());
                            ItemStack solution = new ItemStack(Material.ACTIVATOR_RAIL);
                            for (Material material : Minecraftle.getPossibleItems()) {
                                if (Objects.equals(material.getItemTranslationKey(), config.getString("minecraftle_game." + player.getUniqueId()))) {
                                    solution = new ItemStack(material);
                                    break;
                                }
                            }
                            assert item != null;
                            if (item.getType() != Material.AIR) {
                                config.set("minecraftle_game_tries." + player.getUniqueId(), Optional.of(config.getInt("minecraftle_game_tries." + player.getUniqueId()) + 1));
                                if (Objects.equals(event.getCurrentItem().getType().getItemTranslationKey(), config.getString("minecraftle_game." + player.getUniqueId()))) {
                                    player.closeInventory();
                                    if (player.getWorld().getName().equals("world")) {
                                        Bukkit.broadcast(Component.text("[")
                                                .append(Component.text("Minecraftle", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                                .append(Component.text("] " + player.getName() + " found "))
                                                .append(Component.translatable(Objects.requireNonNull(config.getString("minecraftle_game." + player.getUniqueId())), ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                                .append(Component.text(" in ", ColorType.TEXT.getColor()))
                                                .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                                .append(Component.text(" try.", ColorType.TEXT.getColor())));
                                    } else {
                                        player.sendMessage(Component.text("[")
                                                .append(Component.text("Minecraftle", ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                                .append(Component.text("] " + player.getName() + " found "))
                                                .append(Component.translatable(Objects.requireNonNull(config.getString("minecraftle_game." + player.getUniqueId())), ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                                .append(Component.text(" in ", ColorType.TEXT.getColor()))
                                                .append(Component.text(config.getInt("minecraftle_game_tries." + player.getUniqueId()), ColorType.SPECIAL_1.getColor(), TextDecoration.BOLD))
                                                .append(Component.text(" try.", ColorType.TEXT.getColor()))
                                                .append(Component.text("Sadly, your message hasn't been broadcasted to the whole server as some player are still playing.")));
                                    }
                                } else {
                                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS, SoundCategory.VOICE, 1f, 1f);
                                    player.sendMessage(Component.text("Nope, wrong item. Try again", TextColor.color(255, 85, 85)));

                                    for (Recipe recipe1 : Bukkit.getServer().getRecipesFor(item)) {
                                        if (recipe1 instanceof ShapelessRecipe) {
                                            break;
                                        }
                                    }
                                    Map<Material, ArrayList<ArrayList<Material>>> shapedRecipesList = Minecraftle.getShapedRecipes();
                                    ArrayList<Material> recipe = shapedRecipesList.get(solution.getType()).getFirst();
                                    for (int i = 1; i < 10; i++) { // If item is at the right place
                                        Material itemMaterial = Material.AIR;
                                        if (event.getClickedInventory().getItem(i) != null) {
                                            itemMaterial = Objects.requireNonNull(event.getClickedInventory().getItem(i)).getType();
                                        }
                                        if (itemMaterial == recipe.get(i - 1)) {
                                            if (itemMaterial != Material.AIR) {
                                                player.give(new ItemStack(itemMaterial));
                                            }
                                            event.getClickedInventory().setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
                                        }
                                    }
                                    for (int i = 1; i < 10; i++) { // If item is in the crafting recipe
                                        Material itemMaterial = Material.AIR;
                                        if (event.getClickedInventory().getItem(i) != null) {
                                            itemMaterial = Objects.requireNonNull(event.getClickedInventory().getItem(i)).getType();
                                        }
                                        if (recipe.contains(itemMaterial)) {
                                            if (itemMaterial != Material.AIR) {
                                                player.give(new ItemStack(itemMaterial));
                                            }
                                            event.getClickedInventory().setItem(i, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
                                        }
                                    }
                                    for (int i = 1; i < 10; i++) { // Else
                                        Material itemMaterial = Material.AIR;
                                        if (event.getClickedInventory().getItem(i) != null) {
                                            itemMaterial = Objects.requireNonNull(event.getClickedInventory().getItem(i)).getType();
                                        }
                                        if (itemMaterial != Material.AIR && itemMaterial != Material.LIME_STAINED_GLASS_PANE && itemMaterial != Material.YELLOW_STAINED_GLASS_PANE) {
                                            player.give(new ItemStack(itemMaterial));
                                            event.getClickedInventory().setItem(i, new ItemStack(Material.AIR));
                                        }
                                    }
                                }
                            }
                            event.setCancelled(true);
                        }
                    }
                } else {
                    if (player.getAllowFlight()){
                        event.setCancelled(true);
                    }
                }
            }
            if (event.getView().title().equals(Component.text("Set your pronouns"))) {
                event.setCancelled(true);
                String pronoun1 = config.getString("pronoun_1." + playerUUID);
                String pronoun2 = config.getString("pronoun_2." + playerUUID);
                if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.RED_CONCRETE) {
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
            else if (event.getView().title().equals(Component.text("Do you want to start the event ?", ColorType.MC_ORANGE.getColor()))){
                event.setCancelled(true);
                if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.LIME_CONCRETE){
                    BossBar countBossbar = BossbarManager.getBossbar("count");
                    assert countBossbar != null;
                    BossbarManager.setBossbarVisibility(countBossbar, true);

                    new Mode1(plugin);

                    Voting.addAllGames();


                    BaseAdvancement adv = plugin.getInstances().getAdvancementAPI().createAdvancement(
                            "start",
                            Material.LIME_CONCRETE,
                            "Starting soon!",
                            AdvancementFrameType.TASK,
                            false,
                            "TheEvent580 is starting",
                            "in 3 minutes");

                    for (Player loopPlayer : Bukkit.getOnlinePlayers()){
                        adv.grant(loopPlayer);
                    }

                    player.closeInventory();
                } else if (event.getCurrentItem().getType() == Material.RED_CONCRETE){
                    player.closeInventory();
                }
            }
            else if (event.getView().title().equals(Component.text(FinderSets.getCurrentItemSetName() + "'s Items"))){
                event.setCancelled(true);
            }
            else if (event.getView().title().equals(Component.text("Spectator TP Menu"))){
                event.setCancelled(true);
                if (event.getCurrentItem() != null){
                    if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.PLAYER_HEAD){
                        player.closeInventory(InventoryCloseEvent.Reason.TELEPORT);
                        SkullMeta playerHeadMeta = (SkullMeta) event.getCurrentItem().getItemMeta();
                        Player playerToTpTo = Bukkit.getPlayer(Objects.requireNonNull(Objects.requireNonNull(playerHeadMeta.getPlayerProfile()).getId()));
                        if (playerToTpTo != null){
                            player.teleport(playerToTpTo);
                        } else {
                            player.sendMessage(Component.text("This player does not exist", ColorType.MC_RED.getColor()));
                        }
                    } else if (event.getCurrentItem().getType() == Material.LIME_CARPET){
                        Bukkit.dispatchCommand(player, "specmenu 2");
                    } else if (event.getCurrentItem().getType() == Material.ORANGE_CARPET){
                        Bukkit.dispatchCommand(player, "specmenu");
                    }
                }
            } else if (PlainTextComponentSerializer.plainText().serialize(event.getView().title()).contains("Give ")){
                event.setCancelled(true);
                if (event.getCurrentItem() != null){
                    String timeString = PlainTextComponentSerializer.plainText().serialize(event.getView().title()).substring(5, 7).replaceAll(" ", "");
                    int time = Integer.parseInt(timeString);
                    if (time < 30){
                        time *= 60;
                    }
                    if (event.getCurrentItem().getType() == Material.LIME_CARPET){
                        BuildMasters.openGiveTimeMenu(player, time, 2);
                    } else if (event.getCurrentItem().getType() == Material.ORANGE_CARPET){
                        BuildMasters.openGiveTimeMenu(player, time, 1);
                    } else if (event.getCurrentItem().getType() == Material.PLAYER_HEAD){

                        PlayerManager senderManager = Online.getPlayerManager(player);
                        PlayerTimer senderTimer = senderManager.getTimer();

                        if (senderTimer.getSeconds() > time*3){

                            SkullMeta receiverMeta = (SkullMeta) event.getCurrentItem().getItemMeta();

                            OfflinePlayer receiver = Bukkit.getOfflinePlayer(Objects.requireNonNull(Objects.requireNonNull(receiverMeta.getPlayerProfile()).getId()));

                            if (receiver.isOnline()){

                                PlayerManager receiverManager = Online.getPlayerManager(receiver.getPlayer());
                                PlayerTimer receiverTimer = receiverManager.getTimer();

                                for (int toRemove = time; toRemove > 0; toRemove--){
                                    senderTimer.remove1Second();
                                    receiverTimer.add1Second();
                                }

                                player.sendMessage(Component.text("Sucessfully gave " + PlainTextComponentSerializer.plainText().serialize(event.getView().title()).substring(5) + " to", ColorType.MC_LIME.getColor())
                                        .append(receiverManager.playerComponent())
                                        .append(Component.text("!", ColorType.MC_LIME.getColor())));

                                Objects.requireNonNull(receiverManager.getOnlinePlayer()).sendMessage(Component.text("You received " + PlainTextComponentSerializer.plainText().serialize(event.getView().title()).substring(5) + " from", ColorType.MC_LIME.getColor())
                                        .append(senderManager.playerComponent())
                                        .append(Component.text("!", ColorType.MC_LIME.getColor())));
                            } else {
                                player.sendMessage(Component.text("There was an error retrieving this player, if this error persists, contact TheFox580.", ColorType.MC_RED.getColor()));
                            }

                            player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);

                        } else {
                            player.sendMessage(Component.text("You didn't have enough time to send it to other players (You need at least " + Conversions.convertSecondsToTimeOptimized(time*3) + ")", ColorType.MC_RED.getColor()));
                        }



                    }
                }
            } else if (event.getClickedInventory().getType() == player.getInventory().getType() && Variables.equals("jeu_condi", Game.HUB.getGameCondition())) {
                if (event.getSlotType() == InventoryType.SlotType.ARMOR){
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        HumanEntity player = event.getPlayer();
        if (event.getInventory().getType() == InventoryType.WORKBENCH){
            if (player.getWorld().getName().equals("world")){
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    ItemStack helmet = player.getInventory().getHelmet();
                    player.getInventory().clear();
                    player.getInventory().setHelmet(helmet);
                }, 3L);
            }
        }
    }
}
