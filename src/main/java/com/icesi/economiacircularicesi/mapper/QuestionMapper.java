package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.QuestionDTO.QuestionDTO;
import com.icesi.economiacircularicesi.model.Question.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question fromDTO(QuestionDTO questionDTO);

    QuestionDTO fromQuestion(Question question);

}
