package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BossbarManager {

    private static final Map<NamespacedKey, BossBar> bossbarMap = new HashMap<>();
    private static TheEvent580_2 plugin;

    public BossbarManager(TheEvent580_2 plugin){
        BossbarManager.plugin = plugin;

        BossBar countBossbar =  BossBar.bossBar(Component.text("惠上为", ColorType.NO_SHADOW.getColor()).append(Component.text("Event is paused", ColorType.BOSSBAR.getColor())), 0f, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
        bossbarMap.put(new NamespacedKey(plugin, "count"), countBossbar);

        BossBar mallBossbar =  BossBar.bossBar(Component.text("Refill in : ", ColorType.MC_BLUE.getColor()), 0f, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
        bossbarMap.put(new NamespacedKey(plugin, "mall"), mallBossbar);
    }

    public static @Nullable BossBar getBossbar(String key){
        for (NamespacedKey bossbarKey : bossbarMap.keySet()){
            if (Objects.equals(bossbarKey, new NamespacedKey(plugin, key))){
                return bossbarMap.get(bossbarKey);
            }
        }
        return null;
    }

    public static Map<NamespacedKey, BossBar> getBossbarMap() {
        return bossbarMap;
    }

    public static void setBossbarVisibility(BossBar bossbar, boolean visible){
        if (visible){
            Online.getOnlinePlayers().forEach(playerManager -> {
                Player player = playerManager.getOnlinePlayer();
                if (player != null){
                    player.showBossBar(bossbar);
                }
            });
        } else {
            Online.getOnlinePlayers().forEach(playerManager -> {
                Player player = playerManager.getOnlinePlayer();
                if (player != null){
                    player.hideBossBar(bossbar);
                }
            });
        }
    }

    public static void setBossbarText(BossBar bossbar, Component text){
        bossbar.name(text);
    }

    public static void setBossbarColor(BossBar bossbar, BossBar.Color color){
        bossbar.color(color);
    }

    public static void setBossbarProgression(BossBar bossbar, float progression){
        bossbar.progress(progression);
    }
}
