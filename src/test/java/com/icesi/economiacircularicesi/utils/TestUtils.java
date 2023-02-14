package com.icesi.economiacircularicesi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.dto.UserDTO.UserDTO;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.io.Reader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUtils {
    public static void verifyUserError(ErrorCode correctCode, CustomError customError) {

        assertNotNull(customError);
        assertEquals(correctCode.getMessage(), customError.getMessage());
        assertEquals(correctCode, customError.getCode());

    }

    @SneakyThrows
    public static UserDTO baseUser(String fileName, ObjectMapper objectMapper){
        String body = parseResourceToString("CreateUser.json");
        return objectMapper.readValue(body, UserDTO.class);
    }

    @SneakyThrows
    private static String parseResourceToString(String classpath) {
        Resource resource = new ClassPathResource(classpath);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        }
    }

}
