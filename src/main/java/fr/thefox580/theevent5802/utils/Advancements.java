package fr.thefox580.theevent5802.utils;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import fr.thefox580.theevent5802.TheEvent580_2;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Advancements {

    private final AdvancementTab advancementProgressionTab;
    private final AdvancementTab advancementCustomTab;
    private final RootAdvancement rootProgression;
    private final RootAdvancement rootCustom;
    private final List<BaseAdvancement> advancements = new ArrayList<>();

    public Advancements(TheEvent580_2 plugin){
        UltimateAdvancementAPI api = UltimateAdvancementAPI.getInstance(plugin);
        advancementProgressionTab = api.createAdvancementTab("theevent580_progression");
        AdvancementDisplay rootProgressionDisplay = new AdvancementDisplay(Material.EMERALD_BLOCK, "Welcome to TheEvent580", AdvancementFrameType.TASK, false, false, 0, 0, "Get ready, the event", "is starting soon.");
        rootProgression = new RootAdvancement(advancementProgressionTab, "rootProgression", rootProgressionDisplay, "textures/block/stone.png");
        advancementProgressionTab.registerAdvancements(rootProgression);

        advancementCustomTab = api.createAdvancementTab("theevent580_custom");
        AdvancementDisplay rootCustomDisplay = new AdvancementDisplay(Material.EMERALD_BLOCK, "Want more fun?", AdvancementFrameType.TASK, false, false, 0, 0, "Try to complete as many", "advancements as you can!");
        rootCustom = new RootAdvancement(advancementCustomTab, "rootProgression", rootCustomDisplay, "textures/block/stone.png");
        advancementCustomTab.registerAdvancements(rootCustom);

        createCustomAdvancementsTab(rootCustom);
    }

    private void createCustomAdvancementsTab(RootAdvancement root){
        //TODO : Add the counter advancement
        //TODO : Add all advancements in AdvancementEnum
    }

    public void createAdvancement(String key, Material display, String title, AdvancementFrameType frameType, boolean showToast, String ...description){
        AdvancementDisplay newAdvancementDisplay = new AdvancementDisplay(display, title, frameType, showToast, false, 0, 0, description);
        BaseAdvancement newAdvancement;
        if (advancements.isEmpty()){
            newAdvancement = new BaseAdvancement(key, newAdvancementDisplay, rootProgression);
        } else {
            newAdvancement = new BaseAdvancement(key, newAdvancementDisplay, advancements.getLast());
        }
        advancements.add(newAdvancement);
    }

    public AdvancementTab getProgressionTabTab(){
        return advancementProgressionTab;
    }

}
