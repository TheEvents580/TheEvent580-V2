package fr.thefox580.theevent5802.utils;

import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.*;

public class PlayerAdvancement {

    private final UUID player_uuid;
    private final Map<Advancements, Boolean> advancements = new HashMap<>();

    public PlayerAdvancement(UUID player_uuid){
        this.player_uuid = player_uuid;
        advancements.put(Advancements.LASTSECONDSBM, false);
        advancements.put(Advancements.SECRETBASE, false);
        advancements.put(Advancements.HEROBRINE, false);
        advancements.put(Advancements.HUBPARKOUR, false);
    }

    public UUID getPlayerUUID() {
        return player_uuid;
    }

    public Map<Advancements, Boolean> getAdvancements() {
        return advancements;
    }

    public void setAdvancementCompletion(Advancements advancement, boolean completion){
        advancements.replace(advancement, completion);
    }

    private Document formatAdvancementsDoc(){
        Document doc = new Document();

        advancements.forEach((Advancements adv, Boolean completion) -> doc.append(adv.getId().toString(), completion));

        return doc;
    }


    public Document getPlayerAdvancement(){
        return new Document()
                .append("_id", player_uuid.toString())
                .append("username", Bukkit.getOfflinePlayer(player_uuid).getName())
                .append("advancements", formatAdvancementsDoc());
    }
}
