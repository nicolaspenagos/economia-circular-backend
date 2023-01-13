package com.icesi.economiacircularicesi.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestUtils {

    public static LocalDateTime generateFutureDate(){
        long currentTime = System.currentTimeMillis();
        long futureTime = currentTime + 1000L;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(futureTime), ZoneId.systemDefault());
    }

}
