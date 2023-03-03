package com.icesi.economiacircularicesi.model.Question;

import com.icesi.economiacircularicesi.model.User.User;
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
public class QuestionOption {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "question_option_id")
    private UUID questionOptionId;
    //TODO Extends BaseEntity
    @Column(name = "option_order")
    private int optionOrder;
    @Column(name="option_value")
    private String optionValue;

    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    private Question question;

    @PrePersist
    public void generateId() {
       this.questionOptionId = UUID.randomUUID();
    }


}

