package com.icesi.economiacircularicesi.dto.principle;

import com.icesi.economiacircularicesi.model.activity.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrincipleDTO {

    private UUID id;

    private String description;

    private String title;

    private String name;

    private double score;

    private Set<Activity> activitySet;


}
