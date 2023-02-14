package com.icesi.economiacircularicesi.model.Question;

import com.icesi.economiacircularicesi.model.User.TermsAndConditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "`questions`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "question_order")
    private int questionOrder;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "is_mandatory")
    private boolean isMandatory;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column(name = "activity")
    private String activity;

    @OneToMany(mappedBy = "question", targetEntity = QuestionOption.class)
    private List<QuestionOption> questionOptions;

    @PrePersist
    public void generateId() {
        this.questionId = UUID.randomUUID();
    }

}


