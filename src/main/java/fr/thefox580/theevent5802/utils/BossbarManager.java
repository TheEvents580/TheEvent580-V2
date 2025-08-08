package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import me.clip.placeholderapi.libs.kyori.adventure.text.Component;
import me.clip.placeholderapi.libs.kyori.adventure.bossbar.BossBar;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BossbarManager {

    private static final Map<NamespacedKey, BossBar> bossbarMap = new HashMap<>();
    private static TheEvent580_2 plugin = null;

    public BossbarManager(TheEvent580_2 plugin){
        BossbarManager.plugin = plugin;
        BossBar countBossbar =  BossBar.bossBar(Component.text("惠上为", ColorTypeAlt.NO_SHADOW.getColor()).append(Component.text("Event is paused", ColorTypeAlt.BOSSBAR.getColor())), 0f, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
        bossbarMap.put(new NamespacedKey(plugin, "count"), countBossbar);
        BossBar mallBossbar =  BossBar.bossBar(Component.text("Refill in : ", ColorTypeAlt.MC_BLUE.getColor()), 0f, BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
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
            plugin.audience().all().showBossBar(bossbar);
        } else {
            plugin.audience().all().hideBossBar(bossbar);
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
