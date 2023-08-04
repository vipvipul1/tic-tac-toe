package org.vip.strategies.playstrategy;

import org.vip.models.Board;
import org.vip.models.Move;
import org.vip.models.Player;

public interface BotPlayStrategy extends PlayStrategy {
    @Override
    public Move chooseNextMove(Board board, Player player);
}
