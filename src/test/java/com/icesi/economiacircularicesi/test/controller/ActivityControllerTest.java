package com.icesi.economiacircularicesi.test.controller;

import com.icesi.economiacircularicesi.controller.ActivityController;
import com.icesi.economiacircularicesi.mapper.ActivityMapper;
import com.icesi.economiacircularicesi.mapper.ActivityMapperImpl;
import com.icesi.economiacircularicesi.mapper.QuestionMapperImpl;
import com.icesi.economiacircularicesi.service.ActivityService;
import com.icesi.economiacircularicesi.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class ActivityControllerTest {
    private ActivityController activityController;
    private ActivityMapper activityMapper;

    private ActivityService activityService;

    @BeforeEach
    public void init(){
        activityMapper = new ActivityMapperImpl();
        activityService = mock(ActivityService.class);
        activityController = new ActivityController(activityService, activityMapper);
    }

    @Test
    public void getActivitiesTest(){
        activityController.getActivities();
        verify(activityService, times(1)).getActivities();
    };

    @Test
    public void getActivityTest(){
        final UUID uuid = UUID.randomUUID();
        activityController.getActivity(uuid);
        verify(activityService, times(1)).getActivity(uuid);
    }
}
