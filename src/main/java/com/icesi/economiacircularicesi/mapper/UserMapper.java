package com.icesi.economiacircularicesi.mapper;

import com.icesi.economiacircularicesi.dto.UserDTO;
import com.icesi.economiacircularicesi.dto.UserNoPassDTO;
import com.icesi.economiacircularicesi.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromDTO(UserDTO userDTO);
    UserDTO fromUser(User user);
    UserNoPassDTO fromUserToNoPass(User user);

}
