package fr.thefox580.theevent5802.utils;

import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.*;

public class PlayerAdvancement {

    private final UUID player_uuid;
    private final Map<Advancements, Boolean> advancements = new HashMap<>();

    public PlayerAdvancement(UUID player_uuid){
        this.player_uuid = player_uuid;
        this.advancements.put(Advancements.LASTSECONDSBM, false);
        this.advancements.put(Advancements.SECRETBASE, false);
        this.advancements.put(Advancements.HEROBRINE, false);
        this.advancements.put(Advancements.HUBPARKOUR, false);
    }

    public UUID getPlayerUUID() {
        return player_uuid;
    }

    public Map<Advancements, Boolean> getAdvancements() {
        return advancements;
    }

    public void setAdvancementCompletion(Advancements advancement, boolean completion){
        this.advancements.replace(advancement, completion);
    }

    private Document formatAdvancementsDoc(){
        Document doc = new Document();

        this.advancements.forEach((Advancements adv, Boolean completion) -> doc.append(adv.getId().toString(), completion));

        return doc;
    }


    public Document getPlayerAdvancement(){
        return new Document()
                .append("_id", this.player_uuid.toString())
                .append("username", Bukkit.getOfflinePlayer(this.player_uuid).getName())
                .append("advancements", this.formatAdvancementsDoc());
    }
}
