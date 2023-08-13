package org.vip.tictactoe.models;

import org.vip.tictactoe.exceptions.InvalidDimensionException;
import org.vip.tictactoe.exceptions.InvalidNoOfPlayersException;
import org.vip.tictactoe.exceptions.InvalidSymbolsSelectedException;
import org.vip.tictactoe.strategies.winstrategy.HighPerformanceWinStrategy;
import org.vip.tictactoe.strategies.winstrategy.WinStrategy;
import org.vip.tictactoe.strategies.winstrategy.WinStrategyFactory;
import org.vip.tictactoe.systemutilities.Device;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Game {
    private Board board;
    private Integer filledCells;
    private List<Player> players;
    private Integer nextPlayerIndex;
    private List<Move> moves;
    private GameStatus status;
    private Player winner;
    private WinStrategy winStrategy;

    public GameStatus getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Integer getFilledCells() {
        return filledCells;
    }

    public void setFilledCells(Integer filledCells) {
        this.filledCells = filledCells;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Integer getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(Integer nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public WinStrategy getWinStrategy() {
        return winStrategy;
    }

    public void setWinStrategy(WinStrategy winStrategy) {
        this.winStrategy = winStrategy;
    }

    public void displayBoard() {
        this.board.displayBoard();
    }

    public void makeNextMove() {
        Player player = players.get(nextPlayerIndex);
        System.out.println("It's " + player.getName() + "'s turn");

        Move move = player.chooseNextMove(board);
        if (move != null) {
            int chosenRow = move.getCell().getRow(), chosenCol = move.getCell().getCol();
            Cell boardCell = board.getCells().get(chosenRow).get(chosenCol);
            if (boardCell.getCellState() == CellState.EMPTY) {
                board.applyMove(move);
                moves.add(move);

                // check winner
                GameStatus gameStatus = winStrategy.checkGameStatus(board, move, moves.size());
                if (gameStatus == GameStatus.ENDED) {
                    winner = player;
                }
                status = gameStatus;

                nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
            } else {
                System.out.println("Chosen cell is already filled! Choose another cell!");
            }
        } else {
            System.out.println("The game is already DRAW or some internal error occurred!");
            status = GameStatus.DRAW;
        }
    }

    public void undoMove() {
        if (!moves.isEmpty()) {
            Move lastMove = moves.remove(moves.size() - 1);
            board.undoMove(lastMove);
            nextPlayerIndex = (nextPlayerIndex - 1 + players.size()) % players.size();

            if (winStrategy instanceof HighPerformanceWinStrategy) {
                ((HighPerformanceWinStrategy) winStrategy).undoMove(lastMove);
            }

            System.out.println("Last Move '" + lastMove.getPlayer().getSymbol()
                    + "' at (" + lastMove.getCell().getRow() + "," + lastMove.getCell().getCol() + ") "
                    + "by " + lastMove.getPlayer().getName() + " removed!");
        }
    }

    // Using Builder Design Pattern to build the Game.
    public static class Builder {
        private Integer dimension;
        private List<Player> players;

        private Builder() {
            this.dimension = 0;
            this.players = new ArrayList<>();
        }

        public Builder setDimension(Integer dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private void validateGameInit() throws InvalidNoOfPlayersException, InvalidDimensionException, InvalidSymbolsSelectedException {
            if (players.size() != dimension - 1) {
                throw new InvalidNoOfPlayersException("Number of players should be 1 less than dimension");
            }
            if (dimension > 3) {
                throw new InvalidDimensionException("Dimension should be <= 3");
            }
            HashSet<Character> symbols = new HashSet<>();
            for (Player player: players) {
                symbols.add(player.getSymbol());
            }
            if (symbols.size() != players.size()) {
                throw new InvalidSymbolsSelectedException("2 players cannot have same symbols");
            }
        }

        public Game build() throws InvalidNoOfPlayersException, InvalidDimensionException, InvalidSymbolsSelectedException {
            Game game = null;
            try {
                validateGameInit();
                game = new Game();
                game.status = GameStatus.IN_PROGRESS;
                game.players = this.players;
                game.board = new Board(this.dimension);
                game.filledCells = 0;
                game.nextPlayerIndex = new Random().nextInt(game.players.size());
                game.moves = new ArrayList<>();
                game.winStrategy = WinStrategyFactory.getWinStrategy(Device.getPerformance(), this.dimension);
            } catch (InvalidNoOfPlayersException | InvalidDimensionException | InvalidSymbolsSelectedException e) {
                System.out.println(e.getMessage());
                throw e;
            }
            return game;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
