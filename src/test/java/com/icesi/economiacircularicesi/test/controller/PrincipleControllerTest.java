package com.icesi.economiacircularicesi.test.controller;

import com.icesi.economiacircularicesi.controller.PrincipleController;
import com.icesi.economiacircularicesi.mapper.ActivityMapperImpl;
import com.icesi.economiacircularicesi.mapper.PrincipleMapper;
import com.icesi.economiacircularicesi.mapper.PrincipleMapperImpl;
import com.icesi.economiacircularicesi.service.ActivityService;
import com.icesi.economiacircularicesi.service.PrincipleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class PrincipleControllerTest {

    private PrincipleController principleController;
    private PrincipleMapper principleMapper;

    private PrincipleService principleService;

    @BeforeEach
    public void init(){
        principleMapper = new PrincipleMapperImpl();
        principleService = mock(PrincipleService.class);
        principleController = new PrincipleController(principleMapper, principleService);
    }

    @Test
    public void getPrincipleTest(){
        principleController.getPrinciples();
        verify(principleService, times(1)).getPrinciples();
    };

    @Test
    public void getActivityTest(){
        final UUID uuid = UUID.randomUUID();
        principleController.getPrinciple(uuid);
        verify(principleService, times(1)).getPrinciple(uuid);
    }
}
