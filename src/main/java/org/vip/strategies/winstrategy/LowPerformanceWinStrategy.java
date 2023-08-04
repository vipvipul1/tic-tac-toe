package org.vip.strategies.winstrategy;

import org.vip.models.Board;
import org.vip.models.GameStatus;
import org.vip.models.Move;

public class LowPerformanceWinStrategy implements WinStrategy {
    @Override
    public GameStatus checkGameStatus(Board board, Move move, Integer totalMoves) {
        return null;
    }
}
