package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.model.Activity.Activity;
import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Report.Report;
import com.icesi.economiacircularicesi.model.Report.Score;
import com.icesi.economiacircularicesi.model.Response.Response;
import com.icesi.economiacircularicesi.model.User.User;
import com.icesi.economiacircularicesi.service.*;
import com.icesi.economiacircularicesi.utils.ReportServiceUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private UserService userService;
    private ResponseService responseService;
    private QuestionService questionService;
    private ActivityService activityService;

    @Autowired
    private ReportServiceUtils reportServiceUtils;

    @Override
    public Report getUserReport(UUID userId, UUID responseId) {

        // Making sure that both user and response are not null
        User user = Optional.ofNullable(userService.getUser(userId)).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND, new CustomError(ErrorCode.CODE_U05_USER_NOT_FOUND, ErrorCode.CODE_U05_USER_NOT_FOUND.getMessage())));

        Response response = Optional.ofNullable(responseService.getResponse(responseId)).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, new CustomError(ErrorCode.CODE_R01_RESPONSE_NOT_FOUND, ErrorCode.CODE_R01_RESPONSE_NOT_FOUND.getMessage())));

        List<Activity> activities = activityService.getActivities();
        List<Question> questions = questionService.getQuestions();

        List<Score> reportByActivities = reportServiceUtils.getActivitiesScore(activityService.getActivities(), response, questionService.getQuestions());

        // Building the report
        Report report = new Report();
        report.setDate(LocalDateTime.now());
        report.setReportByActivities(reportByActivities);

        return report;
    }











}
