package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Report.Report;
import com.icesi.economiacircularicesi.model.Report.Score;
import com.icesi.economiacircularicesi.model.Response.Response;
import com.icesi.economiacircularicesi.model.User.User;
import com.icesi.economiacircularicesi.service.QuestionService;
import com.icesi.economiacircularicesi.service.ReportService;
import com.icesi.economiacircularicesi.service.ResponseService;
import com.icesi.economiacircularicesi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private UserService userService;
    private ResponseService responseService;

    private QuestionService questionService;

    @Override
    public Report getUserReport(UUID userId, UUID responseId) {

        // Making sure that both user and response are not null
        User user = Optional.ofNullable(userService.getUser(userId)).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND, new CustomError(ErrorCode.CODE_U05_USER_NOT_FOUND, ErrorCode.CODE_U05_USER_NOT_FOUND.getMessage())));
        Response response =Optional.ofNullable(responseService.getResponse(responseId)).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, new CustomError(ErrorCode.CODE_R01_RESPONSE_NOT_FOUND, ErrorCode.CODE_R01_RESPONSE_NOT_FOUND.getMessage())));


        System.out.println(user.getName()+" "+ response.getUserId());

        //--------------------------------
        Report report = new Report();
        report.setDate(LocalDateTime.now());

        List<Score> a = new ArrayList<>();
        a.add(new Score("A1", "A1 tajsdgas", 1000.0, 800.0, 0.6));
        a.add(new Score("A2", "A2 tajsdgas", 1000.0, 800.0, 0.6));

        List<Score> p = new ArrayList<>();
        p.add(new Score("P1", "P1 tajsdgas", 1000.0, 800.0, 0.6));

        report.setReportByActivities(a);
        report.setReportByPrinciples(p);

        return report;
    }

    private Score rateActivity(String activity){
        List<Question> activityQuestions = questionService.getQuestionsByActivity(activity);

        return null;
    }

}
