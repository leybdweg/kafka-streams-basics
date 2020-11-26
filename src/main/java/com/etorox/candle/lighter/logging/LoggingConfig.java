package com.etorox.candle.lighter.logging;

import ch.qos.logback.classic.Level;

public class LoggingConfig {
    private static ch.qos.logback.classic.Logger packageLogger
            = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger("com.etorox.creditline");
    public static void setPackageLogLevel(Level level){
        packageLogger.setLevel(level);
    }
}
