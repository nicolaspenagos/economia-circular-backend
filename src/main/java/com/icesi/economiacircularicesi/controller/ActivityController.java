package com.icesi.economiacircularicesi.controller;

import com.icesi.economiacircularicesi.api.ActivityAPI;
import com.icesi.economiacircularicesi.dto.ActivityDTO.ActivityDTO;
import com.icesi.economiacircularicesi.mapper.ActivityMapper;
import com.icesi.economiacircularicesi.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ActivityController implements ActivityAPI {

    public ActivityService activityService;
    public ActivityMapper activityMapper;


    @Override
    public List<ActivityDTO> getActivities() {
        return activityService.getActivities().stream().map(activityMapper::fromActivity).collect(Collectors.toList());
    }

    @Override
    public ActivityDTO getActivity(UUID activityId) {
        return activityMapper.fromActivity(activityService.getActivity(activityId));
    }
}
