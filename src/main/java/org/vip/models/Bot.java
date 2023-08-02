package org.vip.models;

import org.vip.strategies.playstrategy.PlayStrategy;

public class Bot extends Player {
    private DifficultyLevel difficultyLevel;

    public Bot(String name, Character symbol, PlayerType playerType, PlayStrategy playStrategy, DifficultyLevel difficultyLevel) {
        super(name, symbol, playerType, playStrategy);
        this.difficultyLevel = difficultyLevel;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
