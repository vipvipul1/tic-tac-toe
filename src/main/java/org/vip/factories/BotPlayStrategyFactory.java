package org.vip.factories;

import org.vip.models.DifficultyLevel;
import org.vip.strategies.playstrategy.BotPlayStrategy;
import org.vip.strategies.playstrategy.EasyBotPlayStrategy;
import org.vip.strategies.playstrategy.HardBotPlayStrategy;
import org.vip.strategies.playstrategy.MediumBotPlayStrategy;


// - This is not the actual Factory Design Pattern implementation.
// - The name is BotPlayStrategyFactory just for clarity that this class
// is responsible for creating BotPlayStrategy objects.
public class BotPlayStrategyFactory {
    public static BotPlayStrategy getBotPlayStrategy(String difficultyLevel) {
        if (DifficultyLevel.valueOf(difficultyLevel) == DifficultyLevel.HARD) {
            return new HardBotPlayStrategy();
        } else if (DifficultyLevel.valueOf(difficultyLevel) == DifficultyLevel.MEDIUM) {
            return new MediumBotPlayStrategy();
        } else {
            return new EasyBotPlayStrategy();
        }
    }
}
