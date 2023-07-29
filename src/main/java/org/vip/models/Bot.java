package org.vip.models;

public class Bot extends Player {
    private DifficultyLevel difficultyLevel;

    public Bot(String name, Character symbol, PlayerType playerType, DifficultyLevel difficultyLevel) {
        super(name, symbol, playerType);
        this.difficultyLevel = difficultyLevel;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
