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
    private final List<BaseAdvancement> advancementsProgressionList = new ArrayList<>();
    private final List<BaseAdvancement> advancementsCustomList = new ArrayList<>();

    public Advancements(TheEvent580_2 plugin){
        UltimateAdvancementAPI api = UltimateAdvancementAPI.getInstance(plugin);
        advancementProgressionTab = api.createAdvancementTab("theevent580_progression");
        AdvancementDisplay rootProgressionDisplay = new AdvancementDisplay(Material.EMERALD_BLOCK, "Welcome to TheEvent580", AdvancementFrameType.TASK, false, false, 0, 0, "Get ready, the event ", "is starting soon.");
        rootProgression = new RootAdvancement(advancementProgressionTab, "rootProgression", rootProgressionDisplay, "textures/block/stone.png");
        advancementProgressionTab.registerAdvancements(rootProgression);

        advancementCustomTab = api.createAdvancementTab("theevent580_custom");
        AdvancementDisplay rootCustomDisplay = new AdvancementDisplay(Material.EMERALD_BLOCK, "Want more fun?", AdvancementFrameType.TASK, false, false, 0, 0, "Try to complete as many advancements as you can!");
        rootCustom = new RootAdvancement(advancementCustomTab, "rootProgression", rootCustomDisplay, "textures/block/stone.png");
        advancementCustomTab.registerAdvancements(rootCustom);

        createCustomAdvancementsTab();
    }

    private void createCustomAdvancementsTab(){
        AdvancementDisplay counterAdvancementDisplay = new AdvancementDisplay(
                Material.BARRIER,
                "Custom Advancement count",
                AdvancementFrameType.CHALLENGE,
                false,
                false,
                1, 0,
                "DM 'TheEvents580' on discord when you collect all ",
                String.valueOf(AdvancementsEnum.values().length),
                " advancements for a special reward");

        BaseAdvancement counterAdvancement = new BaseAdvancement("counter", counterAdvancementDisplay, rootCustom, AdvancementsEnum.values().length);
        advancementsCustomList.add(counterAdvancement);

        int yPos = -AdvancementsEnum.values().length / 2;

        for (AdvancementsEnum adv : AdvancementsEnum.values()){
            AdvancementDisplay customAdvancementDisplay = new AdvancementDisplay(
                    adv.getBlock(),
                    adv.getName(),
                    AdvancementFrameType.TASK,
                    true,
                    false,
                    2, yPos,
                    adv.getDescription());
            BaseAdvancement customAdvancement = new BaseAdvancement(String.valueOf(adv.getId()), customAdvancementDisplay, counterAdvancement);
            advancementsCustomList.add(customAdvancement);
            yPos++;
        }
    }

    public BaseAdvancement createAdvancement(String key, Material display, String title, AdvancementFrameType frameType, boolean showToast, String ...description){
        AdvancementDisplay newAdvancementDisplay = new AdvancementDisplay(display, title, frameType, showToast, false, 0, 0, description);
        BaseAdvancement newAdvancement;
        if (advancementsProgressionList.isEmpty()){
            newAdvancement = new BaseAdvancement(key, newAdvancementDisplay, rootProgression);
        } else {
            newAdvancement = new BaseAdvancement(key, newAdvancementDisplay, advancementsProgressionList.getLast());
        }
        advancementsProgressionList.add(newAdvancement);

        return newAdvancement;
    }

    public AdvancementTab getProgressionTab(){
        return advancementProgressionTab;
    }

    public BaseAdvancement getProgressionAdvancement(int pos){
        return advancementsProgressionList.get(pos);
    }

    public AdvancementTab getCustomTab() {
        return advancementCustomTab;
    }

    public BaseAdvancement getCounterAdvancement(){
        return advancementsCustomList.getFirst();
    }

    public BaseAdvancement getCustomAdvancement(AdvancementsEnum adv){
        return advancementsCustomList.get(adv.getId()+1);
    }
}
