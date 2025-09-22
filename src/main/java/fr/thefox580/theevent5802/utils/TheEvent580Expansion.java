package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TheEvent580Expansion extends PlaceholderExpansion {

    private final TheEvent580_2 plugin;

    public TheEvent580Expansion(TheEvent580_2 plugin){
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "theevent580";
    }

    @Override
    public @NotNull String getAuthor() {
        return "TheEvent580";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getPluginMeta().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier){

        if (player != null){

            PlayerManager pManager;

            if (Players.isPlayer(player)){
                pManager = Players.getPlayerManager(player);
            } else {
                pManager = Spectators.getPlayerManager(player);
            }

            assert pManager != null;

            if (identifier.equals("parkour.solo")){
                if (pManager.getParkourCompletion() == 0){
                    return "You haven't completed the parkour yet!";
                } else if (pManager.getParkourCompletion() == 1) {
                    return "You have completed the parkour for the 1st time!";
                } else {
                    return "You have completed the parkour " + pManager.getParkourCompletion() + " times!";
                }
            }
        }

        return null;
    }


}
