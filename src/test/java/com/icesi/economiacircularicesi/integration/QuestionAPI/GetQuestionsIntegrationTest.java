package com.icesi.economiacircularicesi.integration.QuestionAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionOptionDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserNoPassDTO;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.mapper.UserMapperImpl;
import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.model.Question.QuestionType;
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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class GetQuestionsIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

    }

    @SneakyThrows
    @Test
    public void getQuestionsIntegrationTest(){

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")).andExpect(status().isOk())
                .andReturn();
        QuestionDTO[] questionDTOS = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionDTO[].class);

        //First question in the list validations
        QuestionDTO firstQuestion = questionDTOS[0];

        assertNotNull(firstQuestion);
        assertTrue(firstQuestion instanceof QuestionDTO);
        assertThat(firstQuestion, hasProperty("questionText", is("First question statement")));
        assertThat(firstQuestion, hasProperty("mandatory", is(true)));
        assertThat(firstQuestion, hasProperty("justify", is(true)));
        assertThat(firstQuestion, hasProperty("questionOrder", is(1)));
        assertThat(firstQuestion, hasProperty("type", is(QuestionType.INCREMENTAL_SINGLE_ANSWER)));
        assertThat(firstQuestion, hasProperty("activity", is("A2")));

        assertNotNull(firstQuestion.getQuestionOptions());
        assertTrue(firstQuestion.getQuestionOptions().size()==1);

        QuestionOptionDTO firstOption = firstQuestion.getQuestionOptions().get(0);

        assertNotNull(firstOption);
        assertTrue(firstOption instanceof QuestionOptionDTO);
        assertThat(firstOption, hasProperty("optionOrder", is(1)));
        assertThat(firstOption, hasProperty("optionValue", is("First option.")));

        //Second question in the list validations
        QuestionDTO secondQuestion = questionDTOS[1];

        assertNotNull(secondQuestion);
        assertTrue(secondQuestion instanceof QuestionDTO);
        assertThat(secondQuestion, hasProperty("questionText", is("Second question statement")));
        assertThat(secondQuestion, hasProperty("mandatory", is(true)));
        assertThat(secondQuestion, hasProperty("justify", is(true)));
        assertThat(secondQuestion, hasProperty("questionOrder", is(1)));
        assertThat(secondQuestion, hasProperty("type", is(QuestionType.SINGLE_ANSWER)));
        assertThat(secondQuestion, hasProperty("activity", is("A11")));

        assertNotNull(secondQuestion.getQuestionOptions());
        assertTrue(secondQuestion.getQuestionOptions().size()==1);

        QuestionOptionDTO secondOption = firstQuestion.getQuestionOptions().get(0);

        assertNotNull(secondOption);
        assertTrue(secondOption instanceof QuestionOptionDTO);
        assertThat(secondOption, hasProperty("optionOrder", is(1)));
        assertThat(secondOption, hasProperty("optionValue", is("First option.")));

    }
}