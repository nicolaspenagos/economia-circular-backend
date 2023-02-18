package com.icesi.economiacircularicesi.integration.UserAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constants.User.BaseTermsAndCondsAcceptance;
import com.icesi.economiacircularicesi.constants.User.BaseUser;
import com.icesi.economiacircularicesi.constants.FilePaths;
import com.icesi.economiacircularicesi.dto.UserDTO.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserNoPassDTO;
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
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class UpdateUserIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ObjectMapper objectMapper;

    //This path id belongs to a dummy user previously inserted in the db under the name of Valentina
    private final String savedUserPath = "/users/423bba42-3b09-4e70-9c7d-7ee48289a8f6";

    @BeforeEach
    public void init(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @SneakyThrows
    @Test
    public void updateUserIntegrationTest(){


        // Getting the id from the path
        String savedUsedId = savedUserPath.split("/")[2];

        //So this test check if all valentina's data is replaced by base user data
        UserDTO user = deserializeFromJsonFile(FilePaths.USER_JSON, UserDTO.class, objectMapper);
        user.setUserId(UUID.fromString(savedUsedId));
        String body = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(savedUserPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)).andExpect(status().isOk())
                .andReturn();

        UserNoPassDTO userDTO = objectMapper.readValue(result.getResponse().getContentAsString(), UserNoPassDTO.class);

        TermsAndConditionsDTO userDTOTC = userDTO.getTermsAndConditionsHistory().get(0);
        assertEquals(1, userDTO.getTermsAndConditionsHistory().size());

        assertNotNull(userDTO);
        assertTrue(userDTO instanceof UserNoPassDTO);
        assertThat(userDTO, Matchers.hasProperty("email", is("jhon.doe@email.com")));
        assertThat(userDTO, Matchers.hasProperty("name", is(BaseUser.NAME.value)));
        assertThat(userDTO, Matchers.hasProperty("lastname", is(BaseUser.LASTNAME.value)));
        assertThat(userDTO, Matchers.hasProperty("position", is(BaseUser.POSITION.value)));
        assertThat(userDTO, Matchers.hasProperty("sector", is(BaseUser.SECTOR.value)));
        assertThat(userDTO, Matchers.hasProperty("macrosector", is(BaseUser.MACROSECTOR.value)));
        assertThat(userDTO, Matchers.hasProperty("organization", is(BaseUser.ORGANIZATION.value)));
        assertEquals("2022-02-02T05:00:00",userDTO.getRegistrationDate().toString());

        assertEquals("2022-02-02T05:00:00",userDTOTC.getAcceptanceDate().toString());
        assertThat(userDTOTC, Matchers.hasProperty("link", is(BaseTermsAndCondsAcceptance.LINK.value)));



    }


    @SneakyThrows
    @Test
    public void updateUserByAttributesTest(){

        String body = "{ \"name\": \"Maria Valentina\", \"organization\": \"Icesi\" }";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(savedUserPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)).andExpect(status().isOk())
                .andReturn();

        UserNoPassDTO userDTO = objectMapper.readValue(result.getResponse().getContentAsString(), UserNoPassDTO.class);

        //Checking the properties that were changed
        assertNotNull(userDTO);
        assertTrue(userDTO instanceof UserNoPassDTO);
        assertThat(userDTO, Matchers.hasProperty("name", is("Maria Valentina")));
        assertThat(userDTO, Matchers.hasProperty("organization", is("Icesi")));

    }

}
