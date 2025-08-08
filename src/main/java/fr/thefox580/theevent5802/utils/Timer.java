package fr.thefox580.theevent5802.utils;

import org.jetbrains.annotations.NotNull;

public class Timer {

    private static int seconds = 0;
    private static int maxSeconds = 0;
    private static TimerEnum mode = TimerEnum.STARTING_SOON;
    private static boolean paused = false;

    public Timer(){
    }

    public static int getSeconds(){
        return Timer.seconds;
    }
    public static void setSeconds(int seconds){
        Timer.seconds = seconds;
    }

    public static int getMaxSeconds(){
        return Timer.maxSeconds;
    }
    public static void setMaxSeconds(int maxSeconds){
        Timer.maxSeconds = maxSeconds;
    }

    public static TimerEnum getEnum(){
        return Timer.mode;
    }
    public static void setEnum(TimerEnum mode){
        Timer.mode = mode;
    }

    public static boolean isPaused(){
        return Timer.paused;
    }
    public static void setPaused(boolean pause){
        Timer.paused = pause;
    }

    public static void remove1Second(){
        Timer.seconds--;
    }

    public static @NotNull String getFormatedTimer(){
        if (paused){
            return "--:--";
        } else {
            int minutes = Timer.seconds/60;
            int newSeconds = Timer.seconds-minutes*60;

            String minutesString = ""+minutes;
            String secondsString = ""+newSeconds;

            if (minutes < 10){
                minutesString = "0"+minutes;
            } if (newSeconds < 10) {
                secondsString = "0"+newSeconds;
            }

            return minutesString+":"+secondsString;
        }
    }
}

