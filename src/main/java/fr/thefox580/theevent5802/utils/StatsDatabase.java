package fr.thefox580.theevent5802.utils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.thefox580.theevent5802.TheEvent580_2;
import org.bson.Document;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class StatsDatabase {

    private final TheEvent580_2 plugin;

    private final MongoClient client;
    private final MongoDatabase database;
    private MongoCollection<Document> stats;

    public StatsDatabase(TheEvent580_2 plugin) {
        this.plugin = plugin;

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb+srv://" + plugin.getConfig().getString("mongodb_username") + ":" + plugin.getConfig().getString("mongodb_password") + "@event.or4pq3l.mongodb.net/?retryWrites=true&w=majority&appName=Event"))
                .serverApi(serverApi)
                .build();

        client = MongoClients.create(settings);
        this.database = client.getDatabase("Season_1");

        AtomicBoolean collectionExists = new AtomicBoolean(false);
        this.database.listCollectionNames().forEach(collection -> {
            if (collection.equals(Objects.requireNonNull(plugin.getConfig().getString("episode")))){
                collectionExists.set(true);
            }
        });
        if (!collectionExists.get()) {
            this.database.createCollection(Objects.requireNonNull(plugin.getConfig().getString("episode")));
        }
        this.stats = this.database.getCollection(Objects.requireNonNull(plugin.getConfig().getString("episode")));
    }

    public void addStats(UUID playerUUID, Map<String, Double> scoresMap){
        // Send a ping to confirm a successful connection
        this.database.runCommand(new Document("ping", 1));
        plugin.getLogger().info("Pinged your deployment. You successfully connected to MongoDB!");

        PlayerStats newStats = new PlayerStats(playerUUID);
        List<Score> scoresList = new ArrayList<>();
        for (String game : scoresMap.keySet()) {
            Score score = new Score(Game.valueOf(game.toUpperCase()));
            score.setPoints(scoresMap.get(game));

            scoresList.add(score);
        }
        newStats.setScores(scoresList);
        this.stats.insertOne(newStats.getPlayerStats());
    }

    public PlayerStats getStats(UUID playerUUID){
        // Send a ping to confirm a successful connection
        this.database.runCommand(new Document("ping", 1));
        plugin.getLogger().info("Pinged your deployment. You successfully connected to MongoDB!");
        Document doc = this.stats.find(new Document("_id", playerUUID.toString())).first();
        if (doc == null){
            return new PlayerStats(playerUUID);
        } else {
            List<Score> scores = new ArrayList<>();

            Document docScores = doc.get("scores", Document.class);

            for (String game : docScores.keySet()){
                Score score = new Score(Game.valueOf(game.toUpperCase()));
                score.setPoints(docScores.getDouble(game));
                scores.add(score);
            }

            PlayerStats playerStats = new PlayerStats(playerUUID);

            playerStats.setScores(scores);

            return playerStats;
        }
    }

    public void updateCollection(){
        AtomicBoolean collectionExists = new AtomicBoolean(false);
        this.database.listCollectionNames().forEach(collection -> {
            if (collection.equals(Objects.requireNonNull(plugin.getConfig().getString("episode")))){
                collectionExists.set(true);
            }
        });
        if (!collectionExists.get()) {
            this.database.createCollection(Objects.requireNonNull(plugin.getConfig().getString("episode")));
        }
        this.stats = this.database.getCollection(Objects.requireNonNull(plugin.getConfig().getString("episode")));
    }

    public void shutdown(){
        this.client.close();
    }
}