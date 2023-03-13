package com.icesi.economiacircularicesi.model.Question;

import com.icesi.economiacircularicesi.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "`question_options`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOption extends BaseEntity {

    @Column(name = "option_order")
    private int optionOrder;
    @Column(name="option_value")
    private String optionValue;

    @Column(name="dependent_question_id")
    private UUID dependentQuestionId;

    @Column(name="exclusive")
    private boolean exclusive;

    @Column(name="not_apply")
    private boolean notApply;

    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    private Question question;

    public QuestionOption(UUID id, int optionOrder, String optionValue, Question question, boolean exclusive, boolean notApply) {
        super.setId(id);
        this.optionOrder = optionOrder;
        this.optionValue = optionValue;
        this.question = question;
        this.exclusive = exclusive;
        this.notApply = notApply;
    }

    @PrePersist
    public void generateId() {
        super.setId(UUID.randomUUID());
    }


}

