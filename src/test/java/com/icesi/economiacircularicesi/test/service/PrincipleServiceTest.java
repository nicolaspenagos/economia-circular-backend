package com.icesi.economiacircularicesi.test.service;

import com.icesi.economiacircularicesi.repository.principle_repository.PrincipleRepository;
import com.icesi.economiacircularicesi.service.impl.PrincipleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static org.mockito.Mockito.*;

class PrincipleServiceTest {


    private PrincipleRepository principleRepository;
    private PrincipleServiceImpl principleService;

    @BeforeEach
    private void init(){

        principleRepository = mock(PrincipleRepository.class);
        principleService = new PrincipleServiceImpl(principleRepository);

    }

    @Test
    void getPrinciplesTest(){
        principleService.getPrinciples();
        verify(principleRepository, times(1)).findAll();
    }

    @Test
    void getPrincipleTest(){
        final UUID uuid = UUID.randomUUID();
        principleService.getPrinciple(uuid);
        verify(principleRepository, times(1)).findById(uuid);
    }
}
