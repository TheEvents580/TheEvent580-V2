package fr.thefox580.theevent5802.utils;

public enum SoundEffects {

    COINS("coins");

    private final String name;

    SoundEffects(String name){
        this.name = name;
    }

    public String getID(){
        return "theevent580:sound_effect."+name;
    }
}
