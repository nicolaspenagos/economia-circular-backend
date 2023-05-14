package com.icesi.economiacircularicesi.test.service;

import com.icesi.economiacircularicesi.service.*;
import com.icesi.economiacircularicesi.service.impl.ReportServiceImpl;
import com.icesi.economiacircularicesi.utils.ReportLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class ReportServiceTest {

    private ReportService reportService;
    private UserService userService;
    private ResponseService responseService;
    private QuestionService questionService;
    private ActivityService activityService;
    private ReportLogic reportServiceUtil;
    private PrincipleService principleService;


    @BeforeEach
    private void init(){

        userService = mock(UserService.class);
        responseService = mock(ResponseService.class);
        questionService = mock(QuestionService.class);
        activityService = mock(ActivityService.class);
        reportServiceUtil = mock(ReportLogic.class);
        principleService = mock(PrincipleService.class);
        reportService = new ReportServiceImpl(userService, responseService, questionService, activityService,principleService,reportServiceUtil);

    }


}

