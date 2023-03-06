package com.icesi.economiacircularicesi.dto.QuestionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionOptionDTO {

    private UUID questionOptionId;

    @NotNull
    @Min(value = 1, message = "Order must be positive integers")
    private int optionOrder;

    @NotBlank(message = "Option value may not be blank")
    private String optionValue;

    private UUID dependentQuestionId;

}
