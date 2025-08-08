package fr.thefox580.theevent5802.utils;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PlayerManager{

    private final PlayerStats  stats;
    private final PlayerAdvancement advancement;
    private final PlayerTimer timer;
    private final OfflinePlayer player;
    private final Team team;
    private final UUID uuid;
    private final String name;
    private boolean muted;
    private ColorType color;

    public PlayerManager(OfflinePlayer player, Team team){
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.team = team;
        this.stats = new PlayerStats(uuid);
        this.advancement = new PlayerAdvancement(uuid);
        this.timer = new PlayerTimer(uuid);
        this.color = team.getColorType();
        this.muted = false;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public PlayerAdvancement getAdvancement() {
        return advancement;
    }

    public PlayerTimer getTimer() {
        return timer;
    }

    public OfflinePlayer getOfflinePlayer() {
        return player;
    }

    public @Nullable Player getOnlinePlayer() {
        if (player.isOnline()){
            return player.getPlayer();
        }
        return null;
    }

    public Team getTeam() {
        return team;
    }

    public UUID getUniqueId(){
        return uuid;
    }

    public String getName(){
        return name;
    }

    public boolean getMuted(){
        return muted;
    }

    public void setMuted(boolean isMuted){
        this.muted = isMuted;
    }

    public ColorType getColorType(){
        return color;
    }

    public void setColorType(ColorType color){
        this.color =  color;
    }
}
