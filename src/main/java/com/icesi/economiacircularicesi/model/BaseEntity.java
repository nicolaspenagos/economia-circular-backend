package com.icesi.economiacircularicesi.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "id")
    private UUID id;

    public UUID getId(){
        return this.id;
    }
    public void setId(UUID id){
        this.id = id;
    }

}
