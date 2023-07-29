package org.vip.models;

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
}
