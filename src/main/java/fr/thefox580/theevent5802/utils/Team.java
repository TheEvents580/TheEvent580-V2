package fr.thefox580.theevent5802.utils;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public enum Team {

    ADMIN("Admin", ColorType.MC_DARK_RED, "リ", Material.BROWN_CONCRETE),
    STAFF("Staff", ColorType.RAINBOW, "リ", Material.BLACK_CONCRETE),
    SPECTATORS("Spectators", ColorType.MC_GRAY, "露", Material.GRAY_CONCRETE),
    RED("Red", ColorType.MC_RED, "ラ", Material.RED_CONCRETE),
    ORANGE("Orange", ColorType.MC_ORANGE, "ャ", Material.ORANGE_CONCRETE),
    YELLOW("Yellow", ColorType.MC_YELLOW, "ギ", Material.YELLOW_CONCRETE),
    LIME("Lime", ColorType.MC_LIME, "画", Material.LIME_CONCRETE),
    LIGHT_BLUE("Light Blue", ColorType.MC_AQUA, "動", Material.LIGHT_BLUE_CONCRETE),
    BLUE("Blue", ColorType.MC_BLUE, "像", Material.BLUE_CONCRETE),
    PURPLE("Purple", ColorType.MC_PURPLE, "の", Material.PURPLE_CONCRETE),
    PINK("Pink", ColorType.MC_PINK, "目", Material.PINK_CONCRETE),
    OFFLINE("Offline", ColorType.MC_LIGHT_GRAY, "タ", Material.LIGHT_GRAY_CONCRETE),
    NONE("None", ColorType.TEXT, "", Material.WHITE_CONCRETE);

    private final String name;
    private final ColorType color;
    private final String icon;
    private final Material block;

    Team(String name, ColorType color, String icon, Material block){
        this.name = name;
        this.color = color;
        this.icon = icon;
        this.block = block;
    }

    public String getName() {
        return name;
    }

    public ColorType getColorType() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public Material getMaterial(){
        return block;
    }

    public ItemStack getItemStack(){
        return new ItemStack(block);
    }


    public void createTeamInInv(Inventory gui, int row, boolean spec){
        int slot = 9*(row-1);

        ItemStack concrete = getItemStack();
        ItemMeta concreteMeta = concrete.getItemMeta();

        concreteMeta.displayName(Component.text(getIcon()).decoration(TextDecoration.ITALIC, false)
                .append(Component.text(" "+ getName() + " Team", getColorType().getColor())));

        concrete.setItemMeta(concreteMeta);

        gui.setItem(slot, concrete);
        slot++;

        List<Player> teamPlayers = new ArrayList<>();

        for (PlayerManager playerManager : Online.getOnlinePlayers()){
            Player player = playerManager.getOnlinePlayer();
            if (player != null && playerManager.getTeam() == this && !teamPlayers.contains(player) && teamPlayers.size() < 8){
                if ((!spec && !player.getAllowFlight()) || player.getAllowFlight()) {
                    teamPlayers.add(player);
                }
            }
        }

        for (Player player : teamPlayers){

            PlayerProfile playerProfile = player.getPlayerProfile();

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
            playerHeadMeta.displayName(Component.text(getIcon() + " ", ColorType.TEXT.getColor()).decoration(TextDecoration.ITALIC, false)
                    .append(Component.text(player.getName(), getColorType().getColor())));
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

}
