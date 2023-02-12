package com.icesi.economiacircularicesi.model.Question;

import com.icesi.economiacircularicesi.model.User.TermsAndConditions;
import com.icesi.economiacircularicesi.model.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "`question_types`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionType {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "question_type_id")
    private UUID questionTypeId;

    @Column(name="question_type")
    private String questionType;

    @ManyToOne(targetEntity = QuestionType.class)
    @JoinColumn(name="question_id")
    private Question question;

    @PrePersist
    public void generateId() {
       this.questionTypeId = UUID.randomUUID();
    }

}
