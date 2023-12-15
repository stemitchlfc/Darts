package com.steven;

import com.steven.exceptions.EmptyNameException;
import com.steven.exceptions.InvalidDartsAtDoubleException;
import com.steven.exceptions.InvalidScoreException;
import com.steven.exceptions.ScoreException;

public class Match {

    private final int startingScore;
    private final String playerName;
    private int dartsThrown = 0;
    private int dartsAtDouble = 0;

    public int getCurrentScore() {
        return currentScore;
    }

    public int currentScore;


    public Match(int startingScore, String playerName) {
        if(startingScore >= 170) {
            this.startingScore = startingScore;
        } else {
            throw new ScoreException("Starting score below 170");
        }
        if(playerName.isEmpty()){
            throw new EmptyNameException("Name is empty");
        } else {
            this.playerName = playerName;
        }
        currentScore = startingScore;
    }

    public int removeScore(int score) {
        if (isScoreValid(score)) {
            this.dartsThrown += 3;
            return this.currentScore -= score;
        } else {
            throw new InvalidScoreException("Invalid score");
        }
    }

    public double calculateAverage(){
        return (double) this.getStartingScore() / this.getDartsThrown() * 3;
    }

    public int setDartsAtDouble(int dartsAtDouble){
        if(dartsAtDouble > 3 || dartsAtDouble <= 0){
            throw new InvalidDartsAtDoubleException("Invalid number of darts thrown at a double");
        }
        return this.dartsAtDouble = dartsAtDouble;
    }
    public boolean isLegComplete(int currentScore){
        return currentScore == 0;
    }

    public boolean isOnFinish(int currentScore){
        if(currentScore > 170){
            return false;
        } else if (currentScore == 169 || currentScore == 168 || currentScore == 166
                    || currentScore == 165 || currentScore == 163
                    || currentScore == 162 || currentScore == 159) {
            return false;
        } else if (currentScore <= 1) {
            return false;
        }
        return true;
    }


    public boolean isScoreValid(int score){
        if (score > 180){
            return false;
        } else if (score < 0) {
            return false;
        } else if (score > this.currentScore) {
            return false;
        } else if (this.currentScore - score == 1) {
            return false;
        }
        return true;
    }
    public int getStartingScore() {
        return startingScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getDartsThrown() {
        if(dartsAtDouble == 0){
            return dartsThrown;
        }
        return dartsThrown - 3 + this.dartsAtDouble;
    }

    public int getDartsAtDouble() {
        return dartsAtDouble;
    }
}
