package fr.thefox580.theevent5802.utils;


import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PlayerStats {

    private UUID player_uuid;
    private List<Score> scores = List.of();

    public PlayerStats(UUID player_uuid){
        this.player_uuid = player_uuid;
    }

    public UUID getPlayerUUID() {
        return player_uuid;
    }

    public void setPlayerUUID(UUID player_uuid) {
        this.player_uuid = player_uuid;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Document getScoreStats(){
        Document doc = new Document();

        for (Score score : this.scores){
            doc.append(score.getGame().toString().toLowerCase(), score.getPoints());
        }

        return doc;
    }

    public Document getPlayerStats(){
        return new Document()
                .append("_id", this.player_uuid.toString())
                .append("username", Bukkit.getOfflinePlayer(this.player_uuid).getName())
                .append("scores", this.getScoreStats());
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "playerUUID=" + player_uuid +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerStats that = (PlayerStats) o;
        return Objects.equals(player_uuid, that.player_uuid) && Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player_uuid, scores);
    }
}
