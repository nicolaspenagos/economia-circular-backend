package com.icesi.economiacircularicesi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constants.BaseTermsAndCondsAcceptance;
import com.icesi.economiacircularicesi.constants.BaseUser;
import com.icesi.economiacircularicesi.dto.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.dto.UserNoPassDTO;
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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class GetUsersIntegrationTest {

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
    public void getUsersIntegrationTest(){

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")).andExpect(status().isOk())
                .andReturn();

        UserNoPassDTO[] userDTOs = objectMapper.readValue(result.getResponse().getContentAsString(), UserNoPassDTO[].class);

        UserNoPassDTO jhon = userDTOs[0];
        UserNoPassDTO nicolas = userDTOs[1];

        TermsAndConditionsDTO jhonTC = jhon.getTermsAndConditionsHistory().get(0);
        assertEquals(1, jhon.getTermsAndConditionsHistory().size());

        assertNotNull(jhon);
        assertTrue(jhon instanceof UserNoPassDTO);
        assertThat(jhon, Matchers.hasProperty("email", is("jhon.doe1@email.com")));
        assertThat(jhon, Matchers.hasProperty("name", is(BaseUser.NAME.value)));
        assertThat(jhon, Matchers.hasProperty("lastname", is(BaseUser.LASTNAME.value)));
        assertThat(jhon, Matchers.hasProperty("position", is(BaseUser.POSITION.value)));
        assertThat(jhon, Matchers.hasProperty("sector", is(BaseUser.SECTOR.value)));
        assertThat(jhon, Matchers.hasProperty("macrosector", is(BaseUser.MACROSECTOR.value)));
        assertThat(jhon, Matchers.hasProperty("organization", is(BaseUser.ORGANIZATION.value)));
        assertEquals("2022-02-02T00:00:00",jhon.getRegistrationDate().toString());

        assertEquals("2022-02-02T00:00:00",jhonTC.getAcceptanceDate().toString());
        assertThat(jhonTC, Matchers.hasProperty("link", is(BaseTermsAndCondsAcceptance.LINK.value)));

        TermsAndConditionsDTO nicolasTC = jhon.getTermsAndConditionsHistory().get(0);
        assertEquals(1, nicolas.getTermsAndConditionsHistory().size());

        assertNotNull(nicolas);
        assertTrue(nicolas instanceof UserNoPassDTO);
        assertThat(nicolas, Matchers.hasProperty("email", is("nicolas@email.com")));
        assertThat(nicolas, Matchers.hasProperty("name", is("Nicolas")));
        assertThat(nicolas, Matchers.hasProperty("lastname", is("Penagos")));
        assertThat(nicolas, Matchers.hasProperty("position", is(BaseUser.POSITION.value)));
        assertThat(nicolas, Matchers.hasProperty("sector", is(BaseUser.SECTOR.value)));
        assertThat(nicolas, Matchers.hasProperty("macrosector", is(BaseUser.MACROSECTOR.value)));
        assertThat(nicolas, Matchers.hasProperty("organization", is(BaseUser.ORGANIZATION.value)));
        assertEquals("2022-02-02T00:00:00",nicolas.getRegistrationDate().toString());

        assertEquals("2022-02-02T00:00:00",nicolasTC.getAcceptanceDate().toString());
        assertThat(nicolasTC, Matchers.hasProperty("link", is(BaseTermsAndCondsAcceptance.LINK.value)));

    }

}
