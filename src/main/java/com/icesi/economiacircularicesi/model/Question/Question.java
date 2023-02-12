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

    /*
    @OneToOne(mappedBy = "question", targetEntity = QuestionType.class, cascade=CascadeType.ALL)
    private QuestionType type;*/

    @OneToMany(mappedBy = "question", targetEntity = QuestionOption.class)
    private List<QuestionOption> questionOptions;

    @PrePersist
    public void generateId() {
        this.questionId = UUID.randomUUID();
    }


/*

{

    "questionText":"Mi first question",
    "isMandatory":true,
    "order":1,
    "type":
        {
            "questionType":"MULTIPLE_CHOICE"
        },
    "options":[
        {
            "order":1,
            "value":"option1"
        },
        {
            "order":2,
            "value":"option2"
        }

    ]
}

    */


}


