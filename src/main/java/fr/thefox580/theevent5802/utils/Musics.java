package fr.thefox580.theevent5802.utils;

public enum Musics {

    INTRO("intro"),
    HUB("hub");

    private final String name;

    Musics(String name){
        this.name = name;
    }

    public String getID(){
        return "theevent580:game."+name;
    }
}
