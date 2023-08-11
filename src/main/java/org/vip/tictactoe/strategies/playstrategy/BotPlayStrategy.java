package org.vip.tictactoe.strategies.playstrategy;

import org.vip.tictactoe.models.Board;
import org.vip.tictactoe.models.Move;
import org.vip.tictactoe.models.Player;

public interface BotPlayStrategy extends PlayStrategy {
    @Override
    public Move chooseNextMove(Board board, Player player);
}
