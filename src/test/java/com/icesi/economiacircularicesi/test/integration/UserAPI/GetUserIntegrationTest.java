package com.icesi.economiacircularicesi.test.integration.UserAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constant.User.BaseTermsAndCondsAcceptance;
import com.icesi.economiacircularicesi.constant.User.BaseUser;
import com.icesi.economiacircularicesi.dto.UserDTO.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.dto.UserDTO.UserNoPassDTO;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.mapper.UserMapperImpl;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
class GetUserIntegrationTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ObjectMapper objectMapper;
    private UserMapper userMapper;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        userMapper = new UserMapperImpl();
    }

    @SneakyThrows
    @Test
    void getUserTest(){

        // Path of a previously inserted user in the db
        String path = "/users/4f4c1918-64d9-4913-a703-69de09073ba3";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")).andExpect(status().isOk())
                .andReturn();

        UserNoPassDTO userDTO = objectMapper.readValue(result.getResponse().getContentAsString(), UserNoPassDTO.class);

        TermsAndConditionsDTO userDTOTC = userDTO.getTermsAndConditionsHistory().get(0);
        assertEquals(1, userDTO.getTermsAndConditionsHistory().size());

        assertNotNull(userDTO);
        assertTrue(userDTO instanceof UserNoPassDTO);
        assertThat(userDTO, Matchers.hasProperty("email", is("jhon.doe1@email.com")));
        assertThat(userDTO, Matchers.hasProperty("name", is(BaseUser.NAME.value)));
        assertThat(userDTO, Matchers.hasProperty("position", is(BaseUser.POSITION.value)));
        assertThat(userDTO, Matchers.hasProperty("sector", is(BaseUser.SECTOR.value)));
        assertThat(userDTO, Matchers.hasProperty("macrosector", is(BaseUser.MACROSECTOR.value)));
        assertThat(userDTO, Matchers.hasProperty("organization", is(BaseUser.ORGANIZATION.value)));
        assertEquals("2022-02-02T00:00:00",userDTO.getRegistrationDate().toString());

        assertEquals("2022-02-02T00:00:00",userDTOTC.getAcceptanceDate().toString());
        assertThat(userDTOTC, Matchers.hasProperty("link", is(BaseTermsAndCondsAcceptance.LINK.value)));


    }

}
