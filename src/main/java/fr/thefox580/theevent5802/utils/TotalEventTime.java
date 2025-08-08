package fr.thefox580.theevent5802.utils;

public class TotalEventTime {

    private static int seconds = 18312;

    public static int getSeconds(){
        return seconds;
    }

    public static void add1Second(){
        TotalEventTime.seconds++;
    }

    public static String getFormatedTimer() {
        int minutes = TotalEventTime.seconds /60;
        int newSeconds = TotalEventTime.seconds -minutes*60;
        int hours = minutes/60;
        minutes = minutes-hours*60;

        String hoursString = ""+hours;
        String minutesString = ""+minutes;
        String secondsString = ""+newSeconds;

        if (hours < 10){
            hoursString = "0"+hours;
        }
        if (minutes < 10){
            minutesString = "0"+minutes;
        }
        if (newSeconds < 10) {
            secondsString = "0"+newSeconds;
        }

        return hoursString+":"+minutesString+":"+secondsString;
    }
}
