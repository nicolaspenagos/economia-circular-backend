package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.question.QuestionDTO;
import com.icesi.economiacircularicesi.model.Question.Question;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question fromDTO(QuestionDTO questionDTO);

    QuestionDTO fromQuestion(Question question);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuestionFromQuestion(Question updatedUser, @MappingTarget Question user);  // The @MappingTarget annotation lets us update an existing object without writing a lot of code.

}
