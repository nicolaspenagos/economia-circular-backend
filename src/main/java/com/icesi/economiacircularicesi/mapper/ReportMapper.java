package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.ReportDTO.ReportDTO;
import com.icesi.economiacircularicesi.model.Report.Report;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    Report fromDTO(ReportDTO reportDTO);

    ReportDTO fromResponse(Report report);

}
