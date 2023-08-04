package org.vip.strategies.winstrategy;

import org.vip.models.Board;
import org.vip.models.GameStatus;
import org.vip.models.Move;

public interface WinStrategy {
    public GameStatus checkGameStatus(Board board, Move move, Integer totalMoves);
}
