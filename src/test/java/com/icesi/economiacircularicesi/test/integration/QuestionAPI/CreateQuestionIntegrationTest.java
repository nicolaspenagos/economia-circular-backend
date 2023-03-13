package com.icesi.economiacircularicesi.test.integration.QuestionAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionOptionDTO;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
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

import java.util.UUID;

import static com.icesi.economiacircularicesi.utils.TestUtils.deserializeFromJsonFile;
import static com.icesi.economiacircularicesi.utils.TestUtils.verifyError;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class CreateQuestionIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ObjectMapper objectMapper;


    @BeforeEach
    public void init(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

    }

    @SneakyThrows
    @Test
    public void createValidQuestionIntegrationTest() {

        QuestionDTO baseQuestionDTO = deserializeFromJsonFile("createQuestion.json", QuestionDTO.class, objectMapper);

        String body = objectMapper.writeValueAsString(baseQuestionDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/questions").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk()).andReturn();

        QuestionDTO questionDTO = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionDTO.class);

        QuestionOptionDTO optionDTO = questionDTO.getQuestionOptions().get(1);

        assertNotNull(questionDTO);
        assertTrue(questionDTO instanceof QuestionDTO);
        assertThat(questionDTO, hasProperty("questionText", is("Question statement")));
        assertThat(questionDTO, hasProperty("mandatory", is(true)));
        assertThat(questionDTO, hasProperty("justify", is(true)));
        assertThat(questionDTO, hasProperty("questionOrder", is(1)));
        assertThat(questionDTO, hasProperty("type", is(QuestionType.MULTIPLE_CHOICE)));
        assertThat(questionDTO, hasProperty("hint", is("Hint")));
        assertThat(questionDTO, hasProperty("activityId", is(UUID.fromString("7c1e1808-2ad9-46c4-bd69-aff6c3fa111d"))));

        assertNotNull(questionDTO.getQuestionOptions());
        assertTrue(questionDTO.getQuestionOptions().size()==2);

        assertNotNull(optionDTO);
        assertTrue(optionDTO instanceof QuestionOptionDTO);
        assertThat(optionDTO, hasProperty("optionOrder", is(1)));
        assertThat(optionDTO, hasProperty("optionValue", is("First option.")));
        assertThat(optionDTO, hasProperty("exclusive", is(false)));
        assertThat(optionDTO, hasProperty("dependentQuestionId", is(UUID.fromString("39b4542c-b630-43d5-80a0-050b348b08c7"))));

    }

    @SneakyThrows
    @Test
    public void createInvalidIncrementalQuestionIntegrationTest() {

        QuestionDTO baseQuestionDTO = deserializeFromJsonFile("createQuestion.json", QuestionDTO.class, objectMapper);

        baseQuestionDTO.setType(QuestionType.INCREMENTAL_SINGLE_CHOICE);

        String body = objectMapper.writeValueAsString(baseQuestionDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/questions").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyError(ErrorCode.CODE_Q02_INVALID_QUESTION_OPTIONS, customError);

    }

    @SneakyThrows
    @Test
    public void createInvalidMultipleChoiceQuestionIntegrationTest() {

        QuestionDTO baseQuestionDTO = deserializeFromJsonFile("createQuestion.json", QuestionDTO.class, objectMapper);

        baseQuestionDTO.getQuestionOptions().get(1).setExclusive(true);

        String body = objectMapper.writeValueAsString(baseQuestionDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/questions").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyError(ErrorCode.CODE_Q02_INVALID_QUESTION_OPTIONS, customError);

    }

    @SneakyThrows
    @Test
    public void createInvalidSingleChoiceQuestionIntegrationTest() {

        QuestionDTO baseQuestionDTO = deserializeFromJsonFile("createQuestion.json", QuestionDTO.class, objectMapper);

        baseQuestionDTO.setType(QuestionType.SINGLE_CHOICE);
        baseQuestionDTO.getQuestionOptions().get(1).setExclusive(true);

        String body = objectMapper.writeValueAsString(baseQuestionDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/questions").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyError(ErrorCode.CODE_Q02_INVALID_QUESTION_OPTIONS, customError);

    }


}
