package com.icesi.economiacircularicesi.model.question;

import com.icesi.economiacircularicesi.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "`questions`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseEntity {


    @Column(name = "question_order")
    private int questionOrder;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "mandatory")
    private boolean mandatory;

    @Column(name = "justify")
    private boolean justify;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(name = "hint")
    private String hint;

    @Column(name = "activity_id")
    private UUID activityId;

    @OneToMany(mappedBy = "question", targetEntity = QuestionOption.class)
    private List<QuestionOption> questionOptions;
    public Question(UUID id, int questionOrder, String questionText, boolean mandatory, boolean justify, QuestionType type, String hint,UUID activityRef, List<QuestionOption> questionOptions) {

        super.setId(id);
        this.questionOrder = questionOrder;
        this.questionText = questionText;
        this.mandatory = mandatory;
        this.justify = justify;
        this.type = type;
        this.hint = hint;
        this.activityId = activityRef;
        this.questionOptions = questionOptions;

    }



    @PrePersist
    public void generateId() {
        super.setId(UUID.randomUUID());
    }

}


