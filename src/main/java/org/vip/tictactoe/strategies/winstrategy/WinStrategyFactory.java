package org.vip.tictactoe.strategies.winstrategy;

import org.vip.tictactoe.models.DevicePerformance;

// - This is not the actual Factory Design Pattern implementation.
// - The name is WinStrategyFactory just for clarity that this class
// is responsible for creating WinStrategy objects.
public class WinStrategyFactory {
    public static WinStrategy getWinStrategy(DevicePerformance devicePerformance, Integer dimension) {
        if (devicePerformance == DevicePerformance.HIGH) {
            return new HighPerformanceWinStrategy(dimension);
        } else if (devicePerformance == DevicePerformance.MEDIUM) {
            return new MediumPerformanceWinStrategy();
        } else {
            return new LowPerformanceWinStrategy();
        }
    }
}
