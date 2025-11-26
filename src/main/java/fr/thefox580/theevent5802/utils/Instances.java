package fr.thefox580.theevent5802.utils;

import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import fr.thefox580.theevent5802.TheEvent580_2;

public class Instances {
    private final TheEvent580_2 plugin;

    private VariablesDatabase variablesDatabase;
    private StatsDatabase statsDatabase;
    private AdvancementsDatabase advancementsDatabase;
    private Spectators spectators;
    private Variables variables;
    private BossbarManager bossbars;
    private Points points;
    private Players players;
    private BlockGame blockGame;
    private TheEvent580Expansion placeholderAPIExpansion;
    private Advancements advancements;

    public Instances(TheEvent580_2 plugin){
        this.plugin = plugin;
    }

    private void initializeDatabases(){
        this.variablesDatabase = new VariablesDatabase(plugin);
        //this.statsDatabase = new StatsDatabase(plugin);
        //this.advancementsDatabase = new AdvancementsDatabase(plugin);
    }

    public void initialize(){
        this.spectators = new Spectators(plugin);
        this.variables = new Variables();
        this.bossbars = new BossbarManager(plugin);
        this.points = new Points(plugin);
        this.players = new Players(plugin);
        this.blockGame = new BlockGame(plugin);
        this.placeholderAPIExpansion = new TheEvent580Expansion(plugin);
        this.advancements = new Advancements(plugin);

        placeholderAPIExpansion.register();
        initializeDatabases();
    }

    public VariablesDatabase getVariablesDatabase(){
        return variablesDatabase;
    }

    public StatsDatabase getStatsDatabase(){
        return statsDatabase;
    }

    public AdvancementsDatabase getAdvancementsDatabase(){
        return advancementsDatabase;
    }

    public Spectators getSpectators(){
        return spectators;
    }

    public Variables getVariables(){
        return variables;
    }

    public BossbarManager getBossbars(){return bossbars;}

    public Points getPoints(){
        return points;
    }

    public Players getPlayers(){
        return players;
    }

    public BlockGame getBlockGame(){
        return blockGame;
    }

    public Advancements getAdvancementAPI(){
        return advancements;
    }

}
