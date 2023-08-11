package org.vip.tictactoe.strategies.playstrategy;

import org.vip.tictactoe.models.Board;
import org.vip.tictactoe.models.Cell;
import org.vip.tictactoe.models.Move;
import org.vip.tictactoe.models.Player;

import java.util.Scanner;

public class HumanPlayStrategy implements PlayStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Move chooseNextMove(Board board, Player player) {
        System.out.print("Enter row: ");
        int row = scanner.nextInt();

        System.out.print("Enter column: ");
        int col = scanner.nextInt();

        return new Move(new Cell(row, col), player);
    }
}
