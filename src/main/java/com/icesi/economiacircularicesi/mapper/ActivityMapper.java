package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.activity.ActivityDTO;
import com.icesi.economiacircularicesi.model.activity.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity fromDTO(ActivityDTO activityDTO);

    ActivityDTO fromActivity(Activity activity);

}
