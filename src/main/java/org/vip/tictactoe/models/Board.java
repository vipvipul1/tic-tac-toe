package org.vip.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<Cell>> cells;

    public Board(int dimension) {
        this.cells = new ArrayList<>();
        for (int row = 0; row < dimension; row++) {
            this.cells.add(new ArrayList<>());
            for (int col = 0; col < dimension; col++) {
                this.cells.get(row).add(new Cell(row, col));
            }
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void displayBoard() {
        for (List<Cell> row: cells) {
            for (Cell cell: row) {
                if (CellState.EMPTY == cell.getCellState()) {
                    System.out.print("| ");
                } else {
                    System.out.print("|" + cell.getPlayer().getSymbol());
                }
            }
            System.out.println("|");
        }
    }

    public void applyMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        cells.get(row).get(col).setCellState(CellState.FILLED);
        cells.get(row).get(col).setPlayer(move.getPlayer());
    }

    public void undoMove(Move move) {
        int removeRow = move.getCell().getRow();
        int removeCol = move.getCell().getCol();
        Cell boardCell = cells.get(removeRow).get(removeCol);
        boardCell.setCellState(CellState.EMPTY);
        boardCell.setPlayer(null);
    }
}
