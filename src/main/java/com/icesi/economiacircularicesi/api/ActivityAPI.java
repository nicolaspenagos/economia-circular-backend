package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.ActivityDTO.ActivityDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping("activities")
public interface ActivityAPI {

    @GetMapping
    List<ActivityDTO> getActivities();

    @GetMapping("/{activityId}")
    ActivityDTO getActivity(@PathVariable UUID activityId);


}
