package org.vip.strategies.winstrategy;

import org.vip.models.Board;
import org.vip.models.GameStatus;
import org.vip.models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TC: O(1) | SC: O(N^2)
public class HighPerformanceWinStrategy implements WinStrategy {
    private List<HashMap<Character, Integer>> rowSymbols;
    private List<HashMap<Character, Integer>> colSymbols;
    private Map<Character, Integer> topLeftSymbols;
    private Map<Character, Integer> topRightSymbols;

    public HighPerformanceWinStrategy(Integer dimension) {
        rowSymbols = new ArrayList<>();
        colSymbols = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            rowSymbols.add(new HashMap<>());
            colSymbols.add(new HashMap<>());
        }
        topLeftSymbols = new HashMap<>();
        topRightSymbols = new HashMap<>();
    }

    @Override
    public GameStatus checkGameStatus(Board board, Move move, Integer totalMoves) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int boardSize = board.getCells().size();
        Character symbol = move.getPlayer().getSymbol();

        rowSymbols.get(row).put(symbol, rowSymbols.get(row).getOrDefault(symbol, 0) + 1);
        if (rowSymbols.get(row).get(symbol) == boardSize) {
            return GameStatus.ENDED;
        }
        colSymbols.get(col).put(symbol, colSymbols.get(col).getOrDefault(symbol, 0) + 1);
        if (colSymbols.get(col).get(symbol) == boardSize) {
            return GameStatus.ENDED;
        }
        if (row == col) {
            topLeftSymbols.put(symbol, topLeftSymbols.getOrDefault(symbol, 0) + 1);
            if (topLeftSymbols.get(symbol) == boardSize) {
                return GameStatus.ENDED;
            }
        }
        if (row + col == boardSize - 1) {
            topRightSymbols.put(symbol, topRightSymbols.getOrDefault(symbol, 0) + 1);
            if (topRightSymbols.get(symbol) == boardSize) {
                return GameStatus.ENDED;
            }
        }
        if (totalMoves == boardSize * boardSize) {
            return GameStatus.DRAW;
        }
        return GameStatus.IN_PROGRESS;
    }

    public void undoMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int boardSize = rowSymbols.size();
        Character symbol = move.getPlayer().getSymbol();

        rowSymbols.get(row).put(symbol, rowSymbols.get(row).get(symbol) - 1);
        colSymbols.get(col).put(symbol, colSymbols.get(col).get(symbol) - 1);
        if (row == col) {
            topLeftSymbols.put(symbol, topLeftSymbols.get(symbol) - 1);
        }
        if (row + col == boardSize - 1) {
            topRightSymbols.put(symbol, topRightSymbols.get(symbol) - 1);
        }
    }
}
