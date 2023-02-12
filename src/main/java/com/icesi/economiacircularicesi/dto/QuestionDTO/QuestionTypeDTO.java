package com.icesi.economiacircularicesi.dto.QuestionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTypeDTO {

    private UUID questionOptionId;

    @NotBlank(message = "Question type text may not be blank")
    private String questionType;

}
