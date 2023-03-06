package com.icesi.economiacircularicesi.test.integration.UserAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.mapper.UserMapper;
import com.icesi.economiacircularicesi.mapper.UserMapperImpl;
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

import static com.icesi.economiacircularicesi.utils.TestUtils.verifyError;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@SpringBootTest
public class DeleteUserIntegrationTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

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
    public void deleteUserIntegrationTest(){

        final String userId = "05e16595-98c5-46c3-80cb-209915e52588";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/users/"+userId).contentType(MediaType.APPLICATION_JSON).content("")).andExpect(status().isOk()).andReturn();

        assertEquals("\""+userId+"\"", result.getResponse().getContentAsString());
    }

    @SneakyThrows
    @Test
    public void deleteNonExistentUserIntegrationTest(){

        final String userId = UUID.randomUUID().toString();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/users/"+userId).contentType(MediaType.APPLICATION_JSON).content("")).andExpect(status().isBadRequest()).andReturn();

        CustomError customError = objectMapper.readValue(result.getResponse().getContentAsString(), CustomError.class);
        verifyError(ErrorCode.CODE_U05_USER_NOT_FOUND, customError);
    }


}
