package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.repository.QuestionRepository.QuestionOptionRepository;
import com.icesi.economiacircularicesi.repository.QuestionRepository.QuestionRepository;
import com.icesi.economiacircularicesi.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    private QuestionOptionRepository questionOptionRepository;

    @Override
    public Question createQuestion(Question question) {

        Question savedQuestion = questionRepository.save(question);
        saveQuestionOptions(savedQuestion.getQuestionId(), question.getQuestionOptions());
        return savedQuestion;
    }

    @Override
    public List<Question> getUsers() {

        return StreamSupport.stream(questionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    private void saveQuestionOptions(UUID questionId, List<QuestionOption> questionOptions){
        for(QuestionOption currentOpt:questionOptions){

            Question questionRef = new Question();
            questionRef.setQuestionId(questionId);
            currentOpt.setQuestion(questionRef);

            questionOptionRepository.save(currentOpt);
        }
    }

}
