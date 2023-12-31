package org.vip.tictactoe.controllers;

import org.vip.tictactoe.models.Game;
import org.vip.tictactoe.models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players) throws Exception {
        return Game.builder().setDimension(dimension).setPlayers(players).build();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public void makeNextMove(Game game) {
        game.makeNextMove();
    }

    public void undoMove(Game game) {
        game.undoMove();
    }
}
