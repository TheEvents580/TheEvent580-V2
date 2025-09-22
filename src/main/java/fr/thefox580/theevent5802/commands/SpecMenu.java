package fr.thefox580.theevent5802.commands;

import com.destroystokyo.paper.profile.PlayerProfile;
import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.utils.ColorType;
import fr.thefox580.theevent5802.utils.Online;
import fr.thefox580.theevent5802.utils.PlayerManager;
import fr.thefox580.theevent5802.utils.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecMenu implements CommandExecutor {

    public SpecMenu(TheEvent580_2 plugin){
        Objects.requireNonNull(plugin.getCommand("specmenu")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player){

            if (player.getAllowFlight()){

                Inventory gui = Bukkit.createInventory(player, 9*5, Component.text("Spectator TP Menu"));

                if (args.length == 0){
                    createTeam(gui, Team.RED, 1);
                    createTeam(gui, Team.ORANGE, 2);
                    createTeam(gui, Team.YELLOW, 3);
                    createTeam(gui, Team.LIME, 4);
                    createNextPage(gui);

                } else {
                    createTeam(gui, Team.LIGHT_BLUE, 1);
                    createTeam(gui, Team.BLUE, 2);
                    createTeam(gui, Team.PURPLE, 3);
                    createTeam(gui, Team.PINK, 4);
                    createLastPage(gui);
                }

                player.openInventory(gui);
            }
        }

        return true;
    }

    private void createTeam(Inventory gui, Team team, int row){
        int slot = 9*(row-1);

        ItemStack concrete = team.getItemStack();
        ItemMeta concreteMeta = concrete.getItemMeta();

        concreteMeta.displayName(Component.text(team.getIcon()).decoration(TextDecoration.ITALIC, false)
                .append(Component.text(" "+ team.getName() + " Team", team.getColorType().getColor())));

        concrete.setItemMeta(concreteMeta);

        gui.setItem(slot, concrete);
        slot++;

        List<Player> teamPlayers = new ArrayList<>();

        for (PlayerManager playerManager : Online.getOnlinePlayers()){
            Player player = playerManager.getOnlinePlayer();
            if (player != null && playerManager.getTeam() == team && player.getAllowFlight() && !teamPlayers.contains(player) && teamPlayers.size() < 8){
                teamPlayers.add(player);
            }
        }

        for (Player player : teamPlayers){

            PlayerProfile playerProfile = player.getPlayerProfile();

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
            playerHeadMeta.displayName(Component.text(team.getIcon() + " ", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false)
                    .append(Component.text(player.getName(), team.getColorType().getColor())));
            if (playerProfile.getTextures().isEmpty()){
                playerProfile.update().thenAccept(playerHeadMeta::setPlayerProfile);
            } else {
                playerHeadMeta.setPlayerProfile(playerProfile);
            }
            playerHead.setItemMeta(playerHeadMeta);

            gui.setItem(slot, playerHead);
            slot++;
        }
    }

    private void createNextPage(Inventory gui){
        ItemStack next = new ItemStack(Material.LIME_CARPET);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.displayName(Component.text("Next Page", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        next.setItemMeta(nextMeta);

        gui.setItem(gui.getSize()-1, next);
    }

    private void createLastPage(Inventory gui){
        ItemStack last = new ItemStack(Material.ORANGE_CARPET);
        ItemMeta lastMeta = last.getItemMeta();
        lastMeta.displayName(Component.text("Next Page", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false));
        last.setItemMeta(lastMeta);

        gui.setItem(gui.getSize()-9, last);
    }
}
