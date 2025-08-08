package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.Component;

import java.util.UUID;

public class PlayerTimer {

    private final UUID player_uuid;
    private int seconds;

    public PlayerTimer(UUID player_uuid){
        this.player_uuid = player_uuid;
        this.seconds = 600;
    }

    public UUID getPlayerUUID(){
        return player_uuid;
    }

    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    public int getSeconds(){
        return seconds;
    }

    public String getFormatedPlayerTimer(){
        int minutes = seconds%60;
        int newSeconds = seconds-minutes*60;

        String minutesString = ""+minutes;
        String secondsString = ""+newSeconds;

        if (minutes < 10){
            minutesString = "0"+minutes;
        } if (newSeconds < 10) {
            secondsString = "0"+newSeconds;
        }

        return minutesString+":"+secondsString;
    }

    public Component timeLeft(){
        if (seconds%60 < 1){
            return Component.text(getFormatedPlayerTimer(), ColorType.MC_RED.getColor());
        } else if (seconds%60 < 3) {
            return Component.text(getFormatedPlayerTimer(), ColorType.MC_ORANGE.getColor());
        } else if (seconds%60 < 5){
            return Component.text(getFormatedPlayerTimer(), ColorType.MC_YELLOW.getColor());
        }
        return Component.text(getFormatedPlayerTimer(), ColorType.MC_LIME.getColor());
    }

}
