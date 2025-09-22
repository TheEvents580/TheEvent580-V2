package fr.thefox580.theevent5802.utils;

import fr.thefox580.theevent5802.TheEvent580_2;
import fr.thefox580.theevent5802.games.finder.FinderInstance;

public class Instances {
    private final TheEvent580_2 plugin;

    private VariablesDatabase variablesDatabase;
    private StatsDatabase statsDatabase;
    private FinderInstance economicsInstance;
    private Spectators spectators;
    private Variables variables;
    private BossbarManager bossbars;
    private Points points;
    private Players players;
    private BlockGame blockGame;
    private TheEvent580Expansion placeholderAPIExpansion;

    public Instances(TheEvent580_2 plugin){
        this.plugin = plugin;
        initializeDatabases();
    }

    private void initializeDatabases(){
        this.variablesDatabase = new VariablesDatabase(plugin);
        this.statsDatabase = new StatsDatabase(plugin);
    }

    public void initialize(){
        this.economicsInstance = new FinderInstance();
        this.spectators = new Spectators(plugin);
        this.variables = new Variables();
        this.bossbars = new BossbarManager(plugin);
        this.points = new Points(plugin);
        this.players = new Players(plugin);
        this.blockGame = new BlockGame(plugin);
        this.placeholderAPIExpansion = new TheEvent580Expansion(plugin);

        placeholderAPIExpansion.register();
    }

    public VariablesDatabase getVariablesDatabase(){
        return variablesDatabase;
    }

    public StatsDatabase getStatsDatabase(){
        return statsDatabase;
    }

    public FinderInstance getEconomicsInstance(){
        return economicsInstance;
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

}
