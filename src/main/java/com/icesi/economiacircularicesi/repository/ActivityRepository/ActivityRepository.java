package com.icesi.economiacircularicesi.repository.ActivityRepository;

import com.icesi.economiacircularicesi.model.activity.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository extends CrudRepository<Activity, UUID> {

    @Query(value = "SELECT * FROM activities WHERE activities.name = ?1", nativeQuery = true)
    Optional<Activity> findByName(String name);

}
