package fr.thefox580.theevent5802.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Timer {

    private static int seconds = 0;
    private static int maxSeconds = 0;
    private static TimerEnum mode = TimerEnum.STARTING_SOON;
    private static boolean paused = true;

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

        for (PlayerManager loopPlayer : Spectators.getSpectatorOnlineList()){
            if (loopPlayer.isStaff()){
                Player staff = loopPlayer.getOnlinePlayer();
                if (staff != null){
                    staff.sendMessage(Component.text("[")
                            .append(Component.text("Admin", ColorType.MC_DARK_RED.getColor()))
                            .append(Component.text("] Event state : "))
                            .append(Component.text(Timer.mode.getMeaning(), ColorType.MC_YELLOW.getColor()))
                            .append(Component.text(" --> "))
                            .append(Component.text(mode.getMeaning(), ColorType.MC_LIME.getColor())));
                }
            }
        }

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
            int newSeconds = Timer.seconds%60;

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

    public enum TimerEnum {

        NONE(-1, "Error while retrieving TimerEnum"),
        STARTING_SOON(0, "Event Starting Soon"),
        START(1, "Countdown started"),
        SHOW_GAMES(2, "Decision Crystal showing games"),
        VOTING(3, "Voting"),
        TP_TO_GAME(4, "Teleporting players to game"),
        PRE_GAME(5, "Pre game"),
        IN_GAME(6, "In game"),
        POST_GAME(7, "Post game (excluding last game)"),
        INTERMISSION(8, "Intermission"),
        PRE_VOTING(9, "Pre-voting"),
        POST_LAST_GAME(10, "Post last game"),
        END(11, "Event Over");

        private final int mode;
        private final String meaning;

        TimerEnum(int mode, String meaning){
            this.mode = mode;
            this.meaning = meaning;
        }

        public int getMode() {
            return mode;
        }

        @Override
        public String toString() {
            return meaning;
        }

        public String getMeaning() {
            return toString();
        }

        public static TimerEnum getEnumFromMode(int mode){
            for (TimerEnum timerEnum : TimerEnum.values()){
                if (mode == timerEnum.getMode()){
                    return timerEnum;
                }
            }
            return NONE;
        }

    }

}

