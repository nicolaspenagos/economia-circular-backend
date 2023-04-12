package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.model.Activity.Activity;
import com.icesi.economiacircularicesi.model.Principle.Principle;
import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Report.Report;
import com.icesi.economiacircularicesi.model.Report.Score;
import com.icesi.economiacircularicesi.model.Response.Response;
import com.icesi.economiacircularicesi.model.User.User;
import com.icesi.economiacircularicesi.service.*;
import com.icesi.economiacircularicesi.utils.ErrorExceptionUtils;
import com.icesi.economiacircularicesi.utils.ReportLogic;
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
    private PrincipleService principleService;

    @Autowired
    private ReportLogic reportLogic;

    @Override
    public Report getUserReport(UUID userId, UUID responseId) {

        // Making sure that both user and response are not null
        User user = Optional.ofNullable(userService.getUser(userId)).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND, new CustomError(ErrorCode.CODE_U05_USER_NOT_FOUND, ErrorCode.CODE_U05_USER_NOT_FOUND.getMessage())));

        Response response = Optional.ofNullable(responseService.getResponse(responseId)).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, new CustomError(ErrorCode.CODE_R02_RESPONSE_NOT_FOUND, ErrorCode.CODE_R02_RESPONSE_NOT_FOUND.getMessage())));

        if(!userId.equals(response.getUserId())){
            ErrorExceptionUtils.throwCustomException(HttpStatus.UNAUTHORIZED, ErrorCode.CODE_A04_UNAUTHORIZED);
        }

        List<Activity> activities = activityService.getActivities();
        List<Question> questions = questionService.getQuestions();
        List<Principle> principles = principleService.getPrinciples();

        List<Score> reportByActivities = reportLogic.getActivitiesScore(activities, response, questions);
        List<Score> reportByPrinciples = reportLogic.getPrinciplesScore(principles, activities, reportByActivities);

        // Building the report
        Report report = new Report();
        report.setDate(LocalDateTime.now());
        report.setReportByActivities(reportByActivities);
        report.setReportByPrinciples(reportByPrinciples);

        return report;
    }











}
