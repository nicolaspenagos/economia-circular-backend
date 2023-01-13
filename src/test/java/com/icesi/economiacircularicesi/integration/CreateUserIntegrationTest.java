package com.icesi.economiacircularicesi.integration;

import com.icesi.economiacircularicesi.dto.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.model.TermsAndConditions;
import com.icesi.economiacircularicesi.testConstants.BaseTermsAndCondsAcceptance;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStreamReader;
import java.io.Reader;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.testConstants.BaseUser;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class CreateUserIntegrationTest {

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
    public void CreateUserTest(){

        UserDTO user = baseUser();
        String body = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)).andExpect(status().isOk())
                .andReturn();

        UserDTO userDTO = objectMapper.readValue(result.getResponse().getContentAsString(), UserDTO.class);
        TermsAndConditionsDTO termsAndConditionsDTO = userDTO.getTermsAndConditionsHistory().get(0);

        assertNotNull(userDTO);
        assertTrue(userDTO instanceof UserDTO);
        assertThat(userDTO, hasProperty("email", is(BaseUser.EMAIL.value)));
        assertThat(userDTO, hasProperty("name", is(BaseUser.NAME.value)));
        assertThat(userDTO, hasProperty("lastname", is(BaseUser.LASTNAME.value)));
        assertThat(userDTO, hasProperty("position", is(BaseUser.POSITION.value)));
        assertThat(userDTO, hasProperty("sector", is(BaseUser.SECTOR.value)));
        assertThat(userDTO, hasProperty("macrosector", is(BaseUser.MACROSECTOR.value)));
        assertThat(userDTO, hasProperty("registrationDate", is(BaseUser.DATE.value)));

        assertThat(termsAndConditionsDTO, hasProperty("acceptanceDate", is(BaseTermsAndCondsAcceptance.DATE.value)));
        assertThat(termsAndConditionsDTO, hasProperty("link", is(BaseTermsAndCondsAcceptance.LINK.value)));

    }

    @SneakyThrows
    private UserDTO baseUser(){
        String body = parseResourceToString("CreateUser.json");
        return objectMapper.readValue(body, UserDTO.class);
    }

    @SneakyThrows
    private String parseResourceToString(String classpath) {
        Resource resource = new ClassPathResource(classpath);
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        }
    }


}
