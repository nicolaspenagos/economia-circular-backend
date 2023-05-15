package com.icesi.economiacircularicesi.test.controller;


import com.icesi.economiacircularicesi.controller.ResponseController;
import com.icesi.economiacircularicesi.mapper.ResponseMapperImpl;
import com.icesi.economiacircularicesi.service.ResponseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReponseControllerTest {

    private ResponseController responseController;
    private ResponseService responseService;

    @BeforeEach
    void init(){
        responseService = mock(ResponseService.class);
        responseController = new ResponseController(new ResponseMapperImpl(), responseService);
    }

    @Test
    void createResponseTest(){
        responseController.createResponse(any());
        verify(responseService, times(1)).createResponse(any());
    }

    @Test
    void updateResponseTest(){
        responseController.updateResponse(any(), any());
        verify(responseService, times(1)).updateResponse(any(), any());
    }

    @Test
    void getUserResponsesTest(){
        responseController.getUserResponses(any());
        verify(responseService, times(1)).getUserResponses(any());
    }

    @Test
    void getActiveUserResponse(){
        responseController.getActiveUserResponses(any());
        verify(responseService, times(1)).getUserActiveResponses(any());
    }

    @Test
    void getResponse(){
        responseController.getResponse(any());
        verify(responseService, times(1)).getResponse(any());
    }

}
