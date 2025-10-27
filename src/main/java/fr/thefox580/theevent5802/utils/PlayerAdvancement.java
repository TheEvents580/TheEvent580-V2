package fr.thefox580.theevent5802.utils;

import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.*;

public class PlayerAdvancement {

    private final UUID player_uuid;
    private final Map<AdvancementsEnum, Boolean> advancements = new HashMap<>();

    public PlayerAdvancement(UUID player_uuid){
        this.player_uuid = player_uuid;
        this.advancements.put(AdvancementsEnum.LASTSECONDSBM, false);
        this.advancements.put(AdvancementsEnum.SECRETBASE, false);
        this.advancements.put(AdvancementsEnum.HEROBRINE, false);
        this.advancements.put(AdvancementsEnum.HUBPARKOUR, false);
    }

    public UUID getPlayerUUID() {
        return player_uuid;
    }

    public Map<AdvancementsEnum, Boolean> getAdvancements() {
        return advancements;
    }

    public void setAdvancementCompletion(AdvancementsEnum advancement, boolean completion){
        this.advancements.replace(advancement, completion);
    }

    private Document formatAdvancementsDoc(){
        Document doc = new Document();

        this.advancements.forEach((AdvancementsEnum adv, Boolean completion) -> doc.append(String.valueOf(adv.getId()), completion));

        return doc;
    }


    public Document getPlayerAdvancement(){
        return new Document()
                .append("_id", player_uuid.toString())
                .append("username", Bukkit.getOfflinePlayer(player_uuid).getName())
                .append("advancements", formatAdvancementsDoc());
    }
}
