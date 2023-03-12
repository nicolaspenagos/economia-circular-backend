package com.icesi.economiacircularicesi.utils;

import com.icesi.economiacircularicesi.model.Activity.Activity;
import com.icesi.economiacircularicesi.model.BaseEntity;
import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.model.Question.QuestionType;
import com.icesi.economiacircularicesi.model.Report.Score;
import com.icesi.economiacircularicesi.model.Response.Response;
import com.icesi.economiacircularicesi.model.Response.ResponseOption;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ReportServiceUtils {

    public static <T extends BaseEntity> int  getIndexOf(List<T> list, UUID id){

        for(int i=0; i<list.size(); i++){
            if(list.get(i).getId().equals(id)){
                return i;
            }
        }

        return -1;
    }

    public Score rateActivity(Activity activity, Map<UUID, List<ResponseOption>> responseOptionsByQuestions, List<Question> questions){

       // Score score = new Score(activity.getName(),activity.getTitle(), activity.getScore())
        List<Question> activityQuestions = questions.stream().filter(q->q.getActivityId().equals(activity.getId())).collect(Collectors.toList());

        Double activityObtainedScore = 0.0;


        for(Question currentQuestion: activityQuestions){

            Double questionTotalScore = activity.getScore()/activityQuestions.size();

            System.out.println(currentQuestion.getActivityId());
            activityObtainedScore += scoreQuestion(currentQuestion, responseOptionsByQuestions.get(currentQuestion.getId()), questionTotalScore);

        }

        return new Score(activity.getName(), activity.getTitle(), activity.getScore(), activityObtainedScore, activityObtainedScore/activity.getScore()*100.0);

    }

    public List<Score> getActivitiesScore(List<Activity> activities, Response response, List<Question> questions){

        Map<UUID, Map<UUID, List<ResponseOption>>> optionsByActivityAndQuestion = getResponseOptionsMappedByActivityAndQuestion(response.getSelectedOptions(), activities, questions);

        List<Score> activitiesScore = new ArrayList<>();

        for(Activity currentActivity:activities){
            activitiesScore.add(rateActivity(currentActivity, optionsByActivityAndQuestion.get(currentActivity.getId()), questions));
        }

        return activitiesScore;
    }

    public  Map<UUID, Map<UUID, List<ResponseOption>>> getResponseOptionsMappedByActivityAndQuestion(List<ResponseOption> selectedOptions, List<Activity> activities, List<Question> questions){

        Map<UUID, Map<UUID, List<ResponseOption>>> responseOptsByActivityMap = new HashMap<>();

        // Setting all the keys
        for(Activity activity : activities){
            responseOptsByActivityMap.put(activity.getId(), new HashMap<>());
        }

        // Classifying the options by activities
        for(ResponseOption currentOpt : selectedOptions){

            UUID activityId = questions.get(getIndexOf(questions, currentOpt.getQuestionIdReference())).getActivityId();

            UUID questionId = currentOpt.getQuestionIdReference();

            Map<UUID, List<ResponseOption>>  responseOptsByQuestionMap = responseOptsByActivityMap.get(activityId);

            // Classifying the options by questions
            responseOptsByQuestionMap.computeIfAbsent(questionId, k->new ArrayList<>()).add(currentOpt);

        }

        return responseOptsByActivityMap;
    }

    public double scoreQuestion(Question question, List<ResponseOption> selectedOptions, Double questionScore){

        QuestionType questionType = question.getType();
        List<QuestionOption> questionOptions = question.getQuestionOptions();

        if(questionType.equals(QuestionType.MULTIPLE_CHOICE)){
            return scoreMultipleChoice(questionScore, questionOptions, selectedOptions);
        }

        if(questionType.equals(QuestionType.INCREMENTAL_SINGLE_CHOICE)){
            return scoreIncrementalSingleChoice(questionScore, questionOptions, selectedOptions.get(0));
        }

        if(questionType.equals(QuestionType.SINGLE_CHOICE)){
            System.out.println("-----------------------");
            System.out.println(selectedOptions);
            return scoreSingleChoice(questionScore, questionOptions, selectedOptions.get(0));
        }

        return 0.0;

    }

    public double scoreIncrementalSingleChoice(double questionScore, List<QuestionOption> questionOptions, ResponseOption selectedOption){

        // Due to the first option scores zero points we subtract one from the total options to calculate the incremental score of the rest of the options
        double scorePerOption = questionScore/(questionOptions.size()-1);

        QuestionOption selectedOpt = questionOptions.get(ReportServiceUtils.getIndexOf(questionOptions, selectedOption.getOptionIdReference()));

        return scorePerOption*selectedOpt.getOptionOrder();

    }

    public double scoreMultipleChoice(double questionScore, List<QuestionOption> questionOptions,List<ResponseOption> selectedOptions ){

        int numberOfExclusive = 0;

        for(QuestionOption questionOption : questionOptions){
            if(questionOption.isExclusive()){
                numberOfExclusive++;
            }
        }

        for(ResponseOption selectedOption: selectedOptions ){

            QuestionOption questionOpt = questionOptions.get(ReportServiceUtils.getIndexOf(questionOptions, selectedOption.getOptionIdReference()));

            if(questionOpt.isExclusive())
                return 0.0;
        }

        double scorePerOption = questionScore/(questionOptions.size()-numberOfExclusive);
        int numSelectedOpts = selectedOptions.size();
        return scorePerOption*numSelectedOpts;

    }

    public double scoreSingleChoice(double questionScore, List<QuestionOption> questionOptions, ResponseOption selectedOption){

        QuestionOption selectedOpt = questionOptions.get(ReportServiceUtils.getIndexOf(questionOptions, selectedOption.getOptionIdReference()));

        if(selectedOpt.isExclusive()){
            return 0.0;
        }

        return questionScore;
    }


}


