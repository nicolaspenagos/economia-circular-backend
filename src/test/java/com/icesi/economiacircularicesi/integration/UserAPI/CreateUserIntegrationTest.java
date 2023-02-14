package com.icesi.economiacircularicesi.integration.UserAPI;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.constants.FilePaths;
import com.icesi.economiacircularicesi.dto.UserDTO.TermsAndConditionsDTO;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.constants.BaseTermsAndCondsAcceptance;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.dto.UserDTO.UserDTO;
import com.icesi.economiacircularicesi.constants.BaseUser;

import static com.icesi.economiacircularicesi.utils.TestUtils.baseUser;
import static com.icesi.economiacircularicesi.utils.TestUtils.verifyUserError;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper;
    private UserMapper userMapper;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        userMapper = new UserMapperImpl();
    }

    @SneakyThrows
    @Test
    public void CreateValidUserIntegrationTest() {

        UserDTO user = baseUser(FilePaths.USER_JSON, objectMapper);
        user.setEmail("test@email.com");//A different email of the base user is set to avoid conflicts with the data inserted in the db by other integration tests
        String body = objectMapper.writeValueAsString(user);
        System.out.println(body);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk()).andReturn();

        UserDTO userDTO = objectMapper.readValue(result.getResponse().getContentAsString(), UserDTO.class);
        TermsAndConditionsDTO termsAndConditionsDTO = userDTO.getTermsAndConditionsHistory().get(0);

        assertNotNull(userDTO);
        assertTrue(userDTO instanceof UserDTO);
        assertThat(userDTO, hasProperty("email", is("test@email.com")));
        assertTrue(passwordEncoder.matches(BaseUser.PASSWORD.value, userDTO.getPassword()));
        assertThat(userDTO, hasProperty("name", is(BaseUser.NAME.value)));
        assertThat(userDTO, hasProperty("lastname", is(BaseUser.LASTNAME.value)));
        assertThat(userDTO, hasProperty("position", is(BaseUser.POSITION.value)));
        assertThat(userDTO, hasProperty("sector", is(BaseUser.SECTOR.value)));
        assertThat(userDTO, hasProperty("macrosector", is(BaseUser.MACROSECTOR.value)));
        assertThat(userDTO, Matchers.hasProperty("organization", is(BaseUser.ORGANIZATION.value)));
        assertThat(userDTO, hasProperty("registrationDate", is(BaseUser.DATE.value)));

        assertThat(termsAndConditionsDTO, hasProperty("acceptanceDate", is(BaseTermsAndCondsAcceptance.DATE.value)));
        assertThat(termsAndConditionsDTO, hasProperty("link", is(BaseTermsAndCondsAcceptance.LINK.value)));

    }

    /*
     * CONTROLLER VALIDATIONS
     */
    @SneakyThrows
    @Test
    public void invalidEmailNoAtIntegrationTest() {

        UserDTO baseUserDTO = baseUser(FilePaths.USER_JSON, objectMapper);
        baseUserDTO.setEmail("jhon.doeemail.com");
        String body = objectMapper.writeValueAsString(baseUserDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyUserError(ErrorCode.CODE_U03_INVALID_EMAIL, customError);

    }

    @SneakyThrows
    @Test
    public void invalidEmailNoLocalPartIntegrationTest() {

        UserDTO baseUserDTO = baseUser(FilePaths.USER_JSON, objectMapper);
        baseUserDTO.setEmail("@email.com");
        String body = objectMapper.writeValueAsString(baseUserDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyUserError(ErrorCode.CODE_U03_INVALID_EMAIL, customError);

    }


    @SneakyThrows
    @Test
    public void invalidEmailNoDomainIntegrationTest(){

        UserDTO baseUserDTO = baseUser(FilePaths.USER_JSON, objectMapper);
        baseUserDTO.setEmail("jhon.doe@");
        String body = objectMapper.writeValueAsString(baseUserDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyUserError(ErrorCode.CODE_U03_INVALID_EMAIL, customError);

    }

    @SneakyThrows
    @Test
    public void impossibleRegistrationDateTest(){

        UserDTO baseUserDTO = baseUser(FilePaths.USER_JSON, objectMapper);
        baseUserDTO.setRegistrationDate(LocalDateTime.now().plusDays(1).toString());
        String body = objectMapper.writeValueAsString(baseUserDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyUserError(ErrorCode.CODE_U01_IMPOSSIBLE_DATE, customError);

    }

    @SneakyThrows
    @Test
    public void wrongRegistrationDateIntegrationTest(){

        UserDTO baseUserDTO = baseUser(FilePaths.USER_JSON, objectMapper);
        baseUserDTO.setRegistrationDate("Tuesday, January 10, 2023");
        String body = objectMapper.writeValueAsString(baseUserDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyUserError(ErrorCode.CODE_U02_WRONG_DATE_FORMAT, customError);

    }

    @SneakyThrows
    @Test
    public void impossibleTAndCAcceptanceIntegrationTest(){

        UserDTO baseUserDTO = baseUser(FilePaths.USER_JSON, objectMapper);
        baseUserDTO.getTermsAndConditionsHistory().get(0).setAcceptanceDate(LocalDateTime.now().plusDays(1).toString());
        String body = objectMapper.writeValueAsString(baseUserDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyUserError(ErrorCode.CODE_U01_IMPOSSIBLE_DATE, customError);
    }

    @SneakyThrows
    @Test
    public void wrongTAndCAcceptanceFormatIntegrationTest(){

        UserDTO baseUserDTO = baseUser(FilePaths.USER_JSON, objectMapper);
        baseUserDTO.getTermsAndConditionsHistory().get(0).setAcceptanceDate("Tuesday, January 10, 2023");
        String body = objectMapper.writeValueAsString(baseUserDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyUserError(ErrorCode.CODE_U02_WRONG_DATE_FORMAT, customError);
    }

    /*
     * SERVICE VALIDATIONS
     */
    @SneakyThrows
    @Test
    public void duplicatedEmailIntegrationTest(){

        UserDTO baseUserDTO = baseUser(FilePaths.USER_JSON, objectMapper);
        baseUserDTO.setEmail("jhon.doe1@email.com");
        String body = objectMapper.writeValueAsString(baseUserDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyUserError(ErrorCode.CODE_U04_DUPLICATED_EMAIL, customError);

    }

}
