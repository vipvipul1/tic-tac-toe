package org.vip.tictactoe.systemutilities;

import org.vip.tictactoe.models.DevicePerformance;

public class Device {
    public static DevicePerformance getPerformance() {
        long freeMemoryInMb = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        if (freeMemoryInMb >= 100) {
            return DevicePerformance.HIGH;
        } else if (freeMemoryInMb >= 50) {
            return DevicePerformance.MEDIUM;
        } else {
            return DevicePerformance.LOW;
        }
    }
}
