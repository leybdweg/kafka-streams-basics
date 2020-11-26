package com.etorox.candle.lighter.logging;

import net.logstash.logback.marker.LogstashMarker;

import java.util.UUID;

import static net.logstash.logback.marker.Markers.*;


public class LogMarkers {
    private static String CORRELATION_ID = "correlationId";
    private static String CATEGORIES = "categories";
    private static String USER = "user";
    private static String FAILURE_CODE="failureCode";


    public static LogstashMarker correlationId(UUID correlationId){
        return append(CORRELATION_ID, String.format("%s", correlationId));
    }
    public static LogstashMarker categories(String ... categories){
        return appendArray(CATEGORIES, categories);

    }
    public static LogstashMarker user(String user){
        return append(USER, user);

    }

    public static LogstashMarker failure(int failurecode){
        return append(FAILURE_CODE, failurecode);

    }
}
