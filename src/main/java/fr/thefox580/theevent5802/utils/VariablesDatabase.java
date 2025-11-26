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

public class VariablesDatabase {

    private final TheEvent580_2 plugin;

    private final MongoClient client;
    private final MongoDatabase database;
    private final MongoCollection<Document> vars;

    public VariablesDatabase(TheEvent580_2 plugin) {
        this.plugin = plugin;

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb+srv://" + plugin.getConfig().getString("mongodb_username") + ":" + plugin.getConfig().getString("mongodb_password") + "@event.or4pq3l.mongodb.net/?retryWrites=true&w=majority&appName=Event"))
                .serverApi(serverApi)
                .build();

        client = MongoClients.create(settings);
        this.database = client.getDatabase("variables");

        this.vars = this.database.getCollection("Season 1");
    }

    public void addVariables(){
        // Send a ping to confirm a successful connection
        this.database.runCommand(new Document("ping", 1));
        plugin.getLogger().info("Pinged your deployment. You successfully connected to MongoDB!");

        this.vars.deleteOne(new Document("_id", "7"));
        this.vars.insertOne(plugin.getInstances().getVariables().sendVariablesDoc());
    }

    public void getVariables(boolean force) {
        // Send a ping to confirm a successful connection
        this.database.runCommand(new Document("ping", 1));
        plugin.getLogger().info("Pinged your deployment. You successfully connected to MongoDB!");
        Document doc = this.vars.find(new Document("_id", "7")).first();
        if (doc == null) {
            return;
        }
        plugin.getInstances().getVariables().retrieveVariables(doc, force);
    }

    public void shutdown(){
        this.client.close();
    }
}