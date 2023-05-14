package com.icesi.economiacircularicesi.test.service;

import com.icesi.economiacircularicesi.repository.activity_repository.ActivityRepository;
import com.icesi.economiacircularicesi.service.impl.ActivityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class ActivityServiceTest {

    private ActivityRepository activityRepository;
    private ActivityServiceImpl activityService;

    @BeforeEach
    private void init(){

        activityRepository = mock(ActivityRepository.class);
        activityService = new ActivityServiceImpl(activityRepository);

    }

    @Test
    void getActivitiesTest(){
        activityService.getActivities();
        verify(activityRepository, times(1)).findAll();
    }

    @Test
    void getActivityTest(){
        final UUID uuid = UUID.randomUUID();
        activityService.getActivity(uuid);
        verify(activityRepository, times(1)).findById(uuid);
    }

}
