package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.ActivityDTO.ActivityDTO;
import com.icesi.economiacircularicesi.model.Activity.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity fromDTO(ActivityDTO activityDTO);

    ActivityDTO fromActivity(Activity activity);

}