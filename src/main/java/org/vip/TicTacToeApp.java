package org.vip;

import org.vip.controllers.GameController;
import org.vip.factories.BotPlayStrategyFactory;
import org.vip.models.*;
import org.vip.strategies.playstrategy.BotPlayStrategy;
import org.vip.strategies.playstrategy.HumanPlayStrategy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeApp {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the dimension: ");
        int dimension = Integer.parseInt(br.readLine());
        int noOfHumans = dimension - 1;

        List<Player> players = new ArrayList<>();

        System.out.print("Is there any bot (y/n): ");
        String ch = br.readLine();  // Assumption: Single Character.
        if ("y".equalsIgnoreCase(ch)) {
            noOfHumans--;
            System.out.print("Enter Bot Name: ");
            String botName = br.readLine();

            System.out.print("Enter Bot Symbol: ");
            char botSymbol = br.readLine().charAt(0);

            System.out.print("Enter Bot Difficulty Level (Easy/Medium/Hard): ");
            String difficultyLevel = br.readLine().toUpperCase();

            BotPlayStrategy botPlayStrategy = BotPlayStrategyFactory.getBotPlayStrategy(difficultyLevel);
            Player bot = new Bot(botName, botSymbol, PlayerType.BOT, DifficultyLevel.valueOf(difficultyLevel), botPlayStrategy);
            players.add(bot);
        }
        for (int i = 0; i < noOfHumans; i++) {
            System.out.print("Enter Player " + (i + 1) + " Name: ");
            String playerName = br.readLine();

            System.out.print("Enter Player " + (i + 1) + " Symbol: ");
            Character playerSymbol = br.readLine().charAt(0);

            Player humanPlayer = new Player(playerName, playerSymbol, PlayerType.HUMAN, new HumanPlayStrategy());
            players.add(humanPlayer);
        }

//        Game game = Game.builder().setDimension(dimension).setPlayers(players).build();
        GameController gameController = new GameController();

        Game game = gameController.createGame(dimension, players);
        while (GameStatus.IN_PROGRESS == game.getStatus()) {
            System.out.println("Current Board:");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo (y/n) ?");
            char isUndo = br.readLine().charAt(0);
            if ('y' == isUndo) {
                gameController.undoMove();
            } else {
                gameController.makeNextMove(game);
            }
        }
    }
}
