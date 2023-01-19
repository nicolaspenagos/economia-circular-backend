package com.icesi.economiacircularicesi.utils;

import com.icesi.economiacircularicesi.constant.UserErrorCode;
import com.icesi.economiacircularicesi.error.exception.UserError;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUtils {

    public static LocalDateTime generateFutureDate(){
        long currentTime = System.currentTimeMillis();
        long futureTime = currentTime + 1000L;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(futureTime), ZoneId.systemDefault());
    }

    public static void verifyUserError(UserErrorCode correctCode, UserError userError) {

        assertNotNull(userError);
        assertEquals(correctCode.getMessage(), userError.getMessage());
        assertEquals(correctCode, userError.getCode());

    }

}
