package com.icesi.economiacircularicesi.api;

import com.icesi.economiacircularicesi.dto.ReportDTO.ReportDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("report")
public interface ReportAPI {

    @GetMapping("/{userId}")
    ReportDTO getUserReport(@PathVariable UUID userId);

}
