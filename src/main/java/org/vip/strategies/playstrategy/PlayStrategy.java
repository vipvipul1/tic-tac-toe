package org.vip.strategies.playstrategy;

import org.vip.models.Board;
import org.vip.models.Move;
import org.vip.models.Player;

public interface PlayStrategy {
    public Move chooseNextMove(Board board, Player player);
}
