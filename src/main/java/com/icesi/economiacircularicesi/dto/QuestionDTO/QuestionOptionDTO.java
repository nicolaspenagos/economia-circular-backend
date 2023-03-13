package com.icesi.economiacircularicesi.dto.QuestionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionOptionDTO {

    private UUID questionOptionId;

    @NotNull
    @Min(value = 0, message = "Order must non negative integers")
    private int optionOrder;

    @NotBlank(message = "Option value may not be blank")
    private String optionValue;

    @NotNull(message = "Exclusive must not be null")
    private boolean exclusive;

    @NotNull(message = "Not apply must not be null")
    private boolean notApply;

    private UUID dependentQuestionId;

}
