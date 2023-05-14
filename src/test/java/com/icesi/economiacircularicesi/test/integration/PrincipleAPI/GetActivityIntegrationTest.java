package com.icesi.economiacircularicesi.test.integration.PrincipleAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.dto.ActivityDTO.ActivityDTO;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
class GetActivityIntegrationTest {

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
    void getActivityIntegrationTest() {


        String path = "/activities/";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")).andExpect(status().isOk())
                .andReturn();

        ActivityDTO[] activities = objectMapper.readValue(result.getResponse().getContentAsString(), ActivityDTO[].class);

        assertNotNull(activities);
        assertTrue(activities.length == 2);

        ActivityDTO activityDTO = activities[0];

        assertNotNull(activityDTO);
        assertTrue(activityDTO instanceof ActivityDTO);
        assertThat(activityDTO, hasProperty("description", is("Description text")));
        assertThat(activityDTO, hasProperty("title", is("A1 Title")));
        assertThat(activityDTO, hasProperty("name", is("A1")));
        assertThat(activityDTO, hasProperty("score", is(1000.0)));

        ActivityDTO secondActivityDTO = activities[1];

        assertNotNull(secondActivityDTO);
        assertTrue(secondActivityDTO instanceof ActivityDTO);
        assertThat(secondActivityDTO, hasProperty("description", is("Description text 2")));
        assertThat(secondActivityDTO, hasProperty("title", is("A2 Title")));
        assertThat(secondActivityDTO, hasProperty("name", is("A2")));
        assertThat(secondActivityDTO, hasProperty("score", is(2000.0)));

    }

}
