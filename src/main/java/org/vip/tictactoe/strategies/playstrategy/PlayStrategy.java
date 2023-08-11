package org.vip.tictactoe.strategies.playstrategy;

import org.vip.tictactoe.models.Board;
import org.vip.tictactoe.models.Move;
import org.vip.tictactoe.models.Player;

public interface PlayStrategy {
    public Move chooseNextMove(Board board, Player player);
}
