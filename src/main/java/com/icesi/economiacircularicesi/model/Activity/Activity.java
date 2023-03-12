package com.icesi.economiacircularicesi.model.Activity;

import com.icesi.economiacircularicesi.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "`activities`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private double score;

    @Column(name = "contains_dependent_score_questions")
    private boolean containsDependentScoreQuestions;

    public Activity( UUID id, String description, String title, String name, double score, boolean containsDependentScoreQuestions) {
        super.setId(id);
        this.description = description;
        this.title = title;
        this.name = name;
        this.score = score;
        this.containsDependentScoreQuestions = containsDependentScoreQuestions;
    }

    @PrePersist
    public void generateId() {
        super.setId(UUID.randomUUID());
    }

}
