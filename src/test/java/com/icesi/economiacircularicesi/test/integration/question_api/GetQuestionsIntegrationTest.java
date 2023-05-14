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
class GetQuestionsIntegrationTest {

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
    void getQuestionsIntegrationTest() {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")).andExpect(status().isOk())
                .andReturn();
        QuestionDTO[] questionDTOS = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionDTO[].class);

        //First question in the list validations
        QuestionDTO firstQuestion = questionDTOS[0];

        assertNotNull(firstQuestion);
        assertThat(firstQuestion, hasProperty("questionText", is("First question statement")));
        assertThat(firstQuestion, hasProperty("mandatory", is(true)));
        assertThat(firstQuestion, hasProperty("justify", is(true)));
        assertThat(firstQuestion, hasProperty("questionOrder", is(1)));
        assertThat(firstQuestion, hasProperty("type", is(QuestionType.INCREMENTAL_SINGLE_CHOICE)));
        assertThat(firstQuestion, hasProperty("activityId", is(UUID.fromString("1ac711f2-682c-46e3-83aa-7fecf28f1082"))));


        assertTrue(firstQuestion.getQuestionOptions().size() == 1);

        QuestionOptionDTO firstOption = firstQuestion.getQuestionOptions().get(0);

        assertNotNull(firstOption);
        assertThat(firstOption, hasProperty("optionOrder", is(1)));
        assertThat(firstOption, hasProperty("optionValue", is("First option.")));

        //Second question in the list validations
        QuestionDTO secondQuestion = questionDTOS[1];

        assertThat(secondQuestion, hasProperty("questionText", is("Second question statement")));
        assertThat(secondQuestion, hasProperty("mandatory", is(true)));
        assertThat(secondQuestion, hasProperty("justify", is(true)));
        assertThat(secondQuestion, hasProperty("questionOrder", is(1)));
        assertThat(secondQuestion, hasProperty("type", is(QuestionType.SINGLE_CHOICE)));
        assertThat(secondQuestion, hasProperty("activityId", is(UUID.fromString("200176e0-2600-40e4-87ee-1b832a425caf"))));

        assertNotNull(secondQuestion.getQuestionOptions());

        QuestionOptionDTO secondOption = firstQuestion.getQuestionOptions().get(0);

        assertNotNull(secondOption);
        assertTrue(secondOption instanceof QuestionOptionDTO);
        assertThat(secondOption, hasProperty("optionOrder", is(1)));
        assertThat(secondOption, hasProperty("optionValue", is("First option.")));

    }
}
