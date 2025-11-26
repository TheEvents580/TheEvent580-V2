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

import java.util.UUID;

public class AdvancementsDatabase {

    private final TheEvent580_2 plugin;

    private final MongoClient client;
    private final MongoDatabase database;
    private final MongoCollection<Document> stats;

    public AdvancementsDatabase(TheEvent580_2 plugin) {
        this.plugin = plugin;

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb+srv://" + plugin.getConfig().getString("mongodb_username") + ":" + plugin.getConfig().getString("mongodb_password") + "@event.or4pq3l.mongodb.net/?retryWrites=true&w=majority&appName=Event"))
                .serverApi(serverApi)
                .build();

        client = MongoClients.create(settings);
        this.database = client.getDatabase("advancements");

        this.stats = this.database.getCollection("Season 1");
    }

    public void sendPlayerAdvancements(PlayerAdvancement playerAdvancement){
        // Send a ping to confirm a successful connection
        this.database.runCommand(new Document("ping", 1));
        plugin.getLogger().info("Pinged your deployment. You successfully connected to MongoDB!");

        this.stats.insertOne(playerAdvancement.getPlayerAdvancement());
    }

    public PlayerAdvancement getPlayerAdvancements(UUID player_uuid) {
        // Send a ping to confirm a successful connection
        this.database.runCommand(new Document("ping", 1));
        plugin.getLogger().info("Pinged your deployment. You successfully connected to MongoDB!");
        Document doc = this.stats.find(new Document("_id", player_uuid.toString())).first();
        if (doc == null) {
            return new PlayerAdvancement(player_uuid);
        }
        PlayerAdvancement playerAdvancement = new PlayerAdvancement(player_uuid);

        Document docAdv = doc.get("advancements", Document.class);

        docAdv.forEach((String id, Object value) -> {
            playerAdvancement.setAdvancementCompletion(Advancements.getAdvancementById(Integer.getInteger(id)), (Boolean) value);
        });

        return playerAdvancement;
    }

    public void shutdown(){
        this.client.close();
    }
}