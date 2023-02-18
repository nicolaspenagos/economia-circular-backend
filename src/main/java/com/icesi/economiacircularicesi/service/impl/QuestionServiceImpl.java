package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.constant.ErrorCode;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomError;
import com.icesi.economiacircularicesi.error.exception.CustomError.CustomException;
import com.icesi.economiacircularicesi.mapper.QuestionMapper;
import com.icesi.economiacircularicesi.model.Question.Question;
import com.icesi.economiacircularicesi.model.Question.QuestionOption;
import com.icesi.economiacircularicesi.repository.QuestionRepository.QuestionOptionRepository;
import com.icesi.economiacircularicesi.repository.QuestionRepository.QuestionRepository;
import com.icesi.economiacircularicesi.service.QuestionService;
import com.icesi.economiacircularicesi.utils.ErrorExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private QuestionOptionRepository questionOptionRepository;
    private QuestionMapper questionMapper;

    @Override
    public Question createQuestion(Question question) {

        Question savedQuestion = questionRepository.save(question);
        saveQuestionOptions(savedQuestion.getQuestionId(), question.getQuestionOptions());
        return savedQuestion;
    }

    @Override
    public List<Question> getQuestions() {

        return StreamSupport.stream(questionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Question getQuestion(UUID questionId){
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public Question updateQuestion(UUID questionId, Question questionUpdate) {

        Question question = questionRepository.findById(questionId).orElseThrow(()->new CustomException(HttpStatus.BAD_REQUEST, new CustomError(ErrorCode.CODE_Q01_QUESTION_NOT_FOUND, ErrorCode.CODE_Q01_QUESTION_NOT_FOUND.getMessage())));

        questionMapper.updateQuestionFromQuestion(questionUpdate, question);
        saveQuestionOptions(questionId, question.getQuestionOptions());

        return questionRepository.save(question);
    }

    @Override
    public ResponseEntity<UUID> deleteQuestion(UUID questionId) {

        Optional.ofNullable(getQuestion(questionId))
                .ifPresentOrElse(
                        (question) -> deleteQuestionAndRelations(question),
                        () -> {
                            ErrorExceptionUtils.throwCustomException(HttpStatus.BAD_REQUEST, ErrorCode.CODE_Q01_QUESTION_NOT_FOUND);
                        }
                );
        return new ResponseEntity<>(questionId, HttpStatus.OK);
    }


    private void deleteQuestionAndRelations(Question question) {

        for (QuestionOption currentOptn : question.getQuestionOptions()) {
            questionOptionRepository.delete(currentOptn);
        }
        questionRepository.delete(question);

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
