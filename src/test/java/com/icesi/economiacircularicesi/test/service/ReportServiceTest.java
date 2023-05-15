package com.icesi.economiacircularicesi.test.service;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.custom_error.CustomException;
import com.icesi.economiacircularicesi.model.response.Response;
import com.icesi.economiacircularicesi.model.user.User;
import com.icesi.economiacircularicesi.service.*;
import com.icesi.economiacircularicesi.service.impl.ReportServiceImpl;
import com.icesi.economiacircularicesi.utils.ReportLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportServiceTest {

    private ReportService reportService;
    private UserService userService;
    private ResponseService responseService;
    private QuestionService questionService;
    private ActivityService activityService;
    private ReportLogic reportServiceUtil;
    private PrincipleService principleService;

    private User user;
    private Response response;
    private void setupScenery(){
        user = new User(
                "user@email.com",
                "$2a$10$IoVx1iFkIDQ2VFL7E2ey3e/pgyEA8NoGimS0vsFMm0ZjNXdvkxePW",
                "Jhon",
                "Teacher",
                "Education",
                "Services",
                "Icesi",
                null,
                null
        );
        user.setId(UUID.randomUUID());

        response = new Response(null, null, user.getId(),true, new ArrayList<>(), new ArrayList<>());

        response.setId(UUID.randomUUID());
    }

    @BeforeEach
    void init(){

        userService = mock(UserService.class);
        responseService = mock(ResponseService.class);
        questionService = mock(QuestionService.class);
        activityService = mock(ActivityService.class);
        reportServiceUtil = mock(ReportLogic.class);
        principleService = mock(PrincipleService.class);
        reportService = new ReportServiceImpl(userService, responseService, questionService, activityService,principleService,reportServiceUtil);

    }

    @Test
    void getUnhatorizedReportTest(){

        setupScenery();
        when(userService.getUser(any())).thenReturn(user);
        when(responseService.getResponse(any())).thenReturn(response);
        when(activityService.getActivities()).thenReturn(new ArrayList<>());
        when(principleService.getPrinciples())
                .thenReturn(new ArrayList<>());
        when(questionService.getQuestions()).thenReturn(new ArrayList<>());

        response.setUserId(UUID.randomUUID());


        try{
            reportService.getUserReport(user.getId(), response.getId());
        }catch (CustomException exception){
            assertEquals(HttpStatus.UNAUTHORIZED, exception.getHttpStatus());
            assertNotNull(exception.getError());
            assertEquals(ErrorCode.CODE_A04_UNAUTHORIZED.getMessage(), exception.getError().getMessage());
            assertEquals(ErrorCode.CODE_A04_UNAUTHORIZED, exception.getError().getCode());
        }
    }

    @Test
    void getReportTest(){

        setupScenery();
        when(userService.getUser(any())).thenReturn(user);
        when(responseService.getResponse(any())).thenReturn(response);
        when(activityService.getActivities()).thenReturn(new ArrayList<>());
        when(principleService.getPrinciples())
                .thenReturn(new ArrayList<>());
        when(questionService.getQuestions()).thenReturn(new ArrayList<>());

        assertNotNull(reportService.getUserReport(user.getId(), response.getId()));

    }

}

