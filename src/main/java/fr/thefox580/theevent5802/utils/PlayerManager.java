package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PlayerManager{

    private final OfflinePlayer player;
    private final String name;
    private final Team team;
    private final UUID uuid;
    private final PlayerStats  stats;
    private final PlayerAdvancement advancement;
    private final PlayerTimer timer;
    private int parkourCompletion;
    private boolean muted;
    private ColorType color;
    private final boolean staff;
    private final boolean admin;
    private ItemStack flag;

    public PlayerManager(OfflinePlayer player, Team team, boolean staff, boolean admin){
        this.player = player;
        this.name = player.getName();
        this.team = team;
        this.uuid = player.getUniqueId();
        this.stats = new PlayerStats(uuid);
        this.advancement = new PlayerAdvancement(uuid);
        this.timer = new PlayerTimer(uuid);
        this.parkourCompletion = 0;
        this.muted = false;
        this.color = team.getColorType();
        this.staff = staff;
        this.admin = admin;
        this.flag = new ItemStack(Material.AIR);
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

    public boolean isOnline(){
        return player.isOnline();
    }

    public String getName(){
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public UUID getUniqueId(){
        return uuid;
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

    public int getParkourCompletion(){
        return parkourCompletion;
    }

    public void setParkourCompletion(int parkourCompletion) {
        this.parkourCompletion = parkourCompletion;
    }

    public boolean isMuted(){
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

    public boolean isStaff(){
        return staff;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setFlag(ItemStack flag){
        this.flag = flag;
    }

    public ItemStack getFlag(){
        return flag;
    }

    public boolean isAlive(TheEvent580_2 plugin){
        return Boolean.TRUE.equals(player.getPersistentDataContainer().get(new NamespacedKey(plugin, "alive"), PersistentDataType.BOOLEAN));
    }

    public Component playerComponent(){
        Component head = Component.translatable("%nox_uuid%" + getUniqueId() + ",false,0,-1,1", "\uD83D\uDC64");
        return Component.text(getTeam().getIcon() + " ", ColorType.NO_SHADOW.getColor())
                .append(head)
                .append(Component.text(" " + getName(), getColorType().getColor()));
    }

    public boolean isPlayer(){
        return Players.isPlayer(player);
    }

    public boolean isSpectator(){
        return !isPlayer();
    }
}
