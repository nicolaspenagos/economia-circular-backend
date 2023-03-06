package com.icesi.economiacircularicesi.test.service;

import com.icesi.economiacircularicesi.service.*;
import com.icesi.economiacircularicesi.service.impl.ReportServiceImpl;
import com.icesi.economiacircularicesi.utils.ReportServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ReportServiceTest {

    private ReportService reportService;
    private UserService userService;
    private ResponseService responseService;
    private QuestionService questionService;
    private ActivityService activityService;
    private ReportServiceUtils reportServiceUtil;


    @BeforeEach
    private void init(){

        userService = mock(UserService.class);
        responseService = mock(ResponseService.class);
        questionService = mock(QuestionService.class);
        activityService = mock(ActivityService.class);
        reportServiceUtil = mock(ReportServiceUtils.class);
        reportService = new ReportServiceImpl(userService, responseService, questionService, activityService, reportServiceUtil);

    }

    @Test
    public void ScoreMultipleChoiceTest(){
    }
}

/*
  public double scoreMultipleChoice(double questionScore, List<QuestionOption> questionOptions){

        double scorePerOption = questionScore/questionOptions.size();
        int numSelectedOpts = questionOptions.size();
        return scorePerOption*numSelectedOpts;

    }
 */
