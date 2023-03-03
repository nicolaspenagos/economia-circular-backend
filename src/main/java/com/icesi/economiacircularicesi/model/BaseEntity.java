package com.icesi.economiacircularicesi.model;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    private UUID id;
}
