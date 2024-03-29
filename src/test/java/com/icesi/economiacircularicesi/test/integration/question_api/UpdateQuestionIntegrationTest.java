package com.icesi.economiacircularicesi.test.integration.question_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constant.FilePaths;
import com.icesi.economiacircularicesi.dto.question.QuestionDTO;
import com.icesi.economiacircularicesi.dto.question.QuestionOptionDTO;
import com.icesi.economiacircularicesi.model.question.QuestionType;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
class UpdateQuestionIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ObjectMapper objectMapper;

    //This path id belongs to a dummy question previously inserted in the db under the statement of Last question
    private final String savedQuestionPath = "/questions/98af9133-409b-41ad-a7f8-3f0f7f3b92f1";

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @SneakyThrows
    @Test
    void updateQuestionIntegrationTest() {

        // Getting the id from the path
        String savedQuestionId = savedQuestionPath.split("/")[2];

        //So this test check if all last question data is replaced by base question data
        QuestionDTO question = deserializeFromJsonFile(FilePaths.QUESTION_JSON, QuestionDTO.class, objectMapper);
        question.setId(UUID.fromString(savedQuestionId));
        String body = objectMapper.writeValueAsString(question);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(savedQuestionPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)).andExpect(status().isOk())
                .andReturn();

        QuestionDTO questionDTO = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionDTO.class);

        QuestionOptionDTO optionDTO = questionDTO.getQuestionOptions().get(1);

        assertNotNull(questionDTO);
        assertTrue(questionDTO instanceof QuestionDTO);
        assertThat(questionDTO, hasProperty("questionText", is("Question statement")));
        assertThat(questionDTO, hasProperty("mandatory", is(true)));
        assertThat(questionDTO, hasProperty("justify", is(true)));
        assertThat(questionDTO, hasProperty("questionOrder", is(1)));
        assertThat(questionDTO, hasProperty("type", is(QuestionType.MULTIPLE_CHOICE)));
        assertThat(questionDTO, hasProperty("activityId", is(UUID.fromString("7c1e1808-2ad9-46c4-bd69-aff6c3fa111d"))));

        assertNotNull(questionDTO.getQuestionOptions());
        assertTrue(questionDTO.getQuestionOptions().size() == 2);

        assertNotNull(optionDTO);
        assertTrue(optionDTO instanceof QuestionOptionDTO);
        assertThat(optionDTO, hasProperty("optionOrder", is(1)));
        assertThat(optionDTO, hasProperty("optionValue", is("First option.")));


    }

    @SneakyThrows
    @Test
    void updateQuestionByAttributesTest() {

        String body = "{ \"questionText\": \"Updated text\", \"justify\": \"false\" }";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(savedQuestionPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)).andExpect(status().isOk())
                .andReturn();

        QuestionDTO questionDTO = objectMapper.readValue(result.getResponse().getContentAsString(), QuestionDTO.class);

        //Checking the properties that were changed
        assertNotNull(questionDTO);
        assertTrue(questionDTO instanceof QuestionDTO);
        assertThat(questionDTO, Matchers.hasProperty("questionText", is("Updated text")));
        assertThat(questionDTO, Matchers.hasProperty("justify", is(false)));

    }

}
