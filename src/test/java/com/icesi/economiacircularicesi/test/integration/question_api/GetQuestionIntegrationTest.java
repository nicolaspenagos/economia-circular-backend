package com.icesi.economiacircularicesi.test.integration.question_api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.dto.question.QuestionDTO;
import com.icesi.economiacircularicesi.dto.question.QuestionOptionDTO;

import com.icesi.economiacircularicesi.model.question.QuestionType;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
class GetQuestionIntegrationTest {

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
    void getQuestionIntegrationTest() {

        // Path of a previously inserted question in the db
        String path = "/questions/8090410a-0f48-462a-a1d3-e002a2a5ca1f";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")).andExpect(status().isOk())
                .andReturn();

        QuestionDTO questionDTO = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionDTO.class);

        QuestionOptionDTO optionDTO = questionDTO.getQuestionOptions().get(0);

        assertNotNull(questionDTO);
        assertTrue(questionDTO instanceof QuestionDTO);
        assertThat(questionDTO, hasProperty("questionText", is("Question statement")));
        assertThat(questionDTO, hasProperty("mandatory", is(true)));
        assertThat(questionDTO, hasProperty("justify", is(true)));
        assertThat(questionDTO, hasProperty("questionOrder", is(1)));
        assertThat(questionDTO, hasProperty("type", is(QuestionType.MULTIPLE_CHOICE)));
        assertThat(questionDTO, hasProperty("activityId", is(UUID.fromString("1ac711f2-682c-46e3-83aa-7fecf28f1082"))));

        assertNotNull(questionDTO.getQuestionOptions());
        assertTrue(questionDTO.getQuestionOptions().size() == 1);

        assertNotNull(optionDTO);
        assertTrue(optionDTO instanceof QuestionOptionDTO);
        assertThat(optionDTO, hasProperty("optionOrder", is(1)));
        assertThat(optionDTO, hasProperty("optionValue", is("First option.")));

    }
}
