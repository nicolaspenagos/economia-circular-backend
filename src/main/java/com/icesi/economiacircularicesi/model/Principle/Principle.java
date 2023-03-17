package com.icesi.economiacircularicesi.model.Principle;

import com.icesi.economiacircularicesi.model.Activity.Activity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Table(name = "`principles`")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Principle {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "principle_id")
    private UUID principleId;
    //TODO Extends BaseEntity
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


}
