package com.icesi.economiacircularicesi.service.impl;

import com.icesi.economiacircularicesi.model.Activity.Activity;
import com.icesi.economiacircularicesi.repository.ActivityRepository.ActivityRepository;
import com.icesi.economiacircularicesi.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class ActivityServiceImpl implements ActivityService {

    private ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivities() {
        return StreamSupport.stream(activityRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Activity getActivity(UUID activityId) {
        return activityRepository.findById(activityId).orElse(null);
    }

}
