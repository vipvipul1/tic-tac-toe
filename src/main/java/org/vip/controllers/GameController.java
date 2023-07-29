package org.vip.controllers;

import org.vip.models.Game;
import org.vip.models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players) {
        return Game.builder().setDimension(dimension).setPlayers(players).build();
    }

    public void displayBoard(Game game) {
        game.getBoard().displayBoard();
    }
}
