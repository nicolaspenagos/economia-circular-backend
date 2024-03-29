package com.icesi.economiacircularicesi.test.integration.question_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomError;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static com.icesi.economiacircularicesi.utils.TestUtils.verifyError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
class DeleteQuestionIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ObjectMapper objectMapper;


    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

    }

    @SneakyThrows
    @Test
    void deleteQuestionIntegrationTest() {

        final String idToDelete = "b9589cbf-d309-447d-8b34-0b4d3a890c1f";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/questions/" + idToDelete).contentType(MediaType.APPLICATION_JSON).content("")).andExpect(status().isOk()).andReturn();

        assertEquals("\"" + idToDelete + "\"", result.getResponse().getContentAsString());

    }

    @SneakyThrows
    @Test
    void deleteNonExistentQuestionIntegrationTest() {

        final String randomQuestionId = UUID.randomUUID().toString();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/questions/" + randomQuestionId).contentType(MediaType.APPLICATION_JSON).content("")).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyError(ErrorCode.CODE_Q01_QUESTION_NOT_FOUND, customError);

    }
}
