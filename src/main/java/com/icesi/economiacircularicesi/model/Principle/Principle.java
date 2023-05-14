package com.icesi.economiacircularicesi.model.Principle;

import com.icesi.economiacircularicesi.model.Activity.Activity;
import com.icesi.economiacircularicesi.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Table(name = "`principles`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Principle extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private double score;

    @ManyToMany
    @JoinTable(
            name = "principle_activity",
            joinColumns = @JoinColumn(name = "principle_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private Set<Activity> activitySet;

    public Principle(UUID id, String description, String title, String name, double score, Set<Activity> activitySet) {
        super.setId(id);
        this.description = description;
        this.title = title;
        this.name = name;
        this.score = score;
        this.activitySet = activitySet;
    }

    @PrePersist
    public void generateId() {super.setId(UUID.randomUUID());}

}
