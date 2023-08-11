package org.vip.tictactoe.strategies.winstrategy;

import org.vip.tictactoe.models.Board;
import org.vip.tictactoe.models.GameStatus;
import org.vip.tictactoe.models.Move;

public class LowPerformanceWinStrategy implements WinStrategy {
    @Override
    public GameStatus checkGameStatus(Board board, Move move, Integer totalMoves) {
        return null;
    }
}
