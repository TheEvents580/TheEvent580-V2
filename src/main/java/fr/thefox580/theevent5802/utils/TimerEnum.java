package fr.thefox580.theevent5802.utils;

public enum TimerEnum {

    NONE(-1, "Error while retreiving TimerEnum"),
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
