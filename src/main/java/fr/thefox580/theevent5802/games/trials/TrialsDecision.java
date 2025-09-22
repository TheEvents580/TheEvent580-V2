package fr.thefox580.theevent5802.games.trials;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrialsDecision {

    private static final List<String> trialsList = new ArrayList<>();
    private static final List<Integer> last10Trials = new ArrayList<>();

    public static void resetAllTrials() {
        trialsList.clear();
        last10Trials.clear();

        trialsList.add("Jump once"); //1
        trialsList.add("Jump twice"); //2
        trialsList.add("Sneak once"); //3
        trialsList.add("Sneak twice"); //4
        trialsList.add("Jump out the map"); //5
        trialsList.add("Punch a player once"); //6
        trialsList.add("Type \"Minecraft\" in chat"); //7
        trialsList.add("Type \"TheEvent580\" in chat"); //8
        trialsList.add("Type \"Trials\" in chat"); //9
        trialsList.add("Stand on a Red Concrete block"); //10
        trialsList.add("Stand on a Lime Concrete block"); //11
        trialsList.add("Stand on a Blue Concrete block"); //12
        trialsList.add("Stand on a Gray Concrete block"); //13
        trialsList.add("Go in Positive X (East)"); //14
        trialsList.add("Go in Positive Z (South)"); //15
        trialsList.add("Go in Negative X (West)"); //16
        trialsList.add("Go in Negative Z (North)"); //17
        trialsList.add("Look at a player"); //18
        trialsList.add("Look at a Tinted Glass block"); //19
        trialsList.add("Look up"); //20
        trialsList.add("Look down"); //21
        trialsList.add("Don't jump"); //22
        trialsList.add("Don't sneak"); //23
        trialsList.add("Don't punch a player"); //24
        trialsList.add("Don't jump out of the map"); //25
        trialsList.add("Don't type \"Minecraft\" in chat"); //26
        trialsList.add("Don't type \"TheEvent580\" in chat"); //27
        trialsList.add("Don't type \"Trials\" in chat"); //28
        trialsList.add("Don't stand on a Red Concrete block"); //29
        trialsList.add("Don't stand on a Lime Concrete block"); //30
        trialsList.add("Don't stand on a Blue Concrete block"); //31
        trialsList.add("Don't stand on a Gray Concrete block"); //32
        trialsList.add("Don't go in Positive X (East)"); //33
        trialsList.add("Don't go in Positive Z (South)"); //34
        trialsList.add("Don't go in Negative X (West)"); //35
        trialsList.add("Don't go in Negative Z (North)"); //36
        trialsList.add("Don't look at a player"); //37
        trialsList.add("Don't look at a Tinted Glass block"); //38
        trialsList.add("Don't look up"); //39
        trialsList.add("Don't look down"); //40
        trialsList.add("What's 4*2 (Type in chat)"); //41
        trialsList.add("What's 6*3 (Type in chat)"); //42
        trialsList.add("What's 489-259+5 (Type in chat)"); //43
        trialsList.add("What's 5^1 (Type in chat)"); //44
        trialsList.add("What's 3*35 (Type in chat)"); //45
        trialsList.add("What's 165^0 (Type in chat)"); //46
        trialsList.add("What's 9+10 (Type in chat)"); //47
        trialsList.add("What's 1+1 (Type in chat)"); //48
        trialsList.add("Stop moving"); //49
        trialsList.add("Don't stop moving"); //50

    }

    public static String getLastTrialString() {
        return trialsList.get(last10Trials.getLast());
    }

    public static int getLastTrial() {
        return last10Trials.getLast();
    }

    public static String getTrial(int trialNumber) {
        return trialsList.get(trialNumber);
    }

    public static int chooseNewTrial() {
        int randomNumber = new Random().nextInt(trialsList.size());

        if (last10Trials.contains(randomNumber)) {
            chooseNewTrial();
        } else {
            if (last10Trials.size() >= 10) {
                last10Trials.removeFirst();
            }
            last10Trials.add(randomNumber);
        }

        return randomNumber;
    }

}
