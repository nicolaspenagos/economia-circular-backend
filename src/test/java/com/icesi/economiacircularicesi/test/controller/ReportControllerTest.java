package com.icesi.economiacircularicesi.test.controller;

import com.icesi.economiacircularicesi.controller.ReportController;
import com.icesi.economiacircularicesi.mapper.ReportMapper;
import com.icesi.economiacircularicesi.mapper.ReportMapperImpl;
import com.icesi.economiacircularicesi.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class ReportControllerTest {

    private ReportService reportService;
    private ReportMapper reportMapper;
    private ReportController reportController;


    @BeforeEach
    void init() {
        reportMapper = new ReportMapperImpl();
        reportService = mock(ReportService.class);
        reportController = new ReportController(reportService, reportMapper);
    }

    @Test
    void getReportTest() {
        UUID userId = UUID.randomUUID();
        UUID responseId = UUID.randomUUID();

        reportController.getUserReport(userId, responseId);
        verify(reportService, times(1)).getUserReport(userId, responseId);

    }
}
