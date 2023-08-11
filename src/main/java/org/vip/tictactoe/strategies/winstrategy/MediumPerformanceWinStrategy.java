package org.vip.tictactoe.strategies.winstrategy;

import org.vip.tictactoe.models.Board;
import org.vip.tictactoe.models.Cell;
import org.vip.tictactoe.models.GameStatus;
import org.vip.tictactoe.models.Move;

import java.util.List;

// TC: O(N) | SC: O(1)
public class MediumPerformanceWinStrategy implements WinStrategy {
    @Override
    public GameStatus checkGameStatus(Board board, Move move, Integer totalMoves) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int boardSize = board.getCells().size();
        Character moveSymbol = move.getPlayer().getSymbol();

        List<Cell> moveRowCells = board.getCells().get(row);
        // Row symbol count
        int rowSymbolCount = (int) moveRowCells.stream().filter(cell -> cell.getPlayer().getSymbol() == moveSymbol).count();
        int colSymbolCount = 0;
        int leftDiaSymbolCount = 0;
        int rightDiaSymbolCount = 0;
        for (int i = 0; i < boardSize; i++) {
            // Column symbol count
            List<Cell> rowCells = board.getCells().get(i);
            if (rowCells.get(col).getPlayer().getSymbol() == moveSymbol) {
                colSymbolCount++;
            }
            // Top-Left to Bottom-Right Diagonal symbol count
            if (rowCells.get(i).getPlayer().getSymbol() == moveSymbol) {
                leftDiaSymbolCount++;
            }
            // Top-Right to Bottom-Left Diagonal symbol count
            if (rowCells.get(boardSize - i - 1).getPlayer().getSymbol() == moveSymbol) {
                rightDiaSymbolCount++;
            }
        }
        if (rowSymbolCount == boardSize || colSymbolCount == boardSize || leftDiaSymbolCount == boardSize || rightDiaSymbolCount == boardSize)
            return GameStatus.ENDED;
        if (totalMoves == boardSize * boardSize)
            return GameStatus.DRAW;
        return GameStatus.IN_PROGRESS;
    }
}
