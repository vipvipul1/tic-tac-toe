package org.vip.strategies.playstrategy;

import org.vip.models.Board;
import org.vip.models.Cell;
import org.vip.models.Move;
import org.vip.models.Player;

import java.util.List;

// Chooses the first empty cell and returns.
public class EasyBotPlayStrategy implements BotPlayStrategy {
    @Override
    public Move chooseNextMove(Board board, Player player) {
        Move move = null;
        for (List<Cell> rowCells: board.getCells()) {
            for (Cell cell: rowCells) {
                if (cell.getPlayer() == null) {
                    // Creating a new Cell object for Move (not using the cell of board)
                    // because board's cell is critical info, and we don't want anyone to
                    // accidentally change the attributes' values of board's cell.
                    move = new Move(new Cell(cell.getRow(), cell.getCol()), player);
                }
            }
        }
        return move;
    }
}
