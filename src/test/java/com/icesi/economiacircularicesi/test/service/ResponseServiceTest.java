package com.icesi.economiacircularicesi.test.service;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomException;
import com.icesi.economiacircularicesi.mapper.ResponseMapper;
import com.icesi.economiacircularicesi.mapper.ResponseMapperImpl;
import com.icesi.economiacircularicesi.model.response.Response;
import com.icesi.economiacircularicesi.model.response.ResponseJustify;
import com.icesi.economiacircularicesi.model.response.ResponseOption;
import com.icesi.economiacircularicesi.model.user.User;
import com.icesi.economiacircularicesi.repository.response_repository.ResponseJustifyRepository;
import com.icesi.economiacircularicesi.repository.response_repository.ResponseOptionRepository;
import com.icesi.economiacircularicesi.repository.response_repository.ResponseRepository;
import com.icesi.economiacircularicesi.security.SecurityContextHolder;
import com.icesi.economiacircularicesi.service.ResponseService;
import com.icesi.economiacircularicesi.service.impl.ResponseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ResponseServiceTest {

    private ResponseRepository responseRepository;
    private ResponseOptionRepository responseOptionRepository;
    private ResponseJustifyRepository responseJustifyRepository;
    private ResponseMapper responseMapper;
    private ResponseService responseService;
    private static final UUID ID = UUID.randomUUID();

    private Response response;
    private User user;
    private List<ResponseOption> selectedOptions;

    private List<ResponseJustify> responseJustifies;


    private void setupScenery() {
        user = new User(
                "user@email.com",
                "$2a$10$IoVx1iFkIDQ2VFL7E2ey3e/pgyEA8NoGimS0vsFMm0ZjNXdvkxePW",
                "Jhon",
                "Teacher",
                "Education",
                "Services",
                "Icesi",
                LocalDateTime.now(),
                null
        );

        user.setId(ID);
        selectedOptions = new ArrayList<>();

        // 3 selected options
        selectedOptions.add(new ResponseOption(UUID.randomUUID(), UUID.randomUUID(),UUID.randomUUID(), null));
        selectedOptions.add(new ResponseOption(UUID.randomUUID(), UUID.randomUUID(),UUID.randomUUID(), null));
        selectedOptions.add(new ResponseOption(UUID.randomUUID(), UUID.randomUUID(),UUID.randomUUID(), null));

        responseJustifies = new ArrayList<>();
        responseJustifies.add(new ResponseJustify(UUID.randomUUID(), UUID.randomUUID(), "Justify"));

        response = new Response(null, ID, false,  selectedOptions,responseJustifies );
        response.setId(UUID.randomUUID());
    }

    @BeforeEach
    void init() {

        responseRepository = mock(ResponseRepository.class);
        responseOptionRepository = mock(ResponseOptionRepository.class);
        responseJustifyRepository = mock(ResponseJustifyRepository.class);
        responseMapper = mock(ResponseMapper.class);
        responseService = new ResponseServiceImpl(responseRepository, responseOptionRepository, responseJustifyRepository, responseMapper);
        SecurityContextHolder.getContext().setUserId(ID);
    }

    @Test
    void createResponseTest() {
        setupScenery();
        when(responseRepository.save(response)).thenReturn(response);
        responseService.createResponse(response);
        verify(responseRepository, times(1)).save(response);
    }

    @Test
    void updateResponseTest(){
        setupScenery();
        when(responseRepository.findById(response.getId())).thenReturn(Optional.of(response));
        responseService.updateResponse(response.getId(), response);
        verify(responseMapper, times(1)).updateResponseFromResponse(any(), any());
        verify(responseOptionRepository, times(3)).save(any());
        verify(responseJustifyRepository, times(1)).save(any());
        verify(responseRepository, times(1)).save(response);
    }

    @Test
    void updateUnauthorizedResponseTest(){
        setupScenery();
        response.setUserId(UUID.randomUUID());
        when(responseRepository.findById(response.getId())).thenReturn(Optional.of(response));
        try{
            responseService.updateResponse(response.getId(), response);
        }catch (CustomException exception){
            assertEquals(HttpStatus.UNAUTHORIZED, exception.getHttpStatus());
            assertNotNull(exception.getError());
            assertEquals(ErrorCode.CODE_A04_UNAUTHORIZED.getMessage(), exception.getError().getMessage());
            assertEquals(ErrorCode.CODE_A04_UNAUTHORIZED, exception.getError().getCode());
        }
    }

    @Test
    void getUserResponsesTest(){

        responseService.getUserResponses(ID);
        verify(responseRepository, times(1)).findByUserResponses(ID);
    }

    @Test
    void getUserActiveResponsesTest(){
        responseService.getUserActiveResponses(ID);
        verify(responseRepository, times(1)).findByUserActiveResponses(ID);
    }

    @Test
    void getResponseTest(){
        responseService.getResponse(ID);
        verify(responseRepository, times(1)).findById(ID);
    }
}
