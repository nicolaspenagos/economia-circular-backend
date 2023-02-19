package com.icesi.economiacircularicesi.repository.ActivityRepository;

import com.icesi.economiacircularicesi.model.Activity.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ActivityRepository extends CrudRepository<Activity, UUID> {
}
