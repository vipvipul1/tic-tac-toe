package org.vip.models;

import org.vip.exceptions.InvalidDimensionException;
import org.vip.exceptions.InvalidNoOfPlayersException;
import org.vip.exceptions.InvalidSymbolsSelectedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Integer nextPlayerIndex;
    private GameStatus status;
    private Player winner;

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Integer getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(Integer nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameStatus getStatus() {
        return status;
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

    public void displayBoard() {
        this.board.displayBoard();
    }

    public void makeNextMove() {
        Player player = players.get(nextPlayerIndex);
        System.out.print("It's " + player.getName() + "'s turn: ");

        Move move = player.chooseNextMove(board);
        int chosenRow = move.getCell().getRow(), chosenCol = move.getCell().getCol();
        Cell boardCell = board.getCells().get(chosenRow).get(chosenCol);
        if (boardCell.getCellState() == CellState.EMPTY) {
            board.applyMove(move);
            moves.add(move);

            // check winner

            nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
        } else {
            System.out.println("Chosen cell is already filled! Choose another cell!");
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
                game.nextPlayerIndex = new Random().nextInt(game.players.size());
                game.moves = new ArrayList<>();
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
