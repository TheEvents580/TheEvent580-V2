package fr.thefox580.theevent5802.listeners;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.build_masters.BuildMasters;
import fr.thefox580.theevent5802.utils.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

public class OnWorldInteract implements Listener {

    public OnWorldInteract(TheEvent580_2 plugin){
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
            } else if (Objects.equals(Variables.getVariable("jeu_condi"), 9)){
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
            } else if (!Objects.equals(Variables.getVariable("jeu_condi"), 9)){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerClickEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (event.getMaterial() == Material.FEATHER) {
            assert event.getItem() != null;
            if (event.getItem().displayName().equals(Component.text("Change your fly speed"))) {
                if (player.getAllowFlight()){
                    if (event.getAction() == Action.LEFT_CLICK_AIR && player.getFlySpeed() > 0.1){
                        player.setFlySpeed(player.getFlySpeed()+0.1f);
                    } else if (event.getAction() == Action.RIGHT_CLICK_AIR && player.getFlySpeed() < 0.5){
                        player.setFlySpeed(player.getFlySpeed()-0.1f);
                    }
                    player.sendMessage(Component.text("[")
                            .append(Component.text("Fly", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                            .append(Component.text("] Fly speed is now set to ", ColorType.TEXT.getColor()))
                            .append(Component.text( (int) player.getFlySpeed()*100 + "%", ColorType.SPECIAL_2.getColor(), TextDecoration.BOLD)));
                } else {
                    player.sendMessage(Component.text("You cannot change your fly speed!", ColorType.MC_RED.getColor()));
                }
            }
        } else if (event.getMaterial() == Material.MINECART){
            assert event.getItem() != null;
            if (Timer.getEnum() == TimerEnum.VOTING){
                if (player.getTargetEntity(5) instanceof Player grabbed){
                    player.getInventory().remove(Material.MINECART);
                    player.addPassenger(player);

                    PlayerManager playerManager = Players.getPlayerManager(player);
                    assert playerManager != null;

                    PlayerManager grabbedManager = Players.getPlayerManager(grabbed);
                    assert grabbedManager != null;

                    player.sendMessage(Component.text("You grabbed " + grabbedManager.getTeam().getIcon())
                            .append(Component.text(grabbed.getName(), grabbedManager.getColorType().getColor())));

                    grabbed.sendMessage(Component.text("You've been grabbed by " + playerManager.getTeam().getIcon())
                            .append(Component.text(player.getName(), playerManager.getColorType().getColor())));
                }
            }
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (event.getClickedBlock() == null){
                TheEvent580_2.getPlugin(TheEvent580_2.class).getLogger().warning(player.getName() + " clicked on air");
            }
            else if (event.getClickedBlock().getType() == Material.WHITE_CONCRETE || event.getClickedBlock().getType() == Material.YELLOW_CONCRETE){
                player.give(new ItemStack(Material.EGG));
            }
            else if (event.getClickedBlock().getType() == Material.PALE_OAK_BUTTON){
                Location loc = event.getClickedBlock().getLocation();

                PlayerManager playerManager = Players.getPlayerManager(player);

                if (playerManager != null){
                    playerManager = Spectators.getPlayerManager(player);
                }

                assert playerManager != null;

                if (loc.getX() == 218 && loc.getZ() == -355){
                    player.getInventory().setHelmet(FlagHead.getPrideFlag());
                    playerManager.setColorType(ColorType.PRIDE);

                } else if (loc.getX() == 218 && loc.getZ() == -360){
                    player.getInventory().setHelmet(FlagHead.getAsexualFlag());
                    playerManager.setColorType(ColorType.ASEXUAL);

                } else if (loc.getX() == 216 && loc.getZ() == -362){
                    player.getInventory().setHelmet(FlagHead.getPansexualFlag());
                    playerManager.setColorType(ColorType.PANSEXUAL);

                } else if (loc.getX() == 213 && loc.getZ() == -358){
                    player.getInventory().setHelmet(FlagHead.getTransgenderFlag());
                    playerManager.setColorType(ColorType.TRANSGENDER);

                } else if (loc.getX() == 212 && loc.getZ() == -364){
                    player.getInventory().setHelmet(FlagHead.getLesbianFlag());
                    playerManager.setColorType(ColorType.LESBIAN);

                } else if (loc.getX() == 209 && loc.getZ() == -360){
                    player.getInventory().setHelmet(FlagHead.getNonBinaryFlag());
                    playerManager.setColorType(ColorType.NON_BINARY);

                } else if (loc.getX() == 208 && loc.getZ() == -366){
                    player.getInventory().setHelmet(FlagHead.getGayFlag());
                    playerManager.setColorType(ColorType.GAY);

                } else if (loc.getX() == 205 && loc.getZ() == -362) {
                    player.getInventory().setHelmet(FlagHead.getBisexualFlag());
                    playerManager.setColorType(ColorType.BISEXUAL);

                } else if (loc.getX() == 204 && loc.getZ() == -370) {
                    player.getInventory().setHelmet(FlagHead.getUnlabledFlag());
                    playerManager.setColorType(ColorType.RAINBOW);

                } else if (loc.getX() == 200 && loc.getZ() == -365) {
                    player.getInventory().setHelmet(FlagHead.getAromanticFlag());
                    playerManager.setColorType(ColorType.AROMANTIC);

                }

                player.sendActionBar(Component.text("[", ColorType.TEXT.getColor())
                        .append(Component.text("TheEvent580", ColorType.TITLE.getColor(), TextDecoration.BOLD))
                        .append(Component.text("] You now have the ", ColorType.TEXT.getColor()))
                        .append(Objects.requireNonNull(player.getInventory().getHelmet()).displayName())
                        .append(Component.text(" on your head!", ColorType.TEXT.getColor())));
            }
        }
    }
}
