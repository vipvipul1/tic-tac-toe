package org.vip.models;

import org.vip.exceptions.InvalidDimensionException;
import org.vip.exceptions.InvalidNoOfPlayersException;
import org.vip.exceptions.InvalidSymbolsSelectedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Integer nextPlayerIndex;
    private GameStatus status;
    private Player winner;

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
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

        public Game build() {
            Game game = null;
            try {
                validateGameInit();
                game = new Game();
                game.status = GameStatus.IN_PROGRESS;
                game.players = this.players;
                game.board = new Board(this.dimension);
                game.nextPlayerIndex = 0;
            } catch (InvalidNoOfPlayersException | InvalidDimensionException | InvalidSymbolsSelectedException e) {
                System.out.println(e.getMessage());
            }
            return game;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
