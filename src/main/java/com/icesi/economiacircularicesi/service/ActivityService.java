package com.icesi.economiacircularicesi.service;

import com.icesi.economiacircularicesi.model.activity.Activity;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    List<Activity> getActivities();

    Activity getActivity(UUID activityId);

}
