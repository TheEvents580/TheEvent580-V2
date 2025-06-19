package fr.thefox580.theevent5802.utils;

import java.util.Objects;

public class Score {

    private Game game;
    private int points = 0;

    public Score(Game game){
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Score{" +
                "game='" + game + '\'' +
                ", points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return points == score.points && Objects.equals(game, score.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, points);
    }
}
