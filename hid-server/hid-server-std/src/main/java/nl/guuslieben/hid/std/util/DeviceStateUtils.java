package nl.guuslieben.hid.std.util;

import java.util.function.BooleanSupplier;

public class DeviceStateUtils {

    public static void awaitCondition(BooleanSupplier condition, long intervalMs, long timeoutMs) {
        long startTime = System.currentTimeMillis();

        while (!condition.getAsBoolean()) {
            if (System.currentTimeMillis() - startTime > timeoutMs) {
                throw new IllegalStateException("Condition was not met within the timeout period.");
            }

            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread was interrupted while waiting for condition.", e);
            }
        }
    }
}
