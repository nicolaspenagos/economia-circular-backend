package com.icesi.economiacircularicesi.dto.QuestionDTO;

import com.icesi.economiacircularicesi.model.Question.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private UUID questionId;

    @NotNull
    @Min(value = 1, message = "Order must be positive integers")
    private int questionOrder;

    @NotBlank(message = "Question text may not be blank")
    private String questionText;

    @NotNull(message = "mandatory must not be null")
    private boolean mandatory;

    @NotNull(message = "Type must not be null")
    private QuestionType type;

    @NotBlank(message = "Activity may not be blank")
    private String activity;

    @Valid
    @NotNull
    @NotEmpty(message = "Options may not be empty")
    private List<QuestionOptionDTO> questionOptions;

}
