package org.vip.models;

import org.vip.strategies.playstrategy.PlayStrategy;

import java.util.Scanner;

public class Player {
    private String name;
    private Character symbol;
    private PlayerType playerType;
    private PlayStrategy playStrategy;
    private Scanner scanner;

    public Player(String name, Character symbol, PlayerType playerType, PlayStrategy playStrategy) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.playStrategy = playStrategy;
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move chooseNextMove(Board board) {
        return playStrategy.chooseNextMove(board, this);
    }
}
