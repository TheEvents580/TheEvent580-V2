package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.build_masters.BuildMasters;
import fr.thefox580.theevent5802.games.finder.Finder;
import fr.thefox580.theevent5802.games.finder.FinderSets;
import fr.thefox580.theevent5802.games.parkour.Parkour;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class OnWorldInteract implements Listener {

    private final TheEvent580_2 plugin;

    public OnWorldInteract(TheEvent580_2 plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if (Players.isPlayer(player)){
            if (Timer.isPaused()){
                event.setCancelled(true);
            }
            else if (event.getBlock().getType() == Material.LIME_STAINED_GLASS_PANE || event.getBlock().getType() == Material.YELLOW_STAINED_GLASS_PANE){
                if (player.getWorld().getName().equals("world") || player.getAllowFlight()){
                    if (player.getGameMode() != GameMode.CREATIVE){
                        event.setCancelled(true);
                    }
                }
            } else if (Variables.equals("jeu_condi", 9)){
                if (!player.getAllowFlight()){
                    List<Material> unbreakableBlocks = BuildMasters.getUnbreakableBlocks();
                    if (unbreakableBlocks.contains(event.getBlock().getType())){
                        event.setCancelled(true);
                    } else if (player.getZ() < 3000 && player.getY() != 128){
                        event.setCancelled(true);
                        player.sendMessage(Component.text("Please place the block in the replication zone only", ColorType.MC_RED.getColor()));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if (Players.isPlayer(event.getPlayer())){
            if (Timer.isPaused()){
                event.setCancelled(true);
            } else if (!Variables.equals("jeu_condi", 9)){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerClickEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (event.getMaterial() == Material.FEATHER) {
            assert event.getItem() != null;
            ItemStack feather = event.getItem();
            if (Objects.equals(feather.getItemMeta().customName(), Component.text("Change your fly speed", ColorType.SUBTEXT.getColor())
                    .decoration(TextDecoration.BOLD, false)
                    .decoration(TextDecoration.ITALIC, false))){
                if (player.getAllowFlight()){

                    float before = player.getFlySpeed();
                    int percentageBefore = Math.round(player.getFlySpeed()*1000)-Math.round((player.getFlySpeed()-0.1f)*500);

                    if (event.getAction() == Action.LEFT_CLICK_AIR && player.getFlySpeed() < 0.5f){
                        player.setFlySpeed(player.getFlySpeed()+0.1f);
                    } else if (event.getAction() == Action.RIGHT_CLICK_AIR && player.getFlySpeed() >= 0.2f){
                        player.setFlySpeed(player.getFlySpeed()-0.1f);
                    }
                    int percentageAfter = Math.round(player.getFlySpeed()*1000)-Math.round((player.getFlySpeed()-0.1f)*500);

                    ItemMeta featherMeta = feather.getItemMeta();
                    featherMeta.lore(List.of(Component.text("Right-click to reduce fly speed by 50%", ColorType.SPECIAL_2.getColor()).decoration(TextDecoration.ITALIC, false),
                            Component.text("Left-click to increase fly speed by 50%", ColorType.SPECIAL_2.getColor()).decoration(TextDecoration.ITALIC, false),
                            Component.text("Current fly speed : " + percentageAfter + "%", ColorType.SPECIAL_2.getColor()).decoration(TextDecoration.ITALIC, false)));
                    feather.setItemMeta(featherMeta);
                    player.getInventory().setItemInMainHand(feather);

                    if (before != player.getFlySpeed()){
                        Component message = getFlySpeedMessage(percentageAfter, percentageBefore);

                        player.sendMessage(message);
                    } else {

                        if (percentageAfter == 100){
                            player.sendMessage(Component.text("You reached the minimum speed limit!", ColorType.MC_RED.getColor()));
                        } else {
                            player.sendMessage(Component.text("You reached the maximum speed limit!", ColorType.MC_RED.getColor()));
                        }
                    }
                } else {
                    player.sendMessage(Component.text("You cannot change your fly speed!", ColorType.MC_RED.getColor()));
                }
            }
        } else if (event.getMaterial() == Material.MINECART){
            assert event.getItem() != null;
            if (Timer.getEnum() == Timer.TimerEnum.VOTING){
                if (player.getTargetEntity(5) instanceof Player grabbed){
                    player.getInventory().remove(Material.MINECART);
                    player.addPassenger(player);

                    PlayerManager playerManager = Online.getPlayerManager(player);

                    PlayerManager grabbedManager = Online.getPlayerManager(grabbed);

                    player.sendMessage(Component.text("You grabbed " + grabbedManager.getTeam().getIcon())
                            .append(Component.text(grabbed.getName(), grabbedManager.getColorType().getColor())));

                    grabbed.sendMessage(Component.text("You've been grabbed by " + playerManager.getTeam().getIcon())
                            .append(Component.text(player.getName(), playerManager.getColorType().getColor())));
                }
            }
        } else if (event.getMaterial() == Material.FOX_SPAWN_EGG){
            assert event.getItem() != null;
            if (Timer.getEnum() == Timer.TimerEnum.VOTING){
                event.setCancelled(true);
                player.getInventory().remove(Material.FOX_SPAWN_EGG);
                if (event.getClickedBlock() != null && event.getClickedBlock().getType() != Material.AIR){
                    PlayerManager playerManager = Online.getPlayerManager(player);

                    Location blockAbove = event.getClickedBlock().getLocation().add(0, 1, 0);
                    Entity fox = blockAbove.getWorld().spawn(blockAbove, Fox.class, CreatureSpawnEvent.SpawnReason.SPAWNER_EGG);

                    fox.customName(Component.text(playerManager.getTeam().getIcon(), ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false)
                            .append(Component.text(player.getName() + "'s Fox", playerManager.getColorType().getColor()).decoration(TextDecoration.ITALIC, false)));

                    Objects.requireNonNull(player.getScoreboard().getPlayerTeam(player)).addEntities(fox);

                    fox.setGlowing(true);
                }
            }
        } else if (event.getMaterial() == Material.NETHERITE_SWORD || event.getMaterial() == Material.CROSSBOW){
            assert event.getItem() != null;
            if (Timer.getEnum() == Timer.TimerEnum.VOTING){
                if ((event.getMaterial() == Material.NETHERITE_SWORD && event.getAction().isLeftClick()) || (event.getMaterial() == Material.CROSSBOW && event.getAction().isRightClick())){
                    player.getInventory().remove(event.getMaterial());
                }
            }
        } else if (event.getMaterial() == Material.CARROT_ON_A_STICK) {
            assert event.getItem() != null;
            if (Timer.getEnum() == Timer.TimerEnum.VOTING){
                PlayerManager playerManager = Online.getPlayerManager(player);

                switch (event.getItem().getItemMeta().getCustomModelDataComponent().getStrings().getFirst()){
                    case "swap_players_and_spec" -> {
                        player.getInventory().remove(Material.CARROT_ON_A_STICK);
                        for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation(), 30, 10, 30)){
                            if (entity instanceof Fox){
                                entity.remove();
                            } else if (entity instanceof Player loopPlayer){
                                if (Players.isPlayer(loopPlayer)){
                                    Spectators.readySpectatorDecision(Online.getPlayerManager(loopPlayer));
                                } else {
                                    Spectators.readySpectatorLobby(Online.getPlayerManager(loopPlayer));
                                }
                            }
                        }

                        Bukkit.broadcast((Component.text('[')
                                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                                .append(Component.text(" used the Players <> Spectators swap!", ColorType.TEXT.getColor()))));
                    }
                    case "you_choose_the_game" -> {
                        player.getInventory().remove(Material.CARROT_ON_A_STICK);
                        for (Entity entity : player.getWorld().getNearbyEntities(player.getLocation(), 30, 10, 30)){
                            if (entity instanceof Fox){
                                entity.remove();
                            } else if (entity instanceof Player loopPlayer){
                                if (player.getUniqueId() != loopPlayer.getUniqueId()){
                                    if (Players.isPlayer(loopPlayer)){
                                        Spectators.readySpectatorDecision(Online.getPlayerManager(loopPlayer));
                                    }
                                }
                            }
                        }

                        Bukkit.broadcast((Component.text('[')
                                .append(Component.text("Decision Crystal", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                                .append(Component.text("] ", ColorType.TEXT.getColor()))
                                .append(Component.text(playerManager.getName(), playerManager.getColorType().getColor()))
                                .append(Component.text(" is choosing the next game!", ColorType.TEXT.getColor()))));
                    }
                    case "block_a_game" -> {
                        player.getInventory().remove(Material.CARROT_ON_A_STICK);
                        Location blockLoc = player.getLocation().clone();
                        blockLoc.setY(249);
                        BlockGame.block(blockLoc.getBlock().getType(), playerManager);
                    }
                }
            } else if (Timer.getEnum() == Timer.TimerEnum.IN_GAME){
                if (Variables.equals("jeu_condi", Game.PARKOUR.getGameCondition())){
                    if (Parkour.hasPlayerSkipped(player)){
                        player.sendMessage(Component.text("[")
                                .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                .append(Component.text("] You already skipped a level in the last 10 seconds...", ColorType.TEXT.getColor())));
                    } else {
                        if (Parkour.getMainLevel(player) == Parkour.getSubLevel(player) && Parkour.getSubLevel(player) == 1){
                            player.sendMessage(Component.text("[")
                                    .append(Component.text(Game.PARKOUR.getName(), Game.PARKOUR.getColorType().getColor()))
                                    .append(Component.text("] You cannot skip the first level...", ColorType.TEXT.getColor())));
                            return;
                        }
                        Parkour.setPlayerSkipped(player, plugin);
                    }
                } else if (Variables.equals("jeu_condi", Game.FINDER.getGameCondition())){
                    if (event.getItem().getItemMeta().getCustomModelDataComponent().getStrings().contains("bingo_house")){
                        player.teleport(new Location(Bukkit.getWorld("Finder"), -67, 64, -53));
                        player.sendMessage(Component.text("[")
                                .append(Component.text(Game.FINDER.getName(), Game.FINDER.getColorType().getColor()))
                                .append(Component.text("] You've been teleported to the spawn!", ColorType.TEXT.getColor())));
                    }
                }
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (event.getClickedBlock() == null){
                TheEvent580_2.getPlugin(TheEvent580_2.class).getLogger().warning(player.getName() + " clicked on air");
            }
            else if (event.getClickedBlock().getType() == Material.WHITE_CONCRETE || event.getClickedBlock().getType() == Material.YELLOW_CONCRETE){
                player.give(new ItemStack(Material.EGG, 1));
                player.getInventory().remove(new ItemStack(Material.EGG, 1));
            }
            else if (event.getClickedBlock().getType() == Material.PALE_OAK_BUTTON){
                Location loc = event.getClickedBlock().getLocation();

                PlayerManager playerManager = Players.getPlayerManager(player);

                if (playerManager == null){
                    playerManager = Spectators.getPlayerManager(player);
                }

                assert playerManager != null;

                if (loc.getX() == 218 && loc.getZ() == -355){
                    playerManager.setFlag(FlagHead.getPrideFlag());
                    playerManager.setColorType(ColorType.PRIDE);

                } else if (loc.getX() == 218 && loc.getZ() == -360){
                    playerManager.setFlag(FlagHead.getAsexualFlag());
                    playerManager.setColorType(ColorType.ASEXUAL);

                } else if (loc.getX() == 216 && loc.getZ() == -362){
                    playerManager.setFlag(FlagHead.getPansexualFlag());
                    playerManager.setColorType(ColorType.PANSEXUAL);

                } else if (loc.getX() == 213 && loc.getZ() == -358){
                    playerManager.setFlag(FlagHead.getTransgenderFlag());
                    playerManager.setColorType(ColorType.TRANSGENDER);

                } else if (loc.getX() == 212 && loc.getZ() == -364){
                    playerManager.setFlag(FlagHead.getLesbianFlag());
                    playerManager.setColorType(ColorType.LESBIAN);

                } else if (loc.getX() == 209 && loc.getZ() == -360){
                    playerManager.setFlag(FlagHead.getNonBinaryFlag());
                    playerManager.setColorType(ColorType.NON_BINARY);

                } else if (loc.getX() == 208 && loc.getZ() == -366){
                    playerManager.setFlag(FlagHead.getGayFlag());
                    playerManager.setColorType(ColorType.GAY);

                } else if (loc.getX() == 205 && loc.getZ() == -362) {
                    playerManager.setFlag(FlagHead.getBisexualFlag());
                    playerManager.setColorType(ColorType.BISEXUAL);

                } else if (loc.getX() == 204 && loc.getZ() == -370) {
                    playerManager.setFlag(FlagHead.getUnlabledFlag());
                    playerManager.setColorType(ColorType.RAINBOW);

                } else if (loc.getX() == 200 && loc.getZ() == -365) {
                    playerManager.setFlag(FlagHead.getAromanticFlag());
                    playerManager.setColorType(ColorType.AROMANTIC);

                }

                player.getInventory().setHelmet(playerManager.getFlag());

                player.sendActionBar(Component.text("[", ColorType.TEXT.getColor())
                        .append(Component.text("TheEvent580", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] You now have the ", ColorType.TEXT.getColor()))
                        .append(playerManager.getFlag().displayName())
                        .append(Component.text(" on your head!", ColorType.TEXT.getColor())));
            }
        } if (event.getAction().isRightClick()) {
            if (player.isGliding()) {
                if (player.getInventory().getItemInMainHand().getType() == Material.FIREWORK_ROCKET) {
                    ItemStack rocket = player.getInventory().getItemInMainHand();
                    rocket.setAmount(1);
                    player.give(rocket);
                }

            }
        }
    }

    @EventHandler
    public void onPlayerInteractWithEntity(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (entity.getType() == EntityType.VILLAGER){
            if (Variables.equals("jeu_condi", Game.FINDER.getGameCondition())){
                event.setCancelled(true);
                ItemStack itemInHand = player.getInventory().getItemInMainHand();
                boolean isInSet = FinderSets.getCurrentItemSet().contains(player.getInventory().getItemInMainHand().getType());
                if (isInSet){
                    boolean isGoldenItem = FinderSets.getCurrentItemSet().getFirst().equals(player.getInventory().getItemInMainHand().getType());
                    if (isGoldenItem){
                        if (FinderSets.isGoldenLocked()){
                            player.sendMessage(Component.text("Sorry, but you cannot bank the ", ColorType.MC_RED.getColor())
                                    .append(Component.text("Golden Item", ColorType.RAINBOW.getColor()))
                                    .append(Component.text(" yet!", ColorType.MC_RED.getColor())));
                            return;
                        }
                        int count = itemInHand.getAmount();
                        Finder.playerCollectedMaterialAmount(player, itemInHand.getType(), count);
                        player.getInventory().setItem(player.getInventory().getHeldItemSlot(), ItemStack.of(Material.AIR));
                        return;
                    }
                    int count = itemInHand.getAmount();
                    Finder.playerCollectedMaterialAmount(player, itemInHand.getType(), count);
                    player.getInventory().remove(itemInHand);
                    return;
                }
                player.sendMessage(Component.text("Sorry, but the villagers are not requesting for this item currently... ", ColorType.MC_RED.getColor()));
            }
        }
    }

    private static @NotNull Component getFlySpeedMessage(int percentageAfter, int percentageBefore) {
        Component message = Component.text("[")
                .append(Component.text("Fly", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                .append(Component.text("] Fly speed is now set to ", ColorType.TEXT.getColor()))
                .append(Component.text( percentageAfter + "%", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD));

        if (percentageAfter < percentageBefore){
            message = message.append(Component.text(" (-50%)", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false));
        } else {
            message = message.append(Component.text(" (+50%)", ColorType.TEXT.getColor()).decoration(TextDecoration.BOLD, false));
        }
        return message;
    }
}
