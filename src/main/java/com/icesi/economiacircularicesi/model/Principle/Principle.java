package com.icesi.economiacircularicesi.model.Principle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private double score;

}
