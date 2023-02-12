package com.icesi.economiacircularicesi.dto.QuestionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @NotNull
    private boolean isMandatory;


    /*
    @NotNull
    private QuestionTypeDTO type;
*/

    @NotNull
    @NotEmpty(message = "Options may not be empty")
    private List<QuestionOptionDTO> questionOptions;

}
