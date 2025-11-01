package fr.thefox580.theevent5802.utils;

public class Conversions {

    public static String convertSecondsToTime(int seconds){
        int minutes = seconds/60;
        seconds = seconds%60;

        String minuteString = minutes + " minute";
        String secondString = seconds + " second";

        if (minutes > 1){
            minuteString += "s";
        } if (seconds > 1){
            secondString += "s";
        }

        return minuteString + " and " + secondString;
    }

    public static String convertSecondsToTimeOptimized(int seconds){
        int minutes = seconds/60;
        seconds = seconds%60;

        String minuteString = "";
        String secondString;

        if (minutes > 0){
            minuteString = minutes + " minute";
            if (minutes > 1){
                minuteString += "s";
            }
        }
        secondString = seconds + " second";
        if (seconds > 1){
            secondString += "s";
        }

        if (!minuteString.isEmpty()){
            minuteString += " and ";
        }

        return minuteString + secondString;
    }

}
