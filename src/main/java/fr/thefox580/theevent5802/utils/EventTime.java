package fr.thefox580.theevent5802.utils;

public class EventTime {

    private static int seconds = 0;

    public static int getSeconds(){
        return EventTime.seconds;
    }

    public static void add1Second(){
        EventTime.seconds++;
    }

    public static String getFormatedTimer() {
        int minutes = EventTime.seconds /60;
        int newSeconds = EventTime.seconds -minutes*60;
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
